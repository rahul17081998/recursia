package com.demo.DSA.concept.P001_Tree;

/**
 * Q051. Search in a Binary Search Tree
 * https://leetcode.com/problems/search-in-a-binary-search-tree/
 * <p>
 * Given the root of a binary search tree (BST) and a value, find the node
 * in the BST whose value equals the given value and return the subtree
 * rooted with that node. If such a node does not exist, return null.
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
 * val = 2
 * Output:
 *       2
 *      / \
 *     1   3
 *
 * Example 2:
 * Input:
 *          4
 *        /   \
 *       2     7
 *      / \
 *     1   3
 *
 * val = 5
 * Output: null (5 is not present in the tree)
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 5000].
 * - 1 &lt;= Node.val &lt;= 10^7
 * - root is a binary search tree.
 * - 1 &lt;= val &lt;= 10^7
 * </pre>
 */
public class Q051_SearchInBST {

    /**
     * @implNote TODO: implement.
     * Target approach: Iterative/recursive BST walk - starting at root,
     * compare val against the current node's val. If equal, return the
     * current node. If val is smaller, move into the left subtree; if
     * larger, move into the right subtree. Reaching a null node means the
     * value isn't present.
     * <p>
     * Target Time Complexity: O(h) - walks down a single root-to-node path.
     * <br>
     * Target Space Complexity: O(h) recursive, O(1) if done iteratively.
     */
    public TreeNode searchBST(TreeNode root, int val) {
       if(root==null) return null;
       if(root.val==val) return root;

       if(val<root.val)
           return searchBST(root.left, val);
       else
           return searchBST(root.right, val);

    }
}
