package com.demo.DSA.concept;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Shared, domain-agnostic scaffolding for a package's QXXX driver class
 * (see {@code P002_Graph.GraphMain} and {@code P001_Tree.TreeMain}). Both
 * drivers follow the same shape: one {@code @ParameterizedTest} (or plain
 * {@code @Test}) per question, asserting real output against the
 * documented expected value, runnable individually from the IDE or all at
 * once from the command line. This class holds the bits that don't know
 * anything about graphs or trees, so neither driver has to redefine them.
 */
public final class QuestionDriverSupport {

    private QuestionDriverSupport() {
    }

    /** One question's entry point for the CLI/main() dispatch table. */
    @FunctionalInterface
    public interface QuestionRunner {
        void run() throws Exception;
    }

    /** A per-example check method, e.g. {@code this::q003_NumberOfProvinces}. */
    @FunctionalInterface
    public interface CaseCheck<T> {
        void check(T example) throws Throwable;
    }

    /** Turns a stream of example rows + the method that checks one row into assertAll(...)-ready Executables. */
    public static <T> Executable[] toExecutables(Stream<T> cases, CaseCheck<T> checker) {
        return cases.map(c -> (Executable) () -> checker.check(c)).toArray(Executable[]::new);
    }

    public static void header(Class<?> qClass) {
        System.out.println("\n=== " + qClass.getSimpleName() + " ===");
    }

    /**
     * The whole CLI dispatch policy, shared by every driver's {@code main}:
     * no args runs every question; question numbers (digits extracted from
     * each arg, so "Q017" and "17" both work) run only those. A failing
     * question is caught and logged instead of killing the run, so every
     * requested question gets a verdict; a summary line reports the count
     * when more than one question ran.
     */
    public static void runFromArgs(String[] args, Map<Integer, QuestionRunner> questions) {
        Map<Integer, QuestionRunner> toRun = new LinkedHashMap<>();
        if (args.length == 0) {
            toRun.putAll(questions);
        } else {
            for (String arg : args) {
                String digits = arg.replaceAll("[^0-9]", "");
                Integer qNum = digits.isEmpty() ? null : Integer.parseInt(digits);
                QuestionRunner runner = qNum == null ? null : questions.get(qNum);
                if (runner == null) {
                    System.out.println("Skipping unknown question arg \"" + arg + "\" (expected 1-" + questions.size() + ")");
                    continue;
                }
                toRun.put(qNum, runner);
            }
        }

        List<Integer> failed = new ArrayList<>();
        for (Map.Entry<Integer, QuestionRunner> entry : toRun.entrySet()) {
            try {
                entry.getValue().run();
            } catch (Throwable t) {
                failed.add(entry.getKey());
                System.out.println("*** Q" + String.format("%03d", entry.getKey()) + " FAILED: "
                        + t.getClass().getSimpleName() + (t.getMessage() != null ? " - " + t.getMessage() : ""));
            }
        }

        if (toRun.size() > 1) {
            System.out.println("\n=== " + (toRun.size() - failed.size()) + "/" + toRun.size() + " questions passed"
                    + (failed.isEmpty() ? "" : " (failed: " + failed + ")") + " ===");
        }
    }

    /** Order-independent equality for a list of ints - some algorithms have no canonical output order. */
    public static void assertUnorderedInts(List<Integer> expected, List<Integer> actual, String what) {
        assertNotNull(actual, what + " was null");
        List<Integer> e = new ArrayList<>(expected);
        List<Integer> a = new ArrayList<>(actual);
        e.sort(null);
        a.sort(null);
        assertEquals(e, a, what);
    }

    /**
     * Writes DOT source to {@code <outDir>/<name>.dot}, then shells out to
     * Graphviz ({@code engine -Tpng ...}) to render {@code <outDir>/<name>.png}
     * alongside it. Shared by every package driver that visualizes its input
     * (see {@code P002_Graph.GraphMain}, {@code P003_LinkedList.LinkedListMain}) -
     * this is the standard way to call an external tool like Graphviz from
     * Java (ProcessBuilder, not a library dependency), so each driver only
     * needs to supply its own DOT-source generator.
     */
    public static void renderToPng(File outDir, String engine, String dotSource, String name) throws IOException {
        File dotFile = new File(outDir, name + ".dot");
        try (FileWriter fw = new FileWriter(dotFile)) {
            fw.write(dotSource);
        }

        File pngFile = new File(outDir, name + ".png");
        ProcessBuilder pb = new ProcessBuilder(engine, "-Tpng", dotFile.getPath(), "-o", pngFile.getPath());
        pb.redirectErrorStream(true);
        try {
            Process process = pb.start();
            process.getInputStream().transferTo(System.out);
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.out.println("`" + engine + "` exited with code " + exitCode
                        + " - is Graphviz installed and on PATH? (brew install graphviz)");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Could not run `" + engine + "` (" + e.getMessage()
                    + "). Render it yourself: " + engine + " -Tpng " + dotFile.getPath() + " -o " + pngFile.getPath());
        }
    }
}
