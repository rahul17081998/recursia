package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q024. Delete Node in a Linked List
 * https://leetcode.com/problems/delete-node-in-a-linked-list/
 * https://algomaster.io/learn/dsa/linked-list/delete-node-in-a-linked-list
 * <p>
 * Write a function to delete a node in a singly linked list, given only
 * access to that node - NOT access to the head of the list. It is
 * guaranteed that the node to be deleted is not the tail node.
 *
 * <pre>
 * Example 1:
 * Input: head = [4,5,1,9], node to delete = node with value 5
 * Output: [4,1,9]
 *
 * Example 2:
 * Input: head = [4,5,1,9], node to delete = node with value 1
 * Output: [4,5,9]
 *
 * Example 3 (tricky - deleting the head node itself; the head reference
 * stays the same object, but its value gets overwritten to look like the
 * old second node):
 * Input: head = [4,5,1,9], node to delete = node with value 4 (the head)
 * Output: [5,1,9]
 *
 * Example 4 (minimal case - a 2-node list, deleting the head):
 * Input: head = [4,9], node to delete = node with value 4 (the head)
 * Output: [9]
 *
 * Constraints:
 * - The number of nodes in the given list is in the range [2, 1000].
 * - -1000 &lt;= Node.val &lt;= 1000
 * - The given node is not the tail and is guaranteed to be a valid node
 *   of the linked list.
 * </pre>
 */
public class Q024_DeleteNodeInALinkedList {

    /**
     * @implNote TODO: implement.
     * Target approach: Without the head, there's no way to reach the
     * predecessor to unlink this node the usual way - so instead,
     * "delete" it by overwriting: copy the next node's value into this
     * node (node.val = node.next.val), then skip over the next node
     * (node.next = node.next.next). The node itself is never removed
     * from memory; what's removed is effectively its former successor,
     * after this node has been disguised to look like it.
     * <p>
     * Target Time Complexity: O(1).
     * <br>
     * Target Space Complexity: O(1).
     */
    public void deleteNode(ListNode node) {
        // TODO: implement
    }
}
