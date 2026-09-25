package com.demo.DSA.concept.P001_Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Q020. Boundary Traversal of Binary Tree
 * https://www.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1
 * <p>
 * Given a binary tree, print its boundary nodes anti-clockwise starting
 * from the root: the left boundary (top-down, excluding leaves), then all
 * leaf nodes (left to right), then the right boundary (bottom-up,
 * excluding leaves).
 *
 * <pre>
 * Example 1 (from GFG):
 * Input:
 *      1
 *     / \
 *    2   3
 *
 * Output: [1, 2, 3]
 *
 * Example 2:
 * Input:
 *            1
 *          /   \
 *         2     3
 *        / \     \
 *       4   5     6
 *
 * Output: [1, 2, 4, 5, 6, 3]
 * Explanation: root(1) -> left boundary excluding leaves(2) -> leaves left
 * to right(4, 5, 6) -> right boundary excluding leaves, bottom-up(3).
 *
 * Constraints (per GFG):
 * - 1 &lt;= number of nodes &lt;= 10^5
 * - 1 &lt;= Data of a node &lt;= 10^5
 * - Expected Time Complexity: O(N)
 * - Expected Auxiliary Space: O(Height of the Tree)
 * </pre>
 */
public class Q020_BoundaryTraversal {

    /**
     * @implNote TODO: implement.
     * Target approach: Split into three parts collected separately - (1)
     * root, (2) left boundary top-down skipping leaf nodes, (3) all leaves
     * via a DFS/inorder-style scan left to right, (4) right boundary
     * bottom-up skipping leaf nodes (collect top-down then reverse, or
     * recurse right-first then add on the way back up).
     * <p>
     * Target Time Complexity: O(n) - every node is visited a constant
     * number of times.
     * <br>
     * Target Space Complexity: O(h) - recursion/traversal depth, plus O(n)
     * for the output list.
     */

    HashMap<Integer, List<List<Integer>>> map; // columnNo --> [[h1, nodeValue1], [], [], ...]

    public List<Integer> boundaryTraversal(TreeNode root) {
        // TODO: implement
        return new ArrayList<>();
    }
}
