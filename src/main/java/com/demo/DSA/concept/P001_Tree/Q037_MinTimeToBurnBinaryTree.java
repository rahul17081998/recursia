package com.demo.DSA.concept.P001_Tree;

/**
 * Q037. Minimum Time to Burn a Binary Tree Starting From a Target Node
 * https://www.geeksforgeeks.org/problems/burning-tree/1
 * <p>
 * Given a binary tree and a target node's value, fire is set to the target
 * node. Every second, fire spreads from a burning node to its left child,
 * right child, and parent (if they exist and aren't already burnt). Find
 * the minimum time required to burn the entire tree.
 *
 * <pre>
 * Example 1 (from GFG):
 * Input:
 *                1
 *              /   \
 *             2     3
 *            / \      \
 *           4   5      6
 *              / \      \
 *             7   8      9
 *                          \
 *                          10
 *
 * target = 8
 * Output: 7
 * Explanation: t=1 burns 5. t=2 burns 2, 7. t=3 burns 4, 1. t=4 burns 3.
 * t=5 burns 6. t=6 burns 9. t=7 burns 10. Total time = 7 seconds.
 *
 * Example 2:
 * Input:
 *      1
 *     / \
 *    2   3
 *
 * target = 2
 * Output: 2
 * Explanation: t=1 burns 1 (2's parent). t=2 burns 3 (1's other child).
 * Total time = 2 seconds.
 *
 * Constraints (per GFG):
 * - All node values in the tree are unique.
 * - target is guaranteed to be a value present in the tree.
 * </pre>
 */
public class Q037_MinTimeToBurnBinaryTree {

    /**
     * @implNote TODO: implement.
     * Target approach: Same shape as {@link Q036_AllNodesDistanceKInBinaryTree}
     * - build a node -> parent map via DFS, then BFS outward from the
     * target node (via left, right, and parent links) tracking visited
     * nodes and levels. The answer is the maximum BFS depth reached, i.e.
     * the number of seconds until the last node is visited.
     * <p>
     * Target Time Complexity: O(n) - parent map build plus a full BFS.
     * <br>
     * Target Space Complexity: O(n) - parent map, visited set, and queue.
     */
    public int minTimeToBurnTree(TreeNode root, int target) {
        // TODO: implement
        return -1;
    }
}
