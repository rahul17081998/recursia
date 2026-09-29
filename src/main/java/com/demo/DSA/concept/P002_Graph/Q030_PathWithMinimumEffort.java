package com.demo.DSA.concept.P002_Graph;

import java.util.PriorityQueue;

/**
 * Q030. Path With Minimum Effort
 * https://leetcode.com/problems/path-with-minimum-effort/
 * <p>
 * Given an m x n heights grid, find a path from (0,0) to (m-1,n-1) (moving
 * 4-directionally) that minimizes the "effort" - defined as the maximum
 * absolute difference in heights between two consecutive cells along the
 * path. Return that minimum possible effort.
 *
 * <pre>
 * Example 1:
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: Path [1,3,5,3,5] (down, down, right, right) has max
 * consecutive difference 2, which is optimal.
 *
 * Example 2:
 * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
 * Output: 1
 *
 * Example 3 (a zero-effort path exists):
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: Snake down column 0, along the bottom row, up column 2,
 * along the top row and down column 4 - every cell on it is 1.
 *
 * Example 4 (single row - only one path, so every step counts):
 * Input: heights = [[1,10,6,7,9,10,4,9]]
 * Output: 9
 * Explanation: Step differences are 9,4,1,2,1,6,5. Effort is the MAX (9),
 * not the last step (5). Catches the bug of storing only the current
 * step's difference instead of max(effortSoFar, stepDiff).
 *
 * Example 5 (single cell - start is already the end):
 * Input: heights = [[3]]
 * Output: 0
 *
 * Constraints:
 * - m == heights.length, n == heights[i].length
 * - 1 &lt;= m, n &lt;= 100
 * - 1 &lt;= heights[i][j] &lt;= 10^6
 * </pre>
 */
public class Q030_PathWithMinimumEffort {
    int[] dirX = {1, -1, 0, 0};
    int[] dirY = {0, 0, 1, -1};

    /**
     * @implNote TODO: implement.
     * Target approach: This is Dijkstra's algorithm with a twist - instead
     * of summing edge weights along a path, the "distance" to a cell is
     * the maximum step difference seen so far getting there, and relaxing
     * an edge means: candidateEffort = max(currentEffort,
     * |heights[cur] - heights[neighbor]|); update neighbor's effort if
     * candidateEffort is smaller. Use a min-heap of {effort, cell}, same
     * skeleton as {@link Q025_SA_DijkstraShortestPath}, popping smallest
     * effort first. Stop as soon as the bottom-right cell is popped.
     * <p>
     * Target Time Complexity: O(m * n * log(m * n)) - each cell processed
     * with heap operations, 4 neighbor checks each.
     * <br>
     * Target Space Complexity: O(m * n) - effort grid plus heap.
     */
    public int minimumEffortPath(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;

        int[][] dist = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        pq.offer(new int[]{0, 0, 0});
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            int[] currCell = pq.poll();
            int x = currCell[0]; int y = currCell[1]; int maxHeightDiffUpToCurrCell = currCell[2];
            if (x == row - 1 && y == col - 1) return maxHeightDiffUpToCurrCell;
            for (int c = 0; c < 4; c++) {
                int newX = dirX[c] + x;
                int newY = dirY[c] + y;

                if (newX < 0 || newX >= row || newY < 0 || newY >= col) continue;
                int neighbour = heights[newX][newY];
                int currCellHeight = heights[x][y];

                int NeighbourHeightDiff = Math.abs(neighbour - currCellHeight);
                int newEffect = Math.max(NeighbourHeightDiff, maxHeightDiffUpToCurrCell);

                if (dist[newX][newY] > newEffect) {
                    dist[newX][newY] = newEffect;
                    pq.offer(new int[]{newX, newY, newEffect});
                }
            }
        }

        return 0;
    }
}
