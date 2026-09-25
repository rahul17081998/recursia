package com.demo.DSA.concept.P002_Graph;

/**
 * Q023. Snake and Ladder
 * https://leetcode.com/problems/snakes-and-ladders/
 * <p>
 * An n x n board is numbered 1 to n^2 in a boustrophedon (back-and-forth)
 * pattern starting from the bottom-left, moving right on odd rows (from
 * the bottom) and left on even rows. board[r][c] is -1 for a plain
 * square, or the destination square number for a snake/ladder. Starting
 * on square 1, each move advances by a die roll of 1 to 6 (capped at
 * n^2), immediately following any snake/ladder on the destination
 * square (only one hop, no chaining). Return the minimum number of moves
 * to reach square n^2, or -1 if impossible.
 *
 * <pre>
 * Example 1:
 * Input:
 * board = [
 *   [-1,-1,-1,-1,-1,-1],
 *   [-1,-1,-1,-1,-1,-1],
 *   [-1,-1,-1,-1,-1,-1],
 *   [-1,35,-1,-1,13,-1],
 *   [-1,-1,-1,-1,-1,-1],
 *   [-1,15,-1,-1,-1,-1]
 * ]
 * Output: 4
 *
 * Example 2:
 * Input: board = [[-1,-1],[-1,3]]
 * Output: 1
 *
 * Constraints:
 * - n == board.length == board[i].length
 * - 2 &lt;= n &lt;= 20
 * - board[i][j] is -1 or in the range [1, n^2].
 * - board[n-1][0] != -1
 * - board[0][n-1] != -1 (i.e. the very first and very last squares have
 *   no snake/ladder)
 * </pre>
 */
public class Q023_SnakeAndLadder {

    /**
     * @implNote TODO: implement.
     * Target approach: This is unweighted shortest-path in disguise -
     * each square 1..n^2 is a node, with an edge from square s to every
     * square reachable by a die roll of 1-6 (following any snake/ladder
     * on the landing square). BFS from square 1; the first time square
     * n^2 is dequeued, its BFS depth is the answer. A helper that converts
     * a 1-indexed square number to (row, col) using the boustrophedon
     * pattern is needed to look up board values.
     * <p>
     * Target Time Complexity: O(n^2) - each of the n^2 squares enqueued
     * at most once, with 6 neighbor checks each.
     * <br>
     * Target Space Complexity: O(n^2) - BFS queue plus visited array.
     */
    public int snakesAndLadders(int[][] board) {
        // TODO: implement
        return -1;
    }
}
