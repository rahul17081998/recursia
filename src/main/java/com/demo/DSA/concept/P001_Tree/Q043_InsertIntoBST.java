package com.demo.DSA.concept.P001_Tree;

/**
 * Q043. Insert into a Binary Search Tree
 * https://leetcode.com/problems/insert-into-a-binary-search-tree/
 * <p>
 * Given the root node of a binary search tree (BST) and a value to
 * insert, insert the value into the BST such that the resulting tree
 * remains a valid BST, and return the root of the tree after insertion.
 * There may exist multiple valid ways to insert the value - any one of
 * them is acceptable.
 *
 * <pre>
 * Example 1:
 * Input:
 *          4
 *        /   \
 *       2     7
 *      / \
 *     1   3
 *
 * val = 5
 * Output (one valid tree):
 *          4
 *        /   \
 *       2     7
 *      / \    /
 *     1   3  5
 *
 * Example 2:
 * Input:
 *    40
 *   /  \
 *  20   60
 *
 * val = 50
 * Output (one valid tree):
 *      40
 *    /    \
 *   20     60
 *          /
 *        50
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 10^4].
 * - -10^8 &lt;= Node.val &lt;= 10^8
 * - All the values Node.val are unique.
 * - -10^8 &lt;= val &lt;= 10^8
 * - It is guaranteed that val does not exist in the original BST.
 * </pre>
 */
public class Q043_InsertIntoBST {

    /**
     * @implNote TODO: implement.
     * Target approach: Recursive/iterative BST walk - if root is null,
     * create and return a new node with val. Otherwise compare val against
     * root.val to decide whether to recurse into the left or right
     * subtree, then reattach the (possibly new) subtree.
     * <p>
     * Target Time Complexity: O(h) - walks down one root-to-leaf path.
     * <br>
     * Target Space Complexity: O(h) recursive, O(1) if done iteratively.
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root==null) return new TreeNode(val);

        if(val== root.val) return root; // The val == root.val early return is dead code given the problem's constraint that val never already exists,
        if(val<root.val){
            root.left=insertIntoBST(root.left, val);
        }else{
            root.right=insertIntoBST(root.right, val);
        }

        return root;
    }
}
