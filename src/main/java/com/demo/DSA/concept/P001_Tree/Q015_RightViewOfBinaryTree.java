package com.demo.DSA.concept.P001_Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Q015. Right View of Binary Tree
 * <p>
 * Given the root of a binary tree, return the values of the nodes you can
 * see when the tree is viewed from the right side, ordered from the top
 * level to the bottom level (i.e., the last/rightmost node visible at each
 * level of the tree).
 *
 * <pre>
 * Example:
 * Input:
 *          1
 *        /   \
 *       2     3
 *        \     \
 *         5     4
 *
 * Output: [1, 3, 4]
 * Explanation: Looking from the right, level 0 shows 1, level 1 shows 3
 * (2 is hidden behind it), and level 2 shows 4.
 * </pre>
 */
public class Q015_RightViewOfBinaryTree {

    /**
     * @implNote Approach: BFS level-order traversal. At each level, enqueue
     * the right child before the left child, so the first node dequeued for
     * that level is the rightmost one — add it to the result.
     * <p>
     * Time Complexity: O(n) - every node is visited exactly once.
     * <br>
     * Space Complexity: O(w) - the queue holds at most the width of the
     * widest level (w), which is O(n) in the worst case (e.g. a complete tree).
     */
    public List<Integer> getRightViewOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();


        if(root==null) return result;

        Queue<TreeNode> q= new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int size = q.size();

            int count=0;
            while(size>0){
                TreeNode curr = q.poll();
                if(count==0)
                    result.add(curr.val);
                if(curr.right!=null) q.offer(curr.right);
                if(curr.left!=null) q.offer(curr.left);
                size--;
                count++;
            }

        }

        return result;
    }


}
