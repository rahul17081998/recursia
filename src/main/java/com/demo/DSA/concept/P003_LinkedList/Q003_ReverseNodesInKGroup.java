package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q003. Reverse Nodes in k-Group
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * https://algomaster.io/learn/dsa/linked-list/reverse-nodes-in-k-group
 * <p>
 * Given the head of a linked list, reverse the nodes of the list k at a
 * time, and return the modified list. k is a positive integer and is
 * less than or equal to the length of the linked list. If the number of
 * nodes is not a multiple of k, the left-out nodes at the end should
 * remain as they are.
 *
 * <pre>
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * Explanation: [1,2] and [3,4] each get reversed; the trailing [5] is too
 * short for a full group, so it's left alone.
 *
 * Example 2:
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 *
 * Example 3 (k equals the whole list length):
 * Input: head = [1,2,3,4,5], k = 5
 * Output: [5,4,3,2,1]
 *
 * Example 4 (k = 1 - every group is a single node, so reversing each
 * "group" is a no-op):
 * Input: head = [1,2,3], k = 1
 * Output: [1,2,3]
 *
 * Constraints:
 * - The number of nodes in the list is n.
 * - 1 &lt;= k &lt;= n &lt;= 5000
 * - 0 &lt;= Node.val &lt;= 1000
 * </pre>
 */
public class Q003_ReverseNodesInKGroup {

    /**
     * @implNote TODO: implement.
     * Target approach: For each group, first walk k nodes ahead to check
     * a full group actually exists (count them, or check the kth node is
     * non-null) - if not, stop and leave the remainder untouched. If a
     * full group exists, reverse just those k nodes using
     * {@link Q001_SA_ReverseLinkedList}'s technique, splice the previous
     * group's tail to the new group head, and connect the (now-tail)
     * original group head to the recursive/iterative result of processing
     * the rest of the list.
     * <p>
     * Target Time Complexity: O(n) - every node is visited a constant
     * number of times.
     * <br>
     * Target Space Complexity: O(1) iterative (O(n/k) recursion stack if
     * implemented recursively).
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // TODO: implement
        return null;
    }
}
