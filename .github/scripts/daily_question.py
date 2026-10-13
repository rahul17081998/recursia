#!/usr/bin/env python3
"""
Daily DSA question ticket.

Runs once a day from .github/workflows/daily-question.yml and:
  1. For each open "daily-question" issue whose stub has been replaced,
     runs that question's test cases (`<Topic>Main <qNum>`). All pass ->
     comment + close. Any fail -> comment the failing output (once per
     commit) and keep the ticket open.
  2. Opens ONE new issue for an unsolved question, rotating topics each day
     (Tree -> Graph -> LinkedList -> Sorting -> Tree ...), lowest Q number
     first within a topic.

A question counts as UNSOLVED when some "// TODO: implement" line in it is
followed only by an optional "return ...;" and the closing "}" - i.e. the
method body is still the original stub. Files where real code follows the
TODO comment are treated as solved.

Only the Python standard library is used, so the workflow needs no installs.
"""

import json
import os
import re
import subprocess
import sys
import urllib.error
import urllib.request
from datetime import datetime, timedelta, timezone
from pathlib import Path

REPO = os.environ["GITHUB_REPOSITORY"]          # e.g. rahul17081998/recursia
TOKEN = os.environ["GITHUB_TOKEN"]
BRANCH = os.environ.get("DEFAULT_BRANCH", "main")
DRY_RUN = os.environ.get("DRY_RUN") == "1"

CONCEPT_DIR = Path("src/main/java/com/demo/DSA/concept")
TOPICS = ["P001_Tree", "P002_Graph", "P003_LinkedList", "P004_Sorting"]
LABEL = "daily-question"
MARKER = "<!-- daily-question-file: {} -->"
MARKER_RE = re.compile(r"<!-- daily-question-file: (.+?) -->")


# --------------------------------------------------------------------------
# GitHub REST helpers
# --------------------------------------------------------------------------

def api(method, path, body=None):
    url = f"https://api.github.com{path}"
    data = json.dumps(body).encode() if body is not None else None
    req = urllib.request.Request(url, data=data, method=method)
    req.add_header("Authorization", f"Bearer {TOKEN}")
    req.add_header("Accept", "application/vnd.github+json")
    req.add_header("X-GitHub-Api-Version", "2022-11-28")
    if data is not None:
        req.add_header("Content-Type", "application/json")
    try:
        with urllib.request.urlopen(req) as resp:
            raw = resp.read()
            return json.loads(raw) if raw else None
    except urllib.error.HTTPError as e:
        print(f"GitHub API {method} {path} failed: {e.code} {e.read().decode()}", file=sys.stderr)
        raise


def all_daily_issues():
    """Every issue (open + closed) ever created by this job, newest first."""
    issues, page = [], 1
    while True:
        batch = api("GET", f"/repos/{REPO}/issues?labels={LABEL}&state=all"
                           f"&sort=created&direction=desc&per_page=100&page={page}")
        issues += [i for i in batch if "pull_request" not in i]
        if len(batch) < 100:
            return issues
        page += 1


def ensure_labels():
    wanted = {LABEL: "0e8a16"} | {f"topic:{t}": "1d76db" for t in TOPICS}
    existing = {l["name"] for l in api("GET", f"/repos/{REPO}/labels?per_page=100")}
    for name, color in wanted.items():
        if name not in existing and not DRY_RUN:
            api("POST", f"/repos/{REPO}/labels", {"name": name, "color": color})


# --------------------------------------------------------------------------
# Repo scanning
# --------------------------------------------------------------------------

STUB_TAIL = re.compile(r"^\s*(return\b[^;]*;)?\s*\}", re.S)


def is_unsolved(text):
    for m in re.finditer(r"//\s*TODO: implement[^\n]*\n", text):
        if STUB_TAIL.match(text[m.end():]):
            return True
    return False


def q_number(path):
    m = re.match(r"Q(\d+)", path.name)
    return int(m.group(1)) if m else 10**9


def unsolved_by_topic():
    result = {}
    for topic in TOPICS:
        files = sorted((CONCEPT_DIR / topic).glob("Q*.java"), key=q_number)
        result[topic] = [f for f in files if is_unsolved(f.read_text(encoding="utf-8"))]
    return result


def pretty_name(path):
    # Q031_SwimInRisingWater -> "Q031 Swim In Rising Water"; drops the "SA_" tag
    stem = path.stem
    num, _, rest = stem.partition("_")
    rest = re.sub(r"^SA_", "", rest)
    words = re.sub(r"(?<=[a-z0-9])(?=[A-Z])|_", " ", rest).split()
    return f"{num} {' '.join(words)}"


