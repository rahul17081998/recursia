package com.demo.DSA.concept.P002_Graph;

import java.util.*;

/**
 * Q025. Dijkstra's Algorithm - Shortest Path
 * https://www.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1
 * <p>
 * Given a weighted undirected graph with V vertices (0 to V-1) as an
 * adjacency list (each entry {neighbor, weight}) and a source vertex, all
 * edge weights non-negative, return the shortest distance from source to
 * every vertex.
 *
 * <pre>
 * Example 1:
 * Input: V = 5, edges = [[0,1,4],[0,2,8],[1,4,6],[2,3,2],[3,4,10]],
 *        src = 0
 * Output: [0, 4, 8, 10, 10]
 * Explanation: 0-&gt;1 costs 4; 0-&gt;2 costs 8; 0-&gt;2-&gt;3 costs 10; 0-&gt;1-&gt;4
 * costs 10 (cheaper than 0-&gt;2-&gt;3-&gt;4's 20).
 *
 * Example 2:
 * Input: V = 3, edges = [[0,1,1],[1,2,1]], src = 0
 * Output: [0, 1, 2]
 *
 * Constraints:
 * - 1 &lt;= V &lt;= 10^4
 * - All edge weights are non-negative (Dijkstra doesn't handle negative
 *   weights correctly - see {@link Q027_SA_BellmanFordShortestPath} for
 *   that case).
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * Always finalize whichever un-finalized vertex is currently closest -
 * with non-negative weights, that greedy choice is never wrong. Keep a
 * <code>dist[]</code> array (infinity everywhere except the source,
 * which is 0) and a min-heap of <code>(distance, vertex)</code> pairs,
 * seeded with <code>(0, source)</code>. Repeatedly pop the smallest
 * entry. If it is <strong>stale</strong> - the popped distance is larger
 * than what is already recorded in <code>dist[]</code> - skip it (a
 * cheaper route was already found and processed). Otherwise,
 * <strong>relax</strong> every outgoing edge: if
 * <code>dist[u] + w &lt; dist[v]</code>, update <code>dist[v]</code> and
 * push the improved pair.
 * <p>
 * The reason non-negative weights matter: once a vertex is popped with
 * its true shortest distance, no path discovered later can possibly be
 * shorter, since every future path can only add more (non-negative)
 * distance. Negative edges break that guarantee entirely - that is
 * exactly why Bellman-Ford exists.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://cp-algorithms.com/graph/dijkstra.html">cp-algorithms.com &mdash; Dijkstra Algorithm</a>
 *   - shows both the O(V&sup2;) array version and the heap-optimized
 *   version, plus exactly why negative weights break the correctness
 *   proof.</li>
 *   <li><a href="https://visualgo.net/en/sssp">VisuAlgo.net &mdash; Single-Source Shortest Paths</a>
 *   - animates the min-heap pops and edge relaxations one at a time on a graph you control.</li>
 * </ul>
 */
public class Q025_SA_DijkstraShortestPath {

    /**
     * One entry in the adjacency list = one edge.
     * node -> the neighbour vertex (other end of the edge)
     * wt   -> weight (cost) of this single edge
     */
    public static class Pair {
        int node; int wt;
        Pair(int node, int wt) {
            this.node = node;
            this.wt = wt;
        }
    }

    /**
     * One entry in the priority queue = a candidate shortest path.
     * node -> the vertex we reached
     * dist -> TOTAL distance from src to this vertex (not a single edge)
     */
    public static class State {
        int node; int dist;
        State(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    /**
     * @implNote TODO: implement.
     * Target approach: Initialize dist[] to infinity except dist[src]=0.
     * Use a min-heap (PriorityQueue) of {distance, vertex} pairs, seeded
     * with {0, src}. Repeatedly pop the smallest-distance entry; if it's
     * stale (popped distance &gt; current dist[vertex]), skip it. Otherwise
     * relax every outgoing edge - if dist[vertex] + weight &lt; dist[neighbor],
     * update dist[neighbor] and push {newDist, neighbor}.
     * <p>
     * Dijkstra itself doesn't care whether the graph is directed or
     * undirected - that only affects how the adjacency list is built (add
     * the reverse edge or don't). {@code directed} controls that; other
     * questions with a directed weighted graph (e.g.
     * {@link Q026_NetworkDelayTime}) can call this same method with
     * {@code directed=true} instead of reimplementing Dijkstra.
     * <p>
     * Target Time Complexity: O((V + E) log V) with a binary heap.
     * <br>
     * Target Space Complexity: O(V + E) - adjacency list, dist array, and
     * heap.
     */
    public int[] dijkstra(int V, int[][] edges, int src, boolean directed) {

        // Step 1: build the adjacency list. adj.get(u) = all edges going out of u.
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            adj.get(u).add(new Pair(v, wt));
            if (!directed) adj.get(v).add(new Pair(u, wt)); // undirected -> add the reverse edge too
        }

        // Step 2: dist[i] = shortest distance from src to i found so far.
        // Start with "infinity" everywhere, 0 for the source.
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Step 3: min-heap ordered by total distance -> always gives the closest vertex first
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.dist, b.dist));
        pq.offer(new State(src, 0));

        while (!pq.isEmpty()) {
            State curr = pq.poll();
            int u = curr.node;

            // Stale entry: a shorter path to u was already found and processed -> skip it
            if (curr.dist > dist[u]) continue;

            // Relax every edge u -> v
            for (Pair p : adj.get(u)) {
                int v = p.node;
                int wt = p.wt;

                // Going through u gives a shorter path to v -> update and push
                // (no overflow: dist[u] is always finite here, since u was popped)
                if (dist[v] > dist[u] + wt) {
                    dist[v] = dist[u] + wt;
                    pq.offer(new State(v, dist[v]));
                }
            }
        }

        // Unreachable vertices stay Integer.MAX_VALUE
        return dist;
    }
}
