package com.demo.DSA.concept.P001_Tree;

/**
 * Q050. Populate Next Right Pointers in Each Node
 * https://leetcode.com/problems/populate-next-right-pointers-in-each-node/
 * <p>
 * You are given a perfect binary tree where all leaves are on the same
 * level, and every parent has two children. Each node additionally has a
 * "next" pointer (see the nested {@link Node} class below, since the
 * shared {@link TreeNode} in this package has no next field). Populate
 * each next pointer to point to its next right node on the same level; if
 * there is no next right node, that pointer should be set to null. The
 * tree should initially be populated with all next pointers set to null.
 *
 * <pre>
 * Example 1:
 * Input:
 *              1
 *            /   \
 *           2     3
 *          / \   / \
 *         4   5 6   7
 *
 * Output (level order, "#" marks the end of each level):
 * [1,#, 2,3,#, 4,5,6,7,#]
 * Explanation: node 2's next now points to node 3, node 4's next points
 * to node 5, node 5's next points to node 6, node 6's next points to
 * node 7.
 *
 * Example 2:
 * Input: root = []  (empty tree)
 * Output: []
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 6000].
 * - -1000 &lt;= Node.val &lt;= 1000
 * </pre>
 */
public class Q050_PopulateNextRightPointers {

    /** Perfect-binary-tree node with an extra "next" (right-sibling) pointer, as used by LeetCode 116. */
    public static class Node {
        int val;
        Node left;
        Node right;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    /**
     * @implNote TODO: implement.
     * Target approach: Since the tree is perfect, once level L's next
     * pointers are all set, level L+1 can be connected using only those
     * pointers and O(1) extra space - for each node on the current level,
     * connect node.left.next = node.right, and node.right.next =
     * node.next.left (if node.next exists). Move to the next level via the
     * leftmost node's left child.
     * <p>
     * Target Time Complexity: O(n) - every node visited a constant number
     * of times.
     * <br>
     * Target Space Complexity: O(1) - no queue/recursion needed beyond a
     * couple of pointers (a simpler BFS-with-queue solution is O(w) space,
     * where w is the widest level).
     */
    public Node connect(Node root) {
        // TODO: implement
        return null;
    }
}
