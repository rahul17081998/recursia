package com.demo.DSA.concept.P002_Graph;

/**
 * Q033. Redundant Connection
 * https://leetcode.com/problems/redundant-connection/
 * <p>
 * A tree with n nodes had exactly one extra edge added, making it contain
 * exactly one cycle. Given edges (the resulting graph, with n edges for n
 * nodes), return the edge that, if removed, restores the graph to a tree.
 * If multiple such edges exist, return the one that appears last in the
 * input.
 *
 * <pre>
 * Example 1:
 * Input: edges = [[1,2],[1,3],[2,3]]
 * Output: [2,3]
 * Explanation: 1-2, 1-3, 2-3 form a triangle; removing 2-3 (the last edge
 * that completes the cycle) restores a tree.
 *
 * Example 2:
 * Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
 * Output: [1,4]
 *
 * Constraints:
 * - n == edges.length, 3 &lt;= n &lt;= 1000
 * - edges[i].length == 2, 1 &lt;= a, b &lt;= n
 * - No repeated edges, and the resulting graph is connected.
 * </pre>
 */
public class Q033_RedundantConnection {

    /**
     * @implNote TODO: implement.
     * Target approach: Union-Find (see {@link Q032_SA_DisjointSetUnion}) -
     * process edges in order, and for each edge (u, v), check whether u
     * and v are already in the same set (find(u) == find(v)). If so, this
     * edge is the redundant one (it connects two already-connected nodes,
     * i.e. it's the edge that closes the cycle) - return it immediately,
     * since it's guaranteed to be the LAST such edge encountered in input
     * order when scanning left to right. Otherwise, union them and
     * continue.
     * <p>
     * Target Time Complexity: O(n * alpha(n)) - n union/find operations.
     * <br>
     * Target Space Complexity: O(n) - the DSU parent/rank arrays.
     */
    public int[] findRedundantConnection(int[][] edges) {
        // TODO: implement
        return new int[0];
    }
}