def problem_text(text):
    """First javadoc block, cleaned into Markdown-ish plain text."""
    m = re.search(r"/\*\*(.*?)\*/", text, re.S)
    if not m:
        return ""
    lines = [re.sub(r"^\s*\* ?", "", l) for l in m.group(1).splitlines()]
    body = "\n".join(lines).strip()
    body = re.split(r"<h3>", body)[0]                     # stop before "How it works"
    body = body.replace("<pre>", "```text").replace("</pre>", "```")
    body = re.sub(r"\{@code ([^}]*)\}", r"`\1`", body)
    body = re.sub(r"\{@link ([^}]*)\}", r"`\1`", body)
    body = re.sub(r"</?(p|br)\s*/?>", "\n", body)
    body = (body.replace("&lt;", "<").replace("&gt;", ">")
                .replace("&amp;", "&").replace("&mdash;", "-"))
    return re.sub(r"\n{3,}", "\n\n", body).strip()


def method_signatures(text):
    sigs = []
    for m in re.finditer(r"//\s*TODO: implement[^\n]*\n", text):
        if STUB_TAIL.match(text[m.end():]):
            before = text[:m.start()]
            sig = re.findall(r"\n\s*((?:public|private|protected)[^\n{;=]*\([^)]*\))\s*\{", before)
            if sig:
                sigs.append(sig[-1].strip())
    return sigs


# --------------------------------------------------------------------------
# Running a question's test cases
# --------------------------------------------------------------------------

SHA = os.environ.get("GITHUB_SHA", "local")
CLASSPATH = os.environ.get("TEST_CLASSPATH", "")
RESULT_MARKER = "<!-- daily-question-test-result: {} -->"


def main_class_for(qfile):
    """P002_Graph/Q031_X.java -> com.demo.DSA.concept.P002_Graph.GraphMain"""
    mains = sorted(qfile.parent.glob("*Main.java"))
    if not mains:
        return None
    pkg = ".".join(qfile.parent.relative_to("src/main/java").parts)
    return f"{pkg}.{mains[0].stem}"


def run_question_tests(qfile):
    """Runs `<TopicMain> <qNum>` - the CLI the *Main classes already support -
    and returns ("passed" | "failed" | "no-tests", output)."""
    main_cls = main_class_for(qfile)
    if not main_cls or not CLASSPATH:
        return "no-tests", "No *Main class or classpath available."
    qnum = q_number(qfile)
    try:
        proc = subprocess.run(["java", "-cp", CLASSPATH, main_cls, str(qnum)],
                              capture_output=True, text=True, timeout=300)
    except subprocess.TimeoutExpired as e:
        partial = e.stdout.decode() if isinstance(e.stdout, bytes) else (e.stdout or "")
        return "failed", "Timed out after 300 seconds (infinite loop?)\n" + partial
    out = proc.stdout + proc.stderr
    if "Skipping unknown question" in out:
        return "no-tests", out
    if f"Q{qnum:03d} FAILED" in out or proc.returncode != 0:
        return "failed", out
    return "passed", out


def tail(text, n=40):
    lines = [l for l in text.splitlines() if "JAVA_TOOL_OPTIONS" not in l]
    return "\n".join(lines[-n:]).replace("```", "` ` `")


def already_commented(num, key):
    comments = api("GET", f"/repos/{REPO}/issues/{num}/comments?per_page=100")
    return any(RESULT_MARKER.format(key) in (c.get("body") or "") for c in comments)


# --------------------------------------------------------------------------
# Main
# --------------------------------------------------------------------------

