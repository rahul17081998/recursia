package com.demo.DSA.concept.P001_Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Q036. All Nodes Distance K in Binary Tree
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 * <p>
 * Given the root of a binary tree, a target node's value in that tree, and
 * an integer k, return the values of all nodes that have a distance k from
 * the target node (distance is measured in number of edges, and can go
 * through parent links too, not just children).
 *
 * <pre>
 * Example 1:
 * Input:
 *              3
 *            /   \
 *           5     1
 *          / \   / \
 *         6   2 0   8
 *            / \
 *           7   4
 *
 * target = 5, k = 2
 * Output: [7, 4, 1]
 *
 * Example 2:
 * Input:
 *    1
 *
 * target = 1, k = 3
 * Output: []
 * Explanation: The single node is 3 edges away from nothing - there's no
 * node at distance 3 in a 1-node tree.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 500].
 * - 0 &lt;= Node.val &lt;= 500
 * - All Node.val values are unique.
 * - target is the value of a node in the tree.
 * - 0 &lt;= k &lt;= 1000
 * </pre>
 */
public class Q036_AllNodesDistanceKInBinaryTree {

    /**
     * @implNote TODO: implement.
     * Target approach: First build a node -> parent map via DFS/BFS so the
     * tree can effectively be treated as an undirected graph. Then run a
     * BFS starting from the target node, moving to left child, right
     * child, and parent, tracking visited nodes; the nodes dequeued at BFS
     * depth k are the answer.
     * <p>
     * Target Time Complexity: O(n) - building the parent map plus a BFS,
     * each O(n).
     * <br>
     * Target Space Complexity: O(n) - parent map, visited set, and queue.
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // TODO: implement
        return new ArrayList<>();
    }
}
