package com.demo.DSA.concept.P001_Tree;

 /**
 * Q041. Validate Binary Search Tree
 * https://leetcode.com/problems/validate-binary-search-tree/
 * <p>
 * Given the root of a binary tree, determine if it is a valid binary
 * search tree (BST). A valid BST is defined as: the left subtree of a
 * node contains only nodes with values strictly less than the node's
 * value; the right subtree contains only nodes with values strictly
 * greater; and both the left and right subtrees must also be valid BSTs
 * (not just compared to their immediate parent).
 *
 * <pre>
 * Example 1:
 * Input:
 *      2
 *     / \
 *    1   3
 *
 * Output: true
 *
 * Example 2:
 * Input:
 *            5
 *          /   \
 *         1     4
 *              /  \
 *             3    6
 *
 * Output: false
 * Explanation: The root's value is 5 but its right child's left child (3)
 * is less than 5, violating the BST property for the whole subtree.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 10^4].
 * - -2^31 &lt;= Node.val &lt;= 2^31 - 1
 * </pre>
 */
public class Q041_ValidateBinarySearchTree {

    /**
     * @implNote
     * Target approach: Recursive DFS carrying a valid (min, max) bound for
     * each node (using Long to safely handle Integer.MIN/MAX_VALUE edge
     * cases) - a node must lie strictly within its bounds; recurse left
     * with an updated upper bound and right with an updated lower bound.
     * Alternatively, do an inorder traversal and check the sequence is
     * strictly increasing.
     * <p>
     * Target Time Complexity: O(n) - every node visited once.
     * <br>
     * Target Space Complexity: O(h) - recursion stack depth equals tree
     * height h.
     */
    public boolean isValidBST(TreeNode root) {
        // basically we have to check for each node , left subtree should not contain the greater than the node, and
        // it's right subtree nodes should be greater than then node value
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long min, long max) {
        if(root==null) return true;
        if(root.val<=min || root.val>=max) return false;

        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);

    }
}
