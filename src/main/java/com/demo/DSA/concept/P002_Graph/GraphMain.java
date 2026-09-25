package com.demo.DSA.concept.P002_Graph;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
 */
public class GraphMain {

    private static final File OUT_DIR = new File("graph-output");
    private static final String ENGINE = "neato";

    public static void main(String[] args) throws IOException {
        OUT_DIR.mkdirs();

        q001_BFSTraversalOfGraph();
        q002_DFSTraversalOfGraph();
        q003_NumberOfProvinces();
        q004_NumberOfIslands();
        q005_FloodFill();
        q006_RottingOranges();
        q007_MaxAreaOfIsland();
        q008_SurroundedRegions();
        q009_PacificAtlanticWaterFlow();
        q010_ZeroOneMatrix();
        q011_WallsAndGates();
        q012_CloneGraph();
        q013_DetectCycleInUndirectedGraph();
        q014_DetectCycleInDirectedGraph();
        q015_CourseSchedule();
        q016_CourseScheduleII();
        q017_TopologicalSortKahnsBFS();
        q018_TopologicalSortDFS();
        q019_IsGraphBipartite();
        q020_NumberOfConnectedComponents();
        q021_WordLadder();
        q022_ShortestPathInBinaryMatrix();
        q023_SnakeAndLadder();
        q024_OpenTheLock();
        q025_DijkstraShortestPath();
        q026_NetworkDelayTime();
        q027_BellmanFordShortestPath();
        q028_CheapestFlightsWithinKStops();
        q029_FloydWarshallAllPairsShortestPath();
        q030_PathWithMinimumEffort();
        q031_SwimInRisingWater();
        q032_DisjointSetUnion();
        q033_RedundantConnection();
        q034_AccountsMerge();
        q035_GraphValidTree();
        q036_PrimsMST();
        q037_KruskalsMST();
        q038_CriticalConnectionsBridges();
        q039_ArticulationPoints();
        q040_KosarajusSCC();
        q041_EvaluateDivision();
        q042_ReconstructItinerary();
        q043_AlienDictionary();
        q044_MinimumHeightTrees();
        q045_AllPathsFromSourceToTarget();
        q046_MaxFlowFordFulkerson();
        q047_TarjansSCC();
    }

    // =========================================================================
    // Q001 / Q002 - BFS / DFS traversal
    // =========================================================================

    private static void q001_BFSTraversalOfGraph() throws IOException {
        header(Q001_SA_BFSTraversalOfGraph.class);
        Q001_SA_BFSTraversalOfGraph q = new Q001_SA_BFSTraversalOfGraph();

        int vA = 5;
        int[][] edgesA = {{0, 1}, {0, 2}, {0, 3}, {2, 4}};
        System.out.println("Example A: V=" + vA + ", adj built from edges " + Arrays.deepToString(edgesA));
        System.out.println("bfsOfGraph -> " + q.bfsOfGraph(vA, adj(vA, edgesA, false)));
        render(Q001_SA_BFSTraversalOfGraph.class, "A", vA, edgesA, false, false);

        int vB = 3;
        int[][] edgesB = {{0, 1}, {0, 2}};
        System.out.println("Example B: V=" + vB + ", adj built from edges " + Arrays.deepToString(edgesB));
        System.out.println("bfsOfGraph -> " + q.bfsOfGraph(vB, adj(vB, edgesB, false)));
        render(Q001_SA_BFSTraversalOfGraph.class, "B", vB, edgesB, false, false);
    }

    private static void q002_DFSTraversalOfGraph() throws IOException {
        header(Q002_SA_DFSTraversalOfGraph.class);
        Q002_SA_DFSTraversalOfGraph q = new Q002_SA_DFSTraversalOfGraph();

        int vA = 5;
        int[][] edgesA = {{0, 1}, {0, 2}, {0, 3}, {2, 4}};
        System.out.println("Example A: V=" + vA + ", adj built from edges " + Arrays.deepToString(edgesA));
        System.out.println("dfsOfGraph -> " + q.dfsOfGraph(vA, adj(vA, edgesA, false)));
        render(Q002_SA_DFSTraversalOfGraph.class, "A", vA, edgesA, false, false);

        int vB = 3;
        int[][] edgesB = {{0, 1}, {0, 2}};
        System.out.println("Example B: V=" + vB + ", adj built from edges " + Arrays.deepToString(edgesB));
        System.out.println("dfsOfGraph -> " + q.dfsOfGraph(vB, adj(vB, edgesB, false)));
        render(Q002_SA_DFSTraversalOfGraph.class, "B", vB, edgesB, false, false);
    }

    // =========================================================================
    // Q003 - Number of Provinces
    // =========================================================================

    private static void q003_NumberOfProvinces() throws IOException {
        header(Q003_NumberOfProvinces.class);
        Q003_NumberOfProvinces q = new Q003_NumberOfProvinces();

        int[][] isConnectedA = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println("Example A: isConnected=" + Arrays.deepToString(isConnectedA));
        System.out.println("findCircleNum -> " + q.findCircleNum(isConnectedA));
        // Rendered as plain edges for the diagram: node i-j connected iff isConnected[i][j]==1 (i!=j).
        render(Q003_NumberOfProvinces.class, "A", 3, new int[][]{{0, 1}}, false, false);

        int[][] isConnectedB = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        System.out.println("Example B: isConnected=" + Arrays.deepToString(isConnectedB));
        System.out.println("findCircleNum -> " + q.findCircleNum(isConnectedB));
        render(Q003_NumberOfProvinces.class, "B", 3, new int[0][], false, false);
    }

    // =========================================================================
    // Q004-Q011 - grid/board questions (no Graphviz diagram; grid printed as text)
    // =========================================================================

    private static void q004_NumberOfIslands() {
        header(Q004_NumberOfIslands.class);
        Q004_NumberOfIslands q = new Q004_NumberOfIslands();

        char[][] gridA = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println("Example A grid:");
        printGrid(gridA);
        System.out.println("numIslands -> " + q.numIslands(gridA));

        char[][] gridB = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println("Example B grid:");
        printGrid(gridB);
        System.out.println("numIslands -> " + q.numIslands(gridB));
    }

