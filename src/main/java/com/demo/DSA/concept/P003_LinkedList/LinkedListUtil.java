package com.demo.DSA.concept.P003_LinkedList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Shared helpers so each question file doesn't rebuild lists by hand.
 * Lists are built from LeetCode's common {@code int[] values} array format,
 * e.g. {1,2,3,4,5} -> 1 -> 2 -> 3 -> 4 -> 5 -> null.
 */
public class LinkedListUtil {

    public static ListNode buildList(int[] values) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        for (int v : values) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    /**
     * Same as {@link #buildList}, but wires the last node's {@code next}
     * back to the node at index {@code pos} (0-indexed) to build a cycle -
     * the exact input shape LeetCode uses for Linked List Cycle /
     * Linked List Cycle II. {@code pos = -1} means no cycle.
     */
    public static ListNode buildListWithCycle(int[] values, int pos) {
        if (values.length == 0) return null;
        ListNode[] nodes = new ListNode[values.length];
        for (int i = 0; i < values.length; i++) nodes[i] = new ListNode(values[i]);
        for (int i = 0; i < values.length - 1; i++) nodes[i].next = nodes[i + 1];
        if (pos >= 0) nodes[values.length - 1].next = nodes[pos];
        return nodes[0];
    }

    /** Converts up to {@code limit} nodes to a list - a safety cap so a buggy cyclic result doesn't loop forever. */
    public static List<Integer> toList(ListNode head, int limit) {
        List<Integer> out = new ArrayList<>();
        ListNode curr = head;
        while (curr != null && out.size() < limit) {
            out.add(curr.val);
            curr = curr.next;
        }
        return out;
    }

    public static List<Integer> toList(ListNode head) {
        return toList(head, 100_000);
    }

    public static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode curr = head;
        int guard = 0;
        while (curr != null && guard++ < 1000) {
            sb.append(curr.val).append(" -> ");
            curr = curr.next;
        }
        sb.append(curr == null ? "null" : "... (truncated, still going - cyclic?)");
        System.out.println(sb);
    }

    /**
     * Renders a (possibly cyclic) list as Graphviz DOT source: a left-to-
     * right chain of boxes connected by arrows. Pipe through
     * {@code dot -Tpng} to get an actual diagram, e.g.:
     * {@code dot -Tpng out.dot -o out.png}
     * <p>
     * A cycle is drawn rather than unrolled forever: each node is visited
     * at most once while walking the list, and the first time {@code next}
     * points back to an already-drawn node, that edge is added (labeled
     * "cycle") and the walk stops there.
     */
    public static String toDot(ListNode head) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph G {\n");
        sb.append("  rankdir=LR;\n");
        sb.append("  graph [nodesep=0.5, ranksep=0.7, size=\"10,4\", dpi=180];\n");
        sb.append("  node [shape=box, style=filled, fillcolor=lightblue, fontsize=16];\n");

        Map<ListNode, Integer> ids = new HashMap<>();
        List<ListNode> order = new ArrayList<>();
        ListNode curr = head;
        while (curr != null && !ids.containsKey(curr)) {
            ids.put(curr, order.size());
            order.add(curr);
            curr = curr.next;
        }

        for (int i = 0; i < order.size(); i++) {
            sb.append("  n").append(i).append(" [label=\"").append(order.get(i).val).append("\"];\n");
        }
        for (int i = 0; i < order.size() - 1; i++) {
            sb.append("  n").append(i).append(" -> n").append(i + 1).append(";\n");
        }
        if (curr != null) {
            // curr.next of the last drawn node loops back into an already-drawn node -> a cycle.
            int backTo = ids.get(curr);
            sb.append("  n").append(order.size() - 1).append(" -> n").append(backTo)
                    .append(" [label=\"cycle\", color=red, fontcolor=red, constraint=false];\n");
        }
        sb.append("}\n");
        return sb.toString();
    }
}
