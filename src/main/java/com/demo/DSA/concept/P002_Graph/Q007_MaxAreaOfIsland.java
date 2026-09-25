package com.demo.DSA.concept.P002_Graph;

/**
 * Q007. Max Area of Island
 * https://leetcode.com/problems/max-area-of-island/
 * <p>
 * Given an m x n binary grid, an island is a group of 1's connected
 * 4-directionally. Return the maximum area (number of cells) among all
 * islands in the grid, or 0 if there is no island.
 *
 * <pre>
 * Example 1:
 * Input:
 * grid = [
 *   [1,1,0,0],
 *   [1,1,0,0],
 *   [0,0,1,0],
 *   [0,0,0,1]
 * ]
 * Output: 4
 * Explanation: The top-left 2x2 block of 1's has area 4, larger than
 * either of the two isolated single-cell islands.
 *
 * Example 2:
 * Input: grid = [[0,0,0],[0,0,0]]
 * Output: 0
 *
 * Constraints:
 * - m == grid.length, n == grid[i].length
 * - 1 &lt;= m, n &lt;= 50
 * - grid[i][j] is 0 or 1.
 * </pre>
 */
public class Q007_MaxAreaOfIsland {

    /**
     * @implNote TODO: implement.
     * Target approach: Same scan-and-flood-fill skeleton as Number of
     * Islands, but instead of just counting islands, have the BFS/DFS
     * return the size of the island it just explored, and track the
     * maximum across all unvisited land cells found while scanning.
     * <p>
     * Target Time Complexity: O(m * n) - each cell visited at most once.
     * <br>
     * Target Space Complexity: O(m * n) worst case - BFS queue or DFS
     * recursion stack.
     */
    public int maxAreaOfIsland(int[][] grid) {
        // TODO: implement
        return 0;
    }
}
