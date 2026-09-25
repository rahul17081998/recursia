package com.demo.DSA.concept.P002_Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Node definition used by questions that operate on an explicit
 * node/neighbor structure rather than an adjacency list (e.g. Clone
 * Graph). Mirrors LeetCode's own Node class for that problem.
 */
public class GraphNode {
    public int val;
    public List<GraphNode> neighbors;

    public GraphNode() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public GraphNode(int val) {
        this.val = val;
        neighbors = new ArrayList<>();
    }

    public GraphNode(int val, List<GraphNode> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}
