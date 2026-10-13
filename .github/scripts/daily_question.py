#!/usr/bin/env python3
"""
Daily DSA question ticket.

Runs once a day from .github/workflows/daily-question.yml and:
  1. Closes any open "daily-question" issue whose question has since been
     solved (its "// TODO: implement" stub is gone from the file).
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
# Main
# --------------------------------------------------------------------------

def main():
    ensure_labels()
    issues = all_daily_issues()
    unsolved = unsolved_by_topic()
    unsolved_paths = {str(p) for files in unsolved.values() for p in files}

    # 1) auto-close tickets whose question is now solved
    for issue in issues:
        if issue["state"] != "open":
            continue
        m = MARKER_RE.search(issue.get("body") or "")
        if m and m.group(1) not in unsolved_paths and Path(m.group(1)).exists():
            print(f"Closing #{issue['number']} - {m.group(1)} is solved")
            if not DRY_RUN:
                api("POST", f"/repos/{REPO}/issues/{issue['number']}/comments",
                    {"body": "Solution pushed - the `TODO` stub is gone. Closing automatically. 🎉"})
                api("PATCH", f"/repos/{REPO}/issues/{issue['number']}",
                    {"state": "closed", "state_reason": "completed"})

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
        "- [ ] Push the solution (this issue closes itself once the stub is gone)",
        "",
        f"_{remaining} other unsolved question(s) left in the repo._",
        "",
        MARKER.format(chosen),
    ])
    ist_today = datetime.now(timezone(timedelta(hours=5, minutes=30)))   # date in IST
    title = f"[Daily · {ist_today:%d %b %Y}] {pretty_name(chosen)} ({topic_name})"
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
