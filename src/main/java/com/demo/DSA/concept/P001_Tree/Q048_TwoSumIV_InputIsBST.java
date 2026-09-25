package com.demo.DSA.concept.P001_Tree;

/**
 * Q048. Two Sum IV - Input is a BST
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
 * <p>
 * Given the root of a binary search tree and an integer k, return true if
 * there exist two elements in the BST such that their sum equals k, and
 * false otherwise.
 *
 * <pre>
 * Example 1:
 * Input:
 *          5
 *        /   \
 *       3     6
 *      / \      \
 *     2   4      7
 *
 * k = 9
 * Output: true
 * Explanation: 2 + 7 = 9 (or 3 + 6 = 9).
 *
 * Example 2:
 * Input:
 *          5
 *        /   \
 *       3     6
 *      / \      \
 *     2   4      7
 *
 * k = 28
 * Output: false
 * Explanation: No two distinct node values sum to 28 (max possible pair
 * is 6 + 7 = 13).
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 10^4].
 * - -10^4 &lt;= Node.val &lt;= 10^4
 * - root is guaranteed to be a valid binary search tree.
 * - -10^5 &lt;= k &lt;= 10^5
 * </pre>
 */
public class Q048_TwoSumIV_InputIsBST {

    /**
     * @implNote TODO: implement.
     * Target approach: Traverse the tree (any order) while maintaining a
     * HashSet of values seen so far; for each node, check if (k -
     * node.val) is already in the set - if so, a pair is found. Otherwise
     * add node.val to the set and continue. (The BST property isn't
     * strictly required for this approach, but an inorder traversal +
     * two-pointer technique is an alternative that exploits it.)
     * <p>
     * Target Time Complexity: O(n) - every node visited once with O(1)
     * amortized set operations.
     * <br>
     * Target Space Complexity: O(n) - hash set holding up to all node
     * values.
     */
    public boolean findTarget(TreeNode root, int k) {
        // TODO: implement
        return false;
    }
}
