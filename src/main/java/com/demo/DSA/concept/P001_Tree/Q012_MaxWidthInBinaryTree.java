package com.demo.DSA.concept.P001_Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Q012. Maximum Width in a Binary Tree
 * <p>
 * Given the root of a binary tree, return the maximum number of nodes
 * present at any single level of the tree.
 *
 * <pre>
 * Example:
 * Input:
 *            1
 *          /   \
 *         2     3
 *        / \     \
 *       4   5     6
 *
 * Output: 3
 * Explanation: level 2 (nodes 4, 5, 6) has the most nodes.
 * </pre>
 */
public class Q012_MaxWidthInBinaryTree {

    /**
     * @implNote Approach: BFS level-order traversal — the queue size at the
     * start of each level is exactly that level's node count; track the
     * maximum such size seen across all levels.
     * <p>
     * Time Complexity: O(n) - every node is visited exactly once.
     * <br>
     * Space Complexity: O(w) - the queue holds at most the width of the
     * widest level (w), which is O(n) in the worst case (e.g. a complete tree).
     */
    public int getMaxWidth(TreeNode root) {
        return levelOrderTraversal(root);
    }

    private int levelOrderTraversal(TreeNode root) {
        if(root==null) return 0;
        int maxWidth=0;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int qsize = q.size();
            maxWidth=Math.max(maxWidth, qsize);
            while(qsize>0){
                TreeNode curr = q.poll();
                if(curr.left!=null) q.offer(curr.left);
                if(curr.right!=null) q.offer(curr.right);
                qsize--;
            }
        }

        return maxWidth;
    }


}
    

