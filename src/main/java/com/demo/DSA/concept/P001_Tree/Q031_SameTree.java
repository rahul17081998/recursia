package com.demo.DSA.concept.P001_Tree;

/**
 * Q031. Same Tree
 * https://leetcode.com/problems/same-tree/
 * <p>
 * Given the roots of two binary trees p and q, write a function to check
 * if they are the same. Two binary trees are considered the same if they
 * are structurally identical and the nodes have the same values.
 *
 * <pre>
 * Example 1:
 * Input: p = [1,2,3], q = [1,2,3]
 *
 *   p:  1        q:  1
 *      / \           / \
 *     2   3         2   3
 *
 * Output: true
 *
 * Example 2:
 * Input: p = [1,2], q = [1,null,2]
 *
 *   p:  1        q:  1
 *      /              \
 *     2                2
 *
 * Output: false
 *
 * Constraints:
 * - The number of nodes in both trees is in the range [0, 100].
 * - -10^4 &lt;= Node.val &lt;= 10^4
 * </pre>
 */
public class Q031_SameTree {

    /**
     * @implNote TODO: implement.
     * Target approach: Recursive DFS - both null means equal at this
     * branch; exactly one null or differing values means not equal;
     * otherwise recurse on left subtrees and right subtrees and AND the
     * results.
     * <p>
     * Target Time Complexity: O(min(m, n)) - traversal stops as soon as a
     * mismatch is found; worst case visits every node of the smaller tree.
     * <br>
     * Target Space Complexity: O(min(h1, h2)) - recursion stack depth.
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {

        if(p==null && q==null) return true;
        if(p==null || q==null) return false;

        return (p.val==q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
