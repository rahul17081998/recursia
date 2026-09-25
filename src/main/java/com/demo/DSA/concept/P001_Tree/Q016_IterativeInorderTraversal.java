package com.demo.DSA.concept.P001_Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Q016. Binary Tree Inorder Traversal (Iterative)
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * <p>
 * Given the root of a binary tree, return the inorder traversal of its
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
 * Output: [1,3,2]
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
public class Q016_IterativeInorderTraversal {

    /**
     * @implNote TODO: implement.
     * Target approach: Use an explicit {@link java.util.Deque} as a stack.
     * Push left children while descending; when null, pop, visit, then move
     * to the popped node's right child.
     * <p>
     * Target Time Complexity: O(n) - every node is pushed and popped once.
     * <br>
     * Target Space Complexity: O(h) - stack holds at most the height h of
     * the tree.
     */
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        // TODO: implement
        return new ArrayList<>();
    }
}
