package com.demo.DSA.concept.P002_Graph;

/**
 * Q011. Walls and Gates
 * https://leetcode.com/problems/walls-and-gates/ (premium; also on GFG as
 * "Distance of nearest cell having 1")
 * <p>
 * You are given an m x n grid rooms where each cell is one of: -1 (a
 * wall), 0 (a gate), or INF (2147483647, an empty room). Fill each empty
 * room with the distance to its nearest gate. If a room cannot reach any
 * gate, it stays INF.
 *
 * <pre>
 * Example 1:
 * Input (INF = 2147483647):
 * rooms = [
 *   [INF,-1,0,INF],
 *   [INF,INF,INF,-1],
 *   [INF,-1,INF,-1],
 *   [0,-1,INF,INF]
 * ]
 * Output:
 * [
 *   [3,-1,0,1],
 *   [2,2,1,-1],
 *   [1,-1,2,-1],
 *   [0,-1,3,4]
 * ]
 *
 * Example 2:
 * Input: rooms = [[-1]]
 * Output: [[-1]]
 *
 * Constraints:
 * - m == rooms.length, n == rooms[i].length
 * - 1 &lt;= m, n &lt;= 250
 * - rooms[i][j] is -1, 0, or 2^31 - 1.
 * </pre>
 */
public class Q011_WallsAndGates {

    /**
     * @implNote TODO: implement.
     * Target approach: Multi-source BFS, exactly like 01 Matrix - seed the
     * queue with every gate (0) cell, then expand outward 4-directionally
     * level by level, skipping walls (-1) and cells already assigned a
     * finite distance, writing the current BFS depth into each newly
     * reached empty room.
     * <p>
     * Target Time Complexity: O(m * n) - each room enqueued at most once.
     * <br>
     * Target Space Complexity: O(m * n) - BFS queue in the worst case.
     */
    public void wallsAndGates(int[][] rooms) {
        // TODO: implement
    }
}
