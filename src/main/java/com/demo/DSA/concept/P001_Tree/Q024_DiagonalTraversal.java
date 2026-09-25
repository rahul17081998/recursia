package com.demo.DSA.concept.P001_Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Q024. Diagonal Traversal of Binary Tree
 * https://www.geeksforgeeks.org/problems/diagonal-traversal-of-binary-tree/1
 * <p>
 * Given a binary tree, return its diagonal traversal by considering lines
 * of slope -1 (i.e., every right-child link keeps a node on the same
 * diagonal, while every left-child link starts a new diagonal one level
 * deeper).
 *
 * <pre>
 * Example 1:
 * Input:
 *            1
 *          /   \
 *         2     3
 *        / \     \
 *       4   5     6
 *
 * Output: [1, 3, 6, 2, 5, 4]
 * Explanation: Diagonal 0 = {1, 3, 6} (root, then right-chained). Diagonal
 * 1 = {2, 5} (2's right child 5 stays on 2's diagonal). Diagonal 2 = {4}
 * (2's left child starts a new diagonal).
 *
 * Example 2:
 * Input:
 *        8
 *       / \
 *      3   10
 *     / \    \
 *    1   6    14
 *       / \   /
 *      4   7 13
 *
 * Output: [8, 10, 14, 3, 6, 7, 13, 1, 4]
 * Explanation: Diagonal 0 = {8, 10, 14}. Diagonal 1 = {3, 6, 7, 13} (3's
 * right-chain 6 then 7; 14's left child 13 joins the same diagonal).
 * Diagonal 2 = {1, 4} (3's left child 1, and 6's left child 4).
 *
 * Constraints (typical GFG bounds for this problem):
 * - 1 &lt;= number of nodes &lt;= 10^5
 * - Expected Time Complexity: O(N)
 * - Expected Auxiliary Space: O(N)
 * </pre>
 */
public class Q024_DiagonalTraversal {

    /**
     * @implNote TODO: implement.
     * Target approach: Recursive/BFS traversal tracking a "diagonal level"
     * per node - passing right keeps the same diagonal, passing left
     * increments it. Collect nodes into a map keyed by diagonal level
     * (using a queue/list per key, in the order encountered), then flatten
     * in key order.
     * <p>
     * Target Time Complexity: O(n) - every node is visited once.
     * <br>
     * Target Space Complexity: O(n) - map/queue holding all nodes plus the
     * output.
     */
    public List<Integer> diagonalTraversal(TreeNode root) {
        // TODO: implement
        return new ArrayList<>();
    }
}
