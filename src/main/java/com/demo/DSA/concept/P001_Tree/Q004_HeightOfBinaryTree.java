package com.demo.DSA.concept.P001_Tree;

/**
 * Q004. Height of Binary Tree
 * <p>
 * Given the root of a binary tree, return its height (the number of nodes
 * on the longest path from the root down to the farthest leaf).
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
 * Output: 3
 * </pre>
 */
public class Q004_HeightOfBinaryTree {

    /**
     * @implNote Approach: Recursive DFS — the height of a node is
     * 1 + max(height of left subtree, height of right subtree); a null
     * node has height 0.
     * <p>
     * Time Complexity: O(n) - every node is visited exactly once.
     * <br>
     * Space Complexity: O(h) - recursion stack depth equals the tree
     * height h (O(n) worst case for a skewed tree, O(log n) if balanced).
     */
    public int findHeightOfBinaryTree(TreeNode root) {
        return solve(root);
    }

    private int solve(TreeNode root) {
        if(root==null) return 0;
        return Math.max(solve(root.left), solve(root.right))+1;
    }


}
    

