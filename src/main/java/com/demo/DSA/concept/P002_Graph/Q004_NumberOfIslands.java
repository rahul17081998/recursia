package com.demo.DSA.concept.P002_Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Q004. Number of Islands
 * https://leetcode.com/problems/number-of-islands/
 * <p>
 * Given an m x n 2D binary grid which represents a map of '1's (land) and
 * '0's (water), return the number of islands. An island is surrounded by
 * water and is formed by connecting adjacent lands horizontally or
 * vertically.
 *
 * <pre>
 * Example 1:
 * Input:
 * grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 *
 * Example 2:
 * Input:
 * grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 * Constraints:
 * - m == grid.length, n == grid[i].length
 * - 1 &lt;= m, n &lt;= 300
 * - grid[i][j] is '0' or '1'.
 * </pre>
 */
public class Q004_NumberOfIslands {

    public static class Pair{
        int x; int y;
        Pair(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    /**
     * @implNote TODO: implement.
     * Target approach: Scan every cell; whenever an unvisited '1' is
     * found, that's a new island - increment the count and run BFS/DFS
     * (4-directional) from it, marking every connected '1' as visited (or
     * sinking it to '0') so it's never counted again.
     * <p>
     * Target Time Complexity: O(m * n) - each cell visited a constant
     * number of times.
     * <br>
     * Target Space Complexity: O(m * n) worst case - BFS queue or DFS
     * recursion stack for a grid that's entirely land.
     */
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m =grid[0].length;
//        System.out.println("ffff");

        boolean[][] isVisited = new boolean[n][m];


        int countIsland=0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j]=='1' && isVisited[i][j]==false){
                    markVisited(grid, isVisited, i, j, n, m);
                    countIsland++;
                }

            }
        }

        return countIsland;
    }

    private void markVisited(char[][] grid, boolean[][] isVisited, int i, int j, int n, int m) {

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(i,j));

        isVisited[i][j]=true;

        int[] dirX={1,-1,0,0};
        int[] dirY={0,0,1,-1};

        while(!q.isEmpty()){
            Pair currCoordinate=q.poll();
            for(int c=0; c<4; c++){
                int newX=dirX[c]+currCoordinate.x;
                int newY=dirY[c]+currCoordinate.y;

                if(newX<0 || newX>=n || newY<0 || newY>=m || isVisited[newX][newY] || grid[newX][newY]!='1') continue;
                q.offer(new Pair(newX, newY));
                isVisited[newX][newY]=true;
            }
        }
    }
}
