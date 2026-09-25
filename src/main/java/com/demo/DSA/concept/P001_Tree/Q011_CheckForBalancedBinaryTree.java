package com.demo.DSA.concept.P001_Tree;

/**
 * Q011. Check for Balanced Binary Tree
 * <p>
 * Given the root of a binary tree, determine whether it is height-balanced,
 * i.e. for every node, the height difference between its left and right
 * subtrees is no more than 1.
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
 * Output: true
 * </pre>
 */
public class Q011_CheckForBalancedBinaryTree {

    /**
     * @implNote Approach: Single-pass recursive DFS. While computing each
     * node's height bottom-up, also check whether
     * |leftHeight - rightHeight| > 1 and flip a shared mutable flag to
     * false if so — avoids the O(n^2) approach of recomputing height at
     * every node separately.
     * <p>
     * Time Complexity: O(n) - every node is visited exactly once.
     * <br>
     * Space Complexity: O(h) - recursion stack depth equals the tree
     * height h (O(n) worst case for a skewed tree, O(log n) if balanced).
     */
    public boolean isBalancedTree(TreeNode root) {

        boolean[] isBalanced= {true};
        getHeight(root, isBalanced);
        return isBalanced[0];
    }

    private int getHeight(TreeNode root, boolean[] isBalanced) {
        if(root==null) return 0;
        int leftH = getHeight(root.left, isBalanced);
        int rightH = getHeight(root.right, isBalanced);
        if(Math.abs(leftH-rightH)>1) isBalanced[0]=false;

        return 1 + Math.max(leftH,rightH);
    }


}
