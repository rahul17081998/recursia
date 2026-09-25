package com.demo.DSA.concept.P001_Tree;

/**
 * Q039. Path Sum III
 * https://leetcode.com/problems/path-sum-iii/
 * <p>
 * Given the root of a binary tree and an integer targetSum, return the
 * number of paths where the sum of the values along the path equals
 * targetSum. The path does not need to start or end at the root or a
 * leaf, but it must go downwards (traveling only from parent nodes to
 * child nodes).
 *
 * <pre>
 * Example 1:
 * Input:
 *              10
 *            /    \
 *           5      -3
 *          / \        \
 *         3   2        11
 *        / \   \
 *       3  -2   1
 *
 * targetSum = 8
 * Output: 3
 * Explanation: The paths are 5 -> 3 (sum 8), 5 -> 2 -> 1 (sum 8), and
 * -3 -> 11 (sum 8).
 *
 * Example 2:
 * Input:
 *    1
 *
 * targetSum = 1
 * Output: 1
 * Explanation: The single-node path [1] itself sums to 1.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 1000].
 * - -10^9 &lt;= Node.val &lt;= 10^9
 * - -1000 &lt;= targetSum &lt;= 1000
 * </pre>
 */
public class Q039_PathSumIII {

    /**
     * @implNote TODO: implement.
     * Target approach: DFS while maintaining a running prefix sum from the
     * root to the current node, stored in a HashMap&lt;prefixSum, count&gt;
     * (the "prefix sum" trick, same idea as LeetCode's Subarray Sum Equals
     * K). At each node, check how many times (currentPrefixSum -
     * targetSum) has occurred so far - that count is the number of valid
     * paths ending at this node. Increment the map entry for the current
     * prefix sum before recursing into children, and decrement it
     * (backtrack) after returning.
     * <p>
     * Target Time Complexity: O(n) - each node visited once with O(1)
     * amortized map operations.
     * <br>
     * Target Space Complexity: O(n) - prefix sum map plus O(h) recursion
     * stack.
     */
    public int pathSumIII(TreeNode root, int targetSum) {
        // TODO: implement
        return -1;
    }
}
