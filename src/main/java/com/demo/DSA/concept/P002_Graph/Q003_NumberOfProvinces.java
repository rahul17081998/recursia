package com.demo.DSA.concept.P002_Graph;

/**
 * Q003. Number of Provinces
 * https://leetcode.com/problems/number-of-provinces/
 * <p>
 * There are n cities. Some are directly connected, and some are not.
 * A province is a group of directly or indirectly connected cities and no
 * other cities outside the group. Given an n x n adjacency matrix
 * isConnected where isConnected[i][j] = 1 if the ith and jth cities are
 * directly connected, return the total number of provinces.
 *
 * <pre>
 * Example 1:
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 *
 * Example 2:
 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 *
 * Constraints:
 * - 1 &lt;= n &lt;= 200
 * - isConnected[i][j] is 1 or 0.
 * - isConnected[i][i] == 1
 * - isConnected[i][j] == isConnected[j][i]
 * </pre>
 */
public class Q003_NumberOfProvinces {

    /**
     * @implNote TODO: implement.
     * Target approach: This is "count connected components" on a graph
     * given as an adjacency matrix. For each unvisited city, run a
     * BFS/DFS over the matrix to mark every city in its province as
     * visited, incrementing a province counter once per BFS/DFS launch.
     * <p>
     * Target Time Complexity: O(n^2) - each BFS/DFS scans a full matrix
     * row per visited city.
     * <br>
     * Target Space Complexity: O(n) - visited array plus recursion/queue.
     */
    public int findCircleNum(int[][] isConnected) {
        // TODO: implement
        return 0;
    }
}
