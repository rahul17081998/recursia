package com.demo.DSA.concept.P001_Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Q019. Binary Tree Zigzag Level Order Traversal
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * <p>
 * Given the root of a binary tree, return the zigzag level order traversal
 * of its nodes' values (i.e., left to right for the first level, then
 * right to left for the next level, alternating between).
 *
 * <pre>
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 *
 *        3
 *       / \
 *      9  20
 *         / \
 *        15  7
 *
 * Output: [[3],[20,9],[15,7]]
 *
 * Example 2:
 * Input: root = [1]
 *
 *    1
 *
 * Output: [[1]]
 *
 * Example 3:
 * Input: root = []  (empty tree)
 * Output: []
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 2000].
 * - -100 &lt;= Node.val &lt;= 100
 * </pre>
 */
public class Q019_ZigzagLevelOrderTraversal {

    /**
     * @implNote TODO: implement.
     * Target approach: Standard BFS level-order traversal, but reverse the
     * collected values for every alternate level (track a boolean flag, or
     * insert at the front for right-to-left levels using a Deque).
     * <p>
     * Target Time Complexity: O(n) - every node is visited exactly once.
     * <br>
     * Target Space Complexity: O(w) - queue holds at most the widest level
     * w, which is O(n) in the worst case.
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // TODO: implement
        return new ArrayList<>();
    }
}
