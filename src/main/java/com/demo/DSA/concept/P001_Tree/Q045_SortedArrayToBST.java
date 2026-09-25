package com.demo.DSA.concept.P001_Tree;

/**
 * Q045. Convert Sorted Array to Binary Search Tree
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * <p>
 * Given an integer array nums where the elements are sorted in ascending
 * order, convert it to a height-balanced binary search tree (a BST in
 * which the depth of the two subtrees of every node never differs by more
 * than one). There may be multiple valid answers.
 *
 * <pre>
 * Example 1:
 * Input: nums = [-10,-3,0,5,9]
 *
 * Output (one valid tree):
 *          0
 *         /  \
 *       -3    9
 *       /     /
 *    -10     5
 *
 * Example 2:
 * Input: nums = [1,3]
 *
 * Output (one valid tree):
 *      3
 *     /
 *    1
 *
 * Constraints:
 * - 1 &lt;= nums.length &lt;= 10^4
 * - -10^4 &lt;= nums[i] &lt;= 10^4
 * - nums is sorted in a strictly increasing order.
 * </pre>
 */
public class Q045_SortedArrayToBST {

    /**
     * @implNote TODO: implement.
     * Target approach: Recursively pick the middle element of the current
     * array range as the root (this keeps the tree balanced), then
     * recurse on the left half for the left subtree and the right half for
     * the right subtree.
     * <p>
     * Target Time Complexity: O(n) - every element becomes exactly one
     * node.
     * <br>
     * Target Space Complexity: O(log n) - recursion stack depth for a
     * balanced tree, excluding the output tree itself.
     */
    public TreeNode sortedArrayToBST(int[] nums) {
       return solve(nums, 0, nums.length-1);
    }

    private TreeNode solve(int[] nums, int left, int right) {
        if(left>right) return null;
        int mid = left + (right-left)/2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = solve(nums, left, mid-1);
        root.right= solve(nums, mid+1, right);
        return root;
    }


}
