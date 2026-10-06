package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q005. Linked List Cycle II
 * https://leetcode.com/problems/linked-list-cycle-ii/
 * https://algomaster.io/learn/dsa/linked-list/linked-list-cycle-ii
 * <p>
 * Given the head of a linked list, return the node where the cycle begins.
 * If there is no cycle, return null.
 *
 * <pre>
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: node at index 1 (value 2)
 *
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: node at index 0 (value 1)
 *
 * Example 3 (no cycle):
 * Input: head = [1], pos = -1
 * Output: null
 *
 * Example 4 (single node pointing at itself - the cycle start is that
 * same node):
 * Input: head = [1], pos = 0
 * Output: node at index 0 (value 1)
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 10^4].
 * - -10^5 &lt;= Node.val &lt;= 10^5
 * - pos is -1 or a valid index in the linked list.
 * </pre>
 */
public class Q005_LinkedListCycleII {

    /**
     * @implNote TODO: implement.
     * Target approach: Extends {@link Q004_SA_DetectCycleInLinkedList} -
     * run the same slow/fast pointers until they collide (no collision ->
     * return null). At the collision point, reset one pointer to head and
     * advance both remaining pointers one step at a time; they meet
     * exactly at the cycle's start node. (Proof sketch: if the head-to-
     * cycle-start distance is a and the collision point is b steps into
     * the cycle, the math behind Floyd's algorithm guarantees
     * a == (cycle length - b) mod cycle length, which is precisely the
     * distance from the collision point back around to the start.)
     * <p>
     * Target Time Complexity: O(n).
     * <br>
     * Target Space Complexity: O(1).
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean isCycle = false;

        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow != null && fast != null && slow == fast) {
                isCycle = true;
                break;
            }
        }

        if (!isCycle) return null;

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }


        return slow;
    }
}
