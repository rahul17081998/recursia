package com.demo.DSA.concept.P002_Graph;

import java.util.List;

/**
 * Q002. DFS Traversal of Graph
 * https://www.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1
 * <p>
 * Given an undirected graph with V vertices (0 to V-1) represented as an
 * adjacency list, return the DFS traversal starting from vertex 0,
 * visiting neighbors in the order they appear in the adjacency list.
 *
 * <pre>
 * Example 1:
 * Input: V = 5, adj = [[1,2,3],[0],[0,4],[0],[2]]
 * (edges: 0-1, 0-2, 0-3, 2-4)
 * Output: [0, 1, 2, 4, 3]
 *
 * Example 2:
 * Input: V = 3, adj = [[1,2],[0],[0]]
 * Output: [0, 1, 2]
 *
 * Constraints:
 * - 1 &lt;= V &lt;= 10^5
 * - This variant only requires traversal reachable from vertex 0.
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * DFS commits to one path as deep as it goes before backing up to try
 * another - mark the start vertex visited, then recurse into one
 * unvisited neighbor at a time. It does not return to try a sibling
 * neighbor until the entire subtree beneath the current one has been
 * fully explored. That "go deep, then backtrack" shape produces a
 * completely different visiting order from BFS on the exact same graph.
 * <p>
 * DFS matters beyond simple traversal because it naturally exposes
 * <strong>ancestor/descendant</strong> relationships as it runs - which
 * is exactly the structure that cycle detection, topological sort,
 * bridges, articulation points, and strongly connected components are
 * all built on top of.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://cp-algorithms.com/graph/depth-first-search.html">cp-algorithms.com &mdash; Depth First Search</a>
 *   - covers the traversal plus the applications built directly on it.</li>
 *   <li><a href="https://visualgo.net/en/dfsbfs">VisuAlgo.net &mdash; Graph Traversal (BFS/DFS)</a>
 *   - the same module as BFS, toggled to DFS mode - watch the descend/backtrack shape play out live.</li>
 * </ul>
 */
public class Q002_SA_DFSTraversalOfGraph {

    /**
     * @implNote TODO: implement.
     * Target approach: Recursive (or explicit-stack iterative) DFS from
     * vertex 0 - mark 0 visited, add it to the result, then recurse into
     * each unvisited neighbor in adjacency-list order.
     * <p>
     * Target Time Complexity: O(V + E) - every vertex and edge examined
     * once.
     * <br>
     * Target Space Complexity: O(V) - recursion stack (or explicit stack)
     * plus visited array plus output list.
     */
    public List<Integer> dfsOfGraph(int V, List<List<Integer>> adj) {
        // TODO: implement
        return null;
    }
}
