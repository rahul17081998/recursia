package com.demo.DSA.concept.P002_Graph;

/**
 * Q010. 01 Matrix
 * https://leetcode.com/problems/01-matrix/
 * <p>
 * Given an m x n binary matrix mat, return the distance of the nearest 0
 * for each cell (using 4-directional moves, one step = distance 1).
 *
 * <pre>
 * Example 1:
 * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: [[0,0,0],[0,1,0],[0,0,0]]
 *
 * Example 2:
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 * Output: [[0,0,0],[0,1,0],[1,2,1]]
 *
 * Constraints:
 * - m == mat.length, n == mat[i].length
 * - 1 &lt;= m, n &lt;= 10^4
 * - 1 &lt;= m * n &lt;= 10^4
 * - mat[i][j] is 0 or 1.
 * - There is at least one 0 in mat.
 * </pre>
 */
public class Q010_ZeroOneMatrix {

    /**
     * @implNote TODO: implement.
     * Target approach: Multi-source BFS - seed the queue with every 0
     * cell at distance 0 (their answer is already known), and mark every
     * 1 cell as unvisited/infinity. Expand outward level by level; the
     * first time a 1 cell is reached, its BFS depth is its answer (the
     * shortest distance to any 0), since BFS explores in increasing
     * distance order.
     * <p>
     * Target Time Complexity: O(m * n) - each cell enqueued at most once.
     * <br>
     * Target Space Complexity: O(m * n) - BFS queue plus output matrix.
     */
    public int[][] updateMatrix(int[][] mat) {
        // TODO: implement
        return mat;
    }
}
