package com.demo.DSA.concept.P001_Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Q005. Print Node(s) at Kth Level From Top
 * <p>
 * Given the root of a binary tree and a level k (root is level 1), return
 * the values of all nodes present at level k.
 *
 * <pre>
 * Example:
 * Input:
 *            1
 *          /   \
 *         2     3
 *        / \     \
 *       4   5     6
 *       k = 2
 *
 * Output: [2, 3]
 * </pre>
 */
public class Q005_PrintNodeAtKthLevelFromTop {
    /**
     * @implNote Approach: BFS level-order traversal, tracking the current
     * level number. Once a level's nodes match k, their children are not
     * enqueued, so traversal effectively stops after level k.
     * <p>
     * Time Complexity: O(m) where m is the number of nodes up to and
     * including level k (worst case O(n) if k is the deepest level).
     * <br>
     * Space Complexity: O(w) - the queue holds at most the width of a
     * single level (w).
     */
    public List<Integer> getNodeAtKLevel(TreeNode root, int k){

        // use level order traversal
        List<Integer> kthListNode = new ArrayList<>();
        if(root==null) return kthListNode;

        Queue<TreeNode> q= new LinkedList<>();
        q.offer(root);
        int currLevel=1; // root

        while(!q.isEmpty()){
            int size = q.size();
            while(size>0){
                if(currLevel==k){
                    kthListNode.add(q.poll().val);
                }else{
                    TreeNode curr = q.poll();
                    if(curr.left!=null) q.offer(curr.left);
                    if(curr.right!=null) q.offer(curr.right);
                }
                size--;
            }

            currLevel++;
        }

        return kthListNode;
    }
}
