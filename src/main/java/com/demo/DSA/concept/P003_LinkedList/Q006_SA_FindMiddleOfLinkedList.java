package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q006. Middle of the Linked List
 * https://leetcode.com/problems/middle-of-the-linked-list/
 * https://algomaster.io/learn/dsa/linked-list/middle-of-the-linked-list
 * <p>
 * Given the head of a singly linked list, return the middle node. If
 * there are two middle nodes, return the second one.
 *
 * <pre>
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: node with val 3
 *
 * Example 2 (even length - second of the two middles):
 * Input: head = [1,2,3,4,5,6]
 * Output: node with val 4
 *
 * Example 3 (single node):
 * Input: head = [1]
 * Output: node with val 1
 *
 * Example 4 (smallest even-length case):
 * Input: head = [1,2]
 * Output: node with val 2
 *
 * Constraints:
 * - The number of nodes in the list is in the range [1, 100].
 * - 1 &lt;= Node.val &lt;= 100
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * Slow/fast pointers again, but used as a distance trick rather than for
 * cycle detection: both start at head, slow advances one node per step,
 * fast advances two. By the time fast has reached the end of the list
 * (traveled the full length), slow - moving at half the speed - has only
 * traveled half that distance, so it's sitting exactly on the middle
 * node. The "second middle on even length" rule falls out naturally from
 * the loop condition ({@code while fast != null && fast.next != null}):
 * fast runs out of room one step earlier for even-length lists, leaving
 * slow one node further along than the naive floor(n/2) index.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://algomaster.io/learn/dsa/linked-list/middle-of-the-linked-list">AlgoMaster.io &mdash; Middle of the Linked List</a>
 *   - covers both the two-pass (count then walk) and one-pass slow/fast approaches.</li>
 * </ul>
 */
public class Q006_SA_FindMiddleOfLinkedList {

    /**
     * @implNote TODO: implement.
     * Target approach: slow = head, fast = head; while fast != null and
     * fast.next != null: slow = slow.next, fast = fast.next.next. Return
     * slow once the loop ends.
     * <p>
     * Target Time Complexity: O(n).
     * <br>
     * Target Space Complexity: O(1).
     */
    public ListNode middleNode(ListNode head) {
        // TODO: implement
        return null;
    }
}
