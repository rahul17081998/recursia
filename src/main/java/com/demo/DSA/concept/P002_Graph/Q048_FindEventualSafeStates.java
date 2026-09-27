package com.demo.DSA.concept.P002_Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Q048. Find Eventual Safe States
 * https://leetcode.com/problems/find-eventual-safe-states/<p>
 * https://www.geeksforgeeks.org/problems/eventual-safe-states/1<p>
 * https://algomaster.io/learn/dsa/find-eventual-safe-states
 * <p>
 * Given a directed graph with n nodes (0 to n-1) as an adjacency array
 * (graph[i] lists the nodes i points to), a node with no outgoing edges
 * is a <strong>terminal</strong> node. A node is <strong>safe</strong> if
 * every path starting from it eventually reaches a terminal node (a safe
 * node pointing only to other safe nodes is itself safe) - equivalently,
 * a node is safe iff it can never reach a cycle. Return all safe nodes in
 * ascending order.
 *
 * <pre>
 * Example 1 (LeetCode):
 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * Output: [2,4,5,6]
 * Explanation: Nodes 5 and 6 are terminal (no outgoing edges). Node 2
 * heads straight to terminal node 5, so it's safe (and so is 4, for the
 * same reason). Nodes 0, 1, and 3 form a cycle (0-&gt;1-&gt;3-&gt;0), so they
 * can never reach a terminal node and are unsafe.
 *
 * Example 2 (LeetCode):
 * Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
 * Output: [4]
 * Explanation: Node 4 is the only terminal node, and it's the only node
 * that's safe - node 1 has a self-loop (1-&gt;1), nodes 0 and 3 form a
 * 2-cycle (0-&gt;3-&gt;0), and node 2 feeds into that cycle via 2-&gt;3.
 *
 * Example 3 (GeeksforGeeks):
 * Input: graph = [[],[0,2,3,4],[3],[4],[]]
 * Output: [0, 1, 2, 3, 4]
 * Explanation: Nodes 0 and 4 are terminal. Every path from 1, 2, or 3
 * leads to 0 or 4, so with no cycle anywhere, all 5 nodes are safe.
 *
 * Example 4 (GeeksforGeeks):
 * Input: graph = [[],[2],[3],[2]]
 * Output: [0]
 * Explanation: Node 0 is terminal (safe). Nodes 2 and 3 form a 2-cycle
 * (2-&gt;3-&gt;2), and node 1 feeds into that cycle, so 1, 2, and 3 are all
 * unsafe.
 *
 * Constraints:
 * - n == graph.length
 * - 1 &lt;= n &lt;= 10^4
 * - 0 &lt;= graph[i].length &lt;= n
 * - 0 &lt;= graph[i][j] &lt;= n - 1, graph[i] sorted in strictly increasing order
 * - The graph may contain self-loops (a self-loop is itself a 1-node cycle).
 * - The number of edges is in [1, 4 * 10^4].
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * This is "detect a cycle" wearing a different hat: a node is unsafe
 * exactly when it lies on a cycle or has a path into one, so a 3-color DFS
 * (the same shape as {@link Q014_SA_DetectCycleInDirectedGraph}) settles
 * it in one pass with memoization. Color a node <strong>visiting</strong>
 * when DFS enters it, <strong>safe</strong> when DFS leaves it having found
 * every neighbor safe, and leave it <strong>unvisited</strong> otherwise.
 * Recursing into a neighbor that's currently <strong>visiting</strong> is a
 * back edge to an ancestor - a cycle - so the current node (and everything
 * that led to it) is unsafe. Memoizing the safe/unsafe verdict per node is
 * what keeps this O(V + E) instead of re-walking shared subgraphs.
 * <p>
 * The other classic angle: reverse every edge, so original terminal nodes
 * (no outgoing edges) become sources (in-degree 0) in the reversed graph.
 * Running Kahn's algorithm ({@link Q017_SA_TopologicalSortKahnsBFS}) from
 * those sources peels off exactly the safe nodes, in the order they become
 * "provably safe" - a node can only be output once every node it originally
 * pointed to has already been output.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://leetcode.com/problems/find-eventual-safe-states/description/">LeetCode 802 - Find Eventual Safe States</a>
 *   - the canonical version of this problem, with both the DFS-coloring and reverse-graph/Kahn's solutions discussed in the editorial.</li>
 *   <li><a href="https://www.geeksforgeeks.org/dsa/eventual-safe-states/">GeeksforGeeks - Eventual Safe States</a>
 *   - same problem, phrased over an adjacency-list V/E graph; walks through both approaches side by side.</li>
 * </ul>
 */
public class Q048_FindEventualSafeStates {

    /**
     * @implNote TODO: implement.
     * Target approach: 3-color DFS with memoization - color[] entries are
     * UNVISITED (0), VISITING (1, on the current recursion stack), or SAFE
     * (2, already proven safe). dfs(node): if color[node] is SAFE return
     * true; if VISITING, return false (back edge - cycle found); otherwise
     * mark VISITING, recurse into every neighbor (if any returns false,
     * this node is unsafe - return false), then mark SAFE and return true
     * once every neighbor comes back safe. Run dfs from every node and
     * collect the ones that return true, in ascending order (iterating
     * 0..n-1 already visits them in order).
     * <p>
     * Target Time Complexity: O(V + E) - memoization means each node's
     * neighbors are only ever explored once.
     * <br>
     * Target Space Complexity: O(V) - color array plus the recursion stack.
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        // Make adj graph
        int V= graph.length;
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<V; i++) adj.add(new ArrayList<>());

        for(int i=0; i<V; i++){
            for(Integer v: graph[i]){
                adj.get(v).add(i); // reverse the graph
            }
        }
        Q017_SA_TopologicalSortKahnsBFS bfs = new Q017_SA_TopologicalSortKahnsBFS();
        List<Integer> safeNode = bfs.topoSort(V, adj);
        safeNode.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });

        return safeNode;
    }
}
