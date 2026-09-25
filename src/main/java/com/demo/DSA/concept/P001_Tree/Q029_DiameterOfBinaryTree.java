package com.demo.DSA.concept.P001_Tree;

/**
 * Q029. Diameter of Binary Tree
 * https://leetcode.com/problems/diameter-of-binary-tree/
 * <p>
 * Given the root of a binary tree, return the length of the diameter of
 * the tree - the length (number of edges) of the longest path between any
 * two nodes, which may or may not pass through the root.
 *
 * <pre>
 * Example 1:
 * Input:
 *            1
 *          /   \
 *         2     3
 *        / \
 *       4   5
 *
 * Output: 3
 * Explanation: The longest path is [4, 2, 1, 3] or [5, 2, 1, 3], with 3
 * edges.
 *
 * Example 2:
 * Input:
 *    1
 *     \
 *      2
 *
 * Output: 1
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 10^4].
 * - -100 &lt;= Node.val &lt;= 100
 * </pre>
 */
public class Q029_DiameterOfBinaryTree {

    /**
     * @implNote TODO: implement.
     * Target approach: Post-order DFS computing height of each subtree;
     * while doing so, track the maximum of (leftHeight + rightHeight)
     * across all nodes in a mutable holder (the diameter through that
     * node), since it isn't the same value being returned up the
     * recursion.
     * <p>
     * Target Time Complexity: O(n) - single DFS pass, every node visited
     * once.
     * <br>
     * Target Space Complexity: O(h) - recursion stack depth equals tree
     * height h.
     */


    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null ||(root.left==null && root.right==null)) return 0;
        int[] diameter = {0};
        solve(root, diameter);
        return diameter[0];
    }

    private int solve(TreeNode root, int[] diameter) {
        if(root==null) return 0;


        int leftHeight=  solve(root.left, diameter);
        int rightHeight = solve(root.right, diameter);

        diameter[0] = Math.max(diameter[0], leftHeight+rightHeight);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
