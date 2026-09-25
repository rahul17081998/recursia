package com.demo.DSA.concept.P001_Tree;

import java.util.List;
import java.util.Objects;

/**
 * Q014. Lowest Common Ancestor (LCA) in a Binary Tree
 * <p>
 * Given the root of a binary tree and two node values, return the value of
 * their lowest common ancestor — the deepest node that has both given
 * nodes as descendants (a node can be its own ancestor).
 *
 * <pre>
 * Example:
 * Input:
 *            1
 *          /   \
 *         2     3
 *        / \     \
 *       4   5     6
 *       node1 = 4, node2 = 6
 *
 * Output: 1
 * </pre>
 */
public class Q014_LowestCommonAncestor_LCA {

    /**
     * @implNote Approach: Find the root-to-node path for each target using
     * {@link Q013_FindPathFromRootToAnyNode#getPath}, then walk both paths
     * together — the last index at which they still match is the LCA.
     * <p>
     * Time Complexity: O(n) - two O(n) path lookups plus an O(min(path
     * lengths)) comparison.
     * <br>
     * Space Complexity: O(h) - each root-to-node path is bounded by the
     * tree height h.
     */
    public int getLCA(TreeNode root, int node1, int node2) {

        Q013_FindPathFromRootToAnyNode ob = new Q013_FindPathFromRootToAnyNode();

        List<Integer> pathToNode1 = ob.getPath(root, node1);
        List<Integer> pathToNode2 = ob.getPath(root, node2);
//        System.out.println(pathToNode1);
//        System.out.println(pathToNode2);

        int lca=-1;
        for(int i=0; i<Math.min(pathToNode1.size(), pathToNode2.size()); i++){
            if(Objects.equals(pathToNode1.get(i), pathToNode2.get(i))){
                lca=pathToNode1.get(i);
            }
        }
        return lca;
    }
}
    

