package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q019. Remove Duplicates from Sorted List
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 * https://algomaster.io/learn/dsa/linked-list/remove-duplicates-from-sorted-list
 * <p>
 * Given the head of a sorted linked list, delete all duplicates such
 * that each element appears only once. Return the linked list, still
 * sorted.
 *
 * <pre>
 * Example 1:
 * Input: head = [1,1,2]
 * Output: [1,2]
 *
 * Example 2:
 * Input: head = [1,1,2,3,3]
 * Output: [1,2,3]
 *
 * Example 3 (single node - no duplicates possible, base case):
 * Input: head = [1]
 * Output: [1]
 *
 * Example 4 (the entire list is one repeated value - collapses to a
 * single node):
 * Input: head = [1,1,1,1]
 * Output: [1]
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 300].
 * - -100 &lt;= Node.val &lt;= 100
 * - The list is guaranteed to be sorted in ascending order.
 * </pre>
 */
public class Q019_RemoveDuplicatesFromSortedList {

    /**
     * @implNote TODO: implement.
     * Target approach: Because the list is sorted, every duplicate of a
     * value is adjacent to it - no lookahead beyond one node needed. Walk
     * with a single pointer curr; while curr.next != null and
     * curr.next.val == curr.val, skip it (curr.next = curr.next.next);
     * otherwise advance curr = curr.next.
     * <p>
     * Target Time Complexity: O(n).
     * <br>
     * Target Space Complexity: O(1).
     */
    public ListNode deleteDuplicates(ListNode head) {
        // TODO: implement
        return null;
    }
}
