package com.demo.DSA.concept.P001_Tree;

/**
 * Q040. Count Leaf Nodes and Single-Child Nodes in a Binary Tree
 * https://www.geeksforgeeks.org/dsa/write-a-c-program-to-get-count-of-leaf-nodes-in-a-binary-tree/
 * <p>
 * Given the root of a binary tree: (a) count the number of leaf nodes -
 * nodes where both left and right children are null; and (b) count the
 * number of nodes that have exactly one child.
 *
 * <pre>
 * Example 1:
 * Input:
 *            1
 *          /   \
 *         2     3
 *        / \     \
 *       4   5     6
 *
 * countLeafNodes -> 3 (nodes 4, 5, 6)
 * countSingleChildNodes -> 1 (node 3, which only has a right child)
 *
 * Example 2:
 * Input:
 *    1
 *
 * countLeafNodes -> 1 (node 1 itself, since it has no children)
 * countSingleChildNodes -> 0
 * </pre>
 */
public class Q040_CountLeafAndSingleChildNodes {

    /**
     * @implNote TODO: implement.
     * Target approach: Recursive DFS - a null node contributes 0; a node
     * with both children null contributes 1; otherwise recurse into left
     * and right subtrees and sum their leaf counts.
     * <p>
     * Target Time Complexity: O(n) - every node visited once.
     * <br>
     * Target Space Complexity: O(h) - recursion stack depth equals tree
     * height h.
     */
    public int countLeafNodes(TreeNode root) {
        // TODO: implement
        return -1;
    }

    /**
     * @implNote TODO: implement.
     * Target approach: Recursive DFS - at each non-null node, check if
     * exactly one of left/right is null (and the other non-null); count
     * that node if so, then recurse into both children and sum.
     * <p>
     * Target Time Complexity: O(n) - every node visited once.
     * <br>
     * Target Space Complexity: O(h) - recursion stack depth equals tree
     * height h.
     */
    public int countSingleChildNodes(TreeNode root) {
        // TODO: implement
        return -1;
    }
}
