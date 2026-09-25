package com.demo.DSA.concept.P002_Graph;

import java.util.List;

/**
 * Q014. Detect Cycle in a Directed Graph
 * https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
 * <p>
 * Given a directed graph with V vertices (0 to V-1) as an adjacency list,
 * determine whether it contains a cycle.
 *
 * <pre>
 * Example 1:
 * Input: V = 4, adj = [[1],[2],[3],[1]]
 * (edges: 0-&gt;1, 1-&gt;2, 2-&gt;3, 3-&gt;1)
 * Output: true
 * Explanation: 1 -&gt; 2 -&gt; 3 -&gt; 1 is a cycle.
 *
 * Example 2:
 * Input: V = 4, adj = [[1],[2],[3],[]]
 * Output: false
 *
 * Constraints:
 * - 1 &lt;= V &lt;= 10^5
 * - The graph may be disconnected - check every component.
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * "Already visited" is not enough here - you need to know whether a
 * vertex is an <strong>ancestor on the current path</strong> (the
 * recursion stack), not just visited at some earlier unrelated point.
 * Track two states per vertex: <code>visited</code> (ever explored) and
 * <code>onStack</code> (currently an ancestor in the active DFS call
 * chain). A directed edge to a vertex that is <code>onStack</code> is a
 * genuine cycle; an edge to a vertex that is <code>visited</code> but
 * <em>not</em> <code>onStack</code> is fine - it just means two separate
 * paths happen to converge there.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://cp-algorithms.com/graph/finding-cycle.html">cp-algorithms.com &mdash; Finding a Cycle in a Graph</a>
 *   - same page as the undirected case; read them together to see exactly
 *   why one needs a parent check and the other needs a recursion-stack
 *   check.</li>
 *   <li>YouTube &mdash; search "William Fiset directed graph cycle detection" (Graph Theory playlist)
 *   - animated dry run of the recursion-stack (onStack[]) technique.</li>
 * </ul>
 */
public class Q014_SA_DetectCycleInDirectedGraph {

    /**
     * @implNote TODO: implement.
     * Target approach: DFS from every unvisited vertex while maintaining
     * two boolean arrays - visited[] (ever visited) and
     * inRecursionStack[] (currently on the active DFS path). Before
     * recursing into a neighbor, mark the current vertex inRecursionStack;
     * a cycle exists if DFS reaches a neighbor that is already
     * inRecursionStack (a back edge to an ancestor). Clear the current
     * vertex from inRecursionStack when backtracking. (Unlike the
     * undirected version, a directed graph needs this recursion-stack
     * check, not just a visited check, since revisiting an already-fully-
     * processed vertex via a different path is NOT a cycle.)
     * <p>
     * Target Time Complexity: O(V + E) - every vertex and edge examined
     * once.
     * <br>
     * Target Space Complexity: O(V) - visited + recursion-stack arrays
     * plus the call stack itself.
     */
    public boolean isCyclic(int V, List<List<Integer>> adj) {

        //return DFS(V, adj);
        return new Q017_SA_TopologicalSortKahnsBFS().topoSort(V, adj).size()!=V;

    }

    private boolean DFS(int V, List<List<Integer>> adj) {
        boolean[] vis = new boolean[V];
        boolean[] onStack = new boolean[V];
        for(int currNode=0; currNode<V; currNode++){
            if(!vis[currNode]){
                if(isCyclicComponent(currNode, adj, vis, onStack))
                    return true;
            }
        }
        return false;
    }

    private boolean isCyclicComponent(int u, List<List<Integer>> adj, boolean[] vis, boolean[] onStack) {

        vis[u]=true;
        onStack[u]=true;

        for(Integer neighbour: adj.get(u)){
            if(onStack[neighbour]) return true;
            if(!vis[neighbour] && isCyclicComponent(neighbour, adj, vis, onStack)) return true;
        }
        onStack[u]=false;
        return false;
    }
}
