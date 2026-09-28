package com.demo.DSA.concept.P002_Graph;

import java.util.LinkedList;
import java.util.Queue;

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
    public static class NodeDetails {
        int x; int y; int visitedCellCount;
        NodeDetails(int x, int y, int visitedCellCount) {
            this.x = x;
            this.y = y;
            this.visitedCellCount = visitedCellCount;
        }
    }

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
    int[] dirX = {1, -1, 0, 0, 1, -1, -1, 1};
    int[] dirY = {0, 0, -1, 1, 1, 1, -1, -1};

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;
        boolean[][] vis = new boolean[n][n];
        Queue<NodeDetails> q = new LinkedList<>();
        q.offer(new NodeDetails(0, 0, 1));
        vis[0][0] = true;

        while (!q.isEmpty()) {
            NodeDetails curr = q.poll();
            if (curr.x == n-1 && curr.y == n-1) return curr.visitedCellCount;
            //System.out.println("\nX=" + curr.x + " Y=" + curr.y + " grid=" + grid[curr.x][curr.y]);

            for (int d = 0; d < 8; d++) {
                int newX = dirX[d] + curr.x;
                int newY = dirY[d] + curr.y;

                if (newX < 0 || newX >= n || newY < 0 || newY >= n || grid[newX][newY] == 1 || vis[newX][newY]) continue;
                //System.out.println("grid=" + grid[newX][newY] + " is Visited=" + vis[newX][newY]);
                q.offer(new NodeDetails(newX, newY, curr.visitedCellCount + 1));
                vis[newX][newY] = true;
            }
        }

        return -1;
    }
}
