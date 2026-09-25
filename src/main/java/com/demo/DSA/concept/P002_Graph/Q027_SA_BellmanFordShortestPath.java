package com.demo.DSA.concept.P002_Graph;

/**
 * Q027. Bellman-Ford Algorithm - Shortest Path with Negative Weights
 * https://www.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm2726/1
 * <p>
 * Given a directed weighted graph with V vertices (0 to V-1) as an edge
 * list (edges[i] = [u, v, w]), which may include negative weights but is
 * guaranteed to have no negative-weight cycle reachable from the source,
 * return the shortest distance from src to every vertex. If a graph
 * DOES contain a reachable negative cycle, that should be detected (e.g.
 * return an array of [-1] as a sentinel, or throw, per the variant
 * you're implementing).
 *
 * <pre>
 * Example 1:
 * Input: V = 5, edges = [[0,1,4],[0,2,8],[1,4,6],[2,3,2],[3,4,-10]],
 *        src = 0
 * Output: [0, 4, 8, 10, 0]
 * Explanation: 0-&gt;2-&gt;3-&gt;4 costs 8 + 2 + (-10) = 0, cheaper than 0-&gt;1-&gt;4's
 * 10 - this negative edge is exactly what Dijkstra would get wrong.
 *
 * Example 2 (negative cycle):
 * Input: V = 3, edges = [[0,1,1],[1,2,-1],[2,0,-1]], src = 0
 * Output: cycle detected (0-&gt;1-&gt;2-&gt;0 sums to -1, so distances can be
 * driven arbitrarily low - no well-defined shortest path exists).
 *
 * Constraints:
 * - 1 &lt;= V &lt;= 10^4
 * - Edge weights may be negative.
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * Skip Dijkstra's greedy shortcut entirely - just relax every edge, V-1
 * times. Slower, but correct even with negative weights. Any shortest
 * path in a graph with V vertices uses at most V-1 edges (a simple path
 * cannot revisit a vertex). So: relax <em>every</em> edge in the graph,
 * and repeat that full pass V-1 times - enough rounds to guarantee the
 * longest possible shortest path has been fully propagated. Run one more
 * (the V-th) pass afterward: if any edge can still be relaxed, that means
 * a <strong>negative cycle</strong> reachable from the source exists, and
 * shortest distances are not well-defined (looping the cycle forever
 * drives distance to -&infin;).
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://cp-algorithms.com/graph/bellman_ford.html">cp-algorithms.com &mdash; Bellman-Ford Algorithm</a>
 *   - includes the negative-cycle detection extension and the SPFA
 *   (queue-based) speed-up in the same place.</li>
 *   <li><a href="https://visualgo.net/en/sssp">VisuAlgo.net &mdash; Single-Source Shortest Paths</a>
 *   - same module as Dijkstra, switched to Bellman-Ford mode; watch a round of relaxation converge live.</li>
 * </ul>
 */
public class Q027_SA_BellmanFordShortestPath {

    /**
     * @implNote TODO: implement.
     * Target approach: Initialize dist[] to infinity except dist[src]=0.
     * Relax every edge (u, v, w) - i.e. if dist[u] + w &lt; dist[v], update
     * dist[v] - across all E edges, repeated V-1 times (a shortest path
     * in a graph with V vertices has at most V-1 edges, so V-1 rounds
     * guarantee convergence if no negative cycle exists). Run one more
     * (a Vth) relaxation pass afterward - if ANY edge can still be
     * relaxed, a negative cycle reachable from src exists.
     * <p>
     * Target Time Complexity: O(V * E) - V-1 (plus one check) rounds,
     * each scanning all E edges.
     * <br>
     * Target Space Complexity: O(V) - the dist array.
     */
    public int[] bellmanFord(int V, int[][] edges, int src) {
        // TODO: implement
        return new int[V];
    }
}
