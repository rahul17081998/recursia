package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q001. Reverse Linked List
 * https://leetcode.com/problems/reverse-linked-list/
 * https://algomaster.io/learn/dsa/linked-list/reverse-a-linked-list
 * https://www.geeksforgeeks.org/dsa/reverse-a-linked-list/
 * <p>
 * Given the head of a singly linked list, reverse the list and return the
 * new head.
 *
 * <pre>
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 *
 * Example 2:
 * Input: head = [1,2]
 * Output: [2,1]
 *
 * Example 3 (empty list):
 * Input: head = []
 * Output: []
 *
 * Example 4 (single node - a no-op, easy to accidentally break with an
 * off-by-one in the pointer walk):
 * Input: head = [42]
 * Output: [42]
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 5000].
 * - -5000 &lt;= Node.val &lt;= 5000
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * Walk the list once, keeping three pointers: {@code prev} (initially
 * {@code null}), {@code curr} (starts at {@code head}), and a temporary
 * {@code next} saved <strong>before</strong> {@code curr.next} is
 * overwritten. At each step: save {@code curr.next}, then point
 * {@code curr.next} backward at {@code prev}, then slide both
 * {@code prev} and {@code curr} forward by one. When {@code curr} becomes
 * {@code null}, {@code prev} is sitting on the old tail - the new head.
 * The same idea works recursively: reverse the rest of the list first,
 * then fix up the link between the current node and what used to be its
 * neighbor.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://algomaster.io/learn/dsa/linked-list/reverse-a-linked-list">AlgoMaster.io &mdash; Reverse a Linked List</a>
 *   - walks through both the iterative and recursive approaches side by side.</li>
 *   <li><a href="https://visualgo.net/en/list">VisuAlgo.net &mdash; Linked List</a>
 *   - animates pointer manipulation on a list you control.</li>
 * </ul>
 */
public class Q001_SA_ReverseLinkedList {

    /**
     * @implNote TODO: implement.
     * Target approach: Iterative three-pointer walk - prev=null, curr=head;
     * while curr != null, save next=curr.next, set curr.next=prev, then
     * prev=curr, curr=next. Return prev once the loop ends.
     * <p>
     * Target Time Complexity: O(n) - one pass.
     * <br>
     * Target Space Complexity: O(1) iterative (O(n) recursion stack if
     * implemented recursively instead).
     */
    public ListNode reverseList(ListNode head) {
        // TODO: implement
        return null;
    }
}
