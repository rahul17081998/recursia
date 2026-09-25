package com.demo.DSA.concept.P002_Graph;

/**
 * Q032. Implement Disjoint Set Union (Union-Find)
 * https://www.geeksforgeeks.org/introduction-to-disjoint-set-data-structure-or-union-find-algorithm/
 * <p>
 * Implement a Disjoint Set Union (Union-Find) data structure over n
 * elements (0 to n-1), supporting:
 * - find(x): returns the representative (root) of the set containing x.
 * - union(x, y): merges the sets containing x and y.
 * - connected(x, y): returns whether x and y are in the same set.
 * This is the standard building block behind cycle detection, Kruskal's
 * MST ({@link Q037_SA_KruskalsMST}), and several of the questions in this
 * package ({@link Q033_RedundantConnection},
 * {@link Q034_AccountsMerge}, {@link Q035_GraphValidTree}).
 *
 * <pre>
 * Example:
 * n = 6, elements 0..5, initially each its own set: {0},{1},{2},{3},{4},{5}
 * union(0, 1) -&gt; sets: {0,1},{2},{3},{4},{5}
 * union(2, 3) -&gt; sets: {0,1},{2,3},{4},{5}
 * union(1, 3) -&gt; sets: {0,1,2,3},{4},{5}
 * connected(0, 3) -&gt; true
 * connected(0, 4) -&gt; false
 *
 * Constraints:
 * - 1 &lt;= n &lt;= 10^5, with up to 10^5 union/find operations.
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * A forest where each tree is one group; "same group?" is just "same
 * root?" - made near-instant by two tricks:
 * <ol>
 *   <li><strong>Union by rank/size</strong> - always attach the smaller
 *   tree under the bigger tree's root, so trees stay shallow instead of
 *   degenerating into a chain.</li>
 *   <li><strong>Path compression</strong> - while walking up in
 *   find(), re-point every node visited along the way directly to the
 *   root. The tree flattens permanently, so every future query on those
 *   nodes is instant.</li>
 * </ol>
 * Combined, these two optimizations turn "possibly O(n) per call" into
 * effectively O(1) per call, amortized over any sequence of operations.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://cp-algorithms.com/data_structures/disjoint_set_union.html">cp-algorithms.com &mdash; Disjoint Set Union</a>
 *   - walks through both optimizations separately, then combined, with the
 *   amortized complexity argument spelled out.</li>
 *   <li><a href="https://visualgo.net/en/ufds">VisuAlgo.net &mdash; Union-Find Disjoint Sets</a>
 *   - drag-and-drop union operations and watch path compression flatten the tree in real time.</li>
 * </ul>
 */
public class Q032_SA_DisjointSetUnion {

    private int[] parent;
    private int[] rank;

    public Q032_SA_DisjointSetUnion(int n) {
        // TODO: implement - initialize parent[i] = i for all i, rank[i] = 0.
    }

    /**
     * @implNote TODO: implement.
     * Target approach: Path compression - while walking up to the root,
     * point every visited node directly at the root (either recursively,
     * or iteratively in two passes) so future find() calls on those nodes
     * are O(1).
     * <p>
     * Target Time Complexity: O(alpha(n)) amortized (inverse Ackermann,
     * effectively constant) when combined with union by rank.
     */
    public int find(int x) {
        // TODO: implement
        return x;
    }

    /**
     * @implNote TODO: implement.
     * Target approach: Union by rank - find both roots; if already the
     * same, no-op (this indicates x and y were already connected, which
     * is exactly the check {@link Q033_RedundantConnection} needs).
     * Otherwise attach the smaller-rank tree's root under the larger-rank
     * tree's root (increment rank only when both trees had equal rank),
     * keeping the overall tree shallow.
     * <p>
     * Target Time Complexity: O(alpha(n)) amortized.
     */
    public void union(int x, int y) {
        // TODO: implement
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