    private static void q005_FloodFill() {
        header(Q005_FloodFill.class);
        Q005_FloodFill q = new Q005_FloodFill();

        int[][] imageA = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println("Example A: image=" + Arrays.deepToString(imageA) + ", sr=1, sc=1, color=2");
        System.out.println("floodFill -> " + Arrays.deepToString(q.floodFill(imageA, 1, 1, 2)));

        int[][] imageB = {{0, 0, 0}, {0, 0, 0}};
        System.out.println("Example B: image=" + Arrays.deepToString(imageB) + ", sr=0, sc=0, color=0");
        System.out.println("floodFill -> " + Arrays.deepToString(q.floodFill(imageB, 0, 0, 0)));
    }

    private static void q006_RottingOranges() {
        header(Q006_RottingOranges.class);
        Q006_RottingOranges q = new Q006_RottingOranges();

        int[][] gridA = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println("Example A grid: " + Arrays.deepToString(gridA));
        System.out.println("orangesRotting -> " + q.orangesRotting(gridA));

        int[][] gridB = {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        System.out.println("Example B grid: " + Arrays.deepToString(gridB));
        System.out.println("orangesRotting -> " + q.orangesRotting(gridB));
    }

    private static void q007_MaxAreaOfIsland() {
        header(Q007_MaxAreaOfIsland.class);
        Q007_MaxAreaOfIsland q = new Q007_MaxAreaOfIsland();

        int[][] gridA = {{1, 1, 0, 0}, {1, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        System.out.println("Example A grid: " + Arrays.deepToString(gridA));
        System.out.println("maxAreaOfIsland -> " + q.maxAreaOfIsland(gridA));

        int[][] gridB = {{0, 0, 0}, {0, 0, 0}};
        System.out.println("Example B grid: " + Arrays.deepToString(gridB));
        System.out.println("maxAreaOfIsland -> " + q.maxAreaOfIsland(gridB));
    }

    private static void q008_SurroundedRegions() {
        header(Q008_SurroundedRegions.class);
        Q008_SurroundedRegions q = new Q008_SurroundedRegions();

        char[][] boardA = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        System.out.println("Example A board:");
        printGrid(boardA);
        q.solve(boardA);
        System.out.println("solve -> board mutated in place:");
        printGrid(boardA);

        char[][] boardB = {{'X'}};
        System.out.println("Example B board: " + Arrays.deepToString(boardB));
        q.solve(boardB);
        System.out.println("solve -> " + Arrays.deepToString(boardB));
    }

    private static void q009_PacificAtlanticWaterFlow() {
        header(Q009_PacificAtlanticWaterFlow.class);
        Q009_PacificAtlanticWaterFlow q = new Q009_PacificAtlanticWaterFlow();

        int[][] heightsA = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };
        System.out.println("Example A heights: " + Arrays.deepToString(heightsA));
        System.out.println("pacificAtlantic -> " + q.pacificAtlantic(heightsA));

        int[][] heightsB = {{1}};
        System.out.println("Example B heights: " + Arrays.deepToString(heightsB));
        System.out.println("pacificAtlantic -> " + q.pacificAtlantic(heightsB));
    }

    private static void q010_ZeroOneMatrix() {
        header(Q010_ZeroOneMatrix.class);
        Q010_ZeroOneMatrix q = new Q010_ZeroOneMatrix();

        int[][] matA = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println("Example A mat: " + Arrays.deepToString(matA));
        System.out.println("updateMatrix -> " + Arrays.deepToString(q.updateMatrix(matA)));

        int[][] matB = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        System.out.println("Example B mat: " + Arrays.deepToString(matB));
        System.out.println("updateMatrix -> " + Arrays.deepToString(q.updateMatrix(matB)));
    }

    private static void q011_WallsAndGates() {
        header(Q011_WallsAndGates.class);
        Q011_WallsAndGates q = new Q011_WallsAndGates();
        final int INF = Integer.MAX_VALUE;

        int[][] roomsA = {
                {INF, -1, 0, INF},
                {INF, INF, INF, -1},
                {INF, -1, INF, -1},
                {0, -1, INF, INF}
        };
        System.out.println("Example A rooms (INF=" + INF + "): " + Arrays.deepToString(roomsA));
        q.wallsAndGates(roomsA);
        System.out.println("wallsAndGates -> rooms mutated in place: " + Arrays.deepToString(roomsA));

        int[][] roomsB = {{-1}};
        System.out.println("Example B rooms: " + Arrays.deepToString(roomsB));
        q.wallsAndGates(roomsB);
        System.out.println("wallsAndGates -> " + Arrays.deepToString(roomsB));
    }

    // =========================================================================
    // Q012 - Clone Graph
    // =========================================================================

    private static void q012_CloneGraph() throws IOException {
        header(Q012_CloneGraph.class);
        Q012_CloneGraph q = new Q012_CloneGraph();

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
        System.out.println("Example A: adjList = [[2,4],[1,3],[2,4],[1,3]] (1-indexed 4-cycle), starting at node 1");
        GraphNode clonedA = q.cloneGraph(n1);
        System.out.println("cloneGraph -> " + (clonedA == null ? "null" : "cloned node with val=" + clonedA.val));
        // 0-indexed relabeling of the same 4-cycle, for the diagram.
        render(Q012_CloneGraph.class, "A", 4, new int[][]{{0, 1}, {0, 3}, {1, 2}, {2, 3}}, false, false);

        GraphNode single = new GraphNode(1);
        System.out.println("Example B: adjList = [[]] (single isolated node)");
        GraphNode clonedB = q.cloneGraph(single);
        System.out.println("cloneGraph -> " + (clonedB == null ? "null" : "cloned node with val=" + clonedB.val));
        render(Q012_CloneGraph.class, "B", 1, new int[0][], false, false);
    }

    // =========================================================================
    // Q013 / Q014 - cycle detection
    // =========================================================================

    private static void q013_DetectCycleInUndirectedGraph() throws IOException {
        header(Q013_SA_DetectCycleInUndirectedGraph.class);
        Q013_SA_DetectCycleInUndirectedGraph q = new Q013_SA_DetectCycleInUndirectedGraph();

        int vA = 4;
        int[][] edgesA = {{0, 1}, {1, 2}, {2, 3}, {3, 0}};
        System.out.println("Example A: V=" + vA + ", edges=" + Arrays.deepToString(edgesA));
        System.out.println("isCycle -> " + q.isCycle(vA, adj(vA, edgesA, false)));
        render(Q013_SA_DetectCycleInUndirectedGraph.class, "A", vA, edgesA, false, false);

        int vB = 4;
        int[][] edgesB = {{0, 1}, {1, 2}, {2, 3}};
        System.out.println("Example B: V=" + vB + ", edges=" + Arrays.deepToString(edgesB));
        System.out.println("isCycle -> " + q.isCycle(vB, adj(vB, edgesB, false)));
        render(Q013_SA_DetectCycleInUndirectedGraph.class, "B", vB, edgesB, false, false);
    }

    private static void q014_DetectCycleInDirectedGraph() throws IOException {
        header(Q014_SA_DetectCycleInDirectedGraph.class);
        Q014_SA_DetectCycleInDirectedGraph q = new Q014_SA_DetectCycleInDirectedGraph();

        int vA = 4;
        int[][] edgesA = {{0, 1}, {1, 2}, {2, 3}, {3, 1}};
        System.out.println("Example A: V=" + vA + ", edges=" + Arrays.deepToString(edgesA));
        System.out.println("isCyclic -> " + q.isCyclic(vA, adj(vA, edgesA, true)));
        render(Q014_SA_DetectCycleInDirectedGraph.class, "A", vA, edgesA, true, false);

        int vB = 4;
        int[][] edgesB = {{0, 1}, {1, 2}, {2, 3}};
        System.out.println("Example B: V=" + vB + ", edges=" + Arrays.deepToString(edgesB));
        System.out.println("isCyclic -> " + q.isCyclic(vB, adj(vB, edgesB, true)));
        render(Q014_SA_DetectCycleInDirectedGraph.class, "B", vB, edgesB, true, false);

        int vC = 4;
        int[][] edgesC = {{0, 1}, {0, 2}, {1, 3}, {2, 3}};
        System.out.println("Example C: V=" + vC + ", edges=" + Arrays.deepToString(edgesC)
                + " (diamond DAG - two paths converge on 3, no cycle)");
        System.out.println("isCyclic -> " + q.isCyclic(vC, adj(vC, edgesC, true)));
        render(Q014_SA_DetectCycleInDirectedGraph.class, "C", vC, edgesC, true, false);
    }

    // =========================================================================
    // Q015 / Q016 - Course Schedule I / II
    // =========================================================================

    private static void q015_CourseSchedule() throws IOException {
        header(Q015_CourseSchedule.class);
        Q015_CourseSchedule q = new Q015_CourseSchedule();

        int numCoursesA = 2;
        int[][] prereqA = {{1, 0}};
        System.out.println("Example A: numCourses=" + numCoursesA + ", prerequisites=" + Arrays.deepToString(prereqA));
        System.out.println("canFinish -> " + q.canFinish(numCoursesA, prereqA));
        render(Q015_CourseSchedule.class, "A", numCoursesA, new int[][]{{0, 1}}, true, false);

        int numCoursesB = 2;
        int[][] prereqB = {{1, 0}, {0, 1}};
        System.out.println("Example B: numCourses=" + numCoursesB + ", prerequisites=" + Arrays.deepToString(prereqB));
        System.out.println("canFinish -> " + q.canFinish(numCoursesB, prereqB));
        render(Q015_CourseSchedule.class, "B", numCoursesB, new int[][]{{0, 1}, {1, 0}}, true, false);
    }

    private static void q016_CourseScheduleII() throws IOException {
        header(Q016_CourseScheduleII.class);
        Q016_CourseScheduleII q = new Q016_CourseScheduleII();

        int numCoursesA = 4;
        int[][] prereqA = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println("Example A: numCourses=" + numCoursesA + ", prerequisites=" + Arrays.deepToString(prereqA));
        System.out.println("findOrder -> " + Arrays.toString(q.findOrder(numCoursesA, prereqA)));
        render(Q016_CourseScheduleII.class, "A", numCoursesA, new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}}, true, false);

        int numCoursesB = 2;
        int[][] prereqB = {{1, 0}, {0, 1}};
        System.out.println("Example B: numCourses=" + numCoursesB + ", prerequisites=" + Arrays.deepToString(prereqB));
        System.out.println("findOrder -> " + Arrays.toString(q.findOrder(numCoursesB, prereqB)));
        render(Q016_CourseScheduleII.class, "B", numCoursesB, new int[][]{{0, 1}, {1, 0}}, true, false);
    }

