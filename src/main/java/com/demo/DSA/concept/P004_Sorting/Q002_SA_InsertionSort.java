package com.demo.DSA.concept.P004_Sorting;

/**
 * Q002. Insertion Sort
 * https://www.geeksforgeeks.org/dsa/insertion-sort/
 * https://algomaster.io/learn/dsa/insertion-sort
 * <p>
 * Given an array of integers, sort it in ascending order using Insertion
 * Sort - build up a sorted prefix one element at a time, inserting each
 * new element into its correct position within that prefix (the way you
 * might sort playing cards in your hand).
 *
 * <pre>
 * Example 1 (GFG's own walkthrough):
 * Input: arr = [12,11,13,5,6]
 * Output: [5,6,11,12,13]
 *
 * Example 2 (worst case - reverse sorted, every insertion shifts the
 * whole sorted prefix):
 * Input: arr = [5,4,3,2,1]
 * Output: [1,2,3,4,5]
 *
 * Example 3 (best case - already sorted; each element is compared once
 * against its immediate predecessor and needs no shifting at all, which
 * is exactly why insertion sort is the adaptive choice for
 * nearly-sorted data):
 * Input: arr = [1,2,3,4,5]
 * Output: [1,2,3,4,5]
 *
 * Example 4 (single element - trivially sorted):
 * Input: arr = [1]
 * Output: [1]
 *
 * Constraints:
 * - 0 &lt;= arr.length &lt;= 10^4
 * - -10^5 &lt;= arr[i] &lt;= 10^5
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * Maintain an invariant: everything to the left of the current index is
 * already sorted. For each new element (starting from index 1), save its
 * value, then shift every larger element in the sorted prefix one slot
 * to the right until the correct gap for the saved value opens up, and
 * drop it in. This is exactly why insertion sort is adaptive - on
 * already-sorted input, the "shift larger elements right" step never
 * finds anything to shift, so each element costs O(1) instead of O(n).
 *
 * <h3>Properties</h3>
 * <ul>
 *   <li><strong>Time:</strong> best O(n) (already sorted), average/worst
 *   O(n^2).</li>
 *   <li><strong>Space:</strong> O(1) - in-place.</li>
 *   <li><strong>Stable:</strong> yes - an element is only ever shifted
 *   past strictly larger elements, so equal elements keep their
 *   relative order.</li>
 *   <li><strong>Adaptive:</strong> yes - this is insertion sort's
 *   signature strength, which is exactly why real-world hybrid sorts
 *   (Timsort, Introsort) switch to it for small or nearly-sorted
 *   runs.</li>
 * </ul>
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://algomaster.io/learn/dsa/insertion-sort">AlgoMaster.io &mdash; Insertion Sort</a>
 *   - explains why real-world sorts (Timsort, Introsort) fall back to insertion sort for small subarrays.</li>
 *   <li><a href="https://www.geeksforgeeks.org/dsa/insertion-sort/">GeeksforGeeks &mdash; Insertion Sort</a>
 *   - covers the algorithm and complexity analysis in detail.</li>
 * </ul>
 */
public class Q002_SA_InsertionSort {

    /**
     * @implNote TODO: implement.
     * Target approach: For each index i from 1 to n-1, save key =
     * arr[i], then walk j backward from i-1 while j &gt;= 0 and arr[j] &gt;
     * key, shifting arr[j] into arr[j+1]. Once the loop stops, place key
     * at arr[j+1].
     * <p>
     * Target Time Complexity: O(n^2) worst/average case, O(n) best case
     * (already sorted).
     * <br>
     * Target Space Complexity: O(1) - sorts in place.
     */
    public int[] insertionSort(int[] arr) {
        // TODO: implement
        return arr;
    }
}
