package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q009. Merge Two Sorted Lists
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * https://algomaster.io/learn/dsa/linked-list/merge-two-sorted-lists
 * https://www.geeksforgeeks.org/dsa/merge-two-sorted-linked-lists/
 * <p>
 * You are given the heads of two sorted linked lists. Merge the two
 * lists into one sorted list by splicing together the nodes of the
 * first two lists, and return the head of the merged list.
 *
 * <pre>
 * Example 1:
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 *
 * Example 2 (one list empty):
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 *
 * Example 3 (both empty):
 * Input: list1 = [], list2 = []
 * Output: []
 *
 * Example 4 (no interleaving needed - one list's values are all smaller
 * than the other's, so the loop exits almost immediately and the
 * leftover-splice branch does all the work):
 * Input: list1 = [1,2,3], list2 = [4,5,6]
 * Output: [1,2,3,4,5,6]
 *
 * Constraints:
 * - The number of nodes in both lists is in the range [0, 50].
 * - -100 &lt;= Node.val &lt;= 100
 * - Both list1 and list2 are sorted in non-decreasing order.
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * Classic two-pointer merge, the same core step that powers merge sort's
 * combine phase. Use a dummy head so the very first real node doesn't
 * need special-casing, and a {@code tail} pointer that always points at
 * the last node spliced into the result so far. Repeatedly compare the
 * two lists' current nodes, splice the smaller one onto {@code tail},
 * and advance both that list's pointer and {@code tail}. Once one list
 * runs out, the other is already sorted and can simply be splice-attached
 * whole - no need to keep comparing.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://algomaster.io/learn/dsa/linked-list/merge-two-sorted-lists">AlgoMaster.io &mdash; Merge Two Sorted Lists</a>
 *   - shows both the iterative dummy-node approach and a short recursive one.</li>
 * </ul>
 */
public class Q009_SA_MergeTwoSortedLists {

    /**
     * @implNote TODO: implement.
     * Target approach: dummy = new ListNode(); tail = dummy. While both
     * list1 and list2 are non-null, splice whichever has the smaller val
     * onto tail.next, advance that list's pointer and tail. When the loop
     * ends, tail.next = whichever list is non-null (the leftover sorted
     * tail). Return dummy.next.
     * <p>
     * Target Time Complexity: O(n + m).
     * <br>
     * Target Space Complexity: O(1) - nodes are relinked, not copied.
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // TODO: implement
        return null;
    }
}
