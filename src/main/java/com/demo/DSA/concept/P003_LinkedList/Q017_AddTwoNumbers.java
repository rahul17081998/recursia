package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q017. Add Two Numbers
 * https://leetcode.com/problems/add-two-numbers/
 * https://algomaster.io/learn/dsa/linked-list/add-two-numbers
 * <p>
 * You are given two non-empty linked lists representing two non-negative
 * integers. The digits are stored in reverse order, and each node
 * contains a single digit. Add the two numbers and return the sum as a
 * linked list, in the same reverse-digit format.
 *
 * <pre>
 * Example 1:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 *
 * Example 2 (both zero):
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 * Example 3 (carrying propagates through extra digits):
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 * Explanation: 9999999 + 9999 = 10009998.
 *
 * Example 4 (minimal single-digit case with a carry that grows the
 * result by one digit):
 * Input: l1 = [5], l2 = [5]
 * Output: [0,1]
 * Explanation: 5 + 5 = 10.
 *
 * Constraints:
 * - The number of nodes in each linked list is in the range [1, 100].
 * - 0 &lt;= Node.val &lt;= 9
 * - No leading zeros, except the number 0 itself.
 * </pre>
 */
public class Q017_AddTwoNumbers {

    /**
     * @implNote TODO: implement.
     * Target approach: Elementary-school addition, digit by digit from
     * the least-significant end - which is conveniently the head, since
     * digits are stored in reverse order. Walk l1 and l2 together
     * (continuing past whichever list is shorter, treating its missing
     * digits as 0), carrying overflow into the next position. Use a
     * dummy head to build the result. After both lists are exhausted, if
     * a final carry remains, append one more node for it.
     * <p>
     * Target Time Complexity: O(max(n, m)) where n, m are the two list
     * lengths.
     * <br>
     * Target Space Complexity: O(max(n, m)) for the output list (not
     * counting output, O(1) auxiliary).
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // TODO: implement
        return null;
    }
}
