package com.demo.DSA.concept.P001_Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Q028. Morris Inorder Traversal (O(1) Space)
 * https://www.geeksforgeeks.org/dsa/inorder-tree-traversal-without-recursion/
 * <p>
 * Given the root of a binary tree, return its inorder traversal without
 * using recursion or an explicit stack - using Morris Traversal, which
 * achieves O(1) auxiliary space by temporarily threading the tree: linking
 * each node's inorder predecessor's right pointer back to the node itself,
 * then removing the thread once it has been used.
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
 * Output: [4, 2, 5, 1, 3, 6]
 *
 * Example 2:
 * Input:
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1, 3, 2]
 * </pre>
 */
public class Q028_MorrisInorderTraversal {

    /**
     * @implNote TODO: implement.
     * Target approach: At each current node - if no left child, visit it
     * and move right. Otherwise find the rightmost node in the left
     * subtree (the inorder predecessor); if its right pointer is null,
     * thread it to current and move left; if it already points to current,
     * remove the thread, visit current, and move right.
     * <p>
     * Target Time Complexity: O(n) - each edge is traversed at most twice
     * (once to create the thread, once to remove it).
     * <br>
     * Target Space Complexity: O(1) - no recursion stack or explicit stack,
     * excluding the output list.
     */
    public List<Integer> morrisInorderTraversal(TreeNode root) {
        // TODO: implement
        return new ArrayList<>();
    }
}
