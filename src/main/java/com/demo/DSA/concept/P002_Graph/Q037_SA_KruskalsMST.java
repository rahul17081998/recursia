package com.demo.DSA.concept.P002_Graph;

/**
 * Q037. Minimum Spanning Tree - Kruskal's Algorithm
 * https://www.geeksforgeeks.org/problems/minimum-spanning-tree/1
 * <p>
 * Given a connected, undirected, weighted graph with V vertices (0 to
 * V-1) and an edge list (each entry {u, v, weight}), return the sum of
 * edge weights of a Minimum Spanning Tree (MST), computed using Kruskal's
 * algorithm as an alternative to Prim's approach in
 * {@link Q036_SA_PrimsMST}.
 *
 * <pre>
 * Example 1:
 * Input: V = 5, edges = [[0,1,2],[0,3,6],[1,2,3],[1,3,8],[1,4,5],
 *        [2,4,7],[3,4,9]]
 * Output: 16
 *
 * Example 2:
 * Input: V = 3, edges = [[0,1,1],[1,2,1],[0,2,5]]
 * Output: 2
 *
 * Constraints:
 * - 1 &lt;= V &lt;= 5000
 * - The graph is connected.
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * Sort every edge cheapest-first, and greedily take any edge that does
 * not close a cycle - Union-Find answers "would this create a cycle?"
 * instantly. Sort all edges by weight ascending. Walk the sorted list
 * with a Union-Find (DSU) structure: for each edge (u, v), if
 * <code>find(u) != find(v)</code>, they are in different components -
 * accept the edge into the MST and <code>union(u, v)</code>. If they are
 * already in the same component, skip it (accepting it would only
 * create a cycle). Stop once V-1 edges have been accepted.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://cp-algorithms.com/graph/mst_kruskal.html">cp-algorithms.com &mdash; Minimum Spanning Tree, Kruskal's Algorithm</a>
 *   - the companion page on the same site (search "Kruskal with DSU")
 *   shows the Union-Find-optimized version used in practice.</li>
 *   <li><a href="https://visualgo.net/en/mst">VisuAlgo.net &mdash; Minimum Spanning Tree</a>
 *   - same module as Prim's, switched to Kruskal's mode; watch the sorted-edge accept/reject decisions live.</li>
 * </ul>
 */
public class Q037_SA_KruskalsMST {

    /**
     * @implNote TODO: implement.
     * Target approach: Sort all edges by weight ascending. Using
     * Union-Find (see {@link Q032_SA_DisjointSetUnion}), walk the sorted
     * edges and greedily add each edge to the MST if its two endpoints
     * are NOT already connected (find(u) != find(v)) - adding it and
     * unioning them; skip it otherwise (adding it would create a cycle).
     * Stop once V-1 edges have been added (the MST is complete) or the
     * edge list is exhausted.
     * <p>
     * Target Time Complexity: O(E log E) - dominated by sorting the
     * edges; Union-Find operations are ~O(1) amortized each.
     * <br>
     * Target Space Complexity: O(V + E) - DSU arrays plus the sorted edge
     * list.
     */
    public int spanningTree(int V, int[][] edges) {
        // TODO: implement
        return 0;
    }
}
