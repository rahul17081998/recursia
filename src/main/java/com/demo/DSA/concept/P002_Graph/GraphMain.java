package com.demo.DSA.concept.P002_Graph;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Driver for the entire graph question package, parallel to TreeNode.main
 * in P001_Tree - calls every QXXX question with real example input (pulled
 * straight from that file's own javadoc), prints the input/output, and for
 * questions whose input is naturally a node-and-edge graph, renders it to
 * a PNG via Graphviz. Each PNG is named after its question class
 * ({@code Q0XX_ClassName.png}); when a question has more than one worked
 * example, they're suffixed _A, _B, _C in javadoc order.
 * <p>
 * Grid/board/state-space questions (Number of Islands, Flood Fill, Rotting
 * Oranges, Open the Lock, Snake and Ladder, Path With Minimum Effort, Swim
 * in Rising Water, ...) aren't naturally a small node-edge diagram, so
 * those are called and printed but not rendered to Graphviz.
 * <p>
 * Rendered files land in ./graph-output/ under the working directory this
 * is run from. Requires Graphviz on PATH (`brew install graphviz`).
 * <p>
 * <strong>Structure.</strong> Each question's example inputs live as DATA
 * (a small record, one instance per example) rather than as duplicated
 * code. For question QXXX: a record {@code CaseXXX} holds one example's
 * fields; a static {@code qXXXCases()} supplies the example rows; a single
 * {@code @ParameterizedTest} method {@code qXXX_Name(CaseXXX c)} contains
 * the ONE copy of the actual check logic (print input, compute, print
 * output, render if applicable, assert against the documented expected
 * value); and a no-arg {@code qXXX_Name_all()} feeds every row through
 * {@code assertAll(...)} so the CLI/main path gets the same "run every
 * example, fail as a whole if any is wrong" behavior. Where a problem has
 * more than one valid correct answer (topological sort, MST total weight
 * is unique but the edge set isn't, SCC/articulation-point groupings), the
 * assertion checks the underlying property instead of pinning to one
 * value - see the {@code assert*} helpers near the bottom of this file.
 * <p>
 * Two ways to run a subset instead of everything, both in this one class:
 * <ul>
 *   <li><strong>From the command line / {@code main}</strong>: no program
 *   arguments runs every question (original behavior); one or more question
 *   numbers runs only those, e.g. {@code java GraphMain 17} or
 *   {@code java GraphMain 17 18} - digits are extracted from each argument,
 *   so {@code Q017} and {@code 17} both work. A failing question no longer
 *   halts the run - it's caught, logged, and the rest still execute; a
 *   summary line at the end reports how many of the requested questions
 *   passed.</li>
 *   <li><strong>From the IDE</strong>: every {@code qXXX_Name(CaseXXX)}
 *   method is a {@code @ParameterizedTest}, so IntelliJ shows a gutter run
 *   icon on the method (runs every example as child nodes, parent goes red
 *   if any example fails) and on each individual example once expanded -
 *   click either to run/debug just that much, no run-configuration args
 *   needed.</li>
 * </ul>
 * <p>
 * Note {@code junit-jupiter} is an ordinary (compile-scope) dependency here,
 * not test-only - the assertions below are real method calls the {@code
 * main}/CLI path executes too (not just annotations), so the JUnit jars
 * need to be on the runtime classpath for {@code java GraphMain ...} to
 * work at all, not just for the IDE.
 */
public class GraphMain {

    private static final File OUT_DIR = new File("graph-output");
    private static final String ENGINE = "neato";

    @FunctionalInterface
    private interface QuestionRunner {
        void run() throws IOException;
    }

    /** A per-example check method, e.g. {@code this::q003_NumberOfProvinces}. */
    @FunctionalInterface
    private interface CaseCheck<T> {
        void check(T example) throws Throwable;
    }

    /** Turns a stream of example rows + the method that checks one row into assertAll(...)-ready Executables. */
    private static <T> Executable[] toExecutables(Stream<T> cases, CaseCheck<T> checker) {
        return cases.map(c -> (Executable) () -> checker.check(c)).toArray(Executable[]::new);
    }

    // JUnit @Test/@ParameterizedTest methods must be instance (non-static)
    // methods, so the qXXX_..._all() methods below are instance methods and
    // this map is built from bound `this::` references once main() creates a
    // GraphMain instance.
    private final Map<Integer, QuestionRunner> questions = new LinkedHashMap<>();

    public GraphMain() {
        questions.put(1, this::q001_BFSTraversalOfGraph_all);
        questions.put(2, this::q002_DFSTraversalOfGraph_all);
        questions.put(3, this::q003_NumberOfProvinces_all);
        questions.put(4, this::q004_NumberOfIslands_all);
        questions.put(5, this::q005_FloodFill_all);
        questions.put(6, this::q006_RottingOranges_all);
        questions.put(7, this::q007_MaxAreaOfIsland_all);
        questions.put(8, this::q008_SurroundedRegions_all);
        questions.put(9, this::q009_PacificAtlanticWaterFlow_all);
        questions.put(10, this::q010_ZeroOneMatrix_all);
        questions.put(11, this::q011_WallsAndGates_all);
        questions.put(12, this::q012_CloneGraph_all);
        questions.put(13, this::q013_DetectCycleInUndirectedGraph_all);
        questions.put(14, this::q014_DetectCycleInDirectedGraph_all);
        questions.put(15, this::q015_CourseSchedule_all);
        questions.put(16, this::q016_CourseScheduleII_all);
        questions.put(17, this::q017_TopologicalSortKahnsBFS_all);
        questions.put(18, this::q018_TopologicalSortDFS_all);
        questions.put(19, this::q019_IsGraphBipartite_all);
        questions.put(20, this::q020_NumberOfConnectedComponents_all);
        questions.put(21, this::q021_WordLadder_all);
        questions.put(22, this::q022_ShortestPathInBinaryMatrix_all);
        questions.put(23, this::q023_SnakeAndLadder_all);
        questions.put(24, this::q024_OpenTheLock_all);
        questions.put(25, this::q025_DijkstraShortestPath_all);
        questions.put(26, this::q026_NetworkDelayTime_all);
        questions.put(27, this::q027_BellmanFordShortestPath_all);
        questions.put(28, this::q028_CheapestFlightsWithinKStops_all);
        questions.put(29, this::q029_FloydWarshallAllPairsShortestPath_all);
        questions.put(30, this::q030_PathWithMinimumEffort_all);
        questions.put(31, this::q031_SwimInRisingWater_all);
        questions.put(32, this::q032_DisjointSetUnion);
        questions.put(33, this::q033_RedundantConnection_all);
        questions.put(34, this::q034_AccountsMerge_all);
        questions.put(35, this::q035_GraphValidTree_all);
        questions.put(36, this::q036_PrimsMST_all);
        questions.put(37, this::q037_KruskalsMST_all);
        questions.put(38, this::q038_CriticalConnectionsBridges_all);
        questions.put(39, this::q039_ArticulationPoints_all);
        questions.put(40, this::q040_KosarajusSCC_all);
        questions.put(41, this::q041_EvaluateDivision_all);
        questions.put(42, this::q042_ReconstructItinerary_all);
        questions.put(43, this::q043_AlienDictionary_all);
        questions.put(44, this::q044_MinimumHeightTrees_all);
        questions.put(45, this::q045_AllPathsFromSourceToTarget_all);
        questions.put(46, this::q046_MaxFlowFordFulkerson_all);
        questions.put(47, this::q047_TarjansSCC_all);
    }

    public static void main(String[] args) {
        OUT_DIR.mkdirs();
        GraphMain app = new GraphMain();

        Map<Integer, QuestionRunner> toRun = new LinkedHashMap<>();
        if (args.length == 0) {
            toRun.putAll(app.questions);
        } else {
            for (String arg : args) {
                String digits = arg.replaceAll("[^0-9]", "");
                Integer qNum = digits.isEmpty() ? null : Integer.parseInt(digits);
                QuestionRunner runner = qNum == null ? null : app.questions.get(qNum);
                if (runner == null) {
                    System.out.println("Skipping unknown question arg \"" + arg + "\" (expected 1-" + app.questions.size() + ")");
                    continue;
                }
                toRun.put(qNum, runner);
            }
        }

        // A failing assertion (or any other error) in one question must not stop
        // the rest from running - catch per-question so every requested question
        // gets a verdict, then report which ones failed at the end.
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

    // =========================================================================
    // Q001 / Q002 - BFS / DFS traversal
    // =========================================================================

    private record CaseTraversal(String variant, int v, int[][] edges, List<Integer> expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<CaseTraversal> q001Cases() {
        return Stream.of(
                new CaseTraversal("A", 5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {2, 4}}, List.of(0, 1, 2, 3, 4)),
                new CaseTraversal("B", 3, new int[][]{{0, 1}, {0, 2}}, List.of(0, 1, 2))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q001Cases")
    void q001_BFSTraversalOfGraph(CaseTraversal c) throws IOException {
        Q001_SA_BFSTraversalOfGraph q = new Q001_SA_BFSTraversalOfGraph();
        System.out.println("Example " + c.variant() + ": V=" + c.v() + ", adj built from edges " + Arrays.deepToString(c.edges()));
        List<Integer> actual = q.bfsOfGraph(c.v(), adj(c.v(), c.edges(), false));
        System.out.println("bfsOfGraph -> " + actual);
        render(Q001_SA_BFSTraversalOfGraph.class, c.variant(), c.v(), c.edges(), false, false);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q001_BFSTraversalOfGraph_all() {
        header(Q001_SA_BFSTraversalOfGraph.class);
        assertAll("Q001_BFSTraversalOfGraph", toExecutables(q001Cases(), this::q001_BFSTraversalOfGraph));
    }

    private static Stream<CaseTraversal> q002Cases() {
        return Stream.of(
                new CaseTraversal("A", 5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {2, 4}}, List.of(0, 1, 2, 4, 3)),
                new CaseTraversal("B", 3, new int[][]{{0, 1}, {0, 2}}, List.of(0, 1, 2))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q002Cases")
    void q002_DFSTraversalOfGraph(CaseTraversal c) throws IOException {
        Q002_SA_DFSTraversalOfGraph q = new Q002_SA_DFSTraversalOfGraph();
        System.out.println("Example " + c.variant() + ": V=" + c.v() + ", adj built from edges " + Arrays.deepToString(c.edges()));
        List<Integer> actual = q.dfsOfGraph(c.v(), adj(c.v(), c.edges(), false));
        System.out.println("dfsOfGraph -> " + actual);
        render(Q002_SA_DFSTraversalOfGraph.class, c.variant(), c.v(), c.edges(), false, false);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q002_DFSTraversalOfGraph_all() {
        header(Q002_SA_DFSTraversalOfGraph.class);
        assertAll("Q002_DFSTraversalOfGraph", toExecutables(q002Cases(), this::q002_DFSTraversalOfGraph));
    }

    // =========================================================================
    // Q003 - Number of Provinces
    // =========================================================================

    private record Case003(String variant, int[][] isConnected, int[][] renderEdges, int expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case003> q003Cases() {
        return Stream.of(
                new Case003("A", new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}, new int[][]{{0, 1}}, 2),
                new Case003("B", new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}, new int[0][], 3)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q003Cases")
    void q003_NumberOfProvinces(Case003 c) throws IOException {
        Q003_NumberOfProvinces q = new Q003_NumberOfProvinces();
        System.out.println(c + ": isConnected=" + Arrays.deepToString(c.isConnected()));
        int actual = q.findCircleNum(c.isConnected());
        System.out.println("findCircleNum -> " + actual);
        // Rendered as plain edges for the diagram: node i-j connected iff isConnected[i][j]==1 (i!=j).
        render(Q003_NumberOfProvinces.class, c.variant(), 3, c.renderEdges(), false, false);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q003_NumberOfProvinces_all() {
        header(Q003_NumberOfProvinces.class);
        assertAll("Q003_NumberOfProvinces", toExecutables(q003Cases(), this::q003_NumberOfProvinces));
    }

    // =========================================================================
    // Q004-Q011 - grid/board questions (no Graphviz diagram; grid printed as text)
    // =========================================================================

    private record Case004(String variant, char[][] grid, int expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case004> q004Cases() {
        return Stream.of(
                new Case004("A", new char[][]{
                        {'1', '1', '1', '1', '0'},
                        {'1', '1', '0', '1', '0'},
                        {'1', '1', '0', '0', '0'},
                        {'0', '0', '0', '0', '0'}
                }, 1),
                new Case004("B", new char[][]{
                        {'1', '1', '0', '0', '0'},
                        {'1', '1', '0', '0', '0'},
                        {'0', '0', '1', '0', '0'},
                        {'0', '0', '0', '1', '1'}
                }, 3)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q004Cases")
    void q004_NumberOfIslands(Case004 c) {
        Q004_NumberOfIslands q = new Q004_NumberOfIslands();
        System.out.println(c + " grid:");
        printGrid(c.grid());
        int actual = q.numIslands(c.grid());
        System.out.println("numIslands -> " + actual);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q004_NumberOfIslands_all() {
        header(Q004_NumberOfIslands.class);
        assertAll("Q004_NumberOfIslands", toExecutables(q004Cases(), this::q004_NumberOfIslands));
    }

    private record Case005(String variant, int[][] image, int sr, int sc, int color, int[][] expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case005> q005Cases() {
        return Stream.of(
                new Case005("A", new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2,
                        new int[][]{{2, 2, 2}, {2, 2, 0}, {2, 0, 1}}),
                new Case005("B", new int[][]{{0, 0, 0}, {0, 0, 0}}, 0, 0, 0,
                        new int[][]{{0, 0, 0}, {0, 0, 0}})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q005Cases")
    void q005_FloodFill(Case005 c) {
        Q005_FloodFill q = new Q005_FloodFill();
        System.out.println(c + ": image=" + Arrays.deepToString(c.image()) + ", sr=" + c.sr() + ", sc=" + c.sc() + ", color=" + c.color());
        int[][] actual = q.floodFill(c.image(), c.sr(), c.sc(), c.color());
        System.out.println("floodFill -> " + Arrays.deepToString(actual));
        assert2D(c.expected(), actual, c.toString());
    }

    private void q005_FloodFill_all() {
        header(Q005_FloodFill.class);
        assertAll("Q005_FloodFill", toExecutables(q005Cases(), this::q005_FloodFill));
    }

    private record Case006(String variant, int[][] grid, int expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case006> q006Cases() {
        return Stream.of(
                new Case006("A", new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}, 4),
                new Case006("B", new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}, -1)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q006Cases")
    void q006_RottingOranges(Case006 c) {
        Q006_RottingOranges q = new Q006_RottingOranges();
        System.out.println(c + " grid: " + Arrays.deepToString(c.grid()));
        int actual = q.orangesRotting(c.grid());
        System.out.println("orangesRotting -> " + actual);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q006_RottingOranges_all() {
        header(Q006_RottingOranges.class);
        assertAll("Q006_RottingOranges", toExecutables(q006Cases(), this::q006_RottingOranges));
    }

    private record Case007(String variant, int[][] grid, int expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case007> q007Cases() {
        return Stream.of(
                new Case007("A", new int[][]{{1, 1, 0, 0}, {1, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}}, 4),
                new Case007("B", new int[][]{{0, 0, 0}, {0, 0, 0}}, 0)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q007Cases")
    void q007_MaxAreaOfIsland(Case007 c) {
        Q007_MaxAreaOfIsland q = new Q007_MaxAreaOfIsland();
        System.out.println(c + " grid: " + Arrays.deepToString(c.grid()));
        int actual = q.maxAreaOfIsland(c.grid());
        System.out.println("maxAreaOfIsland -> " + actual);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q007_MaxAreaOfIsland_all() {
        header(Q007_MaxAreaOfIsland.class);
        assertAll("Q007_MaxAreaOfIsland", toExecutables(q007Cases(), this::q007_MaxAreaOfIsland));
    }

    private record Case008(String variant, char[][] board, char[][] expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case008> q008Cases() {
        return Stream.of(
                new Case008("A", new char[][]{
                        {'X', 'X', 'X', 'X'},
                        {'X', 'O', 'O', 'X'},
                        {'X', 'X', 'O', 'X'},
                        {'X', 'O', 'X', 'X'}
                }, new char[][]{
                        {'X', 'X', 'X', 'X'},
                        {'X', 'X', 'X', 'X'},
                        {'X', 'X', 'X', 'X'},
                        {'X', 'O', 'X', 'X'}
                }),
                new Case008("B", new char[][]{{'X'}}, new char[][]{{'X'}})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q008Cases")
    void q008_SurroundedRegions(Case008 c) {
        Q008_SurroundedRegions q = new Q008_SurroundedRegions();
        System.out.println(c + " board:");
        printGrid(c.board());
        q.solve(c.board());
        System.out.println("solve -> board mutated in place:");
        printGrid(c.board());
        assert2D(c.expected(), c.board(), c.toString());
    }

    private void q008_SurroundedRegions_all() {
        header(Q008_SurroundedRegions.class);
        assertAll("Q008_SurroundedRegions", toExecutables(q008Cases(), this::q008_SurroundedRegions));
    }

    private record Case009(String variant, int[][] heights, List<List<Integer>> expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case009> q009Cases() {
        return Stream.of(
                new Case009("A", new int[][]{
                        {1, 2, 2, 3, 5},
                        {3, 2, 3, 4, 4},
                        {2, 4, 5, 3, 1},
                        {6, 7, 1, 4, 5},
                        {5, 1, 1, 2, 4}
                }, List.of(List.of(0, 4), List.of(1, 3), List.of(1, 4), List.of(2, 2), List.of(3, 0), List.of(3, 1), List.of(4, 0))),
                new Case009("B", new int[][]{{1}}, List.of(List.of(0, 0)))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q009Cases")
    void q009_PacificAtlanticWaterFlow(Case009 c) {
        Q009_PacificAtlanticWaterFlow q = new Q009_PacificAtlanticWaterFlow();
        System.out.println(c + " heights: " + Arrays.deepToString(c.heights()));
        List<List<Integer>> actual = q.pacificAtlantic(c.heights());
        System.out.println("pacificAtlantic -> " + actual);
        assertUnorderedListOfLists(c.expected(), actual, c.toString());
    }

    private void q009_PacificAtlanticWaterFlow_all() {
        header(Q009_PacificAtlanticWaterFlow.class);
        assertAll("Q009_PacificAtlanticWaterFlow", toExecutables(q009Cases(), this::q009_PacificAtlanticWaterFlow));
    }

    private record Case010(String variant, int[][] mat, int[][] expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case010> q010Cases() {
        return Stream.of(
                new Case010("A", new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}, new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}),
                new Case010("B", new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}}, new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 2, 1}})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q010Cases")
    void q010_ZeroOneMatrix(Case010 c) {
        Q010_ZeroOneMatrix q = new Q010_ZeroOneMatrix();
        System.out.println(c + " mat: " + Arrays.deepToString(c.mat()));
        int[][] actual = q.updateMatrix(c.mat());
        System.out.println("updateMatrix -> " + Arrays.deepToString(actual));
        assert2D(c.expected(), actual, c.toString());
    }

    private void q010_ZeroOneMatrix_all() {
        header(Q010_ZeroOneMatrix.class);
        assertAll("Q010_ZeroOneMatrix", toExecutables(q010Cases(), this::q010_ZeroOneMatrix));
    }

    private record Case011(String variant, int[][] rooms, int[][] expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case011> q011Cases() {
        final int INF = Integer.MAX_VALUE;
        return Stream.of(
                new Case011("A", new int[][]{
                        {INF, -1, 0, INF},
                        {INF, INF, INF, -1},
                        {INF, -1, INF, -1},
                        {0, -1, INF, INF}
                }, new int[][]{{3, -1, 0, 1}, {2, 2, 1, -1}, {1, -1, 2, -1}, {0, -1, 3, 4}}),
                new Case011("B", new int[][]{{-1}}, new int[][]{{-1}})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q011Cases")
    void q011_WallsAndGates(Case011 c) {
        Q011_WallsAndGates q = new Q011_WallsAndGates();
        System.out.println(c + " rooms (INF=" + Integer.MAX_VALUE + "): " + Arrays.deepToString(c.rooms()));
        q.wallsAndGates(c.rooms());
        System.out.println("wallsAndGates -> rooms mutated in place: " + Arrays.deepToString(c.rooms()));
        assert2D(c.expected(), c.rooms(), c.toString());
    }

    private void q011_WallsAndGates_all() {
        header(Q011_WallsAndGates.class);
        assertAll("Q011_WallsAndGates", toExecutables(q011Cases(), this::q011_WallsAndGates));
    }

    // =========================================================================
    // Q012 - Clone Graph
    // =========================================================================

    private record Case012(String variant, GraphNode original, int renderN, int[][] renderEdges) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case012> q012Cases() {
        // adjList = [[2,4],[1,3],[2,4],[1,3]] (1-indexed per the problem) -> a 4-cycle.
        GraphNode n1 = new GraphNode(1);
        GraphNode n2 = new GraphNode(2);
        GraphNode n3 = new GraphNode(3);
        GraphNode n4 = new GraphNode(4);
        n1.neighbors.add(n2);
        n1.neighbors.add(n4);
        n2.neighbors.add(n1);
        n2.neighbors.add(n3);
        n3.neighbors.add(n2);
        n3.neighbors.add(n4);
        n4.neighbors.add(n1);
        n4.neighbors.add(n3);

        return Stream.of(
                // 0-indexed relabeling of the same 4-cycle, for the diagram.
                new Case012("A", n1, 4, new int[][]{{0, 1}, {0, 3}, {1, 2}, {2, 3}}),
                new Case012("B", new GraphNode(1), 1, new int[0][])
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q012Cases")
    void q012_CloneGraph(Case012 c) throws IOException {
        Q012_CloneGraph q = new Q012_CloneGraph();
        System.out.println(c + ": starting at node val=" + c.original().val);
        GraphNode cloned = q.cloneGraph(c.original());
        System.out.println("cloneGraph -> " + (cloned == null ? "null" : "cloned node with val=" + cloned.val));
        render(Q012_CloneGraph.class, c.variant(), c.renderN(), c.renderEdges(), false, false);
        assertProperClone(c.original(), cloned, c.toString());
    }

    private void q012_CloneGraph_all() {
        header(Q012_CloneGraph.class);
        assertAll("Q012_CloneGraph", toExecutables(q012Cases(), this::q012_CloneGraph));
    }

    // =========================================================================
    // Q013 / Q014 - cycle detection
    // =========================================================================

    private record CaseCycle(String variant, int v, int[][] edges, boolean expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<CaseCycle> q013Cases() {
        return Stream.of(
                new CaseCycle("A", 4, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}}, true),
                new CaseCycle("B", 4, new int[][]{{0, 1}, {1, 2}, {2, 3}}, false)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q013Cases")
    void q013_DetectCycleInUndirectedGraph(CaseCycle c) throws IOException {
        Q013_SA_DetectCycleInUndirectedGraph q = new Q013_SA_DetectCycleInUndirectedGraph();
        System.out.println(c + ": V=" + c.v() + ", edges=" + Arrays.deepToString(c.edges()));
        boolean actual = q.isCycle(c.v(), adj(c.v(), c.edges(), false));
        System.out.println("isCycle -> " + actual);
        render(Q013_SA_DetectCycleInUndirectedGraph.class, c.variant(), c.v(), c.edges(), false, false);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q013_DetectCycleInUndirectedGraph_all() {
        header(Q013_SA_DetectCycleInUndirectedGraph.class);
        assertAll("Q013_DetectCycleInUndirectedGraph", toExecutables(q013Cases(), this::q013_DetectCycleInUndirectedGraph));
    }

    private static Stream<CaseCycle> q014Cases() {
        return Stream.of(
                new CaseCycle("A", 4, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 1}}, true),
                new CaseCycle("B", 4, new int[][]{{0, 1}, {1, 2}, {2, 3}}, false),
                new CaseCycle("C", 4, new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}}, false)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q014Cases")
    void q014_DetectCycleInDirectedGraph(CaseCycle c) throws IOException {
        Q014_SA_DetectCycleInDirectedGraph q = new Q014_SA_DetectCycleInDirectedGraph();
        System.out.println(c + ": V=" + c.v() + ", edges=" + Arrays.deepToString(c.edges()));
        boolean actual = q.isCyclic(c.v(), adj(c.v(), c.edges(), true));
        System.out.println("isCyclic -> " + actual);
        render(Q014_SA_DetectCycleInDirectedGraph.class, c.variant(), c.v(), c.edges(), true, false);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q014_DetectCycleInDirectedGraph_all() {
        header(Q014_SA_DetectCycleInDirectedGraph.class);
        assertAll("Q014_DetectCycleInDirectedGraph", toExecutables(q014Cases(), this::q014_DetectCycleInDirectedGraph));
    }

    // =========================================================================
    // Q015 / Q016 - Course Schedule I / II
    // =========================================================================

    private record Case015(String variant, int numCourses, int[][] prereq, int[][] renderEdges, boolean expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case015> q015Cases() {
        return Stream.of(
                new Case015("A", 2, new int[][]{{1, 0}}, new int[][]{{0, 1}}, true),
                new Case015("B", 2, new int[][]{{1, 0}, {0, 1}}, new int[][]{{0, 1}, {1, 0}}, false)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q015Cases")
    void q015_CourseSchedule(Case015 c) throws IOException {
        Q015_CourseSchedule q = new Q015_CourseSchedule();
        System.out.println(c + ": numCourses=" + c.numCourses() + ", prerequisites=" + Arrays.deepToString(c.prereq()));
        boolean actual = q.canFinish(c.numCourses(), c.prereq());
        System.out.println("canFinish -> " + actual);
        render(Q015_CourseSchedule.class, c.variant(), c.numCourses(), c.renderEdges(), true, false);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q015_CourseSchedule_all() {
        header(Q015_CourseSchedule.class);
        assertAll("Q015_CourseSchedule", toExecutables(q015Cases(), this::q015_CourseSchedule));
    }

    private record Case016(String variant, int numCourses, int[][] prereq, int[][] renderEdges, int[][] mustPrecede, boolean mustBeEmpty) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case016> q016Cases() {
        return Stream.of(
                new Case016("A", 4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}},
                        new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}}, new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}}, false),
                new Case016("B", 2, new int[][]{{1, 0}, {0, 1}}, new int[][]{{0, 1}, {1, 0}}, null, true)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q016Cases")
    void q016_CourseScheduleII(Case016 c) throws IOException {
        Q016_CourseScheduleII q = new Q016_CourseScheduleII();
        System.out.println(c + ": numCourses=" + c.numCourses() + ", prerequisites=" + Arrays.deepToString(c.prereq()));
        int[] actual = q.findOrder(c.numCourses(), c.prereq());
        System.out.println("findOrder -> " + Arrays.toString(actual));
        render(Q016_CourseScheduleII.class, c.variant(), c.numCourses(), c.renderEdges(), true, false);
        if (c.mustBeEmpty()) {
            assertNotNull(actual, c.toString());
            assertEquals(0, actual.length, c + ": cyclic prerequisites, expected an empty order");
        } else {
            // prereq[i] = [course, mustComeFirst] -> edge mustComeFirst -> course.
            assertValidTopoOrderArray(c.numCourses(), c.mustPrecede(), actual, c.toString());
        }
    }

    private void q016_CourseScheduleII_all() {
        header(Q016_CourseScheduleII.class);
        assertAll("Q016_CourseScheduleII", toExecutables(q016Cases(), this::q016_CourseScheduleII));
    }

    // =========================================================================
    // Q017 / Q018 - Topological sort (Kahn's BFS / DFS)
    // =========================================================================

    private record CaseTopo(String variant, int v, int[][] edges) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<CaseTopo> topoCases() {
        return Stream.of(
                new CaseTopo("A", 6, new int[][]{{5, 0}, {5, 2}, {4, 0}, {4, 1}, {2, 3}, {3, 1}}),
                new CaseTopo("B", 3, new int[][]{{0, 1}, {1, 2}})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("topoCases")
    void q017_TopologicalSortKahnsBFS(CaseTopo c) throws IOException {
        Q017_SA_TopologicalSortKahnsBFS q = new Q017_SA_TopologicalSortKahnsBFS();
        System.out.println(c + ": V=" + c.v() + ", edges=" + Arrays.deepToString(c.edges()));
        List<Integer> actual = q.topoSort(c.v(), adj(c.v(), c.edges(), true));
        System.out.println("topoSort -> " + actual);
        render(Q017_SA_TopologicalSortKahnsBFS.class, c.variant(), c.v(), c.edges(), true, false);
        assertValidTopoOrder(c.v(), c.edges(), actual, c.toString());
    }

    private void q017_TopologicalSortKahnsBFS_all() {
        header(Q017_SA_TopologicalSortKahnsBFS.class);
        assertAll("Q017_TopologicalSortKahnsBFS", toExecutables(topoCases(), this::q017_TopologicalSortKahnsBFS));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("topoCases")
    void q018_TopologicalSortDFS(CaseTopo c) throws IOException {
        Q018_SA_TopologicalSortDFS q = new Q018_SA_TopologicalSortDFS();
        System.out.println(c + ": V=" + c.v() + ", edges=" + Arrays.deepToString(c.edges()));
        List<Integer> actual = q.topoSort(c.v(), adj(c.v(), c.edges(), true));
        System.out.println("topoSort -> " + actual);
        render(Q018_SA_TopologicalSortDFS.class, c.variant(), c.v(), c.edges(), true, false);
        assertValidTopoOrder(c.v(), c.edges(), actual, c.toString());
    }

    private void q018_TopologicalSortDFS_all() {
        header(Q018_SA_TopologicalSortDFS.class);
        assertAll("Q018_TopologicalSortDFS", toExecutables(topoCases(), this::q018_TopologicalSortDFS));
    }

    // =========================================================================
    // Q019 - Is Graph Bipartite?
    // =========================================================================

    private record Case019(String variant, int[][] graph, int[][] renderEdges, boolean expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case019> q019Cases() {
        return Stream.of(
                new Case019("A", new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}},
                        new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}, {2, 3}}, false),
                new Case019("B", new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}},
                        new int[][]{{0, 1}, {0, 3}, {1, 2}, {2, 3}}, true)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q019Cases")
    void q019_IsGraphBipartite(Case019 c) throws IOException {
        Q019_IsGraphBipartite q = new Q019_IsGraphBipartite();
        System.out.println(c + ": graph=" + Arrays.deepToString(c.graph()));
        boolean actual = q.isBipartite(c.graph());
        System.out.println("isBipartite -> " + actual);
        render(Q019_IsGraphBipartite.class, c.variant(), 4, c.renderEdges(), false, false);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q019_IsGraphBipartite_all() {
        header(Q019_IsGraphBipartite.class);
        assertAll("Q019_IsGraphBipartite", toExecutables(q019Cases(), this::q019_IsGraphBipartite));
    }

    // =========================================================================
    // Q020 - Number of Connected Components
    // =========================================================================

    private record Case020(String variant, int n, int[][] edges, int expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case020> q020Cases() {
        return Stream.of(
                new Case020("A", 5, new int[][]{{0, 1}, {1, 2}, {3, 4}}, 2),
                new Case020("B", 5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}}, 1)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q020Cases")
    void q020_NumberOfConnectedComponents(Case020 c) throws IOException {
        Q020_NumberOfConnectedComponents q = new Q020_NumberOfConnectedComponents();
        System.out.println(c + ": n=" + c.n() + ", edges=" + Arrays.deepToString(c.edges()));
        int actual = q.countComponents(c.n(), c.edges());
        System.out.println("countComponents -> " + actual);
        render(Q020_NumberOfConnectedComponents.class, c.variant(), c.n(), c.edges(), false, false);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q020_NumberOfConnectedComponents_all() {
        header(Q020_NumberOfConnectedComponents.class);
        assertAll("Q020_NumberOfConnectedComponents", toExecutables(q020Cases(), this::q020_NumberOfConnectedComponents));
    }

    // =========================================================================
    // Q021 - Word Ladder (the implicit "differs by one letter" graph)
    // =========================================================================

    private static final String WORD_LADDER_BEGIN = "hit";
    private static final String WORD_LADDER_END = "cog";

    private record Case021(String variant, List<String> wordList, int expected, boolean renderTransformationGraph) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case021> q021Cases() {
        return Stream.of(
                new Case021("A", List.of("hot", "dot", "dog", "lot", "log", "cog"), 5, true),
                new Case021("B", List.of("hot", "dot", "dog", "lot", "log"), 0, false)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q021Cases")
    void q021_WordLadder(Case021 c) throws IOException {
        Q021_WordLadder q = new Q021_WordLadder();
        System.out.println(c + ": beginWord=" + WORD_LADDER_BEGIN + ", endWord=" + WORD_LADDER_END + ", wordList=" + c.wordList()
                + (c.renderTransformationGraph() ? "" : " (endWord missing -> impossible)"));
        int actual = q.ladderLength(WORD_LADDER_BEGIN, WORD_LADDER_END, c.wordList());
        System.out.println("ladderLength -> " + actual);

        if (c.renderTransformationGraph()) {
            // Render the implicit transformation graph: an edge between any two words
            // (including beginWord) that differ by exactly one letter.
            Set<String> nodes = new LinkedHashSet<>();
            nodes.add(WORD_LADDER_BEGIN);
            nodes.addAll(c.wordList());
            List<String> nodeList = new ArrayList<>(nodes);
            List<String[]> edgeList = new ArrayList<>();
            for (int i = 0; i < nodeList.size(); i++) {
                for (int j = i + 1; j < nodeList.size(); j++) {
                    if (oneLetterApart(nodeList.get(i), nodeList.get(j))) {
                        edgeList.add(new String[]{nodeList.get(i), nodeList.get(j)});
                    }
                }
            }
            renderLabeled(Q021_WordLadder.class, c.variant(), nodeList.toArray(new String[0]),
                    edgeList.toArray(new String[0][]), false, false);
        }

        assertEquals(c.expected(), actual, c.toString());
    }

    private void q021_WordLadder_all() {
        header(Q021_WordLadder.class);
        assertAll("Q021_WordLadder", toExecutables(q021Cases(), this::q021_WordLadder));
    }

    private static boolean oneLetterApart(String a, String b) {
        if (a.length() != b.length()) return false;
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
        }
        return diff == 1;
    }

    // =========================================================================
    // Q022 / Q023 / Q024 - grid / board / state-space (no Graphviz diagram)
    // =========================================================================

    private record Case022(String variant, int[][] grid, int expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case022> q022Cases() {
        return Stream.of(
                new Case022("A", new int[][]{{0, 1}, {1, 0}}, 2),
                new Case022("B", new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}, 4),
                new Case022("C", new int[][]{{1, 0, 0}, {1, 1, 0}, {1, 1, 0}}, -1)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q022Cases")
    void q022_ShortestPathInBinaryMatrix(Case022 c) {
        Q022_ShortestPathInBinaryMatrix q = new Q022_ShortestPathInBinaryMatrix();
        System.out.println(c + " grid: " + Arrays.deepToString(c.grid()));
        int actual = q.shortestPathBinaryMatrix(c.grid());
        System.out.println("shortestPathBinaryMatrix -> " + actual);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q022_ShortestPathInBinaryMatrix_all() {
        header(Q022_ShortestPathInBinaryMatrix.class);
        assertAll("Q022_ShortestPathInBinaryMatrix", toExecutables(q022Cases(), this::q022_ShortestPathInBinaryMatrix));
    }

    private record Case023(String variant, int[][] board, int expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case023> q023Cases() {
        return Stream.of(
                new Case023("A", new int[][]{
                        {-1, -1, -1, -1, -1, -1},
                        {-1, -1, -1, -1, -1, -1},
                        {-1, -1, -1, -1, -1, -1},
                        {-1, 35, -1, -1, 13, -1},
                        {-1, -1, -1, -1, -1, -1},
                        {-1, 15, -1, -1, -1, -1}
                }, 4),
                new Case023("B", new int[][]{{-1, -1}, {-1, 3}}, 1)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q023Cases")
    void q023_SnakeAndLadder(Case023 c) {
        Q023_SnakeAndLadder q = new Q023_SnakeAndLadder();
        System.out.println(c + " board: " + Arrays.deepToString(c.board()));
        int actual = q.snakesAndLadders(c.board());
        System.out.println("snakesAndLadders -> " + actual);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q023_SnakeAndLadder_all() {
        header(Q023_SnakeAndLadder.class);
        assertAll("Q023_SnakeAndLadder", toExecutables(q023Cases(), this::q023_SnakeAndLadder));
    }

    private record Case024(String variant, String[] deadends, String target, int expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case024> q024Cases() {
        return Stream.of(
                new Case024("A", new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202", 6),
                new Case024("B", new String[]{"8888"}, "0009", 1)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q024Cases")
    void q024_OpenTheLock(Case024 c) {
        Q024_OpenTheLock q = new Q024_OpenTheLock();
        System.out.println(c + ": deadends=" + Arrays.toString(c.deadends()) + ", target=" + c.target());
        int actual = q.openLock(c.deadends(), c.target());
        System.out.println("openLock -> " + actual);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q024_OpenTheLock_all() {
        header(Q024_OpenTheLock.class);
        assertAll("Q024_OpenTheLock", toExecutables(q024Cases(), this::q024_OpenTheLock));
    }

    // =========================================================================
    // Q025-Q029 - shortest path algorithms
    // =========================================================================

    private record Case025(String variant, int v, int[][] edges, int[] expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case025> q025Cases() {
        return Stream.of(
                new Case025("A", 5, new int[][]{{0, 1, 4}, {0, 2, 8}, {1, 4, 6}, {2, 3, 2}, {3, 4, 10}}, new int[]{0, 4, 8, 10, 10}),
                new Case025("B", 3, new int[][]{{0, 1, 1}, {1, 2, 1}}, new int[]{0, 1, 2})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q025Cases")
    void q025_DijkstraShortestPath(Case025 c) throws IOException {
        Q025_SA_DijkstraShortestPath q = new Q025_SA_DijkstraShortestPath();
        System.out.println(c + ": V=" + c.v() + ", edges=" + Arrays.deepToString(c.edges()) + ", src=0");
        int[] actual = q.dijkstra(c.v(), c.edges(), 0);
        System.out.println("dijkstra -> " + Arrays.toString(actual));
        render(Q025_SA_DijkstraShortestPath.class, c.variant(), c.v(), c.edges(), false, true);
        assertArrayEquals(c.expected(), actual, c.toString());
    }

    private void q025_DijkstraShortestPath_all() {
        header(Q025_SA_DijkstraShortestPath.class);
        assertAll("Q025_DijkstraShortestPath", toExecutables(q025Cases(), this::q025_DijkstraShortestPath));
    }

    private record Case026(String variant, int[][] times, int n, int k, int expected, boolean render, int[][] renderEdges) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case026> q026Cases() {
        // Nodes are 1-indexed per the problem statement.
        return Stream.of(
                new Case026("A", new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2, 2, true,
                        new int[][]{{1, 0, 1}, {1, 2, 1}, {2, 3, 1}}),
                new Case026("B (k=1)", new int[][]{{1, 2, 1}}, 2, 1, 1, true, new int[][]{{0, 1, 1}}),
                new Case026("B (k=2, source unreachable to node 1)", new int[][]{{1, 2, 1}}, 2, 2, -1, false, null)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q026Cases")
    void q026_NetworkDelayTime(Case026 c) throws IOException {
        Q026_NetworkDelayTime q = new Q026_NetworkDelayTime();
        System.out.println(c + ": times=" + Arrays.deepToString(c.times()) + ", n=" + c.n() + ", k=" + c.k());
        int actual = q.networkDelayTime(c.times(), c.n(), c.k());
        System.out.println("networkDelayTime -> " + actual);
        // 0-indexed relabeling for the diagram.
        if (c.render()) render(Q026_NetworkDelayTime.class, c.variant().substring(0, 1), c.n(), c.renderEdges(), true, true);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q026_NetworkDelayTime_all() {
        header(Q026_NetworkDelayTime.class);
        assertAll("Q026_NetworkDelayTime", toExecutables(q026Cases(), this::q026_NetworkDelayTime));
    }

    private record Case027(String variant, int v, int[][] edges, int[] expected, String note) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case027> q027Cases() {
        return Stream.of(
                new Case027("A", 5, new int[][]{{0, 1, 4}, {0, 2, 8}, {1, 4, 6}, {2, 3, 2}, {3, 4, -10}},
                        new int[]{0, 4, 8, 10, 0}, "has a negative edge"),
                // Per this method's own javadoc contract: signal a negative cycle with a
                // single-element [-1] sentinel array.
                new Case027("B", 3, new int[][]{{0, 1, 1}, {1, 2, -1}, {2, 0, -1}}, new int[]{-1}, "contains a negative cycle")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q027Cases")
    void q027_BellmanFordShortestPath(Case027 c) throws IOException {
        Q027_SA_BellmanFordShortestPath q = new Q027_SA_BellmanFordShortestPath();
        System.out.println(c + ": V=" + c.v() + ", edges=" + Arrays.deepToString(c.edges()) + ", src=0 (" + c.note() + ")");
        int[] actual = q.bellmanFord(c.v(), c.edges(), 0);
        System.out.println("bellmanFord -> " + Arrays.toString(actual));
        render(Q027_SA_BellmanFordShortestPath.class, c.variant(), c.v(), c.edges(), true, true);
        assertArrayEquals(c.expected(), actual, c.toString());
    }

    private void q027_BellmanFordShortestPath_all() {
        header(Q027_SA_BellmanFordShortestPath.class);
        assertAll("Q027_BellmanFordShortestPath", toExecutables(q027Cases(), this::q027_BellmanFordShortestPath));
    }

    private record Case028(String variant, int n, int[][] flights, int src, int dst, int k, int expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case028> q028Cases() {
        return Stream.of(
                new Case028("A", 4, new int[][]{{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}}, 0, 3, 1, 700),
                new Case028("B", 3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 1, 200)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q028Cases")
    void q028_CheapestFlightsWithinKStops(Case028 c) throws IOException {
        Q028_CheapestFlightsWithinKStops q = new Q028_CheapestFlightsWithinKStops();
        System.out.println(c + ": n=" + c.n() + ", flights=" + Arrays.deepToString(c.flights())
                + ", src=" + c.src() + ", dst=" + c.dst() + ", k=" + c.k());
        int actual = q.findCheapestPrice(c.n(), c.flights(), c.src(), c.dst(), c.k());
        System.out.println("findCheapestPrice -> " + actual);
        render(Q028_CheapestFlightsWithinKStops.class, c.variant(), c.n(), c.flights(), true, true);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q028_CheapestFlightsWithinKStops_all() {
        header(Q028_CheapestFlightsWithinKStops.class);
        assertAll("Q028_CheapestFlightsWithinKStops", toExecutables(q028Cases(), this::q028_CheapestFlightsWithinKStops));
    }

    private record Case029(int[][] dist, int[][] expected, int[][] renderEdges) {
    }

    private static Stream<Case029> q029Cases() {
        final int INF = 1_000_000;
        return Stream.of(new Case029(
                new int[][]{
                        {0, 3, INF, 7},
                        {8, 0, 2, INF},
                        {5, INF, 0, 1},
                        {2, INF, INF, 0}
                },
                new int[][]{{0, 3, 5, 6}, {5, 0, 2, 3}, {3, 6, 0, 1}, {2, 5, 7, 0}},
                new int[][]{{0, 1, 3}, {0, 3, 7}, {1, 0, 8}, {1, 2, 2}, {2, 0, 5}, {2, 3, 1}, {3, 0, 2}}
        ));
    }

    @ParameterizedTest(name = "Example A")
    @MethodSource("q029Cases")
    void q029_FloydWarshallAllPairsShortestPath(Case029 c) throws IOException {
        Q029_SA_FloydWarshallAllPairsShortestPath q = new Q029_SA_FloydWarshallAllPairsShortestPath();
        System.out.println("Example A: dist (INF=1000000) = " + Arrays.deepToString(c.dist()));
        q.floydWarshall(c.dist());
        System.out.println("floydWarshall -> dist mutated in place: " + Arrays.deepToString(c.dist()));
        render(Q029_SA_FloydWarshallAllPairsShortestPath.class, null, 4, c.renderEdges(), true, true);
        assert2D(c.expected(), c.dist(), "Example A");
    }

    private void q029_FloydWarshallAllPairsShortestPath_all() {
        header(Q029_SA_FloydWarshallAllPairsShortestPath.class);
        assertAll("Q029_FloydWarshallAllPairsShortestPath", toExecutables(q029Cases(), this::q029_FloydWarshallAllPairsShortestPath));
    }

    // =========================================================================
    // Q030 / Q031 - grid questions (no Graphviz diagram)
    // =========================================================================

    private record Case030(String variant, int[][] heights, int expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case030> q030Cases() {
        return Stream.of(
                new Case030("A", new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}}, 2),
                new Case030("B", new int[][]{{1, 2, 3}, {3, 8, 4}, {5, 3, 5}}, 1)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q030Cases")
    void q030_PathWithMinimumEffort(Case030 c) {
        Q030_PathWithMinimumEffort q = new Q030_PathWithMinimumEffort();
        System.out.println(c + " heights: " + Arrays.deepToString(c.heights()));
        int actual = q.minimumEffortPath(c.heights());
        System.out.println("minimumEffortPath -> " + actual);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q030_PathWithMinimumEffort_all() {
        header(Q030_PathWithMinimumEffort.class);
        assertAll("Q030_PathWithMinimumEffort", toExecutables(q030Cases(), this::q030_PathWithMinimumEffort));
    }

    private record Case031(String variant, int[][] grid, int expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case031> q031Cases() {
        return Stream.of(
                new Case031("A", new int[][]{{0, 2}, {1, 3}}, 3),
                new Case031("B", new int[][]{
                        {0, 1, 2, 3, 4},
                        {24, 23, 22, 21, 5},
                        {12, 13, 14, 15, 16},
                        {11, 17, 18, 19, 20},
                        {10, 9, 8, 7, 6}
                }, 16)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q031Cases")
    void q031_SwimInRisingWater(Case031 c) {
        Q031_SwimInRisingWater q = new Q031_SwimInRisingWater();
        System.out.println(c + " grid: " + Arrays.deepToString(c.grid()));
        int actual = q.swimInWater(c.grid());
        System.out.println("swimInWater -> " + actual);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q031_SwimInRisingWater_all() {
        header(Q031_SwimInRisingWater.class);
        assertAll("Q031_SwimInRisingWater", toExecutables(q031Cases(), this::q031_SwimInRisingWater));
    }

    // =========================================================================
    // Q032 - Disjoint Set Union
    // Not example-based like the rest (a sequential script of mutating ops
    // followed by 2 queries), so it stays a plain @Test rather than being
    // forced into the parameterized-case shape the others use.
    // =========================================================================

    @org.junit.jupiter.api.Test
    void q032_DisjointSetUnion() {
        header(Q032_SA_DisjointSetUnion.class);
        Q032_SA_DisjointSetUnion dsu = new Q032_SA_DisjointSetUnion(6);
        System.out.println("n=6, elements 0..5, initially each its own set");
        dsu.union(0, 1);
        System.out.println("union(0,1)");
        dsu.union(2, 3);
        System.out.println("union(2,3)");
        dsu.union(1, 3);
        System.out.println("union(1,3)");

        assertAll("Q032_DisjointSetUnion",
                () -> {
                    boolean connected03 = dsu.connected(0, 3);
                    System.out.println("connected(0,3) -> " + connected03);
                    assertTrue(connected03, "0 and 3 should be connected via 0-1, 2-3, 1-3");
                },
                () -> {
                    boolean connected04 = dsu.connected(0, 4);
                    System.out.println("connected(0,4) -> " + connected04);
                    assertFalse(connected04, "0 and 4 were never unioned");
                }
        );
    }

    // =========================================================================
    // Q033 - Redundant Connection
    // =========================================================================

    private record Case033(String variant, int[][] edges, int renderN, int[][] renderEdges, int[] expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case033> q033Cases() {
        // Edges are 1-indexed per the problem statement.
        return Stream.of(
                new Case033("A", new int[][]{{1, 2}, {1, 3}, {2, 3}}, 3, new int[][]{{0, 1}, {0, 2}, {1, 2}}, new int[]{2, 3}),
                new Case033("B", new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}, 5,
                        new int[][]{{0, 1}, {1, 2}, {2, 3}, {0, 3}, {0, 4}}, new int[]{1, 4})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q033Cases")
    void q033_RedundantConnection(Case033 c) throws IOException {
        Q033_RedundantConnection q = new Q033_RedundantConnection();
        System.out.println(c + ": edges=" + Arrays.deepToString(c.edges()) + " (1-indexed)");
        int[] actual = q.findRedundantConnection(c.edges());
        System.out.println("findRedundantConnection -> " + Arrays.toString(actual));
        render(Q033_RedundantConnection.class, c.variant(), c.renderN(), c.renderEdges(), false, false);
        assertArrayEquals(c.expected(), actual, c.toString());
    }

    private void q033_RedundantConnection_all() {
        header(Q033_RedundantConnection.class);
        assertAll("Q033_RedundantConnection", toExecutables(q033Cases(), this::q033_RedundantConnection));
    }

    // =========================================================================
    // Q034 - Accounts Merge
    // =========================================================================

    private record Case034(String variant, List<List<String>> accounts, List<List<String>> expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case034> q034Cases() {
        return Stream.of(new Case034("A",
                List.of(
                        List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                        List.of("John", "johnsmith@mail.com", "john00@mail.com"),
                        List.of("Mary", "mary@mail.com"),
                        List.of("John", "johnnybravo@mail.com")
                ),
                List.of(
                        List.of("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"),
                        List.of("Mary", "mary@mail.com"),
                        List.of("John", "johnnybravo@mail.com")
                )
        ));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q034Cases")
    void q034_AccountsMerge(Case034 c) {
        Q034_AccountsMerge q = new Q034_AccountsMerge();
        System.out.println(c + ": accounts=" + c.accounts());
        List<List<String>> actual = q.accountsMerge(c.accounts());
        System.out.println("accountsMerge -> " + actual);
        assertAccountsMergeMatches(c.expected(), actual, c.toString());
    }

    private void q034_AccountsMerge_all() {
        header(Q034_AccountsMerge.class);
        assertAll("Q034_AccountsMerge", toExecutables(q034Cases(), this::q034_AccountsMerge));
    }

    // =========================================================================
    // Q035 - Graph Valid Tree
    // =========================================================================

    private record Case035(String variant, int n, int[][] edges, boolean expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case035> q035Cases() {
        return Stream.of(
                new Case035("A", 5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}}, true),
                new Case035("B", 5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}, false)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q035Cases")
    void q035_GraphValidTree(Case035 c) throws IOException {
        Q035_GraphValidTree q = new Q035_GraphValidTree();
        System.out.println(c + ": n=" + c.n() + ", edges=" + Arrays.deepToString(c.edges()));
        boolean actual = q.validTree(c.n(), c.edges());
        System.out.println("validTree -> " + actual);
        render(Q035_GraphValidTree.class, c.variant(), c.n(), c.edges(), false, false);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q035_GraphValidTree_all() {
        header(Q035_GraphValidTree.class);
        assertAll("Q035_GraphValidTree", toExecutables(q035Cases(), this::q035_GraphValidTree));
    }

    // =========================================================================
    // Q036 / Q037 - MST (Prim's / Kruskal's)
    // =========================================================================

    private record CaseMST(String variant, int v, int[][] edges, int expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<CaseMST> mstCases() {
        return Stream.of(
                new CaseMST("A", 5, new int[][]{{0, 1, 2}, {0, 3, 6}, {1, 2, 3}, {1, 3, 8}, {1, 4, 5}, {2, 4, 7}, {3, 4, 9}}, 16),
                new CaseMST("B", 3, new int[][]{{0, 1, 1}, {1, 2, 1}, {0, 2, 5}}, 2)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("mstCases")
    void q036_PrimsMST(CaseMST c) throws IOException {
        Q036_SA_PrimsMST q = new Q036_SA_PrimsMST();
        System.out.println(c + ": V=" + c.v() + ", edges=" + Arrays.deepToString(c.edges()));
        int actual = q.spanningTree(c.v(), c.edges());
        System.out.println("spanningTree -> " + actual);
        render(Q036_SA_PrimsMST.class, c.variant(), c.v(), c.edges(), false, true);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q036_PrimsMST_all() {
        header(Q036_SA_PrimsMST.class);
        assertAll("Q036_PrimsMST", toExecutables(mstCases(), this::q036_PrimsMST));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("mstCases")
    void q037_KruskalsMST(CaseMST c) throws IOException {
        Q037_SA_KruskalsMST q = new Q037_SA_KruskalsMST();
        System.out.println(c + ": V=" + c.v() + ", edges=" + Arrays.deepToString(c.edges()));
        int actual = q.spanningTree(c.v(), c.edges());
        System.out.println("spanningTree -> " + actual);
        render(Q037_SA_KruskalsMST.class, c.variant(), c.v(), c.edges(), false, true);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q037_KruskalsMST_all() {
        header(Q037_SA_KruskalsMST.class);
        assertAll("Q037_KruskalsMST", toExecutables(mstCases(), this::q037_KruskalsMST));
    }

    // =========================================================================
    // Q038 / Q039 / Q040 - bridges, articulation points, Kosaraju's SCC
    // =========================================================================

    private record Case038(String variant, int n, int[][] edges, List<List<Integer>> expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case038> q038Cases() {
        return Stream.of(
                new Case038("A", 4, new int[][]{{0, 1}, {1, 2}, {2, 0}, {1, 3}}, List.of(List.of(1, 3))),
                new Case038("B", 2, new int[][]{{0, 1}}, List.of(List.of(0, 1)))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q038Cases")
    void q038_CriticalConnectionsBridges(Case038 c) throws IOException {
        Q038_SA_CriticalConnectionsBridges q = new Q038_SA_CriticalConnectionsBridges();
        System.out.println(c + ": n=" + c.n() + ", connections=" + Arrays.deepToString(c.edges()));
        List<List<Integer>> actual = q.criticalConnections(c.n(), edgeList(c.edges()));
        System.out.println("criticalConnections -> " + actual);
        render(Q038_SA_CriticalConnectionsBridges.class, c.variant(), c.n(), c.edges(), false, false);
        assertUnorderedEdges(c.expected(), actual, c.toString());
    }

    private void q038_CriticalConnectionsBridges_all() {
        header(Q038_SA_CriticalConnectionsBridges.class);
        assertAll("Q038_CriticalConnectionsBridges", toExecutables(q038Cases(), this::q038_CriticalConnectionsBridges));
    }

    private record Case039(String variant, int v, int[][] edges, List<Integer> expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case039> q039Cases() {
        return Stream.of(
                new Case039("A", 5, new int[][]{{0, 1}, {0, 2}, {1, 2}, {2, 3}, {3, 4}}, List.of(2, 3)),
                new Case039("B", 4, new int[][]{{0, 1}, {1, 2}, {2, 3}}, List.of(1, 2))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q039Cases")
    void q039_ArticulationPoints(Case039 c) throws IOException {
        Q039_SA_ArticulationPoints q = new Q039_SA_ArticulationPoints();
        System.out.println(c + ": V=" + c.v() + ", edges=" + Arrays.deepToString(c.edges()));
        List<Integer> actual = q.articulationPoints(c.v(), adj(c.v(), c.edges(), false));
        System.out.println("articulationPoints -> " + actual);
        render(Q039_SA_ArticulationPoints.class, c.variant(), c.v(), c.edges(), false, false);
        assertUnorderedInts(c.expected(), actual, c.toString());
    }

    private void q039_ArticulationPoints_all() {
        header(Q039_SA_ArticulationPoints.class);
        assertAll("Q039_ArticulationPoints", toExecutables(q039Cases(), this::q039_ArticulationPoints));
    }

    private record CaseSCC(String variant, int v, int[][] edges, int expected, String note) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<CaseSCC> sccCases() {
        return Stream.of(
                new CaseSCC("A", 5, new int[][]{{0, 1}, {1, 2}, {1, 4}, {2, 0}, {4, 3}}, 3, null),
                new CaseSCC("B", 3, new int[][]{{0, 1}, {1, 2}, {2, 0}}, 1, null)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("sccCases")
    void q040_KosarajusSCC(CaseSCC c) throws IOException {
        Q040_SA_KosarajusSCC q = new Q040_SA_KosarajusSCC();
        System.out.println(c + ": V=" + c.v() + ", edges=" + Arrays.deepToString(c.edges()));
        int actual = q.stronglyConnectedComponents(c.v(), adj(c.v(), c.edges(), true));
        System.out.println("stronglyConnectedComponents -> " + actual);
        render(Q040_SA_KosarajusSCC.class, c.variant(), c.v(), c.edges(), true, false);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q040_KosarajusSCC_all() {
        header(Q040_SA_KosarajusSCC.class);
        assertAll("Q040_KosarajusSCC", toExecutables(sccCases(), this::q040_KosarajusSCC));
    }

    // =========================================================================
    // Q041 - Evaluate Division (weighted, string-labeled directed graph)
    // =========================================================================

    private record Case041(String variant, List<List<String>> equations, double[] values, List<List<String>> queries,
                            String[] renderNodes, String[][] renderEdges, double[] expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case041> q041Cases() {
        return Stream.of(
                new Case041("A", List.of(List.of("a", "b"), List.of("b", "c")), new double[]{2.0, 3.0},
                        List.of(List.of("a", "c"), List.of("b", "a"), List.of("a", "e"), List.of("a", "a"), List.of("x", "x")),
                        new String[]{"a", "b", "c"},
                        new String[][]{{"a", "b", "2.0"}, {"b", "a", "0.5"}, {"b", "c", "3.0"}, {"c", "b", "0.33"}},
                        new double[]{6.0, 0.5, -1.0, 1.0, -1.0}),
                new Case041("B", List.of(List.of("a", "b"), List.of("b", "c"), List.of("bc", "cd")), new double[]{1.5, 2.5, 5.0},
                        List.of(List.of("a", "c"), List.of("c", "b"), List.of("bc", "cd"), List.of("cd", "bc")),
                        new String[]{"a", "b", "c", "bc", "cd"},
                        new String[][]{{"a", "b", "1.5"}, {"b", "a", "0.67"}, {"b", "c", "2.5"}, {"c", "b", "0.4"},
                                {"bc", "cd", "5.0"}, {"cd", "bc", "0.2"}},
                        new double[]{3.75, 0.4, 5.0, 0.2})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q041Cases")
    void q041_EvaluateDivision(Case041 c) throws IOException {
        Q041_EvaluateDivision q = new Q041_EvaluateDivision();
        System.out.println(c + ": equations=" + c.equations() + ", values=" + Arrays.toString(c.values()) + ", queries=" + c.queries());
        double[] actual = q.calcEquation(c.equations(), c.values(), c.queries());
        System.out.println("calcEquation -> " + Arrays.toString(actual));
        renderLabeled(Q041_EvaluateDivision.class, c.variant(), c.renderNodes(), c.renderEdges(), true, true);
        assertArrayEquals(c.expected(), actual, 1e-4, c.toString());
    }

    private void q041_EvaluateDivision_all() {
        header(Q041_EvaluateDivision.class);
        assertAll("Q041_EvaluateDivision", toExecutables(q041Cases(), this::q041_EvaluateDivision));
    }

    // =========================================================================
    // Q042 - Reconstruct Itinerary (string-labeled directed graph)
    // =========================================================================

    private record Case042(String variant, List<List<String>> tickets, String[] renderNodes, String[][] renderEdges, List<String> expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case042> q042Cases() {
        return Stream.of(
                new Case042("A", List.of(List.of("MUC", "LHR"), List.of("JFK", "MUC"), List.of("SFO", "SJC"), List.of("LHR", "SFO")),
                        new String[]{"MUC", "LHR", "JFK", "SFO", "SJC"},
                        new String[][]{{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}},
                        List.of("JFK", "MUC", "LHR", "SFO", "SJC")),
                new Case042("B", List.of(List.of("JFK", "SFO"), List.of("JFK", "ATL"), List.of("SFO", "ATL"),
                        List.of("ATL", "JFK"), List.of("ATL", "SFO")),
                        new String[]{"JFK", "SFO", "ATL"},
                        new String[][]{{"JFK", "SFO"}, {"JFK", "ATL"}, {"SFO", "ATL"}, {"ATL", "JFK"}, {"ATL", "SFO"}},
                        List.of("JFK", "ATL", "JFK", "SFO", "ATL", "SFO"))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q042Cases")
    void q042_ReconstructItinerary(Case042 c) throws IOException {
        Q042_ReconstructItinerary q = new Q042_ReconstructItinerary();
        System.out.println(c + ": tickets=" + c.tickets());
        List<String> actual = q.findItinerary(c.tickets());
        System.out.println("findItinerary -> " + actual);
        renderLabeled(Q042_ReconstructItinerary.class, c.variant(), c.renderNodes(), c.renderEdges(), true, false);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q042_ReconstructItinerary_all() {
        header(Q042_ReconstructItinerary.class);
        assertAll("Q042_ReconstructItinerary", toExecutables(q042Cases(), this::q042_ReconstructItinerary));
    }

    // =========================================================================
    // Q043 - Alien Dictionary (string-labeled directed graph over single letters)
    // =========================================================================

    private record Case043(String variant, String[] words, String[] renderNodes, String[][] renderEdges,
                            String expectedLetters, char[][] mustPrecede, boolean invalid) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case043> q043Cases() {
        return Stream.of(
                new Case043("A", new String[]{"wrt", "wrf", "er", "ett", "rftt"},
                        new String[]{"w", "e", "r", "t", "f"},
                        new String[][]{{"w", "e"}, {"e", "r"}, {"r", "t"}, {"t", "f"}},
                        "wertf", new char[][]{{'t', 'f'}, {'w', 'e'}, {'r', 't'}, {'e', 'r'}}, false),
                new Case043("B", new String[]{"z", "x"}, new String[]{"z", "x"}, new String[][]{{"z", "x"}},
                        "zx", new char[][]{{'z', 'x'}}, false),
                new Case043("C (invalid - longer word precedes its own prefix)", new String[]{"abc", "ab"},
                        null, null, null, null, true)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q043Cases")
    void q043_AlienDictionary(Case043 c) throws IOException {
        Q043_AlienDictionary q = new Q043_AlienDictionary();
        System.out.println(c + ": words=" + Arrays.toString(c.words()));
        String actual = q.alienOrder(c.words());
        System.out.println("alienOrder -> \"" + actual + "\"");
        if (c.invalid()) {
            assertEquals("", actual, c + ": invalid input, no valid order exists");
        } else {
            renderLabeled(Q043_AlienDictionary.class, c.variant(), c.renderNodes(), c.renderEdges(), true, false);
            assertValidLetterOrder(actual, c.expectedLetters(), c.mustPrecede(), c.toString());
        }
    }

    private void q043_AlienDictionary_all() {
        header(Q043_AlienDictionary.class);
        assertAll("Q043_AlienDictionary", toExecutables(q043Cases(), this::q043_AlienDictionary));
    }

    // =========================================================================
    // Q044 - Minimum Height Trees
    // =========================================================================

    private record Case044(String variant, int n, int[][] edges, List<Integer> expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case044> q044Cases() {
        return Stream.of(
                new Case044("A", 4, new int[][]{{1, 0}, {1, 2}, {1, 3}}, List.of(1)),
                new Case044("B", 6, new int[][]{{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}}, List.of(3, 4))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q044Cases")
    void q044_MinimumHeightTrees(Case044 c) throws IOException {
        Q044_MinimumHeightTrees q = new Q044_MinimumHeightTrees();
        System.out.println(c + ": n=" + c.n() + ", edges=" + Arrays.deepToString(c.edges()));
        List<Integer> actual = q.findMinHeightTrees(c.n(), c.edges());
        System.out.println("findMinHeightTrees -> " + actual);
        render(Q044_MinimumHeightTrees.class, c.variant(), c.n(), c.edges(), false, false);
        assertUnorderedInts(c.expected(), actual, c.toString());
    }

    private void q044_MinimumHeightTrees_all() {
        header(Q044_MinimumHeightTrees.class);
        assertAll("Q044_MinimumHeightTrees", toExecutables(q044Cases(), this::q044_MinimumHeightTrees));
    }

    // =========================================================================
    // Q045 - All Paths From Source to Target
    // =========================================================================

    private record Case045(String variant, int[][] graph, List<List<Integer>> expected) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case045> q045Cases() {
        return Stream.of(
                new Case045("A", new int[][]{{1, 2}, {3}, {3}, {}}, List.of(List.of(0, 1, 3), List.of(0, 2, 3))),
                new Case045("B", new int[][]{{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}}, List.of(
                        List.of(0, 4), List.of(0, 3, 4), List.of(0, 1, 3, 4), List.of(0, 1, 2, 3, 4), List.of(0, 1, 4)))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q045Cases")
    void q045_AllPathsFromSourceToTarget(Case045 c) throws IOException {
        Q045_AllPathsFromSourceToTarget q = new Q045_AllPathsFromSourceToTarget();
        System.out.println(c + ": graph=" + Arrays.deepToString(c.graph()));
        List<List<Integer>> actual = q.allPathsSourceTarget(c.graph());
        System.out.println("allPathsSourceTarget -> " + actual);
        render(Q045_AllPathsFromSourceToTarget.class, c.variant(), c.graph().length, toEdgesFromAdjArray(c.graph()), true, false);
        assertUnorderedListOfLists(c.expected(), actual, c.toString());
    }

    private void q045_AllPathsFromSourceToTarget_all() {
        header(Q045_AllPathsFromSourceToTarget.class);
        assertAll("Q045_AllPathsFromSourceToTarget", toExecutables(q045Cases(), this::q045_AllPathsFromSourceToTarget));
    }

    // =========================================================================
    // Q046 - Max Flow (Ford-Fulkerson / Edmonds-Karp)
    // =========================================================================

    private record Case046(String variant, int n, int[][] edges, int source, int sink, int expected, String note) {
        @Override
        public String toString() {
            return "Example " + variant;
        }
    }

    private static Stream<Case046> q046Cases() {
        return Stream.of(
                new Case046("A", 6, new int[][]{{0, 1, 16}, {0, 2, 13}, {1, 2, 10}, {2, 1, 4}, {1, 3, 12},
                        {3, 2, 9}, {2, 4, 14}, {4, 3, 7}, {3, 5, 20}, {4, 5, 4}}, 0, 5, 23, "classic textbook network"),
                new Case046("B", 4, new int[][]{{0, 1, 3}, {0, 2, 2}, {1, 3, 2}, {2, 3, 3}}, 0, 3, 4, null)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q046Cases")
    void q046_MaxFlowFordFulkerson(Case046 c) throws IOException {
        Q046_SA_MaxFlowFordFulkerson q = new Q046_SA_MaxFlowFordFulkerson();
        System.out.println(c + (c.note() != null ? " (" + c.note() + ")" : "") + ": n=" + c.n()
                + ", edges=" + Arrays.deepToString(c.edges()) + ", source=" + c.source() + ", sink=" + c.sink());
        int actual = q.maxFlow(c.n(), c.edges(), c.source(), c.sink());
        System.out.println("maxFlow -> " + actual);
        render(Q046_SA_MaxFlowFordFulkerson.class, c.variant(), c.n(), c.edges(), true, true);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q046_MaxFlowFordFulkerson_all() {
        header(Q046_SA_MaxFlowFordFulkerson.class);
        assertAll("Q046_MaxFlowFordFulkerson", toExecutables(q046Cases(), this::q046_MaxFlowFordFulkerson));
    }

    // =========================================================================
    // Q047 - Strongly Connected Components (Tarjan's)
    // =========================================================================

    @ParameterizedTest(name = "{0}")
    @MethodSource("sccCases")
    void q047_TarjansSCC(CaseSCC c) throws IOException {
        Q047_SA_TarjansSCC q = new Q047_SA_TarjansSCC();
        System.out.println(c + ": V=" + c.v() + ", edges=" + Arrays.deepToString(c.edges()));
        int actual = q.stronglyConnectedComponents(c.v(), adj(c.v(), c.edges(), true));
        System.out.println("stronglyConnectedComponents -> " + actual);
        render(Q047_SA_TarjansSCC.class, c.variant(), c.v(), c.edges(), true, false);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q047_TarjansSCC_all() {
        header(Q047_SA_TarjansSCC.class);
        assertAll("Q047_TarjansSCC", toExecutables(sccCases(), this::q047_TarjansSCC));
    }

    // =========================================================================
    // shared helpers
    // =========================================================================

    private static void header(Class<?> qClass) {
        System.out.println("\n=== " + qClass.getSimpleName() + " ===");
    }

    private static List<List<Integer>> adj(int v, int[][] edges, boolean directed) {
        return GraphUtil.buildAdjacencyList(v, edges, directed);
    }

    private static List<List<Integer>> edgeList(int[][] edges) {
        List<List<Integer>> out = new ArrayList<>();
        for (int[] e : edges) out.add(List.of(e[0], e[1]));
        return out;
    }

    private static int[][] toEdgesFromAdjArray(int[][] adjArray) {
        List<int[]> edges = new ArrayList<>();
        for (int u = 0; u < adjArray.length; u++) {
            for (int v : adjArray[u]) edges.add(new int[]{u, v});
        }
        return edges.toArray(new int[0][]);
    }

    private static void printGrid(char[][] grid) {
        for (char[] row : grid) System.out.println("  " + new String(row));
    }

    private static String pngName(Class<?> qClass, String variant) {
        return qClass.getSimpleName() + (variant == null ? "" : "_" + variant);
    }

    private static void render(Class<?> qClass, String variant, int n, int[][] edges, boolean directed, boolean weighted) throws IOException {
        String dot = weighted ? GraphUtil.toDotWeighted(n, edges, directed) : GraphUtil.toDot(n, edges, directed);
        renderToPng(dot, pngName(qClass, variant));
    }

    private static void renderLabeled(Class<?> qClass, String variant, String[] nodes, String[][] edges, boolean directed, boolean weighted) throws IOException {
        String dot = weighted ? GraphUtil.toDotLabeledWeighted(nodes, edges, directed) : GraphUtil.toDotLabeled(nodes, edges, directed);
        renderToPng(dot, pngName(qClass, variant));
    }

    /**
     * Writes DOT source to {@code graph-output/<name>.dot}, then shells out
     * to Graphviz to render {@code graph-output/<name>.png} alongside it.
     * This is the standard way to call an external tool like Graphviz from
     * Java - ProcessBuilder, not a library dependency.
     */
    private static void renderToPng(String dotSource, String name) throws IOException {
        File dotFile = new File(OUT_DIR, name + ".dot");
        try (FileWriter fw = new FileWriter(dotFile)) {
            fw.write(dotSource);
        }

        File pngFile = new File(OUT_DIR, name + ".png");
        ProcessBuilder pb = new ProcessBuilder(ENGINE, "-Tpng", dotFile.getPath(), "-o", pngFile.getPath());
        pb.redirectErrorStream(true);
        try {
            Process process = pb.start();
            process.getInputStream().transferTo(System.out);
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.out.println("`" + ENGINE + "` exited with code " + exitCode
                        + " - is Graphviz installed and on PATH? (brew install graphviz)");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Could not run `" + ENGINE + "` (" + e.getMessage()
                    + "). Render it yourself: " + ENGINE + " -Tpng " + dotFile.getPath() + " -o " + pngFile.getPath());
        }
    }

    // =========================================================================
    // assertion helpers - compare actual output to the documented expected
    // value for each question's example, so a red/green result actually
    // means something instead of just "didn't throw". Where a problem has
    // more than one valid correct answer, these check the underlying
    // property (valid ordering, correct set regardless of order) instead of
    // pinning to one exact value.
    // =========================================================================

    private static void assert2D(int[][] expected, int[][] actual, String what) {
        assertEquals(Arrays.deepToString(expected), actual == null ? "null" : Arrays.deepToString(actual), what);
    }

    private static void assert2D(char[][] expected, char[][] actual, String what) {
        assertEquals(Arrays.deepToString(expected), actual == null ? "null" : Arrays.deepToString(actual), what);
    }

    private static void assertUnorderedInts(List<Integer> expected, List<Integer> actual, String what) {
        assertNotNull(actual, what + " was null");
        List<Integer> e = new ArrayList<>(expected);
        List<Integer> a = new ArrayList<>(actual);
        e.sort(null);
        a.sort(null);
        assertEquals(e, a, what);
    }

    private static void assertUnorderedListOfLists(List<List<Integer>> expected, List<List<Integer>> actual, String what) {
        assertNotNull(actual, what + " was null");
        assertEquals(new HashSet<>(expected), new HashSet<>(actual), what);
    }

    private static void assertUnorderedEdges(List<List<Integer>> expected, List<List<Integer>> actual, String what) {
        assertNotNull(actual, what + " was null");
        assertEquals(normalizeEdges(expected), normalizeEdges(actual), what);
    }

    private static List<String> normalizeEdges(List<List<Integer>> edges) {
        List<String> keys = new ArrayList<>();
        for (List<Integer> e : edges) {
            int a = e.get(0), b = e.get(1);
            keys.add(Math.min(a, b) + "-" + Math.max(a, b));
        }
        keys.sort(null);
        return keys;
    }

    /**
     * Verifies `actual` is a valid topological order for `size` vertices
     * 0..size-1 given the directed edges in `mustPrecede` (edge [u, v] means
     * u must appear before v): a permutation of every vertex, respecting
     * every edge's ordering constraint. Doesn't pin to one exact ordering,
     * since multiple valid topological orders can exist for the same DAG.
     */
    private static void assertValidTopoOrder(int size, int[][] mustPrecede, List<Integer> actual, String what) {
        assertNotNull(actual, what + " was null");
        assertEquals(size, actual.size(), what + " should contain exactly " + size + " vertices");
        assertEquals(new HashSet<>(actual).size(), actual.size(), what + " has duplicate vertices");
        for (int i = 0; i < size; i++) {
            assertTrue(actual.contains(i), what + " is missing vertex " + i);
        }
        for (int[] e : mustPrecede) {
            int before = actual.indexOf(e[0]);
            int after = actual.indexOf(e[1]);
            assertTrue(before < after, what + ": vertex " + e[0] + " must come before vertex " + e[1]);
        }
    }

    private static void assertValidTopoOrderArray(int size, int[][] mustPrecede, int[] actual, String what) {
        assertNotNull(actual, what + " was null");
        assertValidTopoOrder(size, mustPrecede, Arrays.stream(actual).boxed().collect(Collectors.toList()), what);
    }

    private static void assertValidLetterOrder(String actual, String expectedLetters, char[][] mustPrecede, String what) {
        assertNotNull(actual, what + " was null");
        char[] sortedActual = actual.toCharArray();
        Arrays.sort(sortedActual);
        char[] sortedExpected = expectedLetters.toCharArray();
        Arrays.sort(sortedExpected);
        assertArrayEquals(sortedExpected, sortedActual, what + " doesn't use the expected letter set");
        for (char[] pair : mustPrecede) {
            int before = actual.indexOf(pair[0]);
            int after = actual.indexOf(pair[1]);
            assertTrue(before >= 0 && after >= 0 && before < after,
                    what + ": '" + pair[0] + "' must come before '" + pair[1] + "'");
        }
    }

    private static void assertProperClone(GraphNode original, GraphNode cloned, String what) {
        assertNotNull(cloned, what + " was null");
        Map<Integer, GraphNode> origByVal = new HashMap<>();
        Map<Integer, GraphNode> cloneByVal = new HashMap<>();
        collectNodes(original, origByVal, new HashSet<>());
        collectNodes(cloned, cloneByVal, new HashSet<>());
        assertEquals(origByVal.keySet(), cloneByVal.keySet(), what + ": cloned graph has a different set of node values");
        for (Integer val : origByVal.keySet()) {
            GraphNode o = origByVal.get(val);
            GraphNode c = cloneByVal.get(val);
            assertNotSame(o, c, what + ": node " + val + " was not actually cloned (same object reference)");
            Set<Integer> oNeighbors = o.neighbors.stream().map(n -> n.val).collect(Collectors.toSet());
            Set<Integer> cNeighbors = c.neighbors.stream().map(n -> n.val).collect(Collectors.toSet());
            assertEquals(oNeighbors, cNeighbors, what + ": node " + val + "'s neighbor set doesn't match");
        }
    }

    private static void collectNodes(GraphNode node, Map<Integer, GraphNode> byVal, Set<GraphNode> seen) {
        if (node == null || !seen.add(node)) return;
        byVal.put(node.val, node);
        for (GraphNode n : node.neighbors) collectNodes(n, byVal, seen);
    }

    private static void assertAccountsMergeMatches(List<List<String>> expected, List<List<String>> actual, String what) {
        assertNotNull(actual, what + " was null");
        assertEquals(canonicalAccounts(expected), canonicalAccounts(actual), what);
    }

    private static List<String> canonicalAccounts(List<List<String>> accounts) {
        List<String> keys = new ArrayList<>();
        for (List<String> acc : accounts) {
            String name = acc.get(0);
            List<String> emails = new ArrayList<>(acc.subList(1, acc.size()));
            emails.sort(null);
            keys.add(name + ":" + String.join(",", emails));
        }
        keys.sort(null);
        return keys;
    }
}
