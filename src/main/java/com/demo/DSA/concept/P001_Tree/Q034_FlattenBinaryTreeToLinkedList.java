package com.demo.DSA.concept.P001_Tree;

/**
 * Q034. Flatten Binary Tree to Linked List
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 * <p>
 * Given the root of a binary tree, flatten it to a "linked list" in-place:
 * the flattened tree should use the TreeNode's right child pointer to
 * point to the next node in the list, the left child pointer should always
 * be null, and the ordering should be the same as a preorder traversal.
 *
 * <pre>
 * Example 1:
 * Input:
 *          1
 *        /   \
 *       2     5
 *      / \     \
 *     3   4     6
 *
 * Output (as a right-only chain): 1 -> 2 -> 3 -> 4 -> 5 -> 6
 * As a tree (only right pointers used): 1
 *                                         \
 *                                          2
 *                                           \
 *                                            3
 *                                             \
 *                                              4
 *                                               \
 *                                                5
 *                                                 \
 *                                                  6
 *
 * Example 2:
 * Input: root = []  (empty tree)
 * Output: [] (still empty)
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 2000].
 * - -100 &lt;= Node.val &lt;= 100
 * </pre>
 */
public class Q034_FlattenBinaryTreeToLinkedList {

    /**
     * @implNote TODO: implement.
     * Target approach: Reverse-preorder DFS (right, then left, then
     * current) while keeping a "previously flattened node" reference;
     * for each node visited (in right-left-root order), set its right
     * child to the previous node and left child to null, then update
     * previous to the current node. Alternatively, use the Morris-like
     * O(1) space trick: for each node with a left child, attach the
     * rightmost node of the left subtree's right pointer to the current
     * right subtree, then move left subtree to the right.
     * <p>
     * Target Time Complexity: O(n) - every node processed once.
     * <br>
     * Target Space Complexity: O(h) recursive, or O(1) with the iterative
     * Morris-style approach.
     */
    public void flatten(TreeNode root) {
        // TODO: implement
    }
}
