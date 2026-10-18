package com.demo.DSA.concept.P002_Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Q005. Flood Fill
 * https://leetcode.com/problems/flood-fill/
 * <p>
 * You are given a picture stored as an {@code m x n} grid of integers,
 * {@code image}, where {@code image[i][j]} is the color (pixel value) of
 * the pixel in row {@code i}, column {@code j}. You are also given three
 * integers: {@code sr} and {@code sc} (the row and column of a starting
 * pixel) and {@code color} (the new color). Perform a <b>flood fill</b>
 * starting from {@code image[sr][sc]} and return the modified image.
 * <p>
 * This is the "paint bucket" tool from any paint program: click one
 * pixel, and the whole same-colored region around it changes color.
 * <p>
 * <b>The fill works like this:</b>
 * <ol>
 *   <li>Remember the <b>original color</b> of the starting pixel
 *   {@code image[sr][sc]}, then paint the starting pixel with
 *   {@code color}.</li>
 *   <li>Look at the pixels <b>directly adjacent</b> to it - up, down,
 *   left and right (they share a side). <b>Diagonal pixels do not
 *   count.</b> Every adjacent pixel that has the <b>original color</b> is
 *   painted with {@code color} too.</li>
 *   <li>Repeat step 2 from every pixel you just painted, spreading
 *   outward.</li>
 *   <li>Stop when no adjacent pixel with the original color is left.
 *   Pixels of any <b>other</b> color act as walls - the fill never
 *   changes them and never passes through them.</li>
 * </ol>
 * Only pixels whose color equals the starting pixel's <b>original</b>
 * color are ever repainted. If the original color already equals
 * {@code color}, nothing changes.
 *
 * <pre>
 * Example 1:
 * Input: image = [[1,1,1],
 *                 [1,1,0],
 *                 [1,0,1]], sr = 1, sc = 1, color = 2
 * Output: [[2,2,2],
 *          [2,2,0],
 *          [2,0,1]]
 * Explanation: The start (1,1) has color 1. Every 1 connected to it by
 * up/down/left/right steps becomes 2. The 1 in the bottom-right corner
 * (2,2) stays 1: its only neighbours are 0s, and it touches the region
 * only diagonally, which does not count.
 *
 * Example 2:
 * Input: image = [[0,0,0],
 *                 [0,0,0]], sr = 0, sc = 0, color = 0
 * Output: [[0,0,0],
 *          [0,0,0]]
 * Explanation: The starting pixel's color (0) already equals the new
 * color, so no pixels are changed.
 *
 * Example 3 (a different color is a wall, not part of the fill):
 * Input: image = [[1,3],
 *                 [1,1]], sr = 0, sc = 0, color = 2
 * Output: [[2,3],
 *          [2,2]]
 * Explanation: The original color is 1, so only 1s are repainted. The 3
 * touches the region but has a different color, so it stays 3.
 *
 * Example 4 (the original color can be 0 - it is not special):
 * Input: image = [[0,0],
 *                 [0,0]], sr = 0, sc = 0, color = 5
 * Output: [[5,5],
 *          [5,5]]
 * Explanation: The original color is 0, so every connected 0 becomes 5.
 *
 * Constraints:
 * - m == image.length, n == image[i].length
 * - 1 &lt;= m, n &lt;= 50
 * - 0 &lt;= image[i][j], color &lt; 2^16
 * - 0 &lt;= sr &lt; m, 0 &lt;= sc &lt; n
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * Treat every pixel as a graph node with an edge to each of its 4
 * neighbours - but only walk an edge if the neighbour still has the
 * original color. Then the region to repaint is exactly the connected
 * component that contains {@code (sr, sc)}, so BFS (queue) or DFS
 * (recursion/stack) from the start finds it - the same idea as
 * {@link Q004_NumberOfIslands}, run once instead of from every cell.
 * <p>
 * Repainting a pixel also marks it as visited: once it holds the new
 * color it no longer matches the original color, so it is never added
 * again. That trick only works when the two colors differ - which is why
 * you must return early when {@code image[sr][sc] == color}, or the
 * search loops forever.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://leetcode.com/problems/flood-fill/">LeetCode 733 - Flood Fill</a>
 *   - the canonical version, with both BFS and DFS solutions in the editorial.</li>
 *   <li><a href="https://leetcode.com/problems/number-of-islands/">LeetCode 200 - Number of Islands</a>
 *   - the natural next step: run this same fill from every unvisited land cell.</li>
 * </ul>
 */
public class Q005_FloodFill {

    /**
     * @implNote TODO: implement.
     * Target approach: Record the starting pixel's original color; if it
     * already equals the new color, return the image unchanged (otherwise
     * infinite recursion is possible when color == originalColor). Else
     * run BFS/DFS from (sr, sc), recoloring every 4-directionally
     * connected pixel that still has the original color.
     * <p>
     * Target Time Complexity: O(m * n) - each pixel visited at most once.
     * <br>
     * Target Space Complexity: O(m * n) worst case - BFS queue or DFS
     * recursion stack.
     */
    int[] dirX={1,-1,0,0};
    int[] dirY={0,0,1,-1};
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if(image[sr][sc]==color) return image;
        int initialColor=image[sr][sc];
        int n = image.length; int m=image[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sr,sc});
        image[sr][sc]=color;

        while(!q.isEmpty()){
            int[] pixelLocation =q.poll();
            for(int i=0; i<4; i++){
                int x=dirX[i]+pixelLocation[0];
                int y=dirY[i]+pixelLocation[1];
                if(x<0 || x>=n || y<0 || y>=m || image[x][y]!=initialColor) continue;
                image[x][y]=color;
                q.offer(new int[]{x,y});
            }
        }
        return image;
    }
}
