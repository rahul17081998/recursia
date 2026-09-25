package com.demo.DSA.concept.P001_Tree;

/**
 * Q046. Inorder Successor in BST
 * https://leetcode.com/problems/inorder-successor-in-bst/
 * <p>
 * Given the root of a binary search tree and a node p in it, return the
 * inorder successor of that node in the BST - the node with the smallest
 * value that is strictly greater than p.val. If no such node exists,
 * return null.
 *
 * <pre>
 * Example 1:
 * Input:
 *          2
 *         / \
 *        1   3
 *
 * p = node with value 1
 * Output: node with value 2
 *
 * Example 2:
 * Input:
 *              5
 *            /   \
 *           3     6
 *          / \
 *         2   4
 *
 * p = node with value 6
 * Output: null (6 is the largest value, so it has no inorder successor)
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 10^4].
 * - -10^5 &lt;= Node.val &lt;= 10^5
 * - All Node.val are unique.
 * - p is guaranteed to be a valid node in the tree.
 * </pre>
 */
public class Q046_InorderSuccessorInBST {

    /**
     * @implNote TODO: implement.
     * Target approach: Walk down from root comparing p.val to the current
     * node's value - if current.val > p.val, current is a successor
     * candidate, so record it and move left (there may be a smaller valid
     * successor); otherwise move right. Return the last recorded
     * candidate (or null if none found). This runs in O(h) without
     * needing a full inorder traversal.
     * <p>
     * Target Time Complexity: O(h) - single root-to-node style walk.
     * <br>
     * Target Space Complexity: O(1) iterative, or O(h) if done
     * recursively.
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(p.right==null){
            return getParentOfP(root, p);
        }
        return getSuccessorOfP(p.right);
    }

    /**
     * 1st solution: full inorder traversal (left, node, right), stopping at
     * the first node whose value is greater than p.val - since inorder
     * visits nodes in ascending order, that first match is the smallest
     * value greater than p.val. The ans[0]==null guard stops an ancestor
     * further up the call stack from overwriting an answer already found
     * deeper in the left subtree.
     * <p>
     * Time Complexity: O(n) worst case - unlike {@link #inorderSuccessor},
     * this doesn't use the BST ordering to skip unrelated subtrees.
     * <br>
     * Space Complexity: O(h) recursion stack.
     */
    public TreeNode inorderSuccessorViaTraversal(TreeNode root, TreeNode p) {
        TreeNode[] ans = {null};
        solve(root, p, ans);
        return ans[0];
    }

    private TreeNode getParentOfP(TreeNode root, TreeNode p) {
        TreeNode ans=null;
        while(root!=null){
            if(root.val>p.val){
                ans=root;
                root=root.left;
            }else {
                root=root.right;
            }
        }
        return ans;
    }

    private TreeNode getSuccessorOfP(TreeNode rightOfP) {
       while(rightOfP.left!=null){
           rightOfP=rightOfP.left;
       }
       return rightOfP;
    }


    private void solve(TreeNode root, TreeNode p, TreeNode[] ans) {
        if(root==null) return;
        solve(root.left, p, ans);
        if(root.val>p.val && ans[0]==null) {
            ans[0]=root;
            return;
        }
        solve(root.right, p, ans);
    }
}
