package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q010. Merge k Sorted Lists
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * https://algomaster.io/learn/dsa/linked-list/merge-k-sorted-lists
 * <p>
 * You are given an array of k linked-lists, each sorted in ascending
 * order. Merge all the linked-lists into one sorted linked list and
 * return it.
 *
 * <pre>
 * Example 1:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 *
 * Example 2 (empty input array):
 * Input: lists = []
 * Output: []
 *
 * Example 3 (one empty list among non-empty ones):
 * Input: lists = [[]]
 * Output: []
 *
 * Example 4 (k = 1 with real values - the "merge" is a no-op, but still
 * needs to hand back the list unchanged):
 * Input: lists = [[1,2,3]]
 * Output: [1,2,3]
 *
 * Constraints:
 * - k == lists.length
 * - 0 &lt;= k &lt;= 10^4
 * - 0 &lt;= lists[i].length &lt;= 500
 * - -10^4 &lt;= lists[i][j] &lt;= 10^4
 * - lists[i] is sorted in ascending order.
 * - The sum of lists[i].length will not exceed 10^4.
 * </pre>
 */
public class Q010_MergeKSortedLists {

    /**
     * @implNote TODO: implement.
     * Target approach: Two reasonable strategies, both reusing
     * {@link Q009_SA_MergeTwoSortedLists}: (1) fold the k lists pairwise
     * using divide-and-conquer - merge lists[0] with lists[1],
     * lists[2] with lists[3], etc., then merge those results together,
     * halving the list count each round; or (2) push every list's head
     * into a min-heap keyed by value, repeatedly pop the smallest, splice
     * it onto the result, and push its successor. Divide-and-conquer is
     * usually preferred here since it reuses Q009 directly with no extra
     * data structure.
     * <p>
     * Target Time Complexity: O(N log k) where N = total number of nodes
     * across all lists - log k merge rounds (or heap operations), each
     * touching every node once.
     * <br>
     * Target Space Complexity: O(log k) recursion stack for
     * divide-and-conquer (or O(k) for the heap approach); output nodes
     * are relinked, not copied.
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // TODO: implement
        return null;
    }
}
