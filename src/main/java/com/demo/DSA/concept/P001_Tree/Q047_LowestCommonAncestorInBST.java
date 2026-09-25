package com.demo.DSA.concept.P001_Tree;

/**
 * Q047. Lowest Common Ancestor of a Binary Search Tree
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * <p>
 * Given a binary search tree (BST) and two node values p and q, return the
 * value of their lowest common ancestor (LCA) - the deepest node that has
 * both p and q as descendants (a node can be a descendant of itself).
 * Unlike {@link Q014_LowestCommonAncestor_LCA} (general binary tree), the
 * BST ordering property allows an O(h) solution without building explicit
 * root-to-node paths.
 *
 * <pre>
 * Example 1:
 * Input:
 *              6
 *            /   \
 *           2     8
 *          / \   / \
 *         0   4 7   9
 *            / \
 *           3   5
 *
 * p = 2, q = 8
 * Output: 6
 *
 * Example 2:
 * Input:
 *              6
 *            /   \
 *           2     8
 *          / \   / \
 *         0   4 7   9
 *            / \
 *           3   5
 *
 * p = 2, q = 4
 * Output: 2
 * Explanation: A node can be a descendant of itself per the LCA
 * definition, so since 2 is an ancestor of 4, the answer is 2.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [2, 10^5].
 * - -10^9 &lt;= Node.val &lt;= 10^9
 * - All Node.val are unique.
 * - p != q
 * - p and q will both exist in the BST.
 * </pre>
 */
public class   Q047_LowestCommonAncestorInBST {

    /**
     * @implNote TODO: implement.
     * Target approach: Starting at root, walk down the tree - if both p
     * and q are smaller than the current node's value, move left; if both
     * are larger, move right; otherwise (p and q are on different sides,
     * or one equals the current node) the current node is the LCA.
     * <p>
     * Target Time Complexity: O(h) - single root-to-LCA walk, no branching.
     * <br>
     * Target Space Complexity: O(1) iterative, or O(h) if done
     * recursively.
     */
    public int lowestCommonAncestorBST(TreeNode root, int p, int q) {
        return solve(root, p, q);
        /*
        while(root!=null){
            if(root.val>p && root.val>q)
                root=root.left;
            else if(root.val<p && root.val<q)
                root=root.right;
            else if(root.val==p || root.val==q)
                return root.val;
            else{
                return root.val;
            }
        }
        return 0;
        */
    }

    private int solve(TreeNode root, int p, int q) {

        if(root==null) return 0;
        if(root.val>p && root.val>q)
            return solve(root.left,p,q);
        else if(root.val<p && root.val<q)
            return solve(root.right,p,q);
        else
            return root.val;
    }
}
