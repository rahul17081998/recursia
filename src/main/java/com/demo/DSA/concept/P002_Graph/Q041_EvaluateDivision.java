package com.demo.DSA.concept.P002_Graph;

import java.util.List;

/**
 * Q041. Evaluate Division
 * https://leetcode.com/problems/evaluate-division/
 * <p>
 * You are given equations[i] = [A, B] and values[i] representing A / B =
 * values[i]. Given queries[j] = [C, D], evaluate C / D for each query,
 * returning -1.0 if the answer cannot be determined (e.g. C or D never
 * appears, or no chain of known ratios connects them).
 *
 * <pre>
 * Example 1:
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0],
 *        queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.0, 0.5, -1.0, 1.0, -1.0]
 * Explanation: a/c = (a/b)*(b/c) = 2*3 = 6; b/a = 1/(a/b) = 0.5; "e" is
 * unknown so a/e = -1; a/a = 1 trivially (a is known to exist); "x" never
 * appears anywhere, so x/x = -1.
 *
 * Example 2:
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]],
 *        values = [1.5,2.5,5.0],
 *        queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75, 0.4, 5.0, 0.2]
 *
 * Constraints:
 * - 1 &lt;= equations.length &lt;= 20, equations[i].length == 2
 * - equations[i][0] != equations[i][1]
 * - 1 &lt;= values[i] &lt;= 20.0
 * - 1 &lt;= queries.length &lt;= 20, queries[i].length == 2
 * </pre>
 */
public class Q041_EvaluateDivision {

    /**
     * @implNote TODO: implement.
     * Target approach: Model variables as graph nodes and each equation
     * A/B=v as a weighted DIRECTED edge A-&gt;B with weight v, plus the
     * reciprocal edge B-&gt;A with weight 1/v (division is invertible).
     * Build this adjacency list (String -&gt; List&lt;{neighbor, weight}&gt;)
     * from all equations. For each query [C, D]: if either C or D was
     * never seen as a node, answer -1; if C == D, answer 1 (only if C is
     * a known node); otherwise BFS/DFS from C to D, multiplying edge
     * weights along the path - if D is unreachable, answer -1.
     * <p>
     * Target Time Complexity: O(Q * (V + E)) - one BFS/DFS per query, in
     * a graph with at most 2 * equations.length nodes.
     * <br>
     * Target Space Complexity: O(V + E) - the weighted adjacency map.
     */
    public double[] calcEquation(List<List<String>> equations, double[] values,
                                  List<List<String>> queries) {
        // TODO: implement
        return new double[queries.size()];
    }
}
