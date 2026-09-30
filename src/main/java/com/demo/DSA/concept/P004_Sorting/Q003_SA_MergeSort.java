package com.demo.DSA.concept.P004_Sorting;

/**
 * Q003. Merge Sort (Sort an Array)
 * https://leetcode.com/problems/sort-an-array/
 * https://algomaster.io/learn/dsa/sort-an-array
 * https://www.geeksforgeeks.org/dsa/merge-sort/
 * <p>
 * Given an array of integers, sort it in ascending order using Merge
 * Sort - recursively split the array in half, sort each half, then
 * merge the two sorted halves back together. This is the same
 * divide-and-conquer technique {@code P003_LinkedList.Q011_SA_SortList}
 * applies to a linked list; here it's the array version.
 *
 * <pre>
 * Example 1 (LeetCode's own example):
 * Input: nums = [5,2,3,1]
 * Output: [1,2,3,5]
 *
 * Example 2 (LeetCode's own example, with duplicates):
 * Input: nums = [5,1,1,2,0,0]
 * Output: [0,0,1,1,2,5]
 *
 * Example 3 (GFG's own example, with negative numbers):
 * Input: nums = [-2,-5,-45,0,11]
 * Output: [-45,-5,-2,0,11]
 *
 * Example 4 (single element - base case of the recursion):
 * Input: nums = [1]
 * Output: [1]
 *
 * Constraints:
 * - 1 &lt;= nums.length &lt;= 5 * 10^4
 * - -5 * 10^4 &lt;= nums[i] &lt;= 5 * 10^4
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * The base case is a subarray of length 0 or 1, which is trivially
 * sorted. Otherwise, split at the midpoint, recursively sort each half,
 * then merge: walk two pointers, one into each sorted half, repeatedly
 * copying whichever pointer's element is smaller into a temporary output
 * buffer, then copy over whichever half still has leftover elements once
 * the other is exhausted. Merge sort's guarantee of O(n log n) - always,
 * not just on average - is exactly why it's the standard choice when a
 * worst-case bound matters (contrast with Quick Sort's O(n^2) worst case).
 *
 * <h3>Properties</h3>
 * <ul>
 *   <li><strong>Time:</strong> best/average/worst all O(n log n) -
 *   the split is always exactly in half regardless of input, so there's
 *   no input that makes it degrade.</li>
 *   <li><strong>Space:</strong> O(n) - NOT in-place; the merge step
 *   needs a temporary buffer to combine two sorted halves without
 *   overwriting data it still needs to read.</li>
 *   <li><strong>Stable:</strong> yes, as long as the merge step always
 *   takes from the left half on ties - equal elements from the left
 *   half are copied out before the right half's equal element is even
 *   considered.</li>
 *   <li><strong>Adaptive:</strong> no - it does the same amount of work
 *   regardless of how sorted the input already is.</li>
 * </ul>
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://algomaster.io/learn/dsa/sort-an-array">AlgoMaster.io &mdash; Sort an Array</a>
 *   - compares merge sort against the other classic sorts on this exact problem.</li>
 *   <li><a href="https://cp-algorithms.com/sorting/merge-sort.html">cp-algorithms.com &mdash; Merge Sort</a>
 *   - a rigorous treatment of the algorithm and its complexity proof.</li>
 * </ul>
 */
public class Q003_SA_MergeSort {

    /**
     * @implNote TODO: implement.
     * Target approach: mergeSort(arr, lo, hi) recurses on [lo, mid] and
     * [mid+1, hi] where mid = (lo+hi)/2, base case lo &gt;= hi. After both
     * halves are sorted, merge them: copy [lo, hi] into a temp buffer,
     * then walk two pointers (one starting at lo, one at mid+1 within
     * that buffer) writing back into arr[lo..hi] in sorted order.
     * <p>
     * Target Time Complexity: O(n log n) - always, regardless of input.
     * <br>
     * Target Space Complexity: O(n) - the temporary merge buffer (plus
     * O(log n) recursion stack).
     */
    public int[] sortArray(int[] nums) {
        // TODO: implement
        return nums;
    }
}
