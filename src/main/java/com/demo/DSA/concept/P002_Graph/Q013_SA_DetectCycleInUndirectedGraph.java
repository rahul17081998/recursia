package com.demo.DSA.concept.P002_Graph;

import java.util.List;

/**
 * Q013. Detect Cycle in an Undirected Graph
 * https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
 * <p>
 * Given an undirected graph with V vertices (0 to V-1) as an adjacency
 * list, determine whether it contains a cycle.
 *
 * <pre>
 * Example 1:
 * Input: V = 4, adj = [[1,3],[0,2],[1,3],[0,2]]
 * (edges: 0-1, 1-2, 2-3, 3-0)
 * Output: true
 *
 * Example 2:
 * Input: V = 4, adj = [[1],[0,2],[1,3],[2]]
 * (edges: 0-1, 1-2, 2-3 - a straight chain)
 * Output: false
 *
 * Constraints:
 * - 1 &lt;= V &lt;= 10^5
 * - The graph may be disconnected - check every component.
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * In an undirected adjacency list, edge (u, v) is stored on both u's and
 * v's lists - so DFS/BFS from u will immediately "see" v, and from v it
 * will immediately see u right back. That is not a cycle, it is the same
 * edge read in both directions. The fix is to track each vertex's
 * <strong>parent</strong> in the traversal: a back edge to an
 * already-visited vertex only signals a cycle when that vertex is
 * <em>not</em> the current vertex's parent.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://cp-algorithms.com/graph/finding-cycle.html">cp-algorithms.com &mdash; Finding a Cycle in a Graph</a>
 *   - covers the undirected and directed variants side by side, which
 *   makes the parent-vs-recursion-stack distinction click.</li>
 *   <li>YouTube &mdash; search "William Fiset undirected graph cycle detection" (Graph Theory playlist)
 *   - animated whiteboard dry run of DFS catching a back edge to a non-parent.</li>
 * </ul>
 */
public class Q013_SA_DetectCycleInUndirectedGraph {

    /**
     * @implNote TODO: implement.
     * Target approach: BFS/DFS from every unvisited vertex, tracking each
     * vertex's parent in the traversal. A cycle exists if you ever reach
     * an already-visited neighbor that is NOT the current vertex's
     * immediate parent (revisiting the parent is expected in an
     * undirected graph's adjacency list and isn't itself a cycle).
     * <p>
     * Target Time Complexity: O(V + E) - every vertex and edge examined
     * once.
     * <br>
     * Target Space Complexity: O(V) - visited array plus queue/recursion
     * stack.
     */
    public boolean isCycle(int V, List<List<Integer>> adj) {
        // TODO: implement
        boolean[] vis = new boolean[V];

        for(int i=0;i<V;i++){
            if(!vis[i]){
                //vis[i] = true;
                System.out.println("--> vertex: "+i);
                if(dfs(i,-1,adj,vis)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int parent, List<List<Integer>> adj, boolean[] vis) {
        if(vis[i] && i != parent){
            return true;
        }
        boolean ans = false;
        vis[i] = true;
        for(Integer u : adj.get(i)){
            if(u == parent) continue;
            ans = ans || dfs(u,i,adj,vis);
        }
        return ans;
    }
}
