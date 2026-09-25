package com.demo.DSA.concept.P001_Tree;

/**
 * Q026. Construct Binary Tree from Inorder and Postorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * <p>
 * Given two integer arrays inorder and postorder where inorder is the
 * inorder traversal of a binary tree and postorder is the postorder
 * traversal of the same tree, construct and return the binary tree. All
 * node values are assumed unique.
 *
 * <pre>
 * Example 1:
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output:
 *          3
 *         / \
 *        9  20
 *           / \
 *          15  7
 *
 * Example 2:
 * Input: inorder = [-1], postorder = [-1]
 * Output:
 *        -1
 *
 * Constraints:
 * - 1 &lt;= inorder.length &lt;= 3000
 * - postorder.length == inorder.length
 * - -3000 &lt;= inorder[i], postorder[i] &lt;= 3000
 * - inorder and postorder consist of unique values
 * - Each value of postorder also appears in inorder
 * - inorder is guaranteed to be the inorder traversal of the tree
 * - postorder is guaranteed to be the postorder traversal of the tree
 * </pre>
 */
public class Q026_ConstructTreeFromInorderAndPostorder {

    /**
     * @implNote TODO: implement.
     * Target approach: The last element of postorder is always the root.
     * Find that value's index in inorder to split into left and right
     * subtree ranges, then recurse - build the right subtree before the
     * left subtree since postorder is consumed from the end backwards. Use
     * a value-to-index map over inorder for O(1) lookups.
     * <p>
     * Target Time Complexity: O(n) - each node processed once, O(1) lookup
     * per node with the index map.
     * <br>
     * Target Space Complexity: O(n) - index map plus O(h) recursion stack.
     */
    public TreeNode buildTreeFromInPost(int[] inorder, int[] postorder) {
        // TODO: implement
        return null;
    }
}
