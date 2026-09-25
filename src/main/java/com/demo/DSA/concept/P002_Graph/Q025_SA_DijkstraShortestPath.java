package com.demo.DSA.concept.P002_Graph;

/**
 * Q025. Dijkstra's Algorithm - Shortest Path
 * https://www.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1
 * <p>
 * Given a weighted undirected graph with V vertices (0 to V-1) as an
 * adjacency list (each entry {neighbor, weight}) and a source vertex, all
 * edge weights non-negative, return the shortest distance from source to
 * every vertex.
 *
 * <pre>
 * Example 1:
 * Input: V = 5, edges = [[0,1,4],[0,2,8],[1,4,6],[2,3,2],[3,4,10]],
 *        src = 0
 * Output: [0, 4, 8, 10, 10]
 * Explanation: 0-&gt;1 costs 4; 0-&gt;2 costs 8; 0-&gt;2-&gt;3 costs 10; 0-&gt;1-&gt;4
 * costs 10 (cheaper than 0-&gt;2-&gt;3-&gt;4's 20).
 *
 * Example 2:
 * Input: V = 3, edges = [[0,1,1],[1,2,1]], src = 0
 * Output: [0, 1, 2]
 *
 * Constraints:
 * - 1 &lt;= V &lt;= 10^4
 * - All edge weights are non-negative (Dijkstra doesn't handle negative
 *   weights correctly - see {@link Q027_SA_BellmanFordShortestPath} for
 *   that case).
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * Always finalize whichever un-finalized vertex is currently closest -
 * with non-negative weights, that greedy choice is never wrong. Keep a
 * <code>dist[]</code> array (infinity everywhere except the source,
 * which is 0) and a min-heap of <code>(distance, vertex)</code> pairs,
 * seeded with <code>(0, source)</code>. Repeatedly pop the smallest
 * entry. If it is <strong>stale</strong> - the popped distance is larger
 * than what is already recorded in <code>dist[]</code> - skip it (a
 * cheaper route was already found and processed). Otherwise,
 * <strong>relax</strong> every outgoing edge: if
 * <code>dist[u] + w &lt; dist[v]</code>, update <code>dist[v]</code> and
 * push the improved pair.
 * <p>
 * The reason non-negative weights matter: once a vertex is popped with
 * its true shortest distance, no path discovered later can possibly be
 * shorter, since every future path can only add more (non-negative)
 * distance. Negative edges break that guarantee entirely - that is
 * exactly why Bellman-Ford exists.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://cp-algorithms.com/graph/dijkstra.html">cp-algorithms.com &mdash; Dijkstra Algorithm</a>
 *   - shows both the O(V&sup2;) array version and the heap-optimized
 *   version, plus exactly why negative weights break the correctness
 *   proof.</li>
 *   <li><a href="https://visualgo.net/en/sssp">VisuAlgo.net &mdash; Single-Source Shortest Paths</a>
 *   - animates the min-heap pops and edge relaxations one at a time on a graph you control.</li>
 * </ul>
 */
public class Q025_SA_DijkstraShortestPath {

    /**
     * @implNote TODO: implement.
     * Target approach: Initialize dist[] to infinity except dist[src]=0.
     * Use a min-heap (PriorityQueue) of {distance, vertex} pairs, seeded
     * with {0, src}. Repeatedly pop the smallest-distance entry; if it's
     * stale (popped distance &gt; current dist[vertex]), skip it. Otherwise
     * relax every outgoing edge - if dist[vertex] + weight &lt; dist[neighbor],
     * update dist[neighbor] and push {newDist, neighbor}.
     * <p>
     * Target Time Complexity: O((V + E) log V) with a binary heap.
     * <br>
     * Target Space Complexity: O(V + E) - adjacency list, dist array, and
     * heap.
     */
    public int[] dijkstra(int V, int[][] edges, int src) {
        // TODO: implement
        return new int[V];
    }
}
