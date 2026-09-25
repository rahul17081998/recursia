package com.demo.DSA.concept.P002_Graph;

/**
 * Q005. Flood Fill
 * https://leetcode.com/problems/flood-fill/
 * <p>
 * You are given an image represented by an m x n grid of integers, three
 * integers sr, sc, and color. Perform a flood fill starting from pixel
 * (sr, sc): change the color of that pixel and every pixel connected to
 * it 4-directionally with the same original color, to the new color.
 * Return the modified image.
 *
 * <pre>
 * Example 1:
 * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 *
 * Example 2:
 * Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
 * Output: [[0,0,0],[0,0,0]]
 * Explanation: The starting pixel's color already equals the new color,
 * so no pixels are changed.
 *
 * Constraints:
 * - m == image.length, n == image[i].length
 * - 1 &lt;= m, n &lt;= 50
 * - 0 &lt;= image[i][j], color &lt; 2^16
 * - 0 &lt;= sr &lt; m, 0 &lt;= sc &lt; n
 * </pre>
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
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        // TODO: implement
        return image;
    }
}
