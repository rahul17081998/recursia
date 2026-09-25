package com.demo.DSA.concept.P001_Tree;

import java.util.*;

/**
 * Q022. Top View of Binary Tree
 * https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1
 * <p>
 * Given a binary tree, print the top view - the set of nodes visible when
 * the tree is viewed from the top. Each node has a horizontal distance
 * (HD) from the root: root has HD 0, left child has HD - 1, right child
 * has HD + 1. For each HD, only the topmost (first encountered in level
 * order) node is included, ordered from leftmost to rightmost HD.
 *
 * <pre>
 * Example 1 (from GFG):
 * Input:
 *              10
 *            /    \
 *          20      30
 *         /  \     /  \
 *        40  60   90  100
 *
 * Output: [40, 20, 10, 30, 100]
 * Explanation: 90 sits at HD 0, same as root 10, but 10 was seen first (it
 * is shallower), so 90 is hidden from the top view.
 *
 * Example 2:
 * Input:
 *            1
 *          /   \
 *         2     3
 *        / \     \
 *       4   5     6
 *
 * Output: [4, 2, 1, 3, 6]
 *
 * Constraints (per GFG):
 * - 1 &lt;= T &lt;= 100 (number of test cases)
 * - 1 &lt;= N &lt;= 100 (number of nodes)
 * - Expected Time Complexity: O(N)
 * - Expected Auxiliary Space: O(N)
 * </pre>
 */
public class Q022_TopViewOfBinaryTree {

    public class TempNode{
        int hd;
        TreeNode treeNode=null;

        TempNode(int hd, TreeNode treeNode){
            this.hd=hd;
            this.treeNode=treeNode;
        }
    }
    /**
     * @implNote TODO: implement.
     * Target approach: BFS level order while tracking horizontal distance
     * (HD) per node in the queue. Use a TreeMap&lt;Integer, Integer&gt;
     * keyed by HD; only insert a value the first time its HD is seen (since
     * BFS visits shallower nodes first). Read the map in key order.
     * <p>
     * Target Time Complexity: O(n log n) - n queue operations plus
     * TreeMap insertions.
     * <br>
     * Target Space Complexity: O(n) - queue plus the HD map.
     */
    public List<Integer> topView(TreeNode root) {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        List<Integer> ans = new ArrayList<>();
        if(root==null) return ans;

        Queue<TempNode> q = new LinkedList<>();
        q.offer(new TempNode(0, root));

        while(!q.isEmpty()){
            TempNode curr = q.poll();
            if(!map.containsKey(curr.hd)){
                map.put(curr.hd, curr.treeNode.val);
            }

            if(curr.treeNode.left!=null) q.offer(new TempNode(curr.hd-1, curr.treeNode.left));
            if(curr.treeNode.right!=null) q.offer(new TempNode(curr.hd+1, curr.treeNode.right));
        }

        ans = map.values().stream()
                .toList();


        return ans;
    }
}
