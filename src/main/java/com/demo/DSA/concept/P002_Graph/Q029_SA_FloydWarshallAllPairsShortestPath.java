package com.demo.DSA.concept.P002_Graph;

/**
 * Q029. Floyd Warshall Algorithm - All Pairs Shortest Path
 * https://www.geeksforgeeks.org/problems/implementing-floyd-warshall2042/1
 * <p>
 * Given a directed weighted graph as an n x n adjacency matrix dist,
 * where dist[i][j] is the edge weight from i to j (a large sentinel
 * value, e.g. a very large int, if no direct edge exists, and dist[i][i]
 * = 0), update the matrix in place so that dist[i][j] becomes the
 * shortest distance between every pair of vertices i and j.
 *
 * <pre>
 * Example 1 (INF = a large sentinel):
 * Input:
 * dist = [
 *   [0,   3,   INF, 7],
 *   [8,   0,   2,   INF],
 *   [5,   INF, 0,   1],
 *   [2,   INF, INF, 0]
 * ]
 * Output:
 * [
 *   [0, 3, 5, 6],
 *   [5, 0, 2, 3],
 *   [3, 6, 0, 1],
 *   [2, 5, 7, 0]
 * ]
 * Explanation: e.g. dist[0][2] improves from INF (no direct edge) to 5
 * via 0-&gt;1-&gt;2 (3+2).
 *
 * Constraints:
 * - 1 &lt;= n &lt;= 400 (Floyd-Warshall is O(n^3), so n is kept small)
 * - Edge weights may be negative, but there is no negative cycle.
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * Forget picking one source - compute every pair's shortest distance at
 * once by asking "does routing through vertex k help?" for every k.
 * Start from the direct-edge distance matrix. Then, for each vertex k in
 * turn (k must be the <strong>outermost</strong> loop), check every pair
 * (i, j): is going i &rarr; k &rarr; j cheaper than the best known
 * i &rarr; j found so far? If so, update it:
 * <code>dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])</code>.
 * After k has ranged over every vertex, every cell holds the true
 * shortest distance, because every possible relay point has been
 * considered.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://cp-algorithms.com/graph/all-pair-shortest-path-floyd-warshall.html">cp-algorithms.com &mdash; Floyd-Warshall Algorithm</a>
 *   - explains why k must be the outer loop (a subtle but critical
 *   correctness detail) with a clear proof sketch.</li>
 *   <li><a href="https://visualgo.net/en/apsp">VisuAlgo.net &mdash; All-Pairs Shortest Paths</a>
 *   - watches the distance matrix update cell by cell as each intermediate vertex k is considered.</li>
 * </ul>
 */
public class Q029_SA_FloydWarshallAllPairsShortestPath {

    /**
     * @implNote TODO: implement.
     * Target approach: Triple-nested loop over an intermediate vertex k,
     * then source i, then destination j (k must be the OUTERMOST loop) -
     * for each (i, j), check whether routing through k is shorter:
     * dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]). After k has
     * ranged over all vertices, dist[i][j] holds the true shortest
     * distance, since every possible intermediate vertex has been
     * considered as a potential relay.
     * <p>
     * Target Time Complexity: O(V^3).
     * <br>
     * Target Space Complexity: O(1) extra - updates the matrix in place
     * (O(V^2) for the matrix itself, which is given).
     */
    public void floydWarshall(int[][] dist) {
        // TODO: implement
    }
}