def main():
    check = os.environ.get("CHECK_QUESTION", "").strip()
    if check:                                    # manual "just run the tests for QXXX" mode
        matches = [f for t in TOPICS for f in (CONCEPT_DIR / t).glob("Q*.java")
                   if check.lower() in f.stem.lower() or check.lower() in str(f).lower()]
        if not matches:
            sys.exit(f"No question file matches '{check}'")
        for f in matches:
            verdict, output = run_question_tests(f)
            print(f"\n##### {f}: {verdict.upper()} #####\n{tail(output, 80)}")
            last = " | ".join(l for l in tail(output, 6).splitlines() if l.strip())
            print(f"::notice title={f.stem}: {verdict.upper()}::{last[:900]}")
        return

    ensure_labels()
    issues = all_daily_issues()
    unsolved = unsolved_by_topic()
    unsolved_paths = {str(p) for files in unsolved.values() for p in files}

    # 1) close tickets whose question now PASSES its test cases
    for issue in issues:
        if issue["state"] != "open":
            continue
        m = MARKER_RE.search(issue.get("body") or "")
        if not m or m.group(1) in unsolved_paths or not Path(m.group(1)).exists():
            continue                                   # still a stub - nothing to test yet
        qfile = Path(m.group(1))
        verdict, output = run_question_tests(qfile)
        num = issue["number"]
        print(f"#{num} {qfile.name}: {verdict}")
        print(f"::notice title=#{num} {qfile.stem}: {verdict.upper()}::see issue comments for details")
        if DRY_RUN:
            continue
        if verdict == "passed":
            api("POST", f"/repos/{REPO}/issues/{num}/comments",
                {"body": f"✅ All test cases for `{qfile.stem}` passed on commit {SHA[:7]}. "
                         f"Closing as resolved. 🎉\n\n<details><summary>Test output</summary>\n\n"
                         f"```text\n{tail(output)}\n```\n</details>"})
            api("PATCH", f"/repos/{REPO}/issues/{num}",
                {"state": "closed", "state_reason": "completed"})
        elif verdict == "failed" and not already_commented(num, SHA):
            api("POST", f"/repos/{REPO}/issues/{num}/comments",
                {"body": f"❌ Test cases for `{qfile.stem}` failed on commit {SHA[:7]}. "
                         f"Keeping this ticket open.\n\n```text\n{tail(output)}\n```\n"
                         + RESULT_MARKER.format(SHA)})
        elif verdict == "no-tests" and not already_commented(num, "no-tests"):
            api("POST", f"/repos/{REPO}/issues/{num}/comments",
                {"body": f"⚠️ The `TODO` stub is gone, but no test cases are registered for "
                         f"`{qfile.stem}` in its `*Main` class, so it can't be verified "
                         f"automatically. Close this ticket yourself when you're happy with it.\n"
                         + RESULT_MARKER.format("no-tests")})

    if os.environ.get("MODE") == "close-only":   # push to main: only close solved tickets
        return

    # 2) pick today's question
    ever_issued = {MARKER_RE.search(i.get("body") or "").group(1)
                   for i in issues if MARKER_RE.search(i.get("body") or "")}
    open_issued = {MARKER_RE.search(i.get("body") or "").group(1)
                   for i in issues if i["state"] == "open" and MARKER_RE.search(i.get("body") or "")}

    last_topic = None
    for issue in issues:                                   # newest first
        for l in issue["labels"]:
            if l["name"].startswith("topic:"):
                last_topic = l["name"][len("topic:"):]
                break
        if last_topic:
            break
    start = (TOPICS.index(last_topic) + 1) % len(TOPICS) if last_topic in TOPICS else 0
    rotation = TOPICS[start:] + TOPICS[:start]

    def pick(skip):
        for topic in rotation:
            for f in unsolved[topic]:
                if str(f) not in skip:
                    return topic, f
        return None, None

    topic, chosen = pick(ever_issued)            # prefer questions never ticketed
    if chosen is None:
        topic, chosen = pick(open_issued)        # then re-ticket closed-but-unsolved ones
    if chosen is None:
        print("Nothing left to ticket - every question is solved or already has an open issue. 🏁")
        return

    text = chosen.read_text(encoding="utf-8")
    remaining = len(unsolved_paths) - 1
    sigs = method_signatures(text)
    file_url = f"https://github.com/{REPO}/blob/{BRANCH}/{chosen}"
    topic_name = topic.split("_", 1)[1]

    body = "\n".join([
        f"## {pretty_name(chosen)}",
        f"**Topic:** {topic_name}  ",
        f"**File:** [`{chosen.name}`]({file_url})",
        "",
        "### Problem",
        problem_text(text),
        "",
        "### Implement",
        "```java",
        *(sigs or ["// see the TODO in the file"]),
        "```",
        "",
        "### Checklist",
        "- [ ] Understand the problem and write the approach",
        "- [ ] Implement the method(s) marked `// TODO: implement`",
        "- [ ] Run the matching test in the topic's `*Main` class",
        "- [ ] Push the solution (this issue closes itself once all its test cases pass)",
        "",
        f"_{remaining} other unsolved question(s) left in the repo._",
        "",
        MARKER.format(chosen),
    ])
    ist_today = datetime.now(timezone(timedelta(hours=5, minutes=30)))   # date in IST
    title = f"[{ist_today:%d %b %Y}] {pretty_name(chosen)} ({topic_name})"
    print(f"Opening: {title}")
    if DRY_RUN:
        print(body)
        return
    issue = api("POST", f"/repos/{REPO}/issues",
                {"title": title, "body": body, "labels": [LABEL, f"topic:{topic}"],
                 "assignees": [REPO.split("/")[0]]})
    print(f"Created {issue['html_url']}")


if __name__ == "__main__":
    main()
