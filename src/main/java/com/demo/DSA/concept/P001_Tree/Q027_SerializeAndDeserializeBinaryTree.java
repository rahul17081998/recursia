package com.demo.DSA.concept.P001_Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Q027. Serialize and Deserialize Binary Tree
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * <p>
 * Design an algorithm to serialize a binary tree into a string, and
 * deserialize that string back into the original tree structure. There is
 * no restriction on how the serialization/deserialization algorithm should
 * work, only that a tree can be serialized to a string and that string
 * deserialized back to the original tree.
 *
 * <pre>
 * Example 1:
 * Input:
 *          1
 *         / \
 *        2   3
 *           / \
 *          4   5
 *
 * serialize(root) -> e.g. "1,2,null,null,3,4,null,null,5,null,null"
 * deserialize(that string) -> the same tree structure as above
 *
 * Example 2:
 * Input: root = []  (empty tree)
 * serialize(root) -> e.g. "null" (or an empty marker)
 * deserialize(that string) -> [] (empty tree)
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 10^4].
 * - -1000 &lt;= Node.val &lt;= 1000
 * </pre>
 */
public class Q027_SerializeAndDeserializeBinaryTree {

    /**
     * @implNote TODO: implement.
     * Target approach: Preorder DFS, writing "null" markers for empty
     * children so the structure is fully recoverable, joined by a
     * delimiter (e.g. comma).
     * <p>
     * Target Time Complexity: O(n) - every node visited once.
     * <br>
     * Target Space Complexity: O(n) - output string plus O(h) recursion
     * stack.
     */
    public String serialize(TreeNode root) {
       StringBuilder sb = new StringBuilder();
       helpToSerialize(root, sb);
       return sb.toString();
    }

    private void helpToSerialize(TreeNode root, StringBuilder sb) {
        if(!sb.isEmpty()){
            if(root==null)
                sb.append(",null");
            else
                sb.append(","+root.val);
        }else{
            if(root==null)
                sb.append("null");
            else
                sb.append(root.val);
        }

        if(root==null) return;

        helpToSerialize(root.left, sb);
        helpToSerialize(root.right, sb);
    }

    /**
     * @implNote TODO: implement.
     * Target approach: Split the serialized string by the delimiter into a
     * queue/iterator of tokens, then rebuild via preorder DFS - consume one
     * token per call; a "null" token means no node, otherwise create a node
     * and recurse for left then right.
     * <p>
     * Target Time Complexity: O(n) - every token consumed once.
     * <br>
     * Target Space Complexity: O(n) - tokens list plus O(h) recursion
     * stack.
     */
    public TreeNode deserialize(String data) {
        Queue<String> q = new LinkedList<>(Arrays.stream(data.split(",")).toList());
        return helpToDeserialize(q);
    }

    private TreeNode helpToDeserialize(Queue<String> q) {

        if(q.isEmpty()){
            return null;
        }

        String currVal = q.poll();
        if(Objects.equals(currVal, "null")){
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(currVal));
        root.left=helpToDeserialize(q);
        root.right=helpToDeserialize(q);

        return root;

    }


}
