package com.demo.DSA.concept.P002_Graph;

import java.util.List;

/**
 * Q042. Reconstruct Itinerary
 * https://leetcode.com/problems/reconstruct-itinerary/
 * <p>
 * Given tickets[i] = [from, to] representing a directed flight ticket,
 * reconstruct the itinerary using ALL tickets exactly once, starting from
 * "JFK". If multiple valid itineraries exist, return the one that is
 * smallest in lexical order when read as a single string. It's guaranteed
 * a valid itinerary using all tickets exists.
 *
 * <pre>
 * Example 1:
 * Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],
 *        ["LHR","SFO"]]
 * Output: ["JFK","MUC","LHR","SFO","SJC"]
 *
 * Example 2:
 * Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],
 *        ["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another valid itinerary is ["JFK","SFO","ATL","JFK",
 * "ATL","SFO"], but it's larger in lexical order.
 *
 * Constraints:
 * - 1 &lt;= tickets.length &lt;= 300
 * - tickets[i][0].length == 3, tickets[i][1].length == 3 (all airport
 *   codes are 3 uppercase letters)
 * - All flights must be used exactly once.
 * </pre>
 */
public class Q042_ReconstructItinerary {

    /**
     * @implNote TODO: implement.
     * Target approach: This is finding an Eulerian path (a path using
     * every edge exactly once) via Hierholzer's algorithm. Build an
     * adjacency map from airport to a min-heap (or sorted list) of
     * destination airports, so the lexically smallest option is always
     * tried first. DFS from "JFK": at each airport, repeatedly pop and
     * recurse into the smallest available destination (consuming that
     * ticket), until no tickets remain from the current airport - then
     * add the current airport to the front of the result (post-order).
     * This greedy-with-backtracking-via-post-order construction correctly
     * handles dead ends, since a route that gets "stuck" (using all
     * tickets from a hub prematurely) ends up placed correctly once its
     * remaining branches are exhausted and it's appended in reverse
     * finishing order.
     * <p>
     * Target Time Complexity: O(E log E) - E tickets, each destination
     * list sorted/heap-ordered.
     * <br>
     * Target Space Complexity: O(E) - adjacency map plus recursion stack
     * and result list.
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        // TODO: implement
        return null;
    }
}
