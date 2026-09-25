package com.demo.DSA.concept.P002_Graph;

import java.util.List;

/**
 * Q017. Topological Sort (Kahn's Algorithm - BFS)
 * https://www.geeksforgeeks.org/problems/topological-sort/1
 * <p>
 * Given a Directed Acyclic Graph (DAG) with V vertices (0 to V-1) as an
 * adjacency list, return a valid topological ordering - an ordering of
 * vertices such that for every directed edge u -&gt; v, u appears before v.
 *
 * <pre>
 * Example 1:
 * Input: V = 6, adj = [[],[],[3],[1],[0,1],[0,2]]
 * (edges: 5-&gt;0, 5-&gt;2, 4-&gt;0, 4-&gt;1, 2-&gt;3, 3-&gt;1)
 * Output: [4, 5, 0, 2, 3, 1] (one valid ordering; others may also be
 * valid)
 *
 * Example 2:
 * Input: V = 3, adj = [[1],[2],[]]
 * (edges: 0-&gt;1, 1-&gt;2)
 * Output: [0, 1, 2]
 *
 * Constraints:
 * - 1 &lt;= V &lt;= 10^5
 * - The graph is guaranteed to be a DAG (no cycles).
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * Repeatedly peel off whatever currently has zero remaining
 * prerequisites. Compute every vertex's in-degree (how many edges point
 * <em>into</em> it). Seed a queue with every vertex whose in-degree is
 * already 0 - those have no unmet prerequisites. Pop a vertex, append it
 * to the output, and decrement the in-degree of each of its neighbors;
 * any neighbor that drops to 0 gets enqueued. Because a vertex is only
 * ever enqueued once every one of its prerequisites has already been
 * output, the result is a valid ordering by construction.
 * <p>
 * A useful side effect: if the graph has a cycle, some vertices' in-
 * degree never reaches 0, so the output ends up shorter than V - that is
 * how Course Schedule (Q015/Q016) detects impossibility using this exact
 * algorithm.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://cp-algorithms.com/graph/topological-sort.html">cp-algorithms.com &mdash; Topological Sorting</a>
 *   - explains the DFS-based method too, for comparing both approaches.</li>
 *   <li>YouTube &mdash; search "William Fiset topological sort Kahn's algorithm" (Graph Theory playlist)
 *   - animated dry run of the in-degree queue peeling layer by layer.</li>
 * </ul>
 */
public class Q017_SA_TopologicalSortKahnsBFS {

    /**
     * @implNote TODO: implement.
     * Target approach: Compute in-degree for every vertex, seed a queue
     * with all 0-in-degree vertices, then repeatedly poll a vertex,
     * append it to the result, and decrement each neighbor's in-degree -
     * enqueue any neighbor whose in-degree drops to 0. This naturally
     * produces an order where every vertex is only output once all of
     * its prerequisites have been.
     * <p>
     * Target Time Complexity: O(V + E) - every vertex and edge processed
     * once.
     * <br>
     * Target Space Complexity: O(V) - in-degree array, queue, and result.
     */
    public List<Integer> topoSort(int V, List<List<Integer>> adj) {
        // TODO: implement
        return null;
    }
}
