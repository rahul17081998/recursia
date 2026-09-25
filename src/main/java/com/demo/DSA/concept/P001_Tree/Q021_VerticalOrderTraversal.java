package com.demo.DSA.concept.P001_Tree;

import java.util.*;
/**
 * Q021. Vertical Order Traversal of a Binary Tree
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 * <p>
 * Given the root of a binary tree, calculate the vertical order traversal
 * of the binary tree. For every node at position (row, col), its left and
 * right children are at (row + 1, col - 1) and (row + 1, col + 1)
 * respectively. Group nodes by column from leftmost to rightmost; within
 * the same column and row, order by value ascending if there are ties.
 *
 * <pre>
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 *
 *        3
 *       / \
 *      9  20
 *         / \
 *        15  7
 *
 * Output: [[9],[3,15],[20],[7]]
 *
 * Example 2:
 * Input: root = [1,2,3,4,5,6,7]
 *
 *            1
 *          /   \
 *         2     3
 *        / \   / \
 *       4   5 6   7
 *
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation: Nodes 5 and 6 land on the same row and column, so they are
 * ordered by value ascending (5 before 6).
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 1000].
 * - 0 &lt;= Node.val &lt;= 1000
 * </pre>
 */
public class Q021_VerticalOrderTraversal {

    /**
     * @implNote TODO: implement.
     * Target approach: DFS or BFS while tracking (row, col) for every node;
     * collect into a map keyed by column, storing (row, value) pairs. Sort
     * columns ascending, and within a column sort by row then value.
     * <p>
     * Target Time Complexity: O(n log n) - dominated by sorting nodes
     * within columns/rows.
     * <br>
     * Target Space Complexity: O(n) - map holding all nodes plus the
     * output.
     */

    public static class QueueNode{
        int col;
        int row;
        TreeNode node;

        public QueueNode(int col, int row, TreeNode node){
            this.col=col;
            this.row=row;
            this.node=node;
        }
    }


    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root==null) return ans;

        Queue<QueueNode> q = new LinkedList<>();
        q.offer(new QueueNode(0, 0, root));
        TreeMap<Integer, List<List<Integer>>> map = new TreeMap<>();

        while(!q.isEmpty()){
            QueueNode curr = q.poll();
            List<Integer> pair = Arrays.asList(curr.row, curr.node.val);
            if(map.containsKey(curr.col)){
                map.get(curr.col).add(pair);
            }else{
                List<List<Integer>> list = new ArrayList<>();
                list.add(pair);
                map.put(curr.col, list);
            }

            if(curr.node.left != null) q.offer(new QueueNode(curr.col-1, curr.row+1, curr.node.left));
            if(curr.node.right != null) q.offer(new QueueNode(curr.col+1, curr.row+1, curr.node.right));

        }


        // use streams
        map.values().forEach(arr->
                arr.sort(new Comparator<List<Integer>>() {
                    @Override
                    public int compare(List<Integer> o1, List<Integer> o2) {
                        int result = Integer.compare(o1.get(0), o2.get(0));
                        if(result!=0) return result;
                        return Integer.compare(o1.get(1), o2.get(1));
                    }
                }));
        ans = map.values().stream()
                .map(arr -> arr.stream()
                        .map(pair -> pair.get(1))
                        .toList()
                )
                .toList();
/*
        for(List<List<Integer>> arr: map.values()){
            arr.sort(new Comparator<List<Integer>>() {
                @Override
                public int compare(List<Integer> o1, List<Integer> o2) {
                    int result = Integer.compare(o1.get(0), o2.get(0));
                    if(result!=0) return result;
                    return Integer.compare(o1.get(1), o2.get(1));
                }
            });
        }

        for(List<List<Integer>> arr: map.values()){
            List<Integer> colAns = new ArrayList<>();
            for(List<Integer> pair: arr){
                colAns.add(pair.get(1));
            }
            ans.add(colAns);
        }

 */

        return ans;
    }
}
