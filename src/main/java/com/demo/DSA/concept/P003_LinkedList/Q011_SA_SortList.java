package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q011. Sort List
 * https://leetcode.com/problems/sort-list/
 * https://algomaster.io/learn/dsa/linked-list/sort-list
 * <p>
 * Given the head of a linked list, return the list after sorting it in
 * ascending order. LeetCode's follow-up explicitly challenges O(n log n)
 * time and O(1) extra space - that's what makes merge sort (not
 * quicksort or an array-copy sort) the intended technique for a linked
 * list specifically.
 *
 * <pre>
 * Example 1:
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 *
 * Example 2:
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 *
 * Example 3 (empty list):
 * Input: head = []
 * Output: []
 *
 * Example 4 (single node - already "sorted", base case of the
 * recursion):
 * Input: head = [5]
 * Output: [5]
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 5 * 10^4].
 * - -10^5 &lt;= Node.val &lt;= 10^5
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * Merge sort on a linked list is a natural fit precisely because a list
 * has no O(1) random access (ruling out array-style in-place partitioning
 * schemes like quicksort's) but splitting and merging are both
 * link-relinking operations that don't need random access at all. Find
 * the midpoint with {@link Q006_SA_FindMiddleOfLinkedList}'s slow/fast
 * pointers, physically cut the list into two halves there (the middle
 * node's predecessor's {@code next} is set to {@code null}), recursively
 * sort each half, then combine them with
 * {@link Q009_SA_MergeTwoSortedLists}. The recursion bottoms out at
 * lists of length 0 or 1, which are trivially sorted.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://algomaster.io/learn/dsa/linked-list/sort-list">AlgoMaster.io &mdash; Sort List</a>
 *   - explains exactly why merge sort (not quicksort) is the right choice for a linked list.</li>
 *   <li><a href="https://cp-algorithms.com/sorting/merge-sort.html">cp-algorithms.com &mdash; Merge Sort</a>
 *   - the general algorithm this is a linked-list-specific application of.</li>
 * </ul>
 */
public class Q011_SA_SortList {

    /**
     * @implNote TODO: implement.
     * Target approach: Base case: if head is null or head.next is null,
     * already sorted - return head. Otherwise, find the middle (use the
     * slow/fast technique but stop one node early, or track a "prev of
     * slow" pointer, so the list can actually be split into two
     * independent halves rather than just located). Cut the link between
     * the two halves, recursively sort each half, then merge the two
     * sorted halves with {@link Q009_SA_MergeTwoSortedLists#mergeTwoLists}.
     * <p>
     * Target Time Complexity: O(n log n).
     * <br>
     * Target Space Complexity: O(log n) recursion stack; O(1) auxiliary
     * per merge step (nodes are relinked, not copied).
     */
    public ListNode sortList(ListNode head) {
        // TODO: implement
        return null;
    }
}
