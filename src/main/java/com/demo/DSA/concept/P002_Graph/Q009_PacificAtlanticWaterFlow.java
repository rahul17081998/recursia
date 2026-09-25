package com.demo.DSA.concept.P002_Graph;

import java.util.List;

/**
 * Q009. Pacific Atlantic Water Flow
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 * <p>
 * There is an m x n rectangular island bordered by the Pacific Ocean (top
 * and left edges) and the Atlantic Ocean (bottom and right edges).
 * heights[r][c] is the height of cell (r, c). Water can flow from a cell
 * to an adjacent 4-directional cell with height less than or equal to the
 * current cell's height. Return the list of coordinates [r, c] from which
 * water can flow to BOTH oceans.
 *
 * <pre>
 * Example 1:
 * Input:
 * heights = [
 *   [1,2,2,3,5],
 *   [3,2,3,4,4],
 *   [2,4,5,3,1],
 *   [6,7,1,4,5],
 *   [5,1,1,2,4]
 * ]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 *
 * Example 2:
 * Input: heights = [[1]]
 * Output: [[0,0]]
 * Explanation: The single cell touches both oceans trivially.
 *
 * Constraints:
 * - m == heights.length, n == heights[r].length
 * - 1 &lt;= m, n &lt;= 200
 * - 0 &lt;= heights[r][c] &lt;= 10^5
 * </pre>
 */
public class Q009_PacificAtlanticWaterFlow {

    /**
     * @implNote TODO: implement.
     * Target approach: Reverse the flow direction and think from the
     * oceans inward - water flows "uphill" from an ocean's border cells
     * to any 4-directional neighbor with height >= the current cell
     * (since forward flow requires height non-increasing). Run BFS/DFS
     * from all Pacific border cells to mark pacificReachable[][], and
     * separately from all Atlantic border cells to mark
     * atlanticReachable[][]. The answer is every cell marked true in
     * both.
     * <p>
     * Target Time Complexity: O(m * n) - two independent multi-source
     * BFS/DFS traversals, each visiting every cell at most once.
     * <br>
     * Target Space Complexity: O(m * n) - two boolean grids plus BFS
     * queues/recursion stacks.
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // TODO: implement
        return null;
    }
}
