package com.demo.DSA.concept.P001_Tree;

/**
 * Q049. Convert a Binary Search Tree to a Sorted Doubly Linked List
 * https://www.geeksforgeeks.org/problems/binary-tree-to-dll/1
 * <p>
 * Given the root of a BST, convert it in-place into a sorted doubly linked
 * list, reusing the same node structure: the left pointer should act as
 * "previous" and the right pointer as "next". The conversion follows an
 * inorder traversal, so on a BST the resulting list comes out sorted in
 * ascending order; the head of the list should be returned. (GFG's
 * original problem is stated for any binary tree - inorder order, not
 * necessarily sorted - but applying it to a BST always yields a sorted
 * list, which is the classic interview framing.)
 *
 * <pre>
 * Example 1 (from GFG, general binary tree, inorder order):
 * Input:
 *      1
 *     / \
 *    3   2
 *
 * Output (as a doubly linked list, head returned): 3 &lt;-&gt; 1 &lt;-&gt; 2
 *
 * Example 2 (BST case - sorted order):
 * Input:
 *            10
 *          /    \
 *         5      20
 *               /   \
 *              15    30
 *
 * Output (as a doubly linked list, head returned): 5 &lt;-&gt; 10 &lt;-&gt; 15 &lt;-&gt; 20 &lt;-&gt; 30
 *
 * Constraints (per GFG):
 * - 1 &lt;= number of nodes &lt;= 10^5
 * - 0 &lt;= Data of a node &lt;= 10^5
 * - Expected Time Complexity: O(N)
 * - Expected Auxiliary Space: O(Height of the Tree)
 * </pre>
 */
public class Q049_ConvertBSTToSortedDoublyLinkedList {

    /**
     * @implNote TODO: implement.
     * Target approach: Inorder traversal (recursive or Morris for O(1)
     * space) while keeping a "previously visited node" reference; for each
     * node visited, link prev.right = current and current.left = prev,
     * then advance prev to current. Track the very first node visited as
     * the head of the list to return at the end (optionally link head and
     * tail together for a circular list).
     * <p>
     * Target Time Complexity: O(n) - every node visited once.
     * <br>
     * Target Space Complexity: O(h) recursive, or O(1) with Morris
     * traversal.
     */
    public TreeNode bstToSortedDLL(TreeNode root) {
        // TODO: implement
        return null;
    }
}
