package com.demo.DSA.concept.P002_Graph;

import java.util.List;

/**
 * Q001. BFS Traversal of Graph
 * https://www.geeksforgeeks.org/problems/bfs-traversal-of-graph/1
 * <p>
 * Given an undirected graph with V vertices (0 to V-1) represented as an
 * adjacency list, return the BFS traversal starting from vertex 0,
 * visiting neighbors in the order they appear in the adjacency list.
 *
 * <pre>
 * Example 1:
 * Input: V = 5, adj = [[1,2,3],[0],[0,4],[0],[2]]
 * (edges: 0-1, 0-2, 0-3, 2-4)
 * Output: [0, 1, 2, 3, 4]
 *
 * Example 2:
 * Input: V = 3, adj = [[1,2],[0],[0]]
 * Output: [0, 1, 2]
 *
 * Constraints:
 * - 1 &lt;= V &lt;= 10^5
 * - Graph may be disconnected in general BFS problems, but this variant
 *   only requires traversal reachable from vertex 0.
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * BFS explores a graph in waves: every node at distance 1 from the source
 * is visited before any node at distance 2, and so on. A FIFO queue is
 * what enforces that ordering - mark the source visited and enqueue it,
 * then repeatedly dequeue a vertex and enqueue each of its unvisited
 * neighbors, marking each one visited <strong>at the moment it's
 * enqueued</strong>, not when it's later dequeued. That single detail is
 * what stops the same vertex from being queued twice through two
 * different parents before either copy is ever processed.
 * <p>
 * Because the queue always processes vertices in discovery order, and
 * every edge has the same "length" (1), the first time any vertex is
 * dequeued it is guaranteed to have been reached via a shortest possible
 * path from the source - which is why BFS is the standard tool for
 * shortest paths in unweighted graphs.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://cp-algorithms.com/graph/breadth-first-search.html">cp-algorithms.com &mdash; Breadth First Search</a>
 *   - rigorous, free, and pairs the theory with clean reference code.</li>
 *   <li><a href="https://visualgo.net/en/dfsbfs">VisuAlgo.net &mdash; Graph Traversal (BFS/DFS)</a>
 *   - step through BFS on your own graph, node by node, with the frontier and queue drawn live.</li>
 * </ul>
 */
public class Q001_SA_BFSTraversalOfGraph {

    /**
     * @implNote TODO: implement.
     * Target approach: Standard BFS - use a queue seeded with vertex 0 and
     * a visited[] array. Poll a vertex, add it to the result, and enqueue
     * each unvisited neighbor (marking it visited at enqueue time, not
     * dequeue time, to avoid enqueuing the same vertex twice).
     * <p>
     * Target Time Complexity: O(V + E) - every vertex and edge examined
     * once.
     * <br>
     * Target Space Complexity: O(V) - queue plus visited array plus
     * output list.
     */
    public List<Integer> bfsOfGraph(int V, List<List<Integer>> adj) {
        // TODO: implement
        return null;
    }
}
