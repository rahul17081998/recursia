package com.demo.DSA.concept.P002_Graph;

/**
 * Q012. Clone Graph
 * https://leetcode.com/problems/clone-graph/
 * <p>
 * Given a reference to a node in a connected undirected graph, return a
 * deep copy (clone) of the graph. Each node contains a value and a list
 * of neighbors (see {@link GraphNode}).
 *
 * <pre>
 * Example 1:
 * Input (adjacency list form): adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: node 1's neighbors are 2 and 4; node 2's neighbors are 1
 * and 3; node 3's neighbors are 2 and 4; node 4's neighbors are 1 and 3.
 * Output: a separate graph, identical in structure and values, sharing no
 * node objects with the input.
 *
 * Example 2:
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: a single node with no neighbors.
 *
 * Constraints:
 * - The number of nodes is in the range [0, 100].
 * - 1 &lt;= Node.val &lt;= 100
 * - Node.val is unique for each node.
 * - There are no repeated edges and no self-loops.
 * - The graph is connected.
 * </pre>
 */
public class Q012_CloneGraph {

    /**
     * @implNote TODO: implement.
     * Target approach: DFS (or BFS) from the given node while maintaining
     * a Map&lt;GraphNode, GraphNode&gt; from original node to its clone.
     * On visiting an original node for the first time, create its clone
     * and store the mapping before recursing into neighbors (this handles
     * cycles - if a neighbor is visited again, the map already has its
     * clone). For each neighbor, recurse (creating its clone if needed)
     * and add the resulting clone to the current clone's neighbor list.
     * <p>
     * Target Time Complexity: O(V + E) - every node and edge visited
     * once.
     * <br>
     * Target Space Complexity: O(V) - the map plus recursion stack.
     */
    public GraphNode cloneGraph(GraphNode node) {
        // TODO: implement
        return null;
    }
}
