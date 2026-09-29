package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q022. Flatten a Multilevel Doubly Linked List
 * https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
 * https://algomaster.io/learn/dsa/linked-list/flatten-a-multilevel-doubly-linked-list
 * <p>
 * You are given a doubly linked list, which contains nodes that have an
 * additional child pointer, which may or may not point to a separate
 * doubly linked list, also containing child pointers (and so on,
 * recursively). Flatten the list so all nodes appear in a single-level
 * doubly linked list, where a node's sublist (if it had one) is spliced
 * in right after it, in order.
 *
 * <pre>
 * Example 1:
 * Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * (level-order-ish encoding: node 3 has a child list 7-&gt;8-&gt;9-&gt;10, and
 * node 8 has a child list 11-&gt;12)
 * Output: [1,2,3,7,8,11,12,9,10,4,5,6]
 *
 * Example 2:
 * Input: head = [1,2,null,3]
 * (node 1 has a child list 3)
 * Output: [1,3,2]
 *
 * Example 3 (no children at all - flattening a plain flat list is a
 * no-op, base case):
 * Input: head = [1,2,3]
 * Output: [1,2,3]
 *
 * Constraints:
 * - The number of Nodes will not exceed 1000.
 * - 1 &lt;= Node.val &lt;= 10^5
 * </pre>
 */
public class Q022_FlattenAMultilevelDoublyLinkedList {

    /** LeetCode's own Node type for this problem - doubly linked, plus a child pointer. */
    public static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * @implNote TODO: implement.
     * Target approach: Depth-first, splicing as you go - whenever the
     * current node has a non-null child, that entire child sublist
     * (recursively flattened) needs to be inserted between the current
     * node and its next. Concretely: walk the list; on hitting a node
     * with a child, recursively flatten the child sublist first, save
     * the current node's original next, relink current.next to the
     * (flattened) child head and child head.prev to current, walk to the
     * end of that now-spliced-in sublist, then relink that tail's next
     * to the originally-saved next (and vice versa for prev), clearing
     * the child pointer. Continue the outer walk from wherever it left
     * off (the flattened-in nodes don't need to be re-visited for child
     * pointers, since they were already handled by the recursive call).
     * <p>
     * Target Time Complexity: O(n) - every node visited a constant
     * number of times.
     * <br>
     * Target Space Complexity: O(d) recursion stack, where d = maximum
     * nesting depth of child lists.
     */
    public Node flatten(Node head) {
        // TODO: implement
        return null;
    }
}
