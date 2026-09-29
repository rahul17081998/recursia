package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q008. Reorder List
 * https://leetcode.com/problems/reorder-list/
 * https://algomaster.io/learn/dsa/linked-list/reorder-list
 * <p>
 * Given the head of a singly linked list L0 -&gt; L1 -&gt; ... -&gt; Ln-1 -&gt; Ln,
 * reorder it to L0 -&gt; Ln -&gt; L1 -&gt; Ln-1 -&gt; L2 -&gt; Ln-2 -&gt; ... in place,
 * without modifying node values.
 *
 * <pre>
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [1,4,2,3]
 *
 * Example 2:
 * Input: head = [1,2,3,4,5]
 * Output: [1,5,2,4,3]
 *
 * Example 3 (single node - a no-op):
 * Input: head = [1]
 * Output: [1]
 *
 * Example 4 (two nodes - L0 -&gt; Ln is already L0 -&gt; L1, a fixed point
 * worth checking explicitly since it's easy to accidentally corrupt a
 * short list while merging the two halves):
 * Input: head = [1,2]
 * Output: [1,2]
 *
 * Constraints:
 * - The number of nodes in the list is in the range [1, 5 * 10^4].
 * - 1 &lt;= Node.val &lt;= 1000
 * </pre>
 */
public class Q008_ReorderList {

    /**
     * @implNote TODO: implement.
     * Target approach: Three steps, each reusing a technique above: (1)
     * find the middle with {@link Q006_SA_FindMiddleOfLinkedList}, (2)
     * reverse the second half with {@link Q001_SA_ReverseLinkedList}, (3)
     * merge the first half and the reversed second half by alternating
     * nodes (splice one node from each list, one after another) until one
     * half is exhausted.
     * <p>
     * Target Time Complexity: O(n).
     * <br>
     * Target Space Complexity: O(1) - all three steps work in place
     * (contrast with the O(n)-space approach of dumping nodes into an
     * array/deque first).
     */
    public void reorderList(ListNode head) {
        // TODO: implement
    }
}
