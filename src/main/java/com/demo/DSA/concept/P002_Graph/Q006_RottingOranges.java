package com.demo.DSA.concept.P002_Graph;

/**
 * Q006. Rotting Oranges
 * https://leetcode.com/problems/rotting-oranges/
 * <p>
 * You are given an m x n grid where each cell is 0 (empty), 1 (fresh
 * orange), or 2 (rotten orange). Every minute, any fresh orange that is
 * 4-directionally adjacent to a rotten orange becomes rotten. Return the
 * minimum number of minutes until no cell has a fresh orange, or -1 if
 * that's impossible.
 *
 * <pre>
 * Example 1:
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 *
 * Example 2:
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom-left is never adjacent to a
 * rotten orange, so it can never rot.
 *
 * Constraints:
 * - m == grid.length, n == grid[i].length
 * - 1 &lt;= m, n &lt;= 10
 * - grid[i][j] is 0, 1, or 2.
 * </pre>
 */
public class Q006_RottingOranges {

    /**
     * @implNote TODO: implement.
     * Target approach: Multi-source BFS - seed the queue with every
     * initially-rotten orange's coordinates (and count the fresh oranges).
     * Process level by level (each level = one minute); for every fresh
     * neighbor rotted this round, decrement the fresh count and enqueue
     * it. The number of levels processed is the answer; if fresh oranges
     * remain after the queue empties, return -1.
     * <p>
     * Target Time Complexity: O(m * n) - each cell enqueued at most once.
     * <br>
     * Target Space Complexity: O(m * n) - BFS queue in the worst case.
     */
    public int orangesRotting(int[][] grid) {
        // TODO: implement
        return -1;
    }
}