    // =========================================================================
    // Q017 / Q018 - Topological sort (Kahn's BFS / DFS)
    // =========================================================================

    private static void q017_TopologicalSortKahnsBFS() throws IOException {
        header(Q017_SA_TopologicalSortKahnsBFS.class);
        Q017_SA_TopologicalSortKahnsBFS q = new Q017_SA_TopologicalSortKahnsBFS();

        int vA = 6;
        int[][] edgesA = {{5, 0}, {5, 2}, {4, 0}, {4, 1}, {2, 3}, {3, 1}};
        System.out.println("Example A: V=" + vA + ", edges=" + Arrays.deepToString(edgesA));
        System.out.println("topoSort -> " + q.topoSort(vA, adj(vA, edgesA, true)));
        render(Q017_SA_TopologicalSortKahnsBFS.class, "A", vA, edgesA, true, false);

        int vB = 3;
        int[][] edgesB = {{0, 1}, {1, 2}};
        System.out.println("Example B: V=" + vB + ", edges=" + Arrays.deepToString(edgesB));
        System.out.println("topoSort -> " + q.topoSort(vB, adj(vB, edgesB, true)));
        render(Q017_SA_TopologicalSortKahnsBFS.class, "B", vB, edgesB, true, false);
    }

    private static void q018_TopologicalSortDFS() throws IOException {
        header(Q018_SA_TopologicalSortDFS.class);
        Q018_SA_TopologicalSortDFS q = new Q018_SA_TopologicalSortDFS();

        int vA = 6;
        int[][] edgesA = {{5, 0}, {5, 2}, {4, 0}, {4, 1}, {2, 3}, {3, 1}};
        System.out.println("Example A: V=" + vA + ", edges=" + Arrays.deepToString(edgesA));
        System.out.println("topoSort -> " + q.topoSort(vA, adj(vA, edgesA, true)));
        render(Q018_SA_TopologicalSortDFS.class, "A", vA, edgesA, true, false);

        int vB = 3;
        int[][] edgesB = {{0, 1}, {1, 2}};
        System.out.println("Example B: V=" + vB + ", edges=" + Arrays.deepToString(edgesB));
        System.out.println("topoSort -> " + q.topoSort(vB, adj(vB, edgesB, true)));
        render(Q018_SA_TopologicalSortDFS.class, "B", vB, edgesB, true, false);
    }

    // =========================================================================
    // Q019 - Is Graph Bipartite?
    // =========================================================================

