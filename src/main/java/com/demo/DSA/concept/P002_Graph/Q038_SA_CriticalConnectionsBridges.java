package com.demo.DSA.concept.P002_Graph;

import java.util.List;

/**
 * Q038. Critical Connections in a Network (Bridges in a Graph)
 * https://leetcode.com/problems/critical-connections-in-a-network/
 * <p>
 * There are n servers labeled 0 to n-1 connected by connections (an
 * undirected graph). A critical connection (bridge) is an edge whose
 * removal would disconnect the graph. Return all critical connections.
 *
 * <pre>
 * Example 1:
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: 0-1-2 form a cycle (removing any one of those 3 edges
 * still leaves the graph connected via the other two), but 1-3 is the
 * only bridge - removing it disconnects server 3.
 *
 * Example 2:
 * Input: n = 2, connections = [[0,1]]
 * Output: [[0,1]]
 *
 * Constraints:
 * - 2 &lt;= n &lt;= 10^5
 * - n-1 &lt;= connections.length &lt;= 10^5
 * - No self-loops or duplicate edges; the graph is connected.
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * An edge is a bridge exactly when there is no other way back to it, or
 * higher, from below it in the DFS tree. Run DFS tracking two numbers
 * per vertex: <code>disc[u]</code> (the order u was first discovered)
 * and <code>low[u]</code> (the lowest <code>disc</code> value reachable
 * from u's subtree, via at most one back edge to an ancestor). After
 * recursing into a child v, update
 * <code>low[u] = min(low[u], low[v])</code>. If
 * <code>low[v] &gt; disc[u]</code>, that means nothing in v's entire
 * subtree has a back edge reaching u or higher - so the edge (u, v) is
 * the <em>only</em> connection between that subtree and the rest of the
 * graph. Removing it disconnects the graph: it is a bridge.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://cp-algorithms.com/graph/bridge-searching.html">cp-algorithms.com &mdash; Finding Bridges in O(N+M)</a>
 *   - this low-link idea is reused almost verbatim for articulation points
 *   and Tarjan's SCC; this is the clearest place to learn it first.</li>
 *   <li>YouTube &mdash; search "William Fiset bridges graph theory" (Graph Theory playlist)
 *   - animated dry run of disc[]/low[] values being computed and a bridge being identified.</li>
 * </ul>
 */
public class Q038_SA_CriticalConnectionsBridges {

    /**
     * @implNote TODO: implement.
     * Target approach: Tarjan's bridge-finding algorithm - DFS from any
     * node, assigning each node a discovery time disc[] (the order it was
     * first visited) and a low[] value (the lowest discovery time
     * reachable from that node's subtree, including via one back edge to
     * an ancestor). For each DFS tree edge (u, v), after recursing into
     * v, update low[u] = min(low[u], low[v]); if low[v] &gt; disc[u], no
     * back edge from v's subtree reaches u or higher, meaning edge (u, v)
     * is a bridge. Skip the edge back to u's immediate parent (that's not
     * a "back edge" in the cycle-detecting sense) when updating low[u]
     * from a neighbor.
     * <p>
     * Target Time Complexity: O(V + E) - a single DFS pass.
     * <br>
     * Target Space Complexity: O(V + E) - adjacency list, disc[]/low[]
     * arrays, recursion stack.
     */
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // TODO: implement
        return null;
    }
}
