package com.demo.DSA.concept.P001_Tree;

/**
 * Q044. Delete Node in a BST
 * https://leetcode.com/problems/delete-node-in-a-bst/
 * <p>
 * Given the root node of a binary search tree (BST) and a key, delete the
 * node with the given key from the BST if it exists, and return the root
 * of the resulting BST. There are three deletion cases: (1) the node is a
 * leaf - remove it directly; (2) the node has one child - replace it with
 * that child; (3) the node has two children - replace its value with
 * either its inorder predecessor or inorder successor, then delete that
 * predecessor/successor node instead.
 *
 * <pre>
 * Example 1:
 * Input:
 *            5
 *          /   \
 *         3     6
 *        / \      \
 *       2   4      7
 *
 * key = 3
 * Output (one valid tree, using inorder successor 4):
 *            5
 *          /   \
 *         4     6
 *        /        \
 *       2           7
 *
 * Example 2:
 * Input:
 *            5
 *          /   \
 *         3     6
 *        / \      \
 *       2   4      7
 *
 * key = 0
 * Output: the tree is unchanged (key 0 does not exist in the BST)
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 10^4].
 * - -10^5 &lt;= Node.val &lt;= 10^5
 * - All the values Node.val are unique.
 * - root is guaranteed to be a valid binary search tree.
 * - -10^5 &lt;= key &lt;= 10^5
 * </pre>
 */
public class Q044_DeleteNodeInBST {

    /**
     * @implNote TODO: implement.
     * Target approach: Recursive BST search for the key. On finding it -
     * if it has no left child, return its right child (and vice versa) to
     * splice it out; if it has both children, find the inorder successor
     * (leftmost node of the right subtree), copy that value into the
     * current node, then recursively delete the successor's original node
     * from the right subtree.
     * <p>
     * Target Time Complexity: O(h) - one root-to-node search plus at most
     * one more descent to find the successor.
     * <br>
     * Target Space Complexity: O(h) - recursion stack depth equals tree
     * height h.
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null) return null;

        if(key<root.val){
            root.left=deleteNode(root.left, key);
        }else if(key>root.val){
            root.right=deleteNode(root.right, key);
        }else{
            if(root.left==null) return root.right;
            if(root.right==null) return root.left;

            TreeNode successor=findSuccessor(root.right);
            root.val=successor.val;
            root.right=deleteNode(root.right, successor.val);
        }

        return root;
    }

    private TreeNode findSuccessor(TreeNode node) {
        while(node.left!=null){
            node=node.left;
        }
        return node;
    }
}
