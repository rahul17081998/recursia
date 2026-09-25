package com.demo.DSA.concept.P001_Tree;

/**
 * Q010. Children Sum Property in a Binary Tree
 * <p>
 * Given the root of a binary tree, check whether it satisfies the
 * children sum property: for every node with at least one child, the
 * node's value must equal the sum of its children's values (a missing
 * child counts as 0). Leaf nodes trivially satisfy the property.
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
 * Output: false
 * Explanation: root's children sum to 2 + 3 = 5, which is not equal to
 * the root's value 1.
 * </pre>
 */
public class Q010_ChildrenSumInABinaryTree {

    /**
     * @implNote Approach: Recursive DFS — a subtree satisfies the property
     * if the current node's value equals the sum of its children's values
     * (0 for a missing child) AND both the left and right subtrees also
     * satisfy the property. Null nodes and leaf nodes are trivially true.
     * <p>
     * Time Complexity: O(n) - every node is visited exactly once.
     * <br>
     * Space Complexity: O(h) - recursion stack depth equals the tree
     * height h (O(n) worst case for a skewed tree, O(log n) if balanced).
     */
    public boolean isSumProperty(TreeNode root) {

        if(root==null) return true;
        if(root.left==null && root.right==null) return true; // child nodes

        int sum=0;
        sum=root.left!=null? sum+root.left.val: sum;
        sum=root.right!=null? sum+root.right.val: sum;

        return root.val==sum && isSumProperty(root.left) && isSumProperty(root.right);
    }


}
