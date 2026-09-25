package com.demo.DSA.concept.P002_Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Q018. Topological Sort (DFS-based)
 * https://www.geeksforgeeks.org/problems/topological-sort/1
 * <p>
 * Given a Directed Acyclic Graph (DAG) with V vertices (0 to V-1) as an
 * adjacency list, return a valid topological ordering using a DFS-based
 * approach (as an alternative to Kahn's BFS approach in
 * {@link Q017_SA_TopologicalSortKahnsBFS}).
 *
 * <pre>
 * Example 1:
 * Input: V = 6, adj = [[],[],[3],[1],[0,1],[0,2]]
 * (edges: 5-&gt;0, 5-&gt;2, 4-&gt;0, 4-&gt;1, 2-&gt;3, 3-&gt;1)
 * Output: [5, 4, 2, 3, 1, 0] (one valid ordering; others may also be
 * valid)
 *
 * Example 2:
 * Input: V = 3, adj = [[1],[2],[]]
 * Output: [0, 1, 2]
 *
 * Constraints:
 * - 1 &lt;= V &lt;= 10^5
 * - The graph is guaranteed to be a DAG (no cycles).
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * A vertex is safe to place only once everything it points to is already
 * placed - which is exactly a vertex's DFS <strong>finish time</strong>.
 * Run ordinary DFS. When a vertex has finished exploring every one of its
 * neighbors (post-order - on the way back out of the recursion, not on
 * the way in), push it onto a stack. Once DFS has covered every vertex,
 * popping the stack from top to bottom is a valid topological order: a
 * vertex can only be pushed after everything it points to has already
 * been pushed, so it is guaranteed to sit above (and thus pop before)
 * its dependents.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://cp-algorithms.com/graph/topological-sort.html">cp-algorithms.com &mdash; Topological Sorting</a>
 *   - same article as Kahn's; seeing both derivations together makes the
 *   "finish time = safe position" idea concrete.</li>
 *   <li>YouTube &mdash; search "William Fiset topological sort" (Graph Theory playlist)
 *   - animated dry run of the DFS post-order stack build.</li>
 * </ul>
 */
public class Q018_SA_TopologicalSortDFS {

    /**
     * @implNote TODO: implement.
     * Target approach: Run DFS from every unvisited vertex; after
     * recursing into ALL of a vertex's neighbors (i.e. on the way back
     * out, post-order), push that vertex onto a stack. Once every vertex
     * has been visited, popping the stack from top to bottom yields a
     * valid topological order - a vertex is only pushed after everything
     * it points to has already been pushed, so it naturally ends up above
     * (and thus popped before) its dependents.
     * <p>
     * Target Time Complexity: O(V + E) - every vertex and edge visited
     * once.
     * <br>
     * Target Space Complexity: O(V) - visited array, stack, and recursion
     * stack.
     */
    public List<Integer> topoSort(int V, List<List<Integer>> adj) {

        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        for(int i=0; i<V; i++){
            if(!visited[i]){
                dfs(i, adj, stack, visited);
            }
        }

        List<Integer> ans = new ArrayList<>();
        while(!stack.isEmpty()){
            ans.add(stack.pop());
        }

        return ans;
    }

    private void dfs(int node, List<List<Integer>> adj, Stack<Integer> stack, boolean[] visited) {
        visited[node]=true;

        for(Integer neighbour: adj.get(node)){
            if(!visited[neighbour])
                dfs(neighbour, adj, stack, visited);
        }

        // After visiting all dependency
        stack.add(node);
    }
}
