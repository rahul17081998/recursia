package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q026. Maximum Twin Sum of a Linked List
 * https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/
 * https://algomaster.io/learn/dsa/linked-list/maximum-twin-sum-of-a-linked-list
 * <p>
 * In a linked list of size n, where n is even, the ith node (0-indexed)
 * is called the twin of the (n-1-i)th node. Define the twin sum as the
 * sum of a node and its twin. Return the maximum twin sum of the list.
 *
 * <pre>
 * Example 1:
 * Input: head = [5,4,2,1]
 * Output: 6
 * Explanation: Twin pairs are (5,1) and (4,2), sums 6 and 6 - max is 6.
 *
 * Example 2:
 * Input: head = [4,2,2,3]
 * Output: 7
 * Explanation: Twin pairs are (4,3) and (2,2), sums 7 and 4 - max is 7.
 *
 * Example 3:
 * Input: head = [1,100000]
 * Output: 100001
 *
 * Example 4 (tricky - the maximum twin sum comes from the FIRST pair,
 * not the last one computed; catches the bug of returning the last
 * computed sum instead of tracking a running max):
 * Input: head = [3,2,1,8]
 * Output: 11
 * Explanation: Twin pairs are (3,8)=11 and (2,1)=3 - max is 11.
 *
 * Constraints:
 * - The number of nodes in the list is an even integer in the range
 *   [2, 10^5].
 * - 1 &lt;= Node.val &lt;= 10^5
 * </pre>
 */
public class Q026_MaximumTwinSumOfLinkedList {

    /**
     * @implNote TODO: implement.
     * Target approach: O(1)-extra-space version, chaining three
     * techniques used earlier in this package: find the middle with
     * {@link Q006_SA_FindMiddleOfLinkedList} (a list of even length n has
     * its "second half" start exactly at the middle), reverse that second
     * half in place with {@link Q001_SA_ReverseLinkedList}, then walk the
     * first half and the reversed second half in lockstep, tracking the
     * max of (firstHalf.val + reversedSecondHalf.val) at each step - that
     * sum is exactly node i's twin sum for every i, since the reversed
     * second half visits nodes n-1, n-2, ... in order.
     * <p>
     * Target Time Complexity: O(n).
     * <br>
     * Target Space Complexity: O(1) with the reverse-half trick (O(n) if
     * implemented by first copying values into an array).
     */
    public int pairSum(ListNode head) {
        // TODO: implement
        return -1;
    }
}