    private static void q019_IsGraphBipartite() throws IOException {
        header(Q019_IsGraphBipartite.class);
        Q019_IsGraphBipartite q = new Q019_IsGraphBipartite();

        int[][] graphA = {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
        System.out.println("Example A: graph=" + Arrays.deepToString(graphA));
        System.out.println("isBipartite -> " + q.isBipartite(graphA));
        render(Q019_IsGraphBipartite.class, "A", 4, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}, {2, 3}}, false, false);

        int[][] graphB = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        System.out.println("Example B: graph=" + Arrays.deepToString(graphB));
        System.out.println("isBipartite -> " + q.isBipartite(graphB));
        render(Q019_IsGraphBipartite.class, "B", 4, new int[][]{{0, 1}, {0, 3}, {1, 2}, {2, 3}}, false, false);
    }

    // =========================================================================
    // Q020 - Number of Connected Components
    // =========================================================================

    private static void q020_NumberOfConnectedComponents() throws IOException {
        header(Q020_NumberOfConnectedComponents.class);
        Q020_NumberOfConnectedComponents q = new Q020_NumberOfConnectedComponents();

        int nA = 5;
        int[][] edgesA = {{0, 1}, {1, 2}, {3, 4}};
        System.out.println("Example A: n=" + nA + ", edges=" + Arrays.deepToString(edgesA));
        System.out.println("countComponents -> " + q.countComponents(nA, edgesA));
        render(Q020_NumberOfConnectedComponents.class, "A", nA, edgesA, false, false);

        int nB = 5;
        int[][] edgesB = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        System.out.println("Example B: n=" + nB + ", edges=" + Arrays.deepToString(edgesB));
        System.out.println("countComponents -> " + q.countComponents(nB, edgesB));
        render(Q020_NumberOfConnectedComponents.class, "B", nB, edgesB, false, false);
    }

    // =========================================================================
    // Q021 - Word Ladder (the implicit "differs by one letter" graph)
    // =========================================================================

    private static void q021_WordLadder() throws IOException {
        header(Q021_WordLadder.class);
        Q021_WordLadder q = new Q021_WordLadder();

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println("Example A: beginWord=" + beginWord + ", endWord=" + endWord + ", wordList=" + wordList);
        System.out.println("ladderLength -> " + q.ladderLength(beginWord, endWord, wordList));

        // Render the implicit transformation graph: an edge between any two words
        // (including beginWord) that differ by exactly one letter.
        Set<String> nodes = new LinkedHashSet<>();
        nodes.add(beginWord);
        nodes.addAll(wordList);
        List<String> nodeList = new ArrayList<>(nodes);
        List<String[]> edgeList = new ArrayList<>();
        for (int i = 0; i < nodeList.size(); i++) {
            for (int j = i + 1; j < nodeList.size(); j++) {
                if (oneLetterApart(nodeList.get(i), nodeList.get(j))) {
                    edgeList.add(new String[]{nodeList.get(i), nodeList.get(j)});
                }
            }
        }
        renderLabeled(Q021_WordLadder.class, "A", nodeList.toArray(new String[0]),
                edgeList.toArray(new String[0][]), false, false);

        List<String> wordListB = List.of("hot", "dot", "dog", "lot", "log");
        System.out.println("Example B: beginWord=" + beginWord + ", endWord=" + endWord + ", wordList=" + wordListB
                + " (endWord missing -> impossible)");
        System.out.println("ladderLength -> " + q.ladderLength(beginWord, endWord, wordListB));
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

    private static void q022_ShortestPathInBinaryMatrix() {
        header(Q022_ShortestPathInBinaryMatrix.class);
        Q022_ShortestPathInBinaryMatrix q = new Q022_ShortestPathInBinaryMatrix();

        int[][] gridA = {{0, 1}, {1, 0}};
        System.out.println("Example A grid: " + Arrays.deepToString(gridA));
        System.out.println("shortestPathBinaryMatrix -> " + q.shortestPathBinaryMatrix(gridA));

        int[][] gridB = {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        System.out.println("Example B grid: " + Arrays.deepToString(gridB));
        System.out.println("shortestPathBinaryMatrix -> " + q.shortestPathBinaryMatrix(gridB));

        int[][] gridC = {{1, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        System.out.println("Example C grid: " + Arrays.deepToString(gridC) + " (start cell blocked)");
        System.out.println("shortestPathBinaryMatrix -> " + q.shortestPathBinaryMatrix(gridC));
    }

    private static void q023_SnakeAndLadder() {
        header(Q023_SnakeAndLadder.class);
        Q023_SnakeAndLadder q = new Q023_SnakeAndLadder();

        int[][] boardA = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}
        };
        System.out.println("Example A board: " + Arrays.deepToString(boardA));
        System.out.println("snakesAndLadders -> " + q.snakesAndLadders(boardA));

        int[][] boardB = {{-1, -1}, {-1, 3}};
        System.out.println("Example B board: " + Arrays.deepToString(boardB));
        System.out.println("snakesAndLadders -> " + q.snakesAndLadders(boardB));
    }

    private static void q024_OpenTheLock() {
        header(Q024_OpenTheLock.class);
        Q024_OpenTheLock q = new Q024_OpenTheLock();

        String[] deadendsA = {"0201", "0101", "0102", "1212", "2002"};
        String targetA = "0202";
        System.out.println("Example A: deadends=" + Arrays.toString(deadendsA) + ", target=" + targetA);
        System.out.println("openLock -> " + q.openLock(deadendsA, targetA));

        String[] deadendsB = {"8888"};
        String targetB = "0009";
        System.out.println("Example B: deadends=" + Arrays.toString(deadendsB) + ", target=" + targetB);
        System.out.println("openLock -> " + q.openLock(deadendsB, targetB));
    }

    // =========================================================================
    // Q025-Q029 - shortest path algorithms
    // =========================================================================

    private static void q025_DijkstraShortestPath() throws IOException {
        header(Q025_SA_DijkstraShortestPath.class);
        Q025_SA_DijkstraShortestPath q = new Q025_SA_DijkstraShortestPath();

        int vA = 5;
        int[][] edgesA = {{0, 1, 4}, {0, 2, 8}, {1, 4, 6}, {2, 3, 2}, {3, 4, 10}};
        System.out.println("Example A: V=" + vA + ", edges=" + Arrays.deepToString(edgesA) + ", src=0");
        System.out.println("dijkstra -> " + Arrays.toString(q.dijkstra(vA, edgesA, 0)));
        render(Q025_SA_DijkstraShortestPath.class, "A", vA, edgesA, false, true);

        int vB = 3;
        int[][] edgesB = {{0, 1, 1}, {1, 2, 1}};
        System.out.println("Example B: V=" + vB + ", edges=" + Arrays.deepToString(edgesB) + ", src=0");
        System.out.println("dijkstra -> " + Arrays.toString(q.dijkstra(vB, edgesB, 0)));
        render(Q025_SA_DijkstraShortestPath.class, "B", vB, edgesB, false, true);
    }

    private static void q026_NetworkDelayTime() throws IOException {
        header(Q026_NetworkDelayTime.class);
        Q026_NetworkDelayTime q = new Q026_NetworkDelayTime();

        // Nodes are 1-indexed per the problem statement.
        int[][] timesA = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int nA = 4, kA = 2;
        System.out.println("Example A: times=" + Arrays.deepToString(timesA) + ", n=" + nA + ", k=" + kA);
        System.out.println("networkDelayTime -> " + q.networkDelayTime(timesA, nA, kA));
        // 0-indexed relabeling for the diagram.
        render(Q026_NetworkDelayTime.class, "A", nA, new int[][]{{1, 0, 1}, {1, 2, 1}, {2, 3, 1}}, true, true);

        int[][] timesB = {{1, 2, 1}};
        int nB = 2;
        System.out.println("Example B: times=" + Arrays.deepToString(timesB) + ", n=" + nB + ", k=1");
        System.out.println("networkDelayTime -> " + q.networkDelayTime(timesB, nB, 1));
        System.out.println("Example B (k=2, source unreachable to node 1): times=" + Arrays.deepToString(timesB));
        System.out.println("networkDelayTime -> " + q.networkDelayTime(timesB, nB, 2));
        render(Q026_NetworkDelayTime.class, "B", nB, new int[][]{{0, 1, 1}}, true, true);
    }

    private static void q027_BellmanFordShortestPath() throws IOException {
        header(Q027_SA_BellmanFordShortestPath.class);
        Q027_SA_BellmanFordShortestPath q = new Q027_SA_BellmanFordShortestPath();

        int vA = 5;
        int[][] edgesA = {{0, 1, 4}, {0, 2, 8}, {1, 4, 6}, {2, 3, 2}, {3, 4, -10}};
        System.out.println("Example A: V=" + vA + ", edges=" + Arrays.deepToString(edgesA) + ", src=0 (has a negative edge)");
        System.out.println("bellmanFord -> " + Arrays.toString(q.bellmanFord(vA, edgesA, 0)));
        render(Q027_SA_BellmanFordShortestPath.class, "A", vA, edgesA, true, true);

        int vB = 3;
        int[][] edgesB = {{0, 1, 1}, {1, 2, -1}, {2, 0, -1}};
        System.out.println("Example B: V=" + vB + ", edges=" + Arrays.deepToString(edgesB) + ", src=0 (contains a negative cycle)");
        System.out.println("bellmanFord -> " + Arrays.toString(q.bellmanFord(vB, edgesB, 0)));
        render(Q027_SA_BellmanFordShortestPath.class, "B", vB, edgesB, true, true);
    }

    private static void q028_CheapestFlightsWithinKStops() throws IOException {
        header(Q028_CheapestFlightsWithinKStops.class);
        Q028_CheapestFlightsWithinKStops q = new Q028_CheapestFlightsWithinKStops();

        int nA = 4;
        int[][] flightsA = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        System.out.println("Example A: n=" + nA + ", flights=" + Arrays.deepToString(flightsA) + ", src=0, dst=3, k=1");
        System.out.println("findCheapestPrice -> " + q.findCheapestPrice(nA, flightsA, 0, 3, 1));
        render(Q028_CheapestFlightsWithinKStops.class, "A", nA, flightsA, true, true);

        int nB = 3;
        int[][] flightsB = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        System.out.println("Example B: n=" + nB + ", flights=" + Arrays.deepToString(flightsB) + ", src=0, dst=2, k=1");
        System.out.println("findCheapestPrice -> " + q.findCheapestPrice(nB, flightsB, 0, 2, 1));
        render(Q028_CheapestFlightsWithinKStops.class, "B", nB, flightsB, true, true);
    }

    private static void q029_FloydWarshallAllPairsShortestPath() throws IOException {
        header(Q029_SA_FloydWarshallAllPairsShortestPath.class);
        Q029_SA_FloydWarshallAllPairsShortestPath q = new Q029_SA_FloydWarshallAllPairsShortestPath();
        final int INF = 1_000_000;

        int[][] dist = {
                {0, 3, INF, 7},
                {8, 0, 2, INF},
                {5, INF, 0, 1},
                {2, INF, INF, 0}
        };
        System.out.println("Example A: dist (INF=" + INF + ") = " + Arrays.deepToString(dist));
        q.floydWarshall(dist);
        System.out.println("floydWarshall -> dist mutated in place: " + Arrays.deepToString(dist));

        int[][] edges = {{0, 1, 3}, {0, 3, 7}, {1, 0, 8}, {1, 2, 2}, {2, 0, 5}, {2, 3, 1}, {3, 0, 2}};
        render(Q029_SA_FloydWarshallAllPairsShortestPath.class, null, 4, edges, true, true);
    }

    // =========================================================================
    // Q030 / Q031 - grid questions (no Graphviz diagram)
    // =========================================================================

    private static void q030_PathWithMinimumEffort() {
        header(Q030_PathWithMinimumEffort.class);
        Q030_PathWithMinimumEffort q = new Q030_PathWithMinimumEffort();

        int[][] heightsA = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        System.out.println("Example A heights: " + Arrays.deepToString(heightsA));
        System.out.println("minimumEffortPath -> " + q.minimumEffortPath(heightsA));

        int[][] heightsB = {{1, 2, 3}, {3, 8, 4}, {5, 3, 5}};
        System.out.println("Example B heights: " + Arrays.deepToString(heightsB));
        System.out.println("minimumEffortPath -> " + q.minimumEffortPath(heightsB));
    }

    private static void q031_SwimInRisingWater() {
        header(Q031_SwimInRisingWater.class);
        Q031_SwimInRisingWater q = new Q031_SwimInRisingWater();

        int[][] gridA = {{0, 2}, {1, 3}};
        System.out.println("Example A grid: " + Arrays.deepToString(gridA));
        System.out.println("swimInWater -> " + q.swimInWater(gridA));

        int[][] gridB = {
                {0, 1, 2, 3, 4},
                {24, 23, 22, 21, 5},
                {12, 13, 14, 15, 16},
                {11, 17, 18, 19, 20},
                {10, 9, 8, 7, 6}
        };
        System.out.println("Example B grid: " + Arrays.deepToString(gridB));
        System.out.println("swimInWater -> " + q.swimInWater(gridB));
    }

    // =========================================================================
    // Q032 - Disjoint Set Union
    // =========================================================================

    private static void q032_DisjointSetUnion() {
        header(Q032_SA_DisjointSetUnion.class);
        Q032_SA_DisjointSetUnion dsu = new Q032_SA_DisjointSetUnion(6);
        System.out.println("n=6, elements 0..5, initially each its own set");
        dsu.union(0, 1);
        System.out.println("union(0,1)");
        dsu.union(2, 3);
        System.out.println("union(2,3)");
        dsu.union(1, 3);
        System.out.println("union(1,3)");
        System.out.println("connected(0,3) -> " + dsu.connected(0, 3));
        System.out.println("connected(0,4) -> " + dsu.connected(0, 4));
    }

    // =========================================================================
    // Q033 - Redundant Connection
    // =========================================================================

    private static void q033_RedundantConnection() throws IOException {
        header(Q033_RedundantConnection.class);
        Q033_RedundantConnection q = new Q033_RedundantConnection();

        // Edges are 1-indexed per the problem statement.
        int[][] edgesA = {{1, 2}, {1, 3}, {2, 3}};
        System.out.println("Example A: edges=" + Arrays.deepToString(edgesA) + " (1-indexed)");
        System.out.println("findRedundantConnection -> " + Arrays.toString(q.findRedundantConnection(edgesA)));
        // 0-indexed relabeling for the diagram.
        render(Q033_RedundantConnection.class, "A", 3, new int[][]{{0, 1}, {0, 2}, {1, 2}}, false, false);

        int[][] edgesB = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        System.out.println("Example B: edges=" + Arrays.deepToString(edgesB) + " (1-indexed)");
        System.out.println("findRedundantConnection -> " + Arrays.toString(q.findRedundantConnection(edgesB)));
        render(Q033_RedundantConnection.class, "B", 5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {0, 3}, {0, 4}}, false, false);
    }

    // =========================================================================
    // Q034 - Accounts Merge
    // =========================================================================

    private static void q034_AccountsMerge() {
        header(Q034_AccountsMerge.class);
        Q034_AccountsMerge q = new Q034_AccountsMerge();

        List<List<String>> accounts = List.of(
                List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                List.of("John", "johnsmith@mail.com", "john00@mail.com"),
                List.of("Mary", "mary@mail.com"),
                List.of("John", "johnnybravo@mail.com")
        );
        System.out.println("Example A: accounts=" + accounts);
        System.out.println("accountsMerge -> " + q.accountsMerge(accounts));
    }

    // =========================================================================
    // Q035 - Graph Valid Tree
    // =========================================================================

    private static void q035_GraphValidTree() throws IOException {
        header(Q035_GraphValidTree.class);
        Q035_GraphValidTree q = new Q035_GraphValidTree();

        int nA = 5;
        int[][] edgesA = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        System.out.println("Example A: n=" + nA + ", edges=" + Arrays.deepToString(edgesA));
        System.out.println("validTree -> " + q.validTree(nA, edgesA));
        render(Q035_GraphValidTree.class, "A", nA, edgesA, false, false);

        int nB = 5;
        int[][] edgesB = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        System.out.println("Example B: n=" + nB + ", edges=" + Arrays.deepToString(edgesB));
        System.out.println("validTree -> " + q.validTree(nB, edgesB));
        render(Q035_GraphValidTree.class, "B", nB, edgesB, false, false);
    }

    // =========================================================================
    // Q036 / Q037 - MST (Prim's / Kruskal's)
    // =========================================================================

    private static void q036_PrimsMST() throws IOException {
        header(Q036_SA_PrimsMST.class);
        Q036_SA_PrimsMST q = new Q036_SA_PrimsMST();

        int vA = 5;
        int[][] edgesA = {{0, 1, 2}, {0, 3, 6}, {1, 2, 3}, {1, 3, 8}, {1, 4, 5}, {2, 4, 7}, {3, 4, 9}};
        System.out.println("Example A: V=" + vA + ", edges=" + Arrays.deepToString(edgesA));
        System.out.println("spanningTree -> " + q.spanningTree(vA, edgesA));
        render(Q036_SA_PrimsMST.class, "A", vA, edgesA, false, true);

        int vB = 3;
        int[][] edgesB = {{0, 1, 1}, {1, 2, 1}, {0, 2, 5}};
        System.out.println("Example B: V=" + vB + ", edges=" + Arrays.deepToString(edgesB));
        System.out.println("spanningTree -> " + q.spanningTree(vB, edgesB));
        render(Q036_SA_PrimsMST.class, "B", vB, edgesB, false, true);
    }

    private static void q037_KruskalsMST() throws IOException {
        header(Q037_SA_KruskalsMST.class);
        Q037_SA_KruskalsMST q = new Q037_SA_KruskalsMST();

        int vA = 5;
        int[][] edgesA = {{0, 1, 2}, {0, 3, 6}, {1, 2, 3}, {1, 3, 8}, {1, 4, 5}, {2, 4, 7}, {3, 4, 9}};
        System.out.println("Example A: V=" + vA + ", edges=" + Arrays.deepToString(edgesA) + " (same graph as Q036, other algorithm)");
        System.out.println("spanningTree -> " + q.spanningTree(vA, edgesA));
        render(Q037_SA_KruskalsMST.class, "A", vA, edgesA, false, true);

        int vB = 3;
        int[][] edgesB = {{0, 1, 1}, {1, 2, 1}, {0, 2, 5}};
        System.out.println("Example B: V=" + vB + ", edges=" + Arrays.deepToString(edgesB));
        System.out.println("spanningTree -> " + q.spanningTree(vB, edgesB));
        render(Q037_SA_KruskalsMST.class, "B", vB, edgesB, false, true);
    }

    // =========================================================================
    // Q038 / Q039 / Q040 - bridges, articulation points, Kosaraju's SCC
    // =========================================================================

    private static void q038_CriticalConnectionsBridges() throws IOException {
        header(Q038_SA_CriticalConnectionsBridges.class);
        Q038_SA_CriticalConnectionsBridges q = new Q038_SA_CriticalConnectionsBridges();

        int nA = 4;
        int[][] edgesA = {{0, 1}, {1, 2}, {2, 0}, {1, 3}};
        System.out.println("Example A: n=" + nA + ", connections=" + Arrays.deepToString(edgesA));
        System.out.println("criticalConnections -> " + q.criticalConnections(nA, edgeList(edgesA)));
        render(Q038_SA_CriticalConnectionsBridges.class, "A", nA, edgesA, false, false);

        int nB = 2;
        int[][] edgesB = {{0, 1}};
        System.out.println("Example B: n=" + nB + ", connections=" + Arrays.deepToString(edgesB));
        System.out.println("criticalConnections -> " + q.criticalConnections(nB, edgeList(edgesB)));
        render(Q038_SA_CriticalConnectionsBridges.class, "B", nB, edgesB, false, false);
    }

    private static void q039_ArticulationPoints() throws IOException {
        header(Q039_SA_ArticulationPoints.class);
        Q039_SA_ArticulationPoints q = new Q039_SA_ArticulationPoints();

        int vA = 5;
        int[][] edgesA = {{0, 1}, {0, 2}, {1, 2}, {2, 3}, {3, 4}};
        System.out.println("Example A: V=" + vA + ", edges=" + Arrays.deepToString(edgesA));
        System.out.println("articulationPoints -> " + q.articulationPoints(vA, adj(vA, edgesA, false)));
        render(Q039_SA_ArticulationPoints.class, "A", vA, edgesA, false, false);

        int vB = 4;
        int[][] edgesB = {{0, 1}, {1, 2}, {2, 3}};
        System.out.println("Example B: V=" + vB + ", edges=" + Arrays.deepToString(edgesB));
        System.out.println("articulationPoints -> " + q.articulationPoints(vB, adj(vB, edgesB, false)));
        render(Q039_SA_ArticulationPoints.class, "B", vB, edgesB, false, false);
    }

    private static void q040_KosarajusSCC() throws IOException {
        header(Q040_SA_KosarajusSCC.class);
        Q040_SA_KosarajusSCC q = new Q040_SA_KosarajusSCC();

        int vA = 5;
        int[][] edgesA = {{0, 1}, {1, 2}, {1, 4}, {2, 0}, {4, 3}};
        System.out.println("Example A: V=" + vA + ", edges=" + Arrays.deepToString(edgesA));
        System.out.println("stronglyConnectedComponents -> " + q.stronglyConnectedComponents(vA, adj(vA, edgesA, true)));
        render(Q040_SA_KosarajusSCC.class, "A", vA, edgesA, true, false);

        int vB = 3;
        int[][] edgesB = {{0, 1}, {1, 2}, {2, 0}};
        System.out.println("Example B: V=" + vB + ", edges=" + Arrays.deepToString(edgesB));
        System.out.println("stronglyConnectedComponents -> " + q.stronglyConnectedComponents(vB, adj(vB, edgesB, true)));
        render(Q040_SA_KosarajusSCC.class, "B", vB, edgesB, true, false);
    }

    // =========================================================================
    // Q041 - Evaluate Division (weighted, string-labeled directed graph)
    // =========================================================================

    private static void q041_EvaluateDivision() throws IOException {
        header(Q041_EvaluateDivision.class);
        Q041_EvaluateDivision q = new Q041_EvaluateDivision();

        List<List<String>> equationsA = List.of(List.of("a", "b"), List.of("b", "c"));
        double[] valuesA = {2.0, 3.0};
        List<List<String>> queriesA = List.of(
                List.of("a", "c"), List.of("b", "a"), List.of("a", "e"), List.of("a", "a"), List.of("x", "x"));
        System.out.println("Example A: equations=" + equationsA + ", values=" + Arrays.toString(valuesA) + ", queries=" + queriesA);
        System.out.println("calcEquation -> " + Arrays.toString(q.calcEquation(equationsA, valuesA, queriesA)));
        renderLabeled(Q041_EvaluateDivision.class, "A", new String[]{"a", "b", "c"},
                new String[][]{{"a", "b", "2.0"}, {"b", "a", "0.5"}, {"b", "c", "3.0"}, {"c", "b", "0.33"}},
                true, true);

        List<List<String>> equationsB = List.of(List.of("a", "b"), List.of("b", "c"), List.of("bc", "cd"));
        double[] valuesB = {1.5, 2.5, 5.0};
        List<List<String>> queriesB = List.of(
                List.of("a", "c"), List.of("c", "b"), List.of("bc", "cd"), List.of("cd", "bc"));
        System.out.println("Example B: equations=" + equationsB + ", values=" + Arrays.toString(valuesB) + ", queries=" + queriesB);
        System.out.println("calcEquation -> " + Arrays.toString(q.calcEquation(equationsB, valuesB, queriesB)));
        renderLabeled(Q041_EvaluateDivision.class, "B", new String[]{"a", "b", "c", "bc", "cd"},
                new String[][]{{"a", "b", "1.5"}, {"b", "a", "0.67"}, {"b", "c", "2.5"}, {"c", "b", "0.4"},
                        {"bc", "cd", "5.0"}, {"cd", "bc", "0.2"}},
                true, true);
    }

    // =========================================================================
    // Q042 - Reconstruct Itinerary (string-labeled directed graph)
    // =========================================================================

    private static void q042_ReconstructItinerary() throws IOException {
        header(Q042_ReconstructItinerary.class);
        Q042_ReconstructItinerary q = new Q042_ReconstructItinerary();

        List<List<String>> ticketsA = List.of(
                List.of("MUC", "LHR"), List.of("JFK", "MUC"), List.of("SFO", "SJC"), List.of("LHR", "SFO"));
        System.out.println("Example A: tickets=" + ticketsA);
        System.out.println("findItinerary -> " + q.findItinerary(ticketsA));
        renderLabeled(Q042_ReconstructItinerary.class, "A",
                new String[]{"MUC", "LHR", "JFK", "SFO", "SJC"},
                new String[][]{{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}}, true, false);

        List<List<String>> ticketsB = List.of(
                List.of("JFK", "SFO"), List.of("JFK", "ATL"), List.of("SFO", "ATL"),
                List.of("ATL", "JFK"), List.of("ATL", "SFO"));
        System.out.println("Example B: tickets=" + ticketsB);
        System.out.println("findItinerary -> " + q.findItinerary(ticketsB));
        renderLabeled(Q042_ReconstructItinerary.class, "B",
                new String[]{"JFK", "SFO", "ATL"},
                new String[][]{{"JFK", "SFO"}, {"JFK", "ATL"}, {"SFO", "ATL"}, {"ATL", "JFK"}, {"ATL", "SFO"}},
                true, false);
    }

    // =========================================================================
    // Q043 - Alien Dictionary (string-labeled directed graph over single letters)
    // =========================================================================

    private static void q043_AlienDictionary() throws IOException {
        header(Q043_AlienDictionary.class);
        Q043_AlienDictionary q = new Q043_AlienDictionary();

        String[] wordsA = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println("Example A: words=" + Arrays.toString(wordsA));
        System.out.println("alienOrder -> " + q.alienOrder(wordsA));
        renderLabeled(Q043_AlienDictionary.class, "A", new String[]{"w", "e", "r", "t", "f"},
                new String[][]{{"w", "e"}, {"e", "r"}, {"r", "t"}, {"t", "f"}}, true, false);

        String[] wordsB = {"z", "x"};
        System.out.println("Example B: words=" + Arrays.toString(wordsB));
        System.out.println("alienOrder -> " + q.alienOrder(wordsB));
        renderLabeled(Q043_AlienDictionary.class, "B", new String[]{"z", "x"},
                new String[][]{{"z", "x"}}, true, false);

        String[] wordsC = {"abc", "ab"};
        System.out.println("Example C (invalid - longer word precedes its own prefix): words=" + Arrays.toString(wordsC));
        System.out.println("alienOrder -> \"" + q.alienOrder(wordsC) + "\"");
    }

    // =========================================================================
    // Q044 - Minimum Height Trees
    // =========================================================================

    private static void q044_MinimumHeightTrees() throws IOException {
        header(Q044_MinimumHeightTrees.class);
        Q044_MinimumHeightTrees q = new Q044_MinimumHeightTrees();

        int nA = 4;
        int[][] edgesA = {{1, 0}, {1, 2}, {1, 3}};
        System.out.println("Example A: n=" + nA + ", edges=" + Arrays.deepToString(edgesA));
        System.out.println("findMinHeightTrees -> " + q.findMinHeightTrees(nA, edgesA));
        render(Q044_MinimumHeightTrees.class, "A", nA, edgesA, false, false);

        int nB = 6;
        int[][] edgesB = {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
        System.out.println("Example B: n=" + nB + ", edges=" + Arrays.deepToString(edgesB));
        System.out.println("findMinHeightTrees -> " + q.findMinHeightTrees(nB, edgesB));
        render(Q044_MinimumHeightTrees.class, "B", nB, edgesB, false, false);
    }

    // =========================================================================
    // Q045 - All Paths From Source to Target
    // =========================================================================

    private static void q045_AllPathsFromSourceToTarget() throws IOException {
        header(Q045_AllPathsFromSourceToTarget.class);
        Q045_AllPathsFromSourceToTarget q = new Q045_AllPathsFromSourceToTarget();

        int[][] graphA = {{1, 2}, {3}, {3}, {}};
        System.out.println("Example A: graph=" + Arrays.deepToString(graphA));
        System.out.println("allPathsSourceTarget -> " + q.allPathsSourceTarget(graphA));
        render(Q045_AllPathsFromSourceToTarget.class, "A", graphA.length, toEdgesFromAdjArray(graphA), true, false);

        int[][] graphB = {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
        System.out.println("Example B: graph=" + Arrays.deepToString(graphB));
        System.out.println("allPathsSourceTarget -> " + q.allPathsSourceTarget(graphB));
        render(Q045_AllPathsFromSourceToTarget.class, "B", graphB.length, toEdgesFromAdjArray(graphB), true, false);
    }

    // =========================================================================
    // Q046 - Max Flow (Ford-Fulkerson / Edmonds-Karp)
    // =========================================================================

    private static void q046_MaxFlowFordFulkerson() throws IOException {
        header(Q046_SA_MaxFlowFordFulkerson.class);
        Q046_SA_MaxFlowFordFulkerson q = new Q046_SA_MaxFlowFordFulkerson();

        int nA = 6;
        int[][] edgesA = {{0, 1, 16}, {0, 2, 13}, {1, 2, 10}, {2, 1, 4}, {1, 3, 12},
                {3, 2, 9}, {2, 4, 14}, {4, 3, 7}, {3, 5, 20}, {4, 5, 4}};
        System.out.println("Example A (classic textbook network): n=" + nA + ", edges=" + Arrays.deepToString(edgesA)
                + ", source=0, sink=5");
        System.out.println("maxFlow -> " + q.maxFlow(nA, edgesA, 0, 5));
        render(Q046_SA_MaxFlowFordFulkerson.class, "A", nA, edgesA, true, true);

        int nB = 4;
        int[][] edgesB = {{0, 1, 3}, {0, 2, 2}, {1, 3, 2}, {2, 3, 3}};
        System.out.println("Example B: n=" + nB + ", edges=" + Arrays.deepToString(edgesB) + ", source=0, sink=3");
        System.out.println("maxFlow -> " + q.maxFlow(nB, edgesB, 0, 3));
        render(Q046_SA_MaxFlowFordFulkerson.class, "B", nB, edgesB, true, true);
    }

    // =========================================================================
    // Q047 - Strongly Connected Components (Tarjan's)
    // =========================================================================

    private static void q047_TarjansSCC() throws IOException {
        header(Q047_SA_TarjansSCC.class);
        Q047_SA_TarjansSCC q = new Q047_SA_TarjansSCC();

        int vA = 5;
        int[][] edgesA = {{0, 1}, {1, 2}, {1, 4}, {2, 0}, {4, 3}};
        System.out.println("Example A: V=" + vA + ", edges=" + Arrays.deepToString(edgesA) + " (same graph as Q040)");
        System.out.println("stronglyConnectedComponents -> " + q.stronglyConnectedComponents(vA, adj(vA, edgesA, true)));
        render(Q047_SA_TarjansSCC.class, "A", vA, edgesA, true, false);

        int vB = 3;
        int[][] edgesB = {{0, 1}, {1, 2}, {2, 0}};
        System.out.println("Example B: V=" + vB + ", edges=" + Arrays.deepToString(edgesB));
        System.out.println("stronglyConnectedComponents -> " + q.stronglyConnectedComponents(vB, adj(vB, edgesB, true)));
        render(Q047_SA_TarjansSCC.class, "B", vB, edgesB, true, false);
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
}
