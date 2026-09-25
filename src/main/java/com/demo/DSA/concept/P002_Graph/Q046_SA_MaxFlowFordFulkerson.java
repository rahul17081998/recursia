package com.demo.DSA.concept.P002_Graph;

/**
 * Q046. Maximum Flow - Ford-Fulkerson (Edmonds-Karp) Algorithm
 * https://www.geeksforgeeks.org/problems/find-the-maximum-flow2126/1
 * <p>
 * Given a flow network - a directed graph with n vertices (0 to n-1)
 * where each edge (u, v) has a non-negative capacity, a source vertex,
 * and a sink vertex - return the maximum amount of flow that can be
 * pushed from source to sink, respecting every edge's capacity and
 * conserving flow at every intermediate vertex (flow in == flow out).
 *
 * <pre>
 * Example 1 (the classic textbook flow network):
 * Input: n = 6, edges = [[0,1,16],[0,2,13],[1,2,10],[2,1,4],[1,3,12],
 *        [3,2,9],[2,4,14],[4,3,7],[3,5,20],[4,5,4]], source = 0, sink = 5
 * Output: 23
 * Explanation: One max-flow decomposition: 0-&gt;1-&gt;3-&gt;5 carries 12,
 * 0-&gt;2-&gt;4-&gt;5 carries 4, 0-&gt;2-&gt;4-&gt;3-&gt;5 carries 7, 0-&gt;1-&gt;2-&gt;4-&gt;3-&gt;5
 * carries... (many equivalent decompositions exist); the total achievable
 * flow is 23, which is also the capacity of the network's min cut.
 *
 * Example 2 (simple diamond):
 * Input: n = 4, edges = [[0,1,3],[0,2,2],[1,3,2],[2,3,3]], source = 0,
 *        sink = 3
 * Output: 4
 * Explanation: Send 2 along 0-&gt;1-&gt;3 and 2 along 0-&gt;2-&gt;3; vertex 1's
 * outgoing edge (capacity 2) is the bottleneck on one path, vertex 0's
 * edge to 2 (capacity 2) is the bottleneck on the other - total 4, which
 * matches the min cut (edges out of {0}: 3+2=5, but edges into {3}:
 * 2+3=5; the true min cut here is {0,1} vs {2,3}: edges crossing are
 * 0-&gt;2 (2) and 1-&gt;3 (2), totaling 4).
 *
 * Constraints:
 * - 1 &lt;= n &lt;= 500
 * - 0 &lt;= capacity &lt;= 10^4
 * - source != sink
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * Keep finding any path from source to sink with spare capacity and push
 * flow along it - leaving a "reverse edge" behind so a bad earlier
 * choice can be undone later. Build a <strong>residual graph</strong>:
 * every edge keeps track of its remaining capacity, and gets a paired
 * <strong>reverse edge</strong> starting at 0 capacity. Repeat: find a
 * path from source to sink using only edges with capacity &gt; 0
 * (Edmonds-Karp specifically uses BFS for this, which is what guarantees
 * polynomial time). If no path exists, stop - the current total flow is
 * maximum. Otherwise, find the <strong>bottleneck</strong> (the minimum
 * residual capacity along the path), subtract it from every forward edge
 * on the path, and - this is the crucial part - <em>add</em> it to every
 * reverse edge on the path. Add the bottleneck to the running total
 * flow.
 * <p>
 * The reverse edges are what make this correct: they let a later
 * augmenting path effectively "cancel" flow that an earlier greedy
 * choice pushed the wrong way, without ever needing to explicitly detect
 * or undo that earlier decision.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://cp-algorithms.com/graph/edmonds_karp.html">cp-algorithms.com &mdash; Maximum Flow, Ford-Fulkerson and Edmonds-Karp</a>
 *   - explains why BFS-chosen augmenting paths (vs. any arbitrary DFS
 *   path) is what actually bounds the runtime polynomially.</li>
 *   <li><a href="https://visualgo.net/en/maxflow">VisuAlgo.net &mdash; Max Flow</a>
 *   - animates each augmenting path being found and pushed, with residual/reverse edges drawn live.</li>
 * </ul>
 */
public class Q046_SA_MaxFlowFordFulkerson {

    /**
     * @implNote TODO: implement.
     * Target approach: Ford-Fulkerson method, using BFS to find each
     * augmenting path (this specialization is called Edmonds-Karp, and
     * guarantees polynomial time). Build a residual graph (capacity
     * remaining on each directed edge, plus a reverse edge for every
     * original edge, initially 0 capacity, to allow "undoing" flow).
     * Repeat: BFS from source to sink using only edges with remaining
     * capacity &gt; 0; if sink is unreachable, stop. Otherwise find the
     * bottleneck (minimum residual capacity) along the found path,
     * subtract it from every forward edge on the path and add it to
     * every reverse edge, and add the bottleneck to the total flow.
     * <p>
     * Target Time Complexity: O(V * E^2) with Edmonds-Karp (BFS-chosen
     * augmenting paths) - at most O(V*E) augmentations, each an O(E) BFS.
     * <br>
     * Target Space Complexity: O(V + E) - the residual graph (adjacency
     * list or matrix) plus BFS queue.
     */
    public int maxFlow(int n, int[][] edges, int source, int sink) {
        // TODO: implement
        return 0;
    }
}
