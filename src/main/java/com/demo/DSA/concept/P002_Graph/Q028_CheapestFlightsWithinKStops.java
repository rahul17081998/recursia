package com.demo.DSA.concept.P002_Graph;

/**
 * Q028. Cheapest Flights Within K Stops
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 * <p>
 * There are n cities connected by flights, given as flights[i] = [from,
 * to, price]. Given src, dst, and k, return the cheapest price to travel
 * from src to dst with at most k stops (i.e. at most k+1 edges). Return
 * -1 if no such route exists.
 *
 * <pre>
 * Example 1:
 * Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],
 *        [2,3,200]], src = 0, dst = 3, k = 1
 * Output: 700
 * Explanation: 0-&gt;1-&gt;3 costs 100+600=700 (1 stop). 0-&gt;1-&gt;2-&gt;3 costs
 * 400 but uses 2 stops, exceeding k=1.
 *
 * Example 2:
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0,
 *        dst = 2, k = 1
 * Output: 200
 *
 * Constraints:
 * - 1 &lt;= n &lt;= 100
 * - 0 &lt;= flights.length &lt;= (n * (n - 1) / 2)
 * - 0 &lt;= src, dst, k &lt; n, src != dst
 * - 1 &lt;= price &lt;= 10^4, no duplicate edges or self-loops.
 * </pre>
 */
public class Q028_CheapestFlightsWithinKStops {

    /**
     * @implNote TODO: implement.
     * Target approach: Plain Dijkstra doesn't work directly here, because
     * the "at most k stops" constraint means the cheapest overall path
     * might not be the cheapest path found first. Use a Bellman-Ford-style
     * relaxation limited to k+1 rounds - maintain dist[] (cheapest price
     * to each city found so far), and on each round relax every flight
     * edge using a SNAPSHOT of dist[] from the start of that round (not
     * updates made mid-round), so each round only extends paths by
     * exactly one more edge.
     * <p>
     * Target Time Complexity: O(k * E) - at most k+1 rounds, each
     * scanning all flight edges.
     * <br>
     * Target Space Complexity: O(V) - the dist array (plus its
     * per-round snapshot).
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // TODO: implement
        return -1;
    }
}
