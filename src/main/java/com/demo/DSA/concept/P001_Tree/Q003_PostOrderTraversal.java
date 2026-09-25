package com.demo.DSA.concept.P001_Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Q003. Binary Tree Postorder Traversal
 * <p>
 * Given the root of a binary tree, return the postorder traversal of its
 * nodes' values (left subtree, then right subtree, then root).
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
 * Output: [4, 5, 2, 6, 3, 1]
 * </pre>
 */
public class Q003_PostOrderTraversal {

    /**
     * @implNote Approach: Recursive DFS — visit the left subtree, then the
     * right subtree, then the current node.
     * <p>
     * Time Complexity: O(n) - every node is visited exactly once.
     * <br>
     * Space Complexity: O(h) - recursion stack depth equals the tree
     * height h (O(n) worst case for a skewed tree, O(log n) if balanced).
     */
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }

    private void postOrder(TreeNode root, List<Integer> result) {
        if(root==null) return;

        postOrder(root.left, result);
        postOrder(root.right, result);
        result.add(root.val);
    }
}
    

