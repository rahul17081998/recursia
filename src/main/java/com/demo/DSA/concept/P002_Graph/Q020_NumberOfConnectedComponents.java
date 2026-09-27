package com.demo.DSA.concept.P002_Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Q020. Number of Connected Components in an Undirected Graph
 * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 * (premium; also common on GFG as a plain "count components" exercise)
 * <p>
 * Given n nodes labeled 0 to n-1 and an edge list, return the number of
 * connected components in the undirected graph.
 *
 * <pre>
 * Example 1:
 * Input: n = 5, edges = [[0,1],[1,2],[3,4]]
 * Output: 2
 *
 * Example 2:
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
 * Output: 1
 *
 * Constraints:
 * - 1 &lt;= n &lt;= 2000
 * - 0 &lt;= edges.length &lt;= n * (n - 1) / 2
 * - No self-loops or duplicate edges.
 * </pre>
 */
public class Q020_NumberOfConnectedComponents {

    /**
     * @implNote TODO: implement.
     * Target approach: Build an adjacency list (see {@link GraphUtil}) and
     * run BFS/DFS from every unvisited node, incrementing a component
     * counter once per launch. Alternatively, Union-Find works well here
     * too - union every edge's endpoints, then count distinct roots.
     * <p>
     * Target Time Complexity: O(V + E) with BFS/DFS, or O(E * alpha(V))
     * with Union-Find.
     * <br>
     * Target Space Complexity: O(V + E) - adjacency list plus
     * visited/queue structures (or the DSU parent array).
     */
    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<n; i++) adj.add(new ArrayList<>());

        for(var edge: edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        boolean[] vis =new boolean[n];

        int count=0;
        for(int i=0; i<n; i++){
            if(!vis[i]){
                bfs_MarkComponent(i, adj, vis);
                count++;
            }
        }
        return count;
    }

    private void bfs_MarkComponent(int i, List<List<Integer>> adj, boolean[] vis) {
        vis[i]=true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);

        while(!q.isEmpty()){
            Integer node = q.poll();
            for(Integer neighbour: adj.get(node)){
                if(!vis[neighbour]){
                    q.offer(neighbour);
                    vis[neighbour]=true;
                }
            }
        }
    }
}
