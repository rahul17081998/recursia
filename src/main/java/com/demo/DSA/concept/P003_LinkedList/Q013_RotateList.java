package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q013. Rotate List
 * https://leetcode.com/problems/rotate-list/
 * https://algomaster.io/learn/dsa/linked-list/rotate-list
 * <p>
 * Given the head of a linked list, rotate the list to the right by k
 * places.
 *
 * <pre>
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 *
 * Example 2 (k larger than the list length):
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 * Explanation: k=4 on a 3-node list is the same as k=1 (4 mod 3).
 *
 * Example 3 (k is an exact multiple of the length - no-op):
 * Input: head = [1,2,3], k = 3
 * Output: [1,2,3]
 *
 * Example 4 (single node - rotation is always a no-op regardless of k,
 * even a large one):
 * Input: head = [1], k = 5
 * Output: [1]
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 500].
 * - -100 &lt;= Node.val &lt;= 100
 * - 0 &lt;= k &lt;= 2 * 10^9
 * </pre>
 */
public class Q013_RotateList {

    /**
     * @implNote TODO: implement.
     * Target approach: Handle empty/single-node lists as a no-op early.
     * Walk once to find the length L and the tail node, then normalize
     * k = k % L (k can be far larger than L, and a multiple of L is a
     * no-op). Link tail.next = head to make the list circular, walk
     * (L - k) steps from head to find the new tail, set the new head to
     * newTail.next, then cut newTail.next = null to break the circle.
     * <p>
     * Target Time Complexity: O(n).
     * <br>
     * Target Space Complexity: O(1).
     */
    public ListNode rotateRight(ListNode head, int k) {
        // TODO: implement
        return null;
    }
}
