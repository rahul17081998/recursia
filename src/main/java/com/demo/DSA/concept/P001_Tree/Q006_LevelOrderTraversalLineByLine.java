package com.demo.DSA.concept.P001_Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Q006. Level Order Traversal, Line by Line
 * <p>
 * Given the root of a binary tree, return the level-order traversal of its
 * nodes' values grouped by level (one list per level, top to bottom).
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
 * Output: [[1], [2, 3], [4, 5, 6]]
 * </pre>
 */
public class Q006_LevelOrderTraversalLineByLine {

    /**
     * @implNote Approach: BFS level-order traversal — process the queue one
     * level at a time (using the queue's size at the start of each level as
     * the boundary) and collect each level's values into its own list.
     * <p>
     * Time Complexity: O(n) - every node is visited exactly once.
     * <br>
     * Space Complexity: O(w) - the queue holds at most the width of the
     * widest level (w), which is O(n) in the worst case (e.g. a complete tree).
     */
    public List<List<Integer>> getLevelOrderTraversalLineByLine(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();


        if(root==null) return result;

        Queue<TreeNode> q= new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> nodesAtTheCurrentLevel = new ArrayList<>();

            while(size>0){
                TreeNode curr = q.poll();
                nodesAtTheCurrentLevel.add(curr.val);
                if(curr.left!=null) q.offer(curr.left);
                if(curr.right!=null) q.offer(curr.right);
                size--;
            }
            result.add(nodesAtTheCurrentLevel);
        }

        return result;
    }


}
