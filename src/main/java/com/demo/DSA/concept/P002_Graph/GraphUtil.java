package com.demo.DSA.concept.P002_Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Shared helpers so each question file doesn't rebuild graphs by hand.
 * Graphs are represented as 0-indexed adjacency lists, built from an edge
 * list in LeetCode's common {@code int[][] edges} format, e.g.
 * {{0,1},{1,2},{2,0}}.
 */
public class GraphUtil {

    /** Builds an unweighted adjacency list for n nodes (0..n-1) from an edge list. */
    public static List<List<Integer>> buildAdjacencyList(int n, int[][] edges, boolean directed) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            if (!directed) graph.get(v).add(u);
        }
        return graph;
    }

    /**
     * Builds a weighted adjacency list for n nodes (0..n-1) from an edge
     * list in {u, v, weight} format. Each adjacency entry is {neighbor, weight}.
     */
    public static List<List<int[]>> buildWeightedAdjacencyList(int n, int[][] edges, boolean directed) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            graph.get(u).add(new int[]{v, w});
            if (!directed) graph.get(v).add(new int[]{u, w});
        }
        return graph;
    }

    public static void printAdjacencyList(List<List<Integer>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            System.out.println(i + " -> " + graph.get(i));
        }
    }

    public static void printWeightedAdjacencyList(List<List<int[]>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            StringBuilder sb = new StringBuilder(i + " -> [");
            for (int[] edge : graph.get(i)) {
                sb.append("(").append(edge[0]).append(", w=").append(edge[1]).append(") ");
            }
            sb.append("]");
            System.out.println(sb);
        }
    }

    /**
     * Renders an unweighted edge list as Graphviz DOT source. Pipe the
     * result through {@code dot -Tpng} (or -Tsvg) to get an actual node-
     * and-edge diagram, e.g.:
     * {@code dot -Tpng out.dot -o out.png}
     */
    public static String toDot(int n, int[][] edges, boolean directed) {
        StringBuilder sb = new StringBuilder();
        String connector = directed ? " -> " : " -- ";
        sb.append(directed ? "digraph" : "graph").append(" G {\n");
        appendSizingAttrs(sb);
        sb.append("  node [shape=circle, style=filled, fillcolor=lightblue, fontsize=16, width=0.6];\n");
        for (int i = 0; i < n; i++) sb.append("  ").append(i).append(";\n");
        for (int[] edge : edges) {
            sb.append("  ").append(edge[0]).append(connector).append(edge[1]).append(";\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    /** Same as {@link #toDot}, but labels each edge with its weight. */
    public static String toDotWeighted(int n, int[][] edges, boolean directed) {
        StringBuilder sb = new StringBuilder();
        String connector = directed ? " -> " : " -- ";
        sb.append(directed ? "digraph" : "graph").append(" G {\n");
        appendSizingAttrs(sb);
        sb.append("  node [shape=circle, style=filled, fillcolor=lightblue, fontsize=16, width=0.6];\n");
        for (int i = 0; i < n; i++) sb.append("  ").append(i).append(";\n");
        for (int[] edge : edges) {
            sb.append("  ").append(edge[0]).append(connector).append(edge[1])
                    .append(" [label=\"").append(edge[2]).append("\", fontsize=14];\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    /**
     * Same as {@link #toDot}, but for graphs whose nodes are labeled with
     * strings instead of 0..n-1 integers (e.g. variable names, airport
     * codes, single letters) - used by questions like Evaluate Division,
     * Reconstruct Itinerary, and Alien Dictionary. {@code nodes} lists
     * every node label to declare (so isolated nodes still appear);
     * {@code edges[i] = {from, to}}.
     */
    public static String toDotLabeled(String[] nodes, String[][] edges, boolean directed) {
        StringBuilder sb = new StringBuilder();
        String connector = directed ? " -> " : " -- ";
        sb.append(directed ? "digraph" : "graph").append(" G {\n");
        appendSizingAttrs(sb);
        sb.append("  node [shape=circle, style=filled, fillcolor=lightblue, fontsize=14, width=0.7];\n");
        for (String node : nodes) sb.append("  \"").append(node).append("\";\n");
        for (String[] edge : edges) {
            sb.append("  \"").append(edge[0]).append("\"").append(connector)
                    .append("\"").append(edge[1]).append("\";\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    /** Same as {@link #toDotLabeled}, but labels each edge with a weight string. edges[i] = {from, to, weightLabel}. */
    public static String toDotLabeledWeighted(String[] nodes, String[][] edges, boolean directed) {
        StringBuilder sb = new StringBuilder();
        String connector = directed ? " -> " : " -- ";
        sb.append(directed ? "digraph" : "graph").append(" G {\n");
        appendSizingAttrs(sb);
        sb.append("  node [shape=circle, style=filled, fillcolor=lightblue, fontsize=14, width=0.7];\n");
        for (String node : nodes) sb.append("  \"").append(node).append("\";\n");
        for (String[] edge : edges) {
            sb.append("  \"").append(edge[0]).append("\"").append(connector)
                    .append("\"").append(edge[1]).append("\" [label=\"").append(edge[2]).append("\", fontsize=12];\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    /**
     * Graph-level attributes that turn Graphviz's cramped default layout
     * into a bigger, closer-to-square image: more breathing room between
     * nodes/ranks, a bounding box Graphviz fills as squarely as it can,
     * and a higher DPI so it isn't blurry once enlarged.
     */
    private static void appendSizingAttrs(StringBuilder sb) {
        sb.append("  graph [nodesep=0.7, ranksep=0.9, size=\"8,8\", ratio=compress, dpi=180];\n");
    }
}
