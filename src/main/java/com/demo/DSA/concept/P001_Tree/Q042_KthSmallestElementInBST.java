package com.demo.DSA.concept.P001_Tree;

/**
 * Q042. Kth Smallest Element in a BST
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * <p>
 * Given the root of a binary search tree and an integer k, return the kth
 * smallest value (1-indexed) among all the values of the nodes in the
 * tree.
 *
 * <pre>
 * Example 1:
 * Input:
 *          3
 *         / \
 *        1   4
 *         \
 *          2
 *
 * k = 1
 * Output: 1
 *
 * Example 2:
 * Input:
 *              5
 *            /   \
 *           3     6
 *          / \
 *         2   4
 *        /
 *       1
 *
 * k = 3
 * Output: 3
 * Explanation: Sorted order is [1,2,3,4,5,6]; the 3rd smallest is 3.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 10^4].
 * - 0 &lt;= Node.val &lt;= 10^4
 * - 1 &lt;= k &lt;= number of nodes in the tree
 * </pre>
 */
public class Q042_KthSmallestElementInBST {

    /**
     * @implNote TODO: implement.
     * Target approach: An inorder traversal of a BST visits nodes in
     * ascending order, so do an inorder traversal (recursive or
     * iterative-with-stack, to allow early exit) and stop as soon as the
     * kth node visited is reached.
     * <p>
     * Target Time Complexity: O(h + k) with the iterative stack approach
     * (best case), O(n) worst case for a recursive full traversal.
     * <br>
     * Target Space Complexity: O(h) - stack/recursion depth equals tree
     * height h.
     */
    public int kthSmallest(TreeNode root, int k) {
        // TODO: implement
        int[] count = {0};
        int[] ans= {Integer.MAX_VALUE};
        solve(root, k, ans, count);
        return ans[0];
    }

    private void solve(TreeNode root, int k, int[] ans, int[] count) {
        if(root==null) return;
        solve(root.left, k, ans, count);
        count[0]++;
        if(k== count[0]) {
            ans[0]= root.val;
            return;
        }
        solve(root.right, k, ans, count);
    }
}
