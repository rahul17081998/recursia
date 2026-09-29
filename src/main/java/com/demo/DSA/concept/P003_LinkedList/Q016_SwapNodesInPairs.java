package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q016. Swap Nodes in Pairs
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 * https://algomaster.io/learn/dsa/linked-list/swap-nodes-in-pairs
 * <p>
 * Given a linked list, swap every two adjacent nodes and return its
 * head. You must solve the problem without modifying the values in the
 * list's nodes (only nodes themselves may be changed).
 *
 * <pre>
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 *
 * Example 2 (empty list):
 * Input: head = []
 * Output: []
 *
 * Example 3 (odd length - last node untouched):
 * Input: head = [1,2,3]
 * Output: [2,1,3]
 *
 * Example 4 (single node - zero full pairs, a more minimal case than
 * Example 3's one-pair-plus-leftover):
 * Input: head = [1]
 * Output: [1]
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 100].
 * - 0 &lt;= Node.val &lt;= 100
 * </pre>
 */
public class Q016_SwapNodesInPairs {

    /**
     * @implNote TODO: implement.
     * Target approach: A special case of {@link Q003_ReverseNodesInKGroup}
     * with k=2 (that same relink pattern - detach a pair, reverse the
     * two links, reattach - is exactly what "swap adjacent pair" means).
     * Use a dummy head and a {@code prev} pointer trailing the pair being
     * swapped; for each pair (first, second): prev.next = second,
     * first.next = second.next, second.next = first, then advance
     * prev = first. Stop when fewer than 2 nodes remain.
     * <p>
     * Target Time Complexity: O(n).
     * <br>
     * Target Space Complexity: O(1) iterative (O(n) recursion stack if
     * implemented recursively).
     */
    public ListNode swapPairs(ListNode head) {
        // TODO: implement
        return null;
    }
}
