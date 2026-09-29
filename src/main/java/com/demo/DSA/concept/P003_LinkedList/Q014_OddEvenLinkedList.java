package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q014. Odd Even Linked List
 * https://leetcode.com/problems/odd-even-linked-list/
 * https://algomaster.io/learn/dsa/linked-list/odd-even-linked-list
 * <p>
 * Given the head of a singly linked list, group all the nodes with odd
 * indices together followed by the nodes with even indices (1-indexed),
 * and return the reordered list. The relative order inside the odd group
 * and inside the even group must be preserved.
 *
 * <pre>
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [1,3,5,2,4]
 *
 * Example 2:
 * Input: head = [2,1,3,5,6,4,7]
 * Output: [2,3,6,7,1,5,4]
 *
 * Example 3 (empty list - base case):
 * Input: head = []
 * Output: []
 *
 * Example 4 (two nodes - one odd-indexed, one even-indexed, already in
 * odd-then-even order - a fixed point worth checking explicitly):
 * Input: head = [1,2]
 * Output: [1,2]
 *
 * Constraints:
 * - The number of nodes in the linked list is in the range [0, 10^4].
 * - -10^6 &lt;= Node.val &lt;= 10^6
 * </pre>
 */
public class Q014_OddEvenLinkedList {

    /**
     * @implNote TODO: implement.
     * Target approach: Maintain two running chains in place - odd
     * (starting at head) and even (starting at head.next), plus a saved
     * pointer to the even chain's head. Alternately advance odd.next =
     * odd.next.next and even.next = even.next.next, moving odd/even
     * forward each time, until the even chain runs out. Finally splice
     * odd.next = evenHead to join the two chains.
     * <p>
     * Target Time Complexity: O(n).
     * <br>
     * Target Space Complexity: O(1) - nodes are relinked in place, not
     * copied.
     */
    public ListNode oddEvenList(ListNode head) {
        // TODO: implement
        return null;
    }
}
