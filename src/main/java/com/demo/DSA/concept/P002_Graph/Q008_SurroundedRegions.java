package com.demo.DSA.concept.P002_Graph;

/**
 * Q008. Surrounded Regions
 * https://leetcode.com/problems/surrounded-regions/
 * <p>
 * Given an m x n board containing 'X' and 'O', capture all regions of 'O'
 * that are 4-directionally surrounded by 'X' - i.e. flip every such 'O'
 * to 'X'. A region touching the border is never captured, even if it's
 * otherwise surrounded.
 *
 * <pre>
 * Example 1:
 * Input:
 * board = [
 *   ["X","X","X","X"],
 *   ["X","O","O","X"],
 *   ["X","X","O","X"],
 *   ["X","O","X","X"]
 * ]
 * Output:
 * [
 *   ["X","X","X","X"],
 *   ["X","X","X","X"],
 *   ["X","X","X","X"],
 *   ["X","O","X","X"]
 * ]
 * Explanation: The 'O's in the middle are enclosed and get captured; the
 * bottom-left 'O' touches the border, so it survives.
 *
 * Example 2:
 * Input: board = [["X"]]
 * Output: [["X"]]
 *
 * Constraints:
 * - m == board.length, n == board[i].length
 * - 1 &lt;= m, n &lt;= 200
 * - board[i][j] is 'X' or 'O'.
 * </pre>
 */
public class Q008_SurroundedRegions {

    /**
     * @implNote TODO: implement.
     * Target approach: Reverse the problem - instead of finding enclosed
     * regions, find the regions that are NOT captured (those connected to
     * the border) by running BFS/DFS from every border 'O' and marking
     * everything reachable with a temporary marker (e.g. '#'). Afterward,
     * a single pass flips every remaining 'O' (never marked, so
     * enclosed) to 'X', and every '#' back to 'O'.
     * <p>
     * Target Time Complexity: O(m * n) - each cell visited a constant
     * number of times.
     * <br>
     * Target Space Complexity: O(m * n) worst case - BFS queue or DFS
     * recursion stack.
     */
    public void solve(char[][] board) {
        // TODO: implement
    }
}
