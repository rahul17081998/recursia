package com.demo.DSA.concept.P001_Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Q017. Binary Tree Preorder Traversal (Iterative)
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 * <p>
 * Given the root of a binary tree, return the preorder traversal of its
 * nodes' values, without using recursion (use an explicit stack instead).
 *
 * <pre>
 * Example 1:
 * Input: root = [1,null,2,3]
 *
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,2,3]
 *
 * Example 2:
 * Input: root = []  (empty tree)
 * Output: []
 *
 * Example 3:
 * Input: root = [1]
 *
 *    1
 *
 * Output: [1]
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 100].
 * - -100 &lt;= Node.val &lt;= 100
 * </pre>
 */
public class Q017_IterativePreorderTraversal {

    /**
     * @implNote TODO: implement.
     * Target approach: Use an explicit stack. Push root; while stack is not
     * empty, pop, visit, then push right child first and left child second
     * (so left is processed first).
     * <p>
     * Target Time Complexity: O(n) - every node is pushed and popped once.
     * <br>
     * Target Space Complexity: O(h) - stack holds at most the height h of
     * the tree (O(n) worst case for a skewed tree).
     */
    public List<Integer> preorderTraversalIterative(TreeNode root) {
        // TODO: implement
        return new ArrayList<>();
    }
}
