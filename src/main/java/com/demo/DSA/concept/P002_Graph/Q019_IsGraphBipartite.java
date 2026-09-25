package com.demo.DSA.concept.P002_Graph;

/**
 * Q019. Is Graph Bipartite?
 * https://leetcode.com/problems/is-graph-bipartite/
 * <p>
 * Given an undirected graph as an adjacency list graph (graph[u] lists
 * u's neighbors), determine whether it's bipartite - i.e. whether its
 * vertices can be split into two sets such that every edge connects a
 * vertex in one set to a vertex in the other.
 *
 * <pre>
 * Example 1:
 * Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 * Output: false
 * Explanation: 0, 1, 2 form a triangle (odd cycle), which can't be
 * 2-colored.
 *
 * Example 2:
 * Input: graph = [[1,3],[0,2],[1,3],[0,2]]
 * Output: true
 * Explanation: {0, 2} and {1, 3} is a valid split.
 *
 * Constraints:
 * - graph.length == n, 1 &lt;= n &lt;= 100
 * - graph[u] does not contain u, and has no duplicate values.
 * - The graph may be disconnected.
 * </pre>
 */
public class Q019_IsGraphBipartite {

    /**
     * @implNote TODO: implement.
     * Target approach: Try to 2-color the graph via BFS/DFS from every
     * unvisited vertex - assign the start vertex color 0, and every
     * neighbor the opposite color of the current vertex. If a neighbor is
     * already colored the SAME as the current vertex, the graph is not
     * bipartite. Must check every connected component, not just one.
     * <p>
     * Target Time Complexity: O(V + E) - every vertex and edge examined
     * once.
     * <br>
     * Target Space Complexity: O(V) - color array plus queue/recursion
     * stack.
     */
    public boolean isBipartite(int[][] graph) {
        // TODO: implement
        return false;
    }
}
