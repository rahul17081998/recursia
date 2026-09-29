package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q012. Remove Nth Node From End of List
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * https://algomaster.io/learn/dsa/linked-list/remove-nth-node-from-end-of-list
 * <p>
 * Given the head of a linked list, remove the nth node from the end of
 * the list and return its head.
 *
 * <pre>
 * Example 1:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 *
 * Example 2 (removing the only node):
 * Input: head = [1], n = 1
 * Output: []
 *
 * Example 3 (removing the head itself):
 * Input: head = [1,2], n = 2
 * Output: [2]
 *
 * Example 4 (removing the very last node, n = 1 - the opposite boundary
 * from Example 3):
 * Input: head = [1,2,3], n = 1
 * Output: [1,2]
 *
 * Constraints:
 * - The number of nodes in the list is sz.
 * - 1 &lt;= sz &lt;= 30
 * - 0 &lt;= Node.val &lt;= 100
 * - 1 &lt;= n &lt;= sz
 * </pre>
 */
public class Q012_RemoveNthNodeFromEndOfList {

    /**
     * @implNote TODO: implement.
     * Target approach: One-pass two-pointer with a dummy head (so
     * removing the real head doesn't need special-casing). Advance a
     * {@code fast} pointer n steps ahead of a {@code slow} pointer
     * (both starting at dummy), then advance both together until fast
     * reaches the last node; at that point slow is sitting exactly one
     * node before the target, so slow.next = slow.next.next removes it.
     * <p>
     * Target Time Complexity: O(L) where L = list length - one pass.
     * <br>
     * Target Space Complexity: O(1).
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // TODO: implement
        return null;
    }
}
