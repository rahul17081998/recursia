package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q021. Copy List with Random Pointer
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 * https://algomaster.io/learn/dsa/linked-list/copy-list-with-random-pointer
 * <p>
 * A linked list of length n is given such that each node contains an
 * additional random pointer, which could point to any node in the list,
 * or null. Construct a deep copy of the list - the new list should
 * consist of exactly n brand-new nodes, each with its value set to the
 * value of its corresponding original node, and both its {@code next}
 * and {@code random} pointers pointing to new nodes such that the
 * pointers in the new list represent the same list state as the
 * original (no pointer in the copy may point into the original list).
 *
 * <pre>
 * Example 1:
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * (each pair is [val, randomIndex]; randomIndex=null means random points
 * nowhere)
 * Output: a deep copy with identical val/next/random structure
 *
 * Example 2:
 * Input: head = [[1,1],[2,1]]
 * Output: a deep copy where both nodes' random pointers point at the
 * copy of the second node
 *
 * Example 3 (empty list):
 * Input: head = []
 * Output: []
 *
 * Example 4 (tricky - a single node whose random pointer points at
 * itself; the clone's random pointer must point at the CLONE of itself,
 * not at the original node):
 * Input: head = [[1,0]]
 * Output: a single cloned node whose random points at itself
 *
 * Constraints:
 * - 0 &lt;= n &lt;= 1000
 * - -10^4 &lt;= Node.val &lt;= 10^4
 * - Node.random is null or points to some node in the linked list.
 * </pre>
 */
public class Q021_CopyListWithRandomPointer {

    /** LeetCode's own Node type for this problem - needs a random pointer, unlike the shared {@link ListNode}. */
    public static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * @implNote TODO: implement.
     * Target approach: The classic O(1)-extra-space technique - weave the
     * copy nodes directly into the original list first: for each original
     * node, insert its clone right after it (orig1 -&gt; clone1 -&gt; orig2 -&gt;
     * clone2 -&gt; ...). With that interleaving in place, every clone's
     * random pointer is trivial to set: clone.random = orig.random.next
     * (the clone immediately following whatever orig.random points at,
     * or null if orig.random is null). Finally, unweave the two lists
     * back apart into separate original and copy chains, restoring the
     * original list's structure. (A HashMap&lt;Node, Node&gt; from original to
     * clone is the simpler O(n)-space alternative, useful as a first
     * pass before optimizing.)
     * <p>
     * Target Time Complexity: O(n).
     * <br>
     * Target Space Complexity: O(1) with the weave/unweave technique
     * (O(n) with the HashMap approach).
     */
    public Node copyRandomList(Node head) {
        // TODO: implement
        return null;
    }
}
