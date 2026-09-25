package com.demo.DSA.concept.P001_Tree;

/**
 * Q025. Construct Binary Tree from Preorder and Inorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * <p>
 * Given two integer arrays preorder and inorder where preorder is the
 * preorder traversal of a binary tree and inorder is the inorder traversal
 * of the same tree, construct and return the binary tree. All node values
 * are assumed unique.
 *
 * <pre>
 * Example 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output:
 *          3
 *         / \
 *        9  20
 *           / \
 *          15  7
 *
 * Example 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output:
 *        -1
 *
 * Constraints:
 * - 1 &lt;= preorder.length &lt;= 3000
 * - inorder.length == preorder.length
 * - -3000 &lt;= preorder[i], inorder[i] &lt;= 3000
 * - preorder and inorder consist of unique values
 * - Each value of inorder also appears in preorder
 * - preorder is guaranteed to be the preorder traversal of the tree
 * - inorder is guaranteed to be the inorder traversal of the tree
 * </pre>
 */
public class Q025_ConstructTreeFromPreorderAndInorder {

    /**
     * @implNote TODO: implement.
     * Target approach: The first element of preorder is always the root.
     * Find that value's index in inorder to split the array into left and
     * right subtree ranges, then recurse on each range, advancing a shared
     * preorder index. Use a value-to-index map over inorder for O(1)
     * lookups instead of scanning.
     * <p>
     * Target Time Complexity: O(n) - each node processed once, O(1) lookup
     * per node with the index map.
     * <br>
     * Target Space Complexity: O(n) - index map plus O(h) recursion stack.
     */
    public TreeNode buildTreeFromPreIn(int[] preorder, int[] inorder) {
        // TODO: implement
        return null;
    }
}
