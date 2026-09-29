package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q027. Remove Linked List Elements
 * https://leetcode.com/problems/remove-linked-list-elements/
 * https://algomaster.io/learn/dsa/linked-list/remove-linked-list-elements
 * <p>
 * Given the head of a linked list and an integer val, remove all the
 * nodes of the linked list that have Node.val == val, and return the
 * new head.
 *
 * <pre>
 * Example 1:
 * Input: head = [1,2,6,3,4,5,6], val = 6
 * Output: [1,2,3,4,5]
 *
 * Example 2 (empty list):
 * Input: head = [], val = 1
 * Output: []
 *
 * Example 3 (every node matches, including the head):
 * Input: head = [7,7,7,7], val = 7
 * Output: []
 *
 * Example 4 (val is never present - a no-op, but still must walk the
 * whole list without mistakenly deleting something):
 * Input: head = [1,2,3,4,5], val = 6
 * Output: [1,2,3,4,5]
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 10^4].
 * - 1 &lt;= Node.val &lt;= 50
 * - 0 &lt;= val &lt;= 50
 * </pre>
 */
public class Q027_RemoveLinkedListElements {

    /**
     * @implNote TODO: implement.
     * Target approach: Use a dummy node pointing at head, so removing the
     * real head (possibly several matching nodes in a row, including
     * every node in the list) needs no special-casing. Walk a pointer
     * starting at dummy; while its next node matches val, skip it
     * (curr.next = curr.next.next, staying at curr to check the new
     * next too - values can repeat consecutively); otherwise advance
     * curr = curr.next. Return dummy.next.
     * <p>
     * Target Time Complexity: O(n).
     * <br>
     * Target Space Complexity: O(1).
     */
    public ListNode removeElements(ListNode head, int val) {
        // TODO: implement
        return null;
    }
}
