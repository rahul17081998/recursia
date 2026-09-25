package com.demo.DSA.concept.P001_Tree;

/**
 * Q008. Maximum Value in Binary Tree
 * <p>
 * Given the root of a binary tree, return the largest value among all
 * node values in the tree.
 *
 * <pre>
 * Example:
 * Input:
 *            1
 *          /   \
 *         2     3
 *        / \     \
 *       4   5     6
 *
 * Output: 6
 * </pre>
 */
public class Q008_MaximumInBinaryTree {

    /**
     * @implNote Approach: Recursive DFS — the max of a subtree is the
     * largest of the current node's value, the max of the left subtree,
     * and the max of the right subtree; a null node contributes 0.
     * <p>
     * Time Complexity: O(n) - every node is visited exactly once.
     * <br>
     * Space Complexity: O(h) - recursion stack depth equals the tree
     * height h (O(n) worst case for a skewed tree, O(log n) if balanced).
     */
    public int getLargetNodeByValue(TreeNode root) {
        return solve(root);
    }

    private int solve(TreeNode root) {
        if(root==null) return 0;
        return Math.max(root.val, Math.max(solve(root.left), solve(root.right)));
    }


}
    

