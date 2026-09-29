package com.demo.DSA.concept.P003_LinkedList;

/**
 * Shared singly-linked-list node, mirroring LeetCode's own {@code ListNode}
 * class used across nearly every linked-list problem. Questions that need
 * extra fields (a {@code random} pointer, a {@code child} pointer, a
 * {@code prev} pointer for a doubly-linked variant) define their own nested
 * {@code Node} class instead of bloating this shared type - see
 * {@code Q021_CopyListWithRandomPointer.Node} and
 * {@code Q022_FlattenAMultilevelDoublyLinkedList.Node} for that pattern
 * (same approach as {@code P001_Tree.Q050_PopulateNextRightPointers.Node}).
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
