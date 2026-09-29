package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q020. Remove Duplicates from Sorted List II
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 * https://algomaster.io/learn/dsa/linked-list/remove-duplicates-from-sorted-list-ii
 * <p>
 * Given the head of a sorted linked list, delete all nodes that have
 * duplicate numbers, leaving only distinct numbers from the original
 * list. Return the linked list, still sorted. (Unlike Q019, a value that
 * appears more than once is removed entirely - not just collapsed to one
 * copy.)
 *
 * <pre>
 * Example 1:
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 *
 * Example 2:
 * Input: head = [1,1,1,2,3]
 * Output: [2,3]
 *
 * Example 3 (no duplicates at all - nothing gets removed, base case):
 * Input: head = [1,2,3]
 * Output: [1,2,3]
 *
 * Example 4 (tricky - the entire list is one repeated value, so
 * everything is removed, including what was originally the head; the
 * result is an empty list):
 * Input: head = [1,1]
 * Output: []
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 300].
 * - -100 &lt;= Node.val &lt;= 100
 * - The list is guaranteed to be sorted in ascending order.
 * </pre>
 */
public class Q020_RemoveDuplicatesFromSortedListII {

    /**
     * @implNote TODO: implement.
     * Target approach: Needs a dummy head, since the original head itself
     * might be part of a duplicated run that gets removed entirely. Keep
     * a {@code prev} pointer (starting at dummy) trailing a scan pointer
     * {@code curr}. At each curr, if curr.next != null and
     * curr.next.val == curr.val, this value is duplicated - advance curr
     * through the whole run of equal values, then set prev.next to
     * whatever follows the run (skipping it entirely, including curr
     * itself). Otherwise, this value is unique - advance prev to curr.
     * Either way, advance curr by one afterward.
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
