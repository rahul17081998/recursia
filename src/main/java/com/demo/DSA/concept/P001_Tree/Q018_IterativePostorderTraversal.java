package com.demo.DSA.concept.P001_Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Q018. Binary Tree Postorder Traversal (Iterative)
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 * <p>
 * Given the root of a binary tree, return the postorder traversal of its
 * nodes' values, without using recursion (use one or two explicit stacks
 * instead).
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
 * Output: [3,2,1]
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
public class Q018_IterativePostorderTraversal {

    /**
     * @implNote TODO: implement.
     * Target approach: Two-stack trick — push root onto stack1; pop from
     * stack1 and push onto stack2, pushing left child then right child onto
     * stack1 (reverse of preorder's push order). Finally pop everything
     * from stack2 to get postorder. (A one-stack variant using a
     * "last visited" pointer also works.)
     * <p>
     * Target Time Complexity: O(n) - every node is pushed/popped a constant
     * number of times.
     * <br>
     * Target Space Complexity: O(n) - two stacks, each up to size n in the
     * worst case.
     */
    public List<Integer> postorderTraversalIterative(TreeNode root) {
        // TODO: implement
        return new ArrayList<>();
    }
}
