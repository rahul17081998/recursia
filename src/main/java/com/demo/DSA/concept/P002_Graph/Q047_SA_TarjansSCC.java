package com.demo.DSA.concept.P002_Graph;

import java.util.List;

/**
 * Q047. Strongly Connected Components - Tarjan's Algorithm
 * https://www.geeksforgeeks.org/problems/strongly-connected-component-tarjanss-algo/1
 * <p>
 * Given a directed graph with V vertices (0 to V-1) as an adjacency list,
 * return the number of Strongly Connected Components (SCCs), computed
 * with Tarjan's algorithm - a single-DFS-pass alternative to Kosaraju's
 * two-pass approach in {@link Q040_SA_KosarajusSCC}. Same problem, same
 * example graphs as Q040, so the two implementations can be checked
 * against each other directly.
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
 * Get Kosaraju's answer in a single DFS pass, reusing the low-link idea
 * from bridges and articulation points plus an explicit stack. Same
 * <code>disc[]</code>/<code>low[]</code> DFS as bridge-finding, with one
 * addition: push every vertex onto an explicit stack when first
 * visited, and mark it <code>onStack</code>. After recursing into a
 * neighbor, update <code>low[u] = min(low[u], low[v])</code> - but only
 * if that neighbor is <strong>still on the stack</strong>. That guard is
 * the key difference from bridge-finding: a neighbor that has already
 * been popped belongs to a different, already-finished SCC, and must
 * not be allowed to influence <code>low[u]</code>.
 * <p>
 * When a vertex u is found with <code>low[u] == disc[u]</code>, it is
 * the "root" of its SCC - pop the stack down through and including u.
 * Everything popped in that moment is exactly one complete SCC.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://cp-algorithms.com/graph/strongly-connected-components.html">cp-algorithms.com &mdash; Strongly Connected Components and Condensation Graph</a>
 *   - same page as Kosaraju's; read them back to back to see exactly what
 *   the second DFS pass and transpose graph buy you (or do not).</li>
 *   <li>YouTube &mdash; search "William Fiset Tarjan's strongly connected components algorithm" (Graph Theory playlist)
 *   - animated dry run of the explicit stack and the low-link SCC-root pop.</li>
 * </ul>
 */
public class Q047_SA_TarjansSCC {

    /**
     * @implNote TODO: implement.
     * Target approach: Single DFS pass tracking, per vertex: disc[] (the
     * order it was first visited), low[] (the lowest discovery time
     * reachable from its subtree, including via one back edge - same
     * idea as the low-link values in
     * {@link Q038_SA_CriticalConnectionsBridges} and
     * {@link Q039_SA_ArticulationPoints}, but for a DIRECTED graph), and
     * onStack[] (whether it's currently pushed onto an explicit stack of
     * "not yet assigned to a finished SCC" vertices). Push each vertex
     * onto the stack when first visited. After recursing into a neighbor,
     * update low[u] = min(low[u], low[v]) if the neighbor is still on the
     * stack (an already-popped neighbor belongs to a different, already-
     * finished SCC and must NOT influence low[u]). When a vertex u is
     * found with low[u] == disc[u] (u is the root of its SCC), pop the
     * stack down to and including u - everything popped is exactly one
     * SCC.
     * <p>
     * Target Time Complexity: O(V + E) - a single DFS pass, unlike
     * Kosaraju's two passes plus building a transpose graph.
     * <br>
     * Target Space Complexity: O(V + E) - adjacency list, disc[]/low[]/
     * onStack[] arrays, the explicit stack, and the recursion stack.
     */
    public int stronglyConnectedComponents(int V, List<List<Integer>> adj) {
        // TODO: implement
        return 0;
    }
}
