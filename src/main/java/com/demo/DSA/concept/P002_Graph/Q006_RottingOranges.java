package com.demo.DSA.concept.P002_Graph;

import java.util.LinkedList;
import java.util.Queue;

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
        Queue<int[]> q = new LinkedList<>();
        int n= grid.length;
        int m=grid[0].length;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j]==2){
                    q.offer(new int[]{i,j});
                }
            }
        }
        return getMinTimeToRottingAllOranges(q, grid, n, m);
    }

    private int getMinTimeToRottingAllOranges(Queue<int[]> q, int[][] grid, int n, int m) {
        int[] dirX={1,-1,0,0};
        int[] dirY={0,0,1,-1};

        int time=-1;

        while(!q.isEmpty()){
            int size=q.size();
            time++;
            while(size>0){
                int[] location = q.poll();
                size--;

                for(int i=0; i<4; i++){
                    int x =dirX[i]+location[0];
                    int y =dirY[i]+location[1];
                    if(x<0 || x>=n || y<0 || y>=m || grid[x][y]!=1) continue;
                    q.offer(new int[]{x,y});
                    grid[x][y]=2;
                }
            }
        }

        boolean isAnyFreshOrg=false;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j]==1){
                    isAnyFreshOrg=true;
                    return -1;
                }
            }
        }

        return Math.max(0,time);
    }
}
