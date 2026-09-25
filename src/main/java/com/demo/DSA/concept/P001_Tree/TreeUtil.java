package com.demo.DSA.concept.P001_Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Shared helpers so each question file doesn't rebuild trees by hand.
 * buildTree takes LeetCode's level-order array format, e.g. {1,2,3,null,4}.
 */
public class TreeUtil {

    public static TreeNode buildTree(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) return null;

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        int i = 1;
        while (i < values.length && !queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (i < values.length) {
                Integer leftVal = values[i++];
                if (leftVal != null) {
                    current.left = new TreeNode(leftVal);
                    queue.add(current.left);
                }
            }
            if (i < values.length) {
                Integer rightVal = values[i++];
                if (rightVal != null) {
                    current.right = new TreeNode(rightVal);
                    queue.add(current.right);
                }
            }
        }
        return root;
    }

    public static String printLevelOrder(TreeNode root) {
        StringBuilder sb = new StringBuilder("[");
        // LinkedList (not ArrayDeque) because this traversal queues null
        // placeholders for missing children, and ArrayDeque disallows nulls.
        Queue<TreeNode> queue = new java.util.LinkedList<>();
        if (root != null) queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current == null) {
                sb.append("null, ");
                continue;
            }
            sb.append(current.val).append(", ");
            if (current.left != null || current.right != null) {
                queue.add(current.left);
                queue.add(current.right);
            }
        }
        if (sb.length() > 1) sb.setLength(sb.length() - 2);
        return sb.append("]").toString();
    }

    /**
     * Prints the tree as an actual shape with branch lines, e.g. for
     * {15, 10, 23, 7, 9, 18, 28}:
     *   15
     *  /    \
     * 10   23
     * / \  / \
     * 7 9 18 28
     */
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("(empty tree)");
            return;
        }
        for (String line : buildBox(root).lines) {
            System.out.println(line);
        }
    }

    private static class Box {
        final List<String> lines;
        final int width;
        final int mid; // column, within lines.get(0), that a parent's branch should connect to

        Box(List<String> lines, int width, int mid) {
            this.lines = lines;
            this.width = width;
            this.mid = mid;
        }
    }

    private static Box buildBox(TreeNode node) {
        String label = String.valueOf(node.val);
        Box left = node.left == null ? null : buildBox(node.left);
        Box right = node.right == null ? null : buildBox(node.right);

        if (left == null && right == null) {
            List<String> lines = new ArrayList<>();
            lines.add(label);
            return new Box(lines, label.length(), label.length() / 2);
        }

        int gap = 1;
        int leftWidth = left == null ? 0 : left.width;
        int rightWidth = right == null ? 0 : right.width;
        int leftConnCol = left == null ? -1 : left.mid;
        int rightConnCol = right == null ? -1 : leftWidth + gap + right.mid;

        int rootMid;
        if (left != null && right != null) {
            rootMid = (leftConnCol + rightConnCol) / 2;
        } else if (left != null) {
            rootMid = leftConnCol;
        } else {
            rootMid = rightConnCol;
        }

        int labelStart = rootMid - label.length() / 2;
        int leftPad = Math.max(0, -labelStart);
        int childrenWidth = leftWidth + (left != null && right != null ? gap : 0) + rightWidth;

        if (left != null) leftConnCol += leftPad;
        if (right != null) rightConnCol += leftPad;
        rootMid += leftPad;
        labelStart += leftPad;

        int totalWidth = Math.max(childrenWidth + leftPad, labelStart + label.length());

        StringBuilder labelLine = new StringBuilder(" ".repeat(totalWidth));
        labelLine.replace(labelStart, labelStart + label.length(), label);
        StringBuilder connLine = new StringBuilder(" ".repeat(totalWidth));
        if (left != null) connLine.setCharAt(leftConnCol, '/');
        if (right != null) connLine.setCharAt(rightConnCol, '\\');

        List<String> lines = new ArrayList<>();
        lines.add(labelLine.toString());
        lines.add(connLine.toString());

        String padStr = " ".repeat(leftPad);
        if (left != null && right != null) {
            String gapStr = " ".repeat(gap);
            int maxRows = Math.max(left.lines.size(), right.lines.size());
            for (int i = 0; i < maxRows; i++) {
                String l = i < left.lines.size() ? left.lines.get(i) : " ".repeat(leftWidth);
                String r = i < right.lines.size() ? right.lines.get(i) : " ".repeat(rightWidth);
                lines.add(padStr + l + gapStr + r);
            }
        } else {
            Box only = left != null ? left : right;
            for (String l : only.lines) {
                lines.add(padStr + l);
            }
        }

        return new Box(lines, totalWidth, rootMid);
    }
}