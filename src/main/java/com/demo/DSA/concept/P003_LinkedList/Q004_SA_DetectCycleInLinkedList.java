package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q004. Linked List Cycle
 * https://leetcode.com/problems/linked-list-cycle/
 * https://algomaster.io/learn/dsa/linked-list/linked-list-cycle
 * https://www.geeksforgeeks.org/dsa/detect-loop-in-a-linked-list/
 * <p>
 * Given the head of a linked list, determine whether the list has a cycle
 * in it - i.e. whether some node can be reached again by continuously
 * following the {@code next} pointer.
 *
 * <pre>
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1 (tail connects to node index 1)
 * Output: true
 *
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: true
 *
 * Example 3 (no cycle):
 * Input: head = [1], pos = -1
 * Output: false
 *
 * Example 4 (single node pointing at itself - the smallest possible
 * cycle; a fast pointer that steps by 2 must not skip past it):
 * Input: head = [1], pos = 0
 * Output: true
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 10^4].
 * - -10^5 &lt;= Node.val &lt;= 10^5
 * - pos is -1 or a valid index in the linked list.
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * Floyd's Tortoise and Hare: run two pointers from the head, a slow one
 * advancing one node per step and a fast one advancing two. If the list
 * is acyclic, the fast pointer simply reaches {@code null} first. If
 * there IS a cycle, both pointers are eventually confined to the loop,
 * and the fast pointer gains exactly one extra step on the slow pointer
 * every iteration - so the gap between them (mod cycle length) shrinks by
 * 1 each time and must hit 0, meaning they collide, within at most one
 * full lap of the cycle. A collision is therefore a definitive "yes,
 * there's a cycle"; the fast pointer hitting {@code null} is a
 * definitive "no".
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://algomaster.io/learn/dsa/linked-list/linked-list-cycle">AlgoMaster.io &mdash; Linked List Cycle</a>
 *   - lays out the slow/fast pointer proof step by step.</li>
 *   <li><a href="https://cp-algorithms.com/others/tortoise_and_hare.html">cp-algorithms.com &mdash; Floyd's Tortoise and Hare</a>
 *   - the general cycle-detection algorithm this problem is a direct application of.</li>
 * </ul>
 */
public class Q004_SA_DetectCycleInLinkedList {

    /**
     * @implNote TODO: implement.
     * Target approach: slow = head, fast = head; while fast != null and
     * fast.next != null: slow = slow.next, fast = fast.next.next; if
     * slow == fast at any point, a cycle exists - return true. If the
     * loop exits normally (fast or fast.next hit null), no cycle - return
     * false.
     * <p>
     * Target Time Complexity: O(n).
     * <br>
     * Target Space Complexity: O(1) - only two pointers, no extra
     * structure (contrast with the O(n)-space HashSet-of-visited-nodes
     * approach).
     */
    public boolean hasCycle(ListNode head) {
        // TODO: implement
        return false;
    }
}
