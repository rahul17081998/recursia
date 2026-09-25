package com.demo.DSA.concept.P001_Tree;

/**
 * Q038. Path Sum
 * https://leetcode.com/problems/path-sum/
 * <p>
 * Given the root of a binary tree and an integer targetSum, return true if
 * the tree has a root-to-leaf path such that adding up all the values
 * along the path equals targetSum, and false otherwise.
 *
 * <pre>
 * Example 1:
 * Input:
 *              5
 *            /   \
 *           4     8
 *          /     / \
 *         11    13  4
 *        /  \         \
 *       7    2          1
 *
 * targetSum = 22
 * Output: true
 * Explanation: 5 -> 4 -> 11 -> 2 sums to 22.
 *
 * Example 2:
 * Input:
 *      1
 *     / \
 *    2   3
 *
 * targetSum = 5
 * Output: false
 * Explanation: There are two root-to-leaf paths (1-&gt;2 = 3, 1-&gt;3 = 4);
 * neither equals 5.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 5000].
 * - -1000 &lt;= Node.val &lt;= 1000
 * - -1000 &lt;= targetSum &lt;= 1000
 * </pre>
 */
public class Q038_PathSum {


    /**
     * @implNote TODO: implement.
     * Target approach: Recursive DFS subtracting the current node's value
     * from the remaining target as you descend; at a leaf node, check if
     * the remaining target equals the leaf's value. A null root has no
     * path, so return false immediately.
     * <p>
     * Target Time Complexity: O(n) - worst case visits every node.
     * <br>
     * Target Space Complexity: O(h) - recursion stack depth equals tree
     * height h.
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null) return false;
        boolean[] isSumExist= {false};
        istTargetSumAvailable(root, 0, targetSum, isSumExist);
        return isSumExist[0];
    }

    private void istTargetSumAvailable(TreeNode root, int currSum, int targetSum, boolean[] isSumExist) {
        if(root==null) return ;
        currSum +=root.val;
        if(root.left==null && root.right==null && targetSum==currSum) isSumExist[0]=true;


        istTargetSumAvailable(root.left, currSum, targetSum, isSumExist);
        istTargetSumAvailable(root.right, currSum, targetSum, isSumExist);
        currSum -=root.val;

    }
}
