package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q002. Reverse Linked List II
 * https://leetcode.com/problems/reverse-linked-list-ii/
 * https://algomaster.io/learn/dsa/linked-list/reverse-linked-list-ii
 * <p>
 * Given the head of a singly linked list and two integers left and right
 * where left &lt;= right, reverse only the nodes from position left to
 * position right (1-indexed), and return the resulting list's head.
 *
 * <pre>
 * Example 1:
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 *
 * Example 2:
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 * Explanation: Reversing a single node in place is a no-op.
 *
 * Example 3 (reverse from the very head):
 * Input: head = [3,5], left = 1, right = 2
 * Output: [5,3]
 *
 * Example 4 (reversal starts exactly at the head of a longer list - the
 * returned head itself changes, unlike Example 1 where left &gt; 1 and the
 * original head stays in place):
 * Input: head = [1,2,3,4,5], left = 1, right = 3
 * Output: [3,2,1,4,5]
 *
 * Constraints:
 * - The number of nodes in the list is n.
 * - 1 &lt;= n &lt;= 500
 * - -500 &lt;= Node.val &lt;= 500
 * - 1 &lt;= left &lt;= right &lt;= n
 * </pre>
 */
public class Q002_ReverseLinkedListII {

    /**
     * @implNote TODO: implement.
     * Target approach: Walk left-1 steps with a dummy-node-anchored
     * {@code prevLeft} pointer to land right before position left. Then
     * reverse exactly (right - left + 1) nodes in place - reusing
     * {@link Q001_SA_ReverseLinkedList}'s three-pointer technique but
     * bounded to that sublist - and finally splice: prevLeft.next should
     * point at the new sublist head, and the original left-th node (now
     * the sublist's tail) should point at whatever followed position
     * right.
     * <p>
     * Target Time Complexity: O(n) - single pass.
     * <br>
     * Target Space Complexity: O(1).
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // TODO: implement
        return null;
    }
}
