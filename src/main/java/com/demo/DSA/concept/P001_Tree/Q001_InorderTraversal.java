package com.demo.DSA.concept.P001_Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Q001. Binary Tree Inorder Traversal
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * <p>
 * Given the root of a binary tree, return the inorder traversal of its
 * nodes' values (left subtree, then root, then right subtree).
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
 * Output: [4, 2, 5, 1, 3, 6]
 * </pre>
 */
public class Q001_InorderTraversal {

    /**
     * @implNote Approach: Recursive DFS — visit the left subtree, then the
     * current node, then the right subtree.
     * <p>
     * Time Complexity: O(n) - every node is visited exactly once.
     * <br>
     * Space Complexity: O(h) - recursion stack depth equals the tree
     * height h (O(n) worst case for a skewed tree, O(log n) if balanced).
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    private void traverse(TreeNode node, List<Integer> result) {
        if (node == null) return;
        traverse(node.left, result);
        result.add(node.val);
        traverse(node.right, result);
    }
}

