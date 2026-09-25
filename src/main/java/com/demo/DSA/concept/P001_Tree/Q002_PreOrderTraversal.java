package com.demo.DSA.concept.P001_Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Q002. Binary Tree Preorder Traversal
 * <p>
 * Given the root of a binary tree, return the preorder traversal of its
 * nodes' values (root, then left subtree, then right subtree).
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
 * Output: [1, 2, 4, 5, 3, 6]
 * </pre>
 */
public class Q002_PreOrderTraversal {

        /**
         * @implNote Approach: Recursive DFS — visit the current node first,
         * then the left subtree, then the right subtree.
         * <p>
         * Time Complexity: O(n) - every node is visited exactly once.
         * <br>
         * Space Complexity: O(h) - recursion stack depth equals the tree
         * height h (O(n) worst case for a skewed tree, O(log n) if balanced).
         */
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            preorder(root, result);
            return result;
        }

        private void preorder(TreeNode root, List<Integer> result) {
            if(root==null) return;
            result.add(root.val);
            preorder(root.left, result);
            preorder(root.right, result);
        }
    }
    

