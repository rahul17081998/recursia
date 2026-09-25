package com.demo.DSA.concept.P002_Graph;

/**
 * Q036. Minimum Spanning Tree - Prim's Algorithm
 * https://www.geeksforgeeks.org/problems/minimum-spanning-tree/1
 * <p>
 * Given a connected, undirected, weighted graph with V vertices (0 to
 * V-1) as an adjacency list (each entry {neighbor, weight}), return the
 * sum of edge weights of a Minimum Spanning Tree (MST) - a subset of
 * edges connecting all vertices with no cycle, at minimum total weight.
 *
 * <pre>
 * Example 1:
 * Input: V = 5, edges = [[0,1,2],[0,3,6],[1,2,3],[1,3,8],[1,4,5],
 *        [2,4,7],[3,4,9]]
 * Output: 16
 * Explanation: One valid MST uses edges 0-1(2), 1-2(3), 1-4(5), 0-3(6),
 * summing to 2+3+5+6=16.
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
 * Grow one tree outward, always adding whichever edge is cheapest right
 * now among everything reachable from the tree so far. Start from any
 * single vertex as a one-node tree. Push all of its edges onto a
 * min-heap. Repeatedly pop the cheapest edge: if it reaches a vertex not
 * yet in the tree, add that vertex and edge to the MST, then push all of
 * the newly added vertex's edges too. If the popped edge reaches a
 * vertex already in the tree, discard it - adding it would only create a
 * cycle, not extend the tree.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://cp-algorithms.com/graph/mst_prim.html">cp-algorithms.com &mdash; Minimum Spanning Tree, Prim's Algorithm</a>
 *   - covers the dense-graph O(V&sup2;) array version too, useful to know
 *   when the heap version is not actually faster.</li>
 *   <li><a href="https://visualgo.net/en/mst">VisuAlgo.net &mdash; Minimum Spanning Tree</a>
 *   - watches the tree grow outward edge by edge, with the frontier heap shown live.</li>
 * </ul>
 */
public class Q036_SA_PrimsMST {

    /**
     * @implNote TODO: implement.
     * Target approach: Grow the MST one vertex at a time - start from any
     * vertex (e.g. 0), mark it in the MST, and push all its edges onto a
     * min-heap keyed by weight. Repeatedly pop the cheapest edge; if it
     * leads to a vertex not yet in the MST, add that vertex, add the edge
     * weight to the total, and push all of the new vertex's edges onto
     * the heap. Skip popped edges that lead to an already-included
     * vertex. Stop once V vertices are included.
     * <p>
     * Target Time Complexity: O(E log E) with a binary heap.
     * <br>
     * Target Space Complexity: O(V + E) - adjacency list, visited array,
     * and heap.
     */
    public int spanningTree(int V, int[][] edges) {
        return 0;
    }
}
