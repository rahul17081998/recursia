package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q028. Next Greater Node In Linked List
 * https://leetcode.com/problems/next-greater-node-in-linked-list/
 * https://algomaster.io/learn/dsa/linked-list/next-greater-node-in-linked-list
 * <p>
 * You are given the head of a linked list with n nodes. For each node,
 * find the value of the next node that is strictly greater than it, and
 * return an integer array answer where answer[i] is this value for the
 * ith node (0-indexed). If no such next greater node exists, answer[i]
 * should be 0.
 *
 * <pre>
 * Example 1:
 * Input: head = [2,1,5]
 * Output: [5,5,0]
 *
 * Example 2:
 * Input: head = [2,7,4,3,5]
 * Output: [7,0,5,5,0]
 *
 * Example 3 (strictly decreasing - no greater element ever follows):
 * Input: head = [5,4,3,2,1]
 * Output: [0,0,0,0,0]
 *
 * Example 4 (strictly increasing - the opposite extreme from Example 3;
 * every node's next greater element is literally its immediate
 * successor, so the stack never holds more than one index at a time):
 * Input: head = [1,2,3,4,5]
 * Output: [2,3,4,5,0]
 *
 * Constraints:
 * - The number of nodes in the list is n.
 * - 1 &lt;= n &lt;= 10^4
 * - 1 &lt;= Node.val &lt;= 10^9
 * </pre>
 */
public class Q028_NextGreaterNodeInLinkedList {

    /**
     * @implNote TODO: implement.
     * Target approach: Monotonic decreasing stack of indices, same
     * pattern as the array "next greater element" problem. Walk the list
     * once, converting it to an array of values as you go (or tracking
     * an index counter). Maintain a stack of indices whose answer isn't
     * known yet - values decreasing from bottom to top. At each new
     * value v: while the stack's top index has a smaller value than v,
     * pop it and set its answer to v (v is its next greater element);
     * then push the current index. Indices left on the stack once the
     * list is exhausted have no next greater element - their answer stays
     * 0 (the array's default).
     * <p>
     * Target Time Complexity: O(n) - each index is pushed and popped at
     * most once.
     * <br>
     * Target Space Complexity: O(n) - the value array/list, plus the
     * stack.
     */
    public int[] nextLargerNodes(ListNode head) {
        // TODO: implement
        return new int[0];
    }
}
