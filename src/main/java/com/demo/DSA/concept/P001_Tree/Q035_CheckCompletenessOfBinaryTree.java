package com.demo.DSA.concept.P001_Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Q035. Check Completeness of a Binary Tree
 * https://leetcode.com/problems/check-completeness-of-a-binary-tree/
 * <p>
 * Given the root of a binary tree, determine if it is a complete binary
 * tree - every level, except possibly the last, is completely filled, and
 * all nodes in the last level are as far left as possible.
 *
 * <pre>
 * Example 1:
 * Input:
 *            1
 *          /   \
 *         2     3
 *        / \   /
 *       4   5 6
 *
 * Output: true
 *
 * Example 2:
 * Input:
 *            1
 *          /   \
 *         2     3
 *        / \     \
 *       4   5     7
 *
 * Output: false
 * Explanation: Node 6 is missing at position 5 before node 7 appears at
 * position 6, so the last level's nodes are not as far left as possible.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 100].
 * - 1 &lt;= Node.val &lt;= 1000
 * </pre>
 */
public class Q035_CheckCompletenessOfBinaryTree {

    /**
     * @implNote BFS level order, enqueuing only real (non-null) children -
     * no null placeholders. A node with a left child missing but a right
     * child present immediately fails completeness. Otherwise, the first
     * time a node is found with any missing child (left-only child, or a
     * leaf), a flag is raised; every node dequeued after that must itself
     * have no children, or the tree is not complete.
     * <p>
     * Time Complexity: O(n) - every node processed once.
     * <br>
     * Space Complexity: O(n) - queue holds up to the widest level.
     */
    public boolean isCompleteTree(TreeNode root) {
        if(root==null) return true;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean isNullSeen=false;

        while(!q.isEmpty()){

            TreeNode curr = q.poll();
            if(isNullSeen){
                if(curr.left!=null || curr.right!=null) return false;
            }else{
                if(curr.left==null && curr.right!=null) return false;

                if(curr.left==null || curr.right==null) isNullSeen=true;
                if(curr.left!=null) q.offer(curr.left);
                if(curr.right!=null) q.offer(curr.right);
            }
        }


        return true;
    }
}
