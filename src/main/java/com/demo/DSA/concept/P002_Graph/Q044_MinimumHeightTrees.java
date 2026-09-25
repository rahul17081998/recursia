package com.demo.DSA.concept.P002_Graph;

import java.util.List;

/**
 * Q044. Minimum Height Trees
 * https://leetcode.com/problems/minimum-height-trees/
 * <p>
 * A tree with n nodes (0 to n-1) is given as an edge list (n-1 edges,
 * undirected, connected, acyclic). For each node, if the tree is rooted
 * there, it has some height (the max distance from root to any leaf).
 * Return all nodes that, as roots, give the Minimum Height Tree (MHT) -
 * there are at most 2 such nodes.
 *
 * <pre>
 * Example 1:
 * Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 * Output: [1]
 * Explanation: Rooting at 1 gives height 1 (a "star" shape); any other
 * root gives height 2.
 *
 * Example 2:
 * Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * Output: [3, 4]
 * Explanation: Rooting at either 3 or 4 gives the minimum possible
 * height (2); both are equally central.
 *
 * Constraints:
 * - 1 &lt;= n &lt;= 2 * 10^4
 * - edges.length == n - 1 (a valid tree, no cycles, fully connected)
 * </pre>
 */
public class Q044_MinimumHeightTrees {

    /**
     * @implNote TODO: implement.
     * Target approach: The MHT root(s) are exactly the tree's "centroid"
     * node(s), found by repeatedly trimming leaves layer by layer (like
     * peeling an onion) - very similar in spirit to Kahn's topological
     * sort in {@link Q017_SA_TopologicalSortKahnsBFS}, but using node degree
     * instead of in-degree. Build the adjacency list and each node's
     * degree; seed a queue with all current leaves (degree == 1, or the
     * single node itself if n == 1). Repeatedly remove a full layer of
     * leaves at once, decrementing the degree of their neighbors and
     * enqueuing any neighbor that becomes a new leaf (degree drops to 1),
     * until 2 or fewer nodes remain - those are the answer.
     * <p>
     * Target Time Complexity: O(V) - since edges.length == V - 1, this is
     * O(V + E) = O(V).
     * <br>
     * Target Space Complexity: O(V) - adjacency list, degree array, and
     * queue.
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // TODO: implement
        return null;
    }
}
