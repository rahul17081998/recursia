package com.demo.DSA.concept.P001_Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Q023. Bottom View of Binary Tree
 * https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1
 * <p>
 * Given a binary tree, print the bottom view - the set of nodes visible
 * when the tree is viewed from the bottom. Each node has a horizontal
 * distance (HD) from the root, same rule as top view. For each HD, the
 * bottom-most node (i.e., the last one encountered in level order) is
 * included; if two nodes share an HD, the one appearing later in level
 * order wins.
 *
 * <pre>
 * Example 1 (from GFG):
 * Input:
 *            1
 *          /   \
 *         2     3
 *        / \     \
 *       4   5     6
 *
 * Output: [4, 2, 5, 3, 6]
 * Explanation: node 5 (HD 0) overwrites node 1 (HD 0) since 5 is seen
 * later during level order traversal.
 *
 * Example 2 (from GFG):
 * Input:
 *                  20
 *               /      \
 *              8        22
 *             / \       / \
 *            5   3     4   25
 *               / \        /
 *             10   14    28
 *
 * Output: [5, 10, 4, 28, 25]
 *
 * Constraints (typical GFG bounds for this problem):
 * - 1 &lt;= number of nodes &lt;= 10^5
 * - Expected Time Complexity: O(N)
 * - Expected Auxiliary Space: O(N)
 * </pre>
 */
public class Q023_BottomViewOfBinaryTree {

    /**
     * @implNote TODO: implement.
     * Target approach: BFS level order while tracking horizontal distance
     * (HD) per node. Use a TreeMap&lt;Integer, Integer&gt; keyed by HD and
     * always overwrite the value for that HD (since BFS visits nodes in
     * top-to-bottom, left-to-right order, the last write per HD is the
     * bottom-most node). Read the map in key order.
     * <p>
     * Target Time Complexity: O(n log n) - n queue operations plus
     * TreeMap insertions.
     * <br>
     * Target Space Complexity: O(n) - queue plus the HD map.
     */
    public List<Integer> bottomView(TreeNode root) {
        // TODO: implement
        return new ArrayList<>();
    }
}
