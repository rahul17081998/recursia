package com.demo.DSA.concept.P001_Tree;

/**
 * Q007. Size of Binary Tree
 * <p>
 * Given the root of a binary tree, return the total number of nodes in it.
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
public class Q007_SizeOfBinaryTree {

    /**
     * @implNote Approach: Recursive DFS — the size of a subtree is
     * 1 (the node itself) + size of left subtree + size of right subtree;
     * a null node contributes 0.
     * <p>
     * Time Complexity: O(n) - every node is visited exactly once.
     * <br>
     * Space Complexity: O(h) - recursion stack depth equals the tree
     * height h (O(n) worst case for a skewed tree, O(log n) if balanced).
     */
    public int findTotalNodesOfBinaryTree(TreeNode root) {
        return solve(root);
    }

    private int solve(TreeNode root) {
        if(root==null) return 0;
        return 1 + solve(root.left) + solve(root.right);
    }


}
    

