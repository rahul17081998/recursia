package com.demo.DSA.concept.P002_Graph;

/**
 * Q022. Shortest Path in Binary Matrix
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/
 * <p>
 * Given an n x n binary grid, find the length of the shortest clear path
 * from (0, 0) to (n-1, n-1), moving 8-directionally (including
 * diagonals) through cells with value 0 only. Path length is the number
 * of visited cells. Return -1 if no such path exists.
 *
 * <pre>
 * Example 1:
 * Input: grid = [[0,1],[1,0]]
 * Output: 2
 *
 * Example 2:
 * Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
 * Output: 4
 *
 * Example 3:
 * Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
 * Output: -1
 * Explanation: The starting cell (0,0) itself is blocked.
 *
 * Constraints:
 * - n == grid.length == grid[i].length
 * - 1 &lt;= n &lt;= 100
 * - grid[i][j] is 0 or 1.
 * </pre>
 */
public class Q022_ShortestPathInBinaryMatrix {

    /**
     * @implNote TODO: implement.
     * Target approach: BFS from (0,0) if it's clear (grid[0][0] == 0),
     * exploring all 8 neighboring directions per cell. Track distance via
     * BFS level (or a distance grid), returning the level at which
     * (n-1,n-1) is first reached; -1 if the queue empties without
     * reaching it.
     * <p>
     * Target Time Complexity: O(n^2) - each cell enqueued at most once,
     * with 8 neighbor checks each.
     * <br>
     * Target Space Complexity: O(n^2) - BFS queue plus visited tracking.
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        // TODO: implement
        return -1;
    }
}
