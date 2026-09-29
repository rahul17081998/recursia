package com.demo.DSA.concept.P002_Graph;

import java.util.PriorityQueue;

/**
 * Q031. Swim in Rising Water
 * https://leetcode.com/problems/swim-in-rising-water/
 * <p>
 * Given an n x n grid where grid[i][j] is the elevation at that cell (all
 * elevations are a permutation of 0..n*n-1), water starts rising: at time
 * t, every cell with elevation &lt;= t is submerged. You may swim between
 * two 4-directionally adjacent cells only if both are currently
 * submerged. Starting at (0,0), return the minimum time t at which you
 * can reach (n-1,n-1).
 *
 * <pre>
 * Example 1:
 * Input: grid = [[0,2],[1,3]]
 * Output: 3
 * Explanation: At t=3, every cell is submerged, and (0,0)-&gt;(0,1)-&gt;(1,1)
 * (elevations 0,2,3) or (0,0)-&gt;(1,0)-&gt;(1,1) (0,1,3) are both valid; t=3
 * is the smallest time this is possible since (1,1)=3 must be submerged.
 *
 * Example 2:
 * Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],
 *        [11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 *
 * Constraints:
 * - n == grid.length == grid[i].length
 * - 1 &lt;= n &lt;= 50
 * - 0 &lt;= grid[i][j] &lt; n^2, all values distinct.
 * </pre>
 */
public class Q031_SwimInRisingWater {

    /**
     * @implNote TODO: implement.
     * Target approach: Same "minimize the maximum edge weight along the
     * path" idea as {@link Q030_PathWithMinimumEffort} - here a cell's
     * "cost" to enter is its own elevation (since you must wait for the
     * water to reach at least that high to stand there). Run a
     * Dijkstra-like search with a min-heap of {maxElevationSoFar, cell},
     * where relaxing a move to a neighbor takes
     * max(currentMaxElevation, neighbor's elevation) as the new effort.
     * Pop until (n-1,n-1) is reached. (An alternative approach: binary
     * search on t, checking via BFS/DFS whether (0,0) can reach (n-1,n-1)
     * using only cells with elevation &lt;= t.)
     * <p>
     * Target Time Complexity: O(n^2 log n) with the Dijkstra-style
     * approach.
     * <br>
     * Target Space Complexity: O(n^2) - visited grid plus heap.
     */
    int[] dirX = {1, -1, 0, 0};
    int[] dirY = {0, 0, 1, -1};
    public int swimInWater(int[][] grid) {
        int n = grid.length;

        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        pq.offer(new int[]{0, 0, grid[0][0]});
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            int[] currNode = pq.poll();
            int x = currNode[0]; int y = currNode[1]; int minTimeReqToReachCurrNode = currNode[2];

            for (int i = 0; i < 4; i++) {
                int newX = dirX[i] + x;
                int newY = dirY[i] + y;

                if (newX < 0 || newX >= n || newY < 0 || newY >= n) continue;
                if (dist[newX][newY] < dist[x][y]) continue;

                int MinTimeToReachNeighbour = Math.max(grid[newX][newY], dist[x][y]);

                if (dist[newX][newY] > MinTimeToReachNeighbour) {

                    dist[newX][newY] = MinTimeToReachNeighbour;
                    pq.offer(new int[]{newX, newY, dist[newX][newY]});
                }

            }
        }
        return Math.max(dist[n-1][n-1], grid[0][0]);
    }
}
