package com.demo.DSA.concept.P001_Tree;

/**
 * Q032. Symmetric Tree
 * https://leetcode.com/problems/symmetric-tree/
 * <p>
 * Given the root of a binary tree, check whether it is a mirror of itself
 * (i.e., symmetric around its center).
 *
 * <pre>
 * Example 1:
 * Input:
 *            1
 *          /   \
 *         2     2
 *        / \   / \
 *       3   4 4   3
 *
 * Output: true
 *
 * Example 2:
 * Input:
 *          1
 *        /   \
 *       2     2
 *        \     \
 *         3     3
 *
 * Output: false
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 1000].
 * - -100 &lt;= Node.val &lt;= 100
 * </pre>
 */
public class Q032_SymmetricTree {

    /**
     * @implNote TODO: implement.
     * Target approach:
     *
     * Recursive helper comparing two subtrees (initially
     * root.left and root.right) - they're mirrors if both are null, or
     * both are non-null with equal values and left1 mirrors right2 while
     * right1 mirrors left2.
     * <p>
     * Target Time Complexity: O(n) - every node visited once.
     * <br>
     * Target Space Complexity: O(h) - recursion stack depth equals tree
     * height h.
     */
    public boolean isSymmetric(TreeNode root) {
        return root==null || isSymmetricTree(root.left, root.right);
    }

    private boolean isSymmetricTree(TreeNode leftTree, TreeNode rightTree) {
        if(leftTree==null || rightTree==null) return leftTree==rightTree;
        if(leftTree.val!=rightTree.val) return false;

        return isSymmetricTree(leftTree.left, rightTree.right) && isSymmetricTree(leftTree.right, rightTree.left);
    }
}
