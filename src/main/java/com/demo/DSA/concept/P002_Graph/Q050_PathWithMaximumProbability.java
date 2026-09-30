package com.demo.DSA.concept.P002_Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Q050. Path with Maximum Probability
 * https://leetcode.com/problems/path-with-maximum-probability/
 * https://algomaster.io/learn/dsa/path-with-maximum-probability
 * <p>
 * You are given an undirected weighted graph of {@code n} nodes
 * (0-indexed), represented by an edge list where {@code edges[i] = [a, b]}
 * is an undirected edge connecting nodes {@code a} and {@code b} with a
 * probability of success of traversing that edge {@code succProb[i]}.
 * Given two nodes {@code start} and {@code end}, find the path with the
 * maximum probability of success to go from start to end and return its
 * success probability. If there is no path from start to end, return 0.
 * Answers within 1e-5 of the correct answer are accepted.
 *
 * <pre>
 * Example 1:
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2],
 *        start = 0, end = 2
 * Output: 0.25000
 * Explanation: 0-&gt;2 directly is 0.2; 0-&gt;1-&gt;2 is 0.5 * 0.5 = 0.25,
 * which is higher.
 *
 * Example 2:
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3],
 *        start = 0, end = 2
 * Output: 0.30000
 * Explanation: Now the direct edge (0.3) beats the two-hop path (0.25).
 *
 * Example 3:
 * Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
 * Output: 0.00000
 * Explanation: There is no path between 0 and 2.
 *
 * Example 4 (more edges can still win):
 * Input: n = 4, edges = [[0,1],[1,2],[2,3],[0,3]],
 *        succProb = [0.9,0.9,0.9,0.5], start = 0, end = 3
 * Output: 0.72900
 * Explanation: 0-&gt;1-&gt;2-&gt;3 is 0.9^3 = 0.729, better than the direct
 * edge's 0.5 - fewest edges is not the same as highest probability.
 *
 * Constraints:
 * - 2 &lt;= n &lt;= 10^4
 * - 0 &lt;= start, end &lt; n, start != end
 * - 0 &lt;= edges.length &lt;= 2 * 10^4, edges[i].length == 2
 * - 0 &lt;= a, b &lt; n, a != b
 * - succProb.length == edges.length, 0 &lt;= succProb[i] &lt;= 1
 * - There is at most one edge between every two nodes.
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * This is {@link Q025_SA_DijkstraShortestPath} turned upside down: instead
 * of <em>adding</em> weights and keeping the <em>smallest</em> total, you
 * <em>multiply</em> probabilities and keep the <em>largest</em> product.
 * Keep a {@code prob[]} array (0 everywhere, 1 at {@code start}) and a
 * <strong>max-heap</strong> of {@code (node, probability)} pairs seeded with
 * {@code (start, 1.0)}. Pop the most likely node; if its probability is
 * lower than what {@code prob[]} already holds, it is stale - skip it.
 * Otherwise relax every edge: if {@code prob[u] * p &gt; prob[v]}, update
 * {@code prob[v]} and push the improved pair.
 * <p>
 * Why greedy is still safe: every edge probability is between 0 and 1, so
 * multiplying by another edge can never <em>increase</em> a path's
 * probability. That is the same "extending a path never makes it better"
 * guarantee that non-negative weights give plain Dijkstra - so the first
 * time a node is popped, its probability is final. (An edge with
 * probability above 1 would break that, just like a negative edge breaks
 * Dijkstra - see {@link Q027_SA_BellmanFordShortestPath}.)
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://leetcode.com/problems/path-with-maximum-probability/">LeetCode 1514 - Path with Maximum Probability</a>
 *   - the canonical version; the editorial also shows a Bellman-Ford solution to compare against.</li>
 *   <li><a href="https://algomaster.io/learn/dsa/path-with-maximum-probability">AlgoMaster - Path with Maximum Probability</a>
 *   - same problem, alongside the rest of AlgoMaster's shortest-path unit.</li>
 * </ul>
 */
public class Q050_PathWithMaximumProbability {

    /**
     * @implNote TODO: implement.
     * Target approach: Build an undirected adjacency list of
     * {neighbour, probability} pairs. prob[] = 0.0 everywhere,
     * prob[start] = 1.0. Max-heap ordered by probability (descending),
     * seeded with {start, 1.0}. Pop the highest-probability entry; skip it
     * if stale (popped value &lt; prob[node]); return it immediately if the
     * node is {@code end}. Otherwise relax every edge: newProb =
     * prob[u] * edgeProb, and if newProb &gt; prob[v], update prob[v] and
     * push {v, newProb}. If the heap empties without reaching end, return
     * 0.0.
     * <p>
     * Target Time Complexity: O((V + E) log V) with a binary heap.
     * <br>
     * Target Space Complexity: O(V + E) - adjacency list, prob array, and
     * heap.
     */
    public static class NodeSuccDetail {
        int node;
        double succProb;
        NodeSuccDetail(int node, double succProb) {
            this.node = node;
            this.succProb = succProb;
        }
    }


    public static class NodeCurrState {
        int node;
        double maxSuccessProbToReachThisNode;
        NodeCurrState(int node, double maxSuccessProbToReachThisNode) {
            this.node = node;
            this.maxSuccessProbToReachThisNode = maxSuccessProbToReachThisNode;
        }
    }


    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<List<NodeSuccDetail>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(new NodeSuccDetail(v, succProb[i]));
            adj.get(v).add(new NodeSuccDetail(u, succProb[i]));
        }

        return applyDijkstra(start, end, n, adj);

    }

    private double applyDijkstra(int src, int dest, int n, List<List<NodeSuccDetail>> adj) {

        double[] probDist = new double[n];
        Arrays.fill(probDist, 0.0);

        // MAX-heap (s2 compared before s1): always process the node with the highest
        // success probability first - opposite of Dijkstra's usual min-heap, because
        // here a bigger value is better (we multiply probabilities, not add distances)
        PriorityQueue<NodeCurrState> pq = new PriorityQueue<>((NodeCurrState s1, NodeCurrState s2) -> Double.compare(s2.maxSuccessProbToReachThisNode, s1.maxSuccessProbToReachThisNode));
        pq.offer(new NodeCurrState(src, 1));
        probDist[src] = 1;

        while (!pq.isEmpty()) {
            NodeCurrState currNode = pq.poll();
            int u = currNode.node;
            if (probDist[u] > currNode.maxSuccessProbToReachThisNode) continue;

            for (NodeSuccDetail neighbour : adj.get(u)) {
                int v = neighbour.node; double probWt = neighbour.succProb;

                if (probDist[v] < probDist[u] * probWt) {
                    probDist[v] = probDist[u] * probWt;
                    pq.offer(new NodeCurrState(v, probDist[v]));
                }
            }
        }

        return probDist[dest];
    }
}
