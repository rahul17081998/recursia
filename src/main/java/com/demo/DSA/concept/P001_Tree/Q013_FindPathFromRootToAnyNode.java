package com.demo.DSA.concept.P001_Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Q013. Find Path From Root to Any Node
 * <p>
 * Given the root of a binary tree and a target node value, return the path
 * of node values from the root to that target node (inclusive). If the
 * target does not exist in the tree, return an empty list.
 *
 * <pre>
 * Example:
 * Input:
 *            1
 *          /   \
 *         2     3
 *        / \     \
 *       4   5     6
 *       target = 6
 *
 * Output: [1, 3, 6]
 * </pre>
 */
public class Q013_FindPathFromRootToAnyNode {

    /**
     * @implNote Approach: DFS with backtracking — add the current node to
     * the path, return true immediately if it's the target, otherwise
     * recurse into the left and right subtrees; if neither finds the
     * target, remove the current node from the path (backtrack) and
     * return false.
     * <p>
     * Time Complexity: O(n) worst case - may visit every node before
     * finding the target (or determining it doesn't exist).
     * <br>
     * Space Complexity: O(h) - recursion stack depth plus the path list,
     * both bounded by the tree height h.
     */
    public List<Integer> getPath(TreeNode root, int target) {
        List<Integer> pathToNode = new ArrayList<>();
        isPathExist(root, pathToNode, target);

        return pathToNode;
    }

    private boolean isPathExist(TreeNode root, List<Integer> pathToNode, int target) {

        if(root==null) return false;
        pathToNode.add(root.val);

        if(root.val==target) return true; // path exist

        if(isPathExist(root.left, pathToNode, target) ||
        isPathExist(root.right, pathToNode, target)){
            return true;
        }

        pathToNode.remove(pathToNode.size()-1);
        return false;
    }


}
    

