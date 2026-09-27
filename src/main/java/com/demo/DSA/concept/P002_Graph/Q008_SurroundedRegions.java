package com.demo.DSA.concept.P002_Graph;

import java.util.*;

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
     *
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

    int[] dirX={1,-1,0,0};
    int[] dirY={0,0,-1,1};
    public void solve(char[][] board) {
        // TODO: implement

        int n=board.length;
        int m=board[0].length;
        if(n==1 || m==1) return;

        for(int row=0; row<n; row++){
            for(int col=0; col<m; col++){
                if((row==0 || row==n-1 || col==0 || col==m-1) && board[row][col]=='O'){
                    System.out.println("i and j : "+row+col+" value is :"+board[row][col]);
                    bfsMarkZeroRegion(row,col,board, n, m);
                }
            }
        }

        for(int row=0; row<n; row++){
            for(int col=0; col<m; col++){
                if(board[row][col]=='O'){
                    board[row][col]='X';
                }else if(board[row][col]=='Z'){
                    board[row][col]='O';
                }
            }
        }
    }

    private void bfsMarkZeroRegion(int row, int col, char[][] board, int n, int m) {
        Queue<List<Integer>> q = new LinkedList<>();
        q.offer(new ArrayList<>(Arrays.asList(row,col)));
        board[row][col]='Z'; // visited

        while(!q.isEmpty()){
            List<Integer>coordinate = q.poll();
            for(int d=0; d<4; d++){
                int newRow=dirX[d]+coordinate.get(0);
                int newCol=dirY[d]+coordinate.get(1);
                if(newRow<0 || newRow>=n || newCol<0 || newCol>=m || board[newRow][newCol]!='O') continue;
                q.offer(new ArrayList<>(Arrays.asList(newRow, newCol)));
                board[newRow][newCol]='Z';
            }
        }

    }
}
