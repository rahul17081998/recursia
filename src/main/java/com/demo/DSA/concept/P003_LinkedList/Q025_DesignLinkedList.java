package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q025. Design Linked List
 * https://leetcode.com/problems/design-linked-list/
 * https://algomaster.io/learn/dsa/linked-list/design-linked-list
 * <p>
 * Design your own implementation of a singly linked list. Implement the
 * MyLinkedList class:
 * <ul>
 *   <li>{@code MyLinkedList()} - initializes an empty list.</li>
 *   <li>{@code int get(int index)} - get the value of the index-th node
 *   (0-indexed). Return -1 if invalid.</li>
 *   <li>{@code void addAtHead(int val)} - add a node of value val before
 *   the first element.</li>
 *   <li>{@code void addAtTail(int val)} - add a node of value val after
 *   the last element.</li>
 *   <li>{@code void addAtIndex(int index, int val)} - add a node of
 *   value val before the index-th node. If index equals the length, the
 *   node is appended to the end. If index is greater than the length, the
 *   node is not inserted. If index is negative, insert at the head.</li>
 *   <li>{@code void deleteAtIndex(int index)} - delete the index-th node,
 *   if valid.</li>
 * </ul>
 *
 * <pre>
 * Example (LeetCode's own walkthrough):
 * Input:
 *   ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
 *   [[], [1], [3], [1, 2], [1], [1], [1]]
 * Output:
 *   [null, null, null, null, 2, null, 3]
 * Explanation:
 *   MyLinkedList list = new MyLinkedList();
 *   list.addAtHead(1);          // list: 1
 *   list.addAtTail(3);          // list: 1 -&gt; 3
 *   list.addAtIndex(1, 2);      // list: 1 -&gt; 2 -&gt; 3
 *   list.get(1);                 // returns 2
 *   list.deleteAtIndex(1);      // list: 1 -&gt; 3
 *   list.get(1);                 // returns 3
 *
 * Constraints:
 * - 0 &lt;= index, val &lt;= 1000
 * - Please do not use the built-in LinkedList library.
 * - At most 2000 calls will be made to get, addAtHead, addAtTail,
 *   addAtIndex and deleteAtIndex.
 * </pre>
 */
public class Q025_DesignLinkedList {

    private ListNode head;
    private int size;

    /**
     * @implNote TODO: implement.
     * Target approach: Maintain a private head pointer and a size
     * counter (avoids re-walking the list just to validate an index).
     * Every operation reduces to the same primitive: walk (index - 1)
     * steps from a dummy node standing in front of head to find the
     * predecessor of the target position, then relink. Using a dummy
     * predecessor node for every insert/delete (including at index 0)
     * avoids special-casing "inserting/deleting the head" separately.
     * <p>
     * Target Time Complexity: O(index) per operation - no random access
     * on a singly linked list.
     * <br>
     * Target Space Complexity: O(n) total for n stored elements; O(1)
     * auxiliary per operation.
     */
    public Q025_DesignLinkedList() {
        // TODO: implement
    }

    public int get(int index) {
        // TODO: implement
        return -1;
    }

    public void addAtHead(int val) {
        // TODO: implement
    }

    public void addAtTail(int val) {
        // TODO: implement
    }

    public void addAtIndex(int index, int val) {
        // TODO: implement
    }

    public void deleteAtIndex(int index) {
        // TODO: implement
    }
}
