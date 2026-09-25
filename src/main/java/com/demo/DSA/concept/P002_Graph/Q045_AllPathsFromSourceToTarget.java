package com.demo.DSA.concept.P002_Graph;

import java.util.List;

/**
 * Q045. All Paths From Source to Target
 * https://leetcode.com/problems/all-paths-from-source-to-target/
 * <p>
 * Given a Directed Acyclic Graph (DAG) of n nodes (0 to n-1) as an
 * adjacency list graph, return every possible path from node 0 to node
 * n-1, in any order.
 *
 * <pre>
 * Example 1:
 * Input: graph = [[1,2],[3],[3],[]]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: Two ways from node 0 to node 3: via node 1, or via node 2.
 *
 * Example 2:
 * Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 *
 * Constraints:
 * - n == graph.length, 2 &lt;= n &lt;= 15
 * - 0 &lt;= graph[i][j] &lt; n, graph[i][j] != i (no self-loops)
 * - The input graph is guaranteed to be a DAG.
 * </pre>
 */
public class Q045_AllPathsFromSourceToTarget {

    /**
     * @implNote TODO: implement.
     * Target approach: Backtracking DFS from node 0 - maintain a
     * currentPath list, starting with [0]. At each node, if it's n-1,
     * record a copy of currentPath as a complete result. Otherwise, for
     * each neighbor, append it to currentPath, recurse, then remove it
     * (backtrack) before trying the next neighbor. Since the graph is
     * guaranteed acyclic, no visited-set is needed to prevent infinite
     * loops.
     * <p>
     * Target Time Complexity: O(2^V * V) worst case - up to
     * exponentially many paths in a DAG, each of length up to V.
     * <br>
     * Target Space Complexity: O(V) for the recursion stack / current
     * path, excluding the output.
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // TODO: implement
        return null;
    }
}
