package com.demo.DSA.concept.P001_Tree;

/**
 * Q030. Binary Tree Maximum Path Sum
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * <p>
 * A path in a binary tree is a sequence of nodes where each pair of
 * adjacent nodes has an edge connecting them, and a node cannot appear in
 * the sequence more than once. Given the root of a binary tree, return the
 * maximum path sum of any non-empty path (the path does not need to pass
 * through the root, and node values may be negative).
 *
 * <pre>
 * Example 1:
 * Input:
 *      1
 *     / \
 *    2   3
 *
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3, with sum 2 + 1 + 3 = 6.
 *
 * Example 2:
 * Input:
 *          -10
 *          /  \
 *         9   20
 *             /  \
 *            15    7
 *
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7, with sum 15 + 20 + 7 = 42.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 3 * 10^4].
 * - -1000 &lt;= Node.val &lt;= 1000
 * </pre>
 */
public class Q030_BinaryTreeMaximumPathSum {

    /**
     * @implNote TODO: implement.
     * Target approach: Post-order DFS returning the best downward path sum
     * starting at a node (clamped to 0 if negative, since a negative branch
     * should just be excluded). While recursing, track the global max of
     * (node.val + best left downward + best right downward) in a mutable
     * holder, since a path "through" a node can use both children, but the
     * value returned to the parent can only use one side.
     * <p>
     * Target Time Complexity: O(n) - single DFS pass, every node visited
     * once.
     * <br>
     * Target Space Complexity: O(h) - recursion stack depth equals tree
     * height h.
     */
    public int maxPathSum(TreeNode root) {
        if(root==null) return Integer.MIN_VALUE;
        int[] maxSum = {Integer.MIN_VALUE};
        solve(root, maxSum);
        return maxSum[0];
    }

    private int solve(TreeNode root, int[] maxSum) {
        if(root==null) return 0;



        int leftMaxSum=  solve(root.left, maxSum);
        int rightMaxSum = solve(root.right, maxSum);


        maxSum[0] = Math.max(maxSum[0], leftMaxSum+rightMaxSum+root.val);

        if((root.val + Math.max(Math.max(leftMaxSum, rightMaxSum), 0))<0) return 0;
        return root.val + Math.max(Math.max(leftMaxSum, rightMaxSum), 0);
    }
}
