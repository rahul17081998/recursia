package com.demo.DSA.concept.P002_Graph;

import java.util.List;

/**
 * Q040. Strongly Connected Components - Kosaraju's Algorithm
 * https://www.geeksforgeeks.org/problems/strongly-connected-components-kosarajus-algo/1
 * <p>
 * Given a directed graph with V vertices (0 to V-1) as an adjacency list,
 * return the number of Strongly Connected Components (SCCs) - maximal
 * groups of vertices where every vertex can reach every other vertex in
 * the same group via directed edges.
 *
 * <pre>
 * Example 1:
 * Input: V = 5, adj = [[1],[2,4],[0],[],[3]]
 * (edges: 0-&gt;1, 1-&gt;2, 1-&gt;4, 2-&gt;0, 4-&gt;3)
 * Output: 3
 * Explanation: {0,1,2} form a cycle (SCC), {3} and {4} are each singleton
 * SCCs (4-&gt;3 has no way back to 4).
 *
 * Example 2:
 * Input: V = 3, adj = [[1],[2],[0]]
 * Output: 1
 * Explanation: The whole graph is one cycle, hence one SCC.
 *
 * Constraints:
 * - 1 &lt;= V &lt;= 10^5
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * DFS twice: once to learn a safe order, once on the reversed graph in
 * that order - each launch on the second pass sweeps out exactly one
 * SCC. (1) DFS the original graph, pushing each vertex onto a stack in
 * <strong>finish order</strong> - the same post-order trick as DFS-based
 * topological sort. (2) Build the <strong>transpose</strong> graph
 * (every edge reversed). (3) Pop the stack one vertex at a time; for
 * every vertex not yet visited, DFS on the <em>transpose</em> graph from
 * it. Everything reached during one such launch is exactly one strongly
 * connected component - processing in decreasing finish-time order
 * guarantees a launch can never "leak" into an already-finished SCC.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://cp-algorithms.com/graph/strongly-connected-components.html">cp-algorithms.com &mdash; Strongly Connected Components and Condensation Graph</a>
 *   - also covers the "condensation graph" (collapsing each SCC to one
 *   vertex), a natural next step once you can find SCCs.</li>
 *   <li>YouTube &mdash; search "William Fiset Kosaraju's algorithm" (Graph Theory playlist)
 *   - animated dry run of both DFS passes and the transpose graph.</li>
 * </ul>
 */
public class Q040_SA_KosarajusSCC {

    /**
     * @implNote TODO: implement.
     * Target approach: Three-pass algorithm - (1) run a DFS over the
     * original graph, pushing each vertex onto a stack in POST-order
     * (finish time), same idea as {@link Q018_SA_TopologicalSortDFS}; (2)
     * build the transpose graph (every edge reversed); (3) pop vertices
     * off the stack one at a time, and for each unvisited one, run a
     * DFS/BFS on the TRANSPOSE graph from it - everything reached in that
     * traversal is exactly one SCC. The reasoning: processing in
     * decreasing finish-time order on the transpose graph ensures each
     * DFS launch can't "leak" into a different SCC that finished earlier.
     * <p>
     * Target Time Complexity: O(V + E) - two DFS passes plus building the
     * transpose graph.
     * <br>
     * Target Space Complexity: O(V + E) - original graph, transpose
     * graph, visited array, and stack.
     */
    public int stronglyConnectedComponents(int V, List<List<Integer>> adj) {
        // TODO: implement
        return 0;
    }
}
