package com.demo.DSA.concept.P002_Graph;

import java.util.List;

/**
 * Q039. Articulation Points in a Graph
 * https://www.geeksforgeeks.org/problems/articulation-point-1/1
 * <p>
 * Given an undirected graph with V vertices (0 to V-1) as an adjacency
 * list, find all articulation points (cut vertices) - vertices whose
 * removal (along with all edges incident to it) increases the number of
 * connected components.
 *
 * <pre>
 * Example 1:
 * Input: V = 5, adj = [[1,2],[0,2],[0,1,3],[2,4],[3]]
 * (edges: 0-1, 0-2, 1-2, 2-3, 3-4)
 * Output: [2, 3]
 * Explanation: Removing vertex 2 disconnects {3,4} from {0,1}; removing
 * vertex 3 disconnects {4} from the rest.
 *
 * Example 2:
 * Input: V = 4, adj = [[1],[0,2],[1,3],[2]]
 * (edges: 0-1, 1-2, 2-3 - a straight chain)
 * Output: [1, 2]
 *
 * Constraints:
 * - 1 &lt;= V &lt;= 10^5
 * - The graph may be disconnected - process every component.
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * Same low-link machinery as bridges, but asking about a vertex: would
 * removing it split its children's subtrees apart? Using the same
 * <code>disc[]</code>/<code>low[]</code> DFS as bridge-finding: a
 * non-root vertex u is a cut vertex if it has some child v with
 * <code>low[v] &gt;= disc[u]</code> - meaning v's subtree has no way to
 * reach above u without passing through u itself. The DFS root is a
 * special case handled separately: it is a cut vertex only if it has
 * <strong>two or more children</strong> in the DFS tree (those
 * children's subtrees only connect to each other through the root).
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://cp-algorithms.com/graph/cutpoints.html">cp-algorithms.com &mdash; Finding Articulation Points in O(N+M)</a>
 *   - spells out the root-vertex special case clearly, the detail most
 *   explanations gloss over.</li>
 *   <li>YouTube &mdash; search "William Fiset articulation points graph theory" (Graph Theory playlist)
 *   - animated dry run showing exactly which vertex's removal splits the graph.</li>
 * </ul>
 */
public class Q039_SA_ArticulationPoints {

    /**
     * @implNote TODO: implement.
     * Target approach: Tarjan's algorithm, closely related to the bridge-
     * finding approach in {@link Q038_SA_CriticalConnectionsBridges} - track
     * disc[] and low[] via DFS. A non-root vertex u is an articulation
     * point if it has a child v in the DFS tree with low[v] &gt;= disc[u]
     * (v's subtree can't reach back above u without going through u).
     * The DFS root is a special case - it's an articulation point iff it
     * has 2 or more children in the DFS tree (meaning removing it splits
     * those subtrees apart, since they only connected to each other
     * through the root).
     * <p>
     * Target Time Complexity: O(V + E) - a single DFS pass per component.
     * <br>
     * Target Space Complexity: O(V + E) - adjacency list, disc[]/low[]
     * arrays, recursion stack.
     */
    public List<Integer> articulationPoints(int V, List<List<Integer>> adj) {
        // TODO: implement
        return null;
    }
}
