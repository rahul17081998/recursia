package com.demo.DSA.concept.P002_Graph;

/**
 * Q026. Network Delay Time
 * https://leetcode.com/problems/network-delay-time/
 * <p>
 * You are given a network of n nodes labeled 1 to n, and times[i] = [u,
 * v, w] meaning a signal travels from u to v with time w. Starting a
 * signal from node k, return the minimum time for the signal to reach
 * all n nodes, or -1 if it's impossible for all nodes to receive it.
 *
 * <pre>
 * Example 1:
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * Output: 2
 * Explanation: From 2: node 1 and 3 arrive at time 1; node 4 (via 2-&gt;3-&gt;4)
 * arrives at time 2 - the max over all nodes is 2.
 *
 * Example 2:
 * Input: times = [[1,2,1]], n = 2, k = 1
 * Output: 1
 *
 * Example 3:
 * Input: times = [[1,2,1]], n = 2, k = 2
 * Output: -1
 * Explanation: Node 1 is unreachable from node 2.
 *
 * Constraints:
 * - 1 &lt;= n &lt;= 100
 * - 1 &lt;= times.length &lt;= 6000
 * - times[i].length == 3, 1 &lt;= u, v &lt;= n, u != v
 * - 0 &lt;= w &lt;= 100
 * - All (u, v) pairs are unique (there can be multiple edges though, in
 *   general - take the minimum weight one, or let Dijkstra's relaxation
 *   handle it naturally).
 * </pre>
 */
public class Q026_NetworkDelayTime {

    /**
     * @implNote TODO: implement.
     * Target approach: A direct application of
     * {@link Q025_SA_DijkstraShortestPath} - run Dijkstra from node k over
     * the directed weighted graph to get the shortest time to every node.
     * If any node's distance is still infinity, return -1 (unreachable);
     * otherwise the answer is the maximum distance across all nodes (the
     * time for the LAST node to receive the signal).
     * <p>
     * Target Time Complexity: O((V + E) log V) with a binary heap.
     * <br>
     * Target Space Complexity: O(V + E) - adjacency list, dist array, and
     * heap.
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // TODO: implement
        return -1;
    }
}
