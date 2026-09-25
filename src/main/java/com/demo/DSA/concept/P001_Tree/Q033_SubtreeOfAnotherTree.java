package com.demo.DSA.concept.P001_Tree;
/**
 * Q033. Subtree of Another Tree
 * https://leetcode.com/problems/subtree-of-another-tree/
 * <p>
 * Given the roots of two binary trees root and subRoot, return true if
 * there is a subtree of root with the same structure and node values as
 * subRoot, and false otherwise. A subtree of a tree consists of a node in
 * that tree and all of that node's descendants.
 *
 * <pre>
 * Example 1:
 * Input:
 * root:            3            subRoot:   4
 *                 /   \                   / \
 *                4     5                 1   2
 *               / \
 *              1   2
 *
 * Output: true
 *
 * Example 2:
 * Input:
 * root:              3            subRoot:   4
 *                   /   \                   / \
 *                  4     5                 1   2
 *                 / \
 *                1   2
 *               /
 *              0
 *
 * Output: false
 * Explanation: root's subtree rooted at 4 now has an extra node (0) under
 * 1, so it no longer matches subRoot exactly.
 *
 * Constraints:
 * - The number of nodes in root is in the range [1, 2000].
 * - The number of nodes in subRoot is in the range [1, 1000].
 * - -10^4 &lt;= Node.val &lt;= 10^4
 * </pre>
 */
public class Q033_SubtreeOfAnotherTree {

    /**
     * @implNote TODO: implement.
     * Target approach: For every node in root (DFS), check whether the
     * subtree rooted there is identical to subRoot, reusing a "same tree"
     * comparison such as {@link Q031_SameTree#isSameTree}. Return true as
     * soon as one match is found.
     * <p>
     * Target Time Complexity: O(m * n) - for each of the m nodes in root, a
     * comparison against the n-node subRoot may be performed.
     * <br>
     * Target Space Complexity: O(h1 + h2) - recursion stacks of both
     * traversals.
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root==null || subRoot==null)
            return root==subRoot;

        boolean []ans={false};
        isSubtreeExist(root, subRoot, ans);
        return ans[0];
    }

    private void isSubtreeExist(TreeNode root, TreeNode subRoot, boolean[] ans) {
        if(root==null) return;

        if(isSameSubTree(root, subRoot)) {
            ans[0]=true;
            return;
        }

        isSubtreeExist(root.left, subRoot, ans);
        isSubtreeExist(root.right, subRoot, ans);

    }

    private boolean isSameSubTree(TreeNode root, TreeNode subRoot) {
        if(root==null || subRoot==null) return root==subRoot;

        return (root.val==subRoot.val) && isSameSubTree(root.left, subRoot.left) && isSameSubTree(root.right, subRoot.right);
    }

}
