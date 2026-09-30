package com.demo.DSA.concept.P004_Sorting;

/**
 * Q007. Kth Largest Element in an Array
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * https://www.geeksforgeeks.org/dsa/kth-largestor-smallest-element-unsorted-array/
 * <p>
 * Given an integer array nums and an integer k, return the kth largest
 * element in the array (the kth largest in sorted order, not the kth
 * distinct element).
 *
 * <pre>
 * Example 1 (LeetCode's own example):
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 *
 * Example 2 (LeetCode's own example, with duplicates):
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 *
 * Example 3 (tricky - every element is identical; a partition-based
 * approach must not loop forever when nothing is ever strictly less
 * than or greater than the pivot):
 * Input: nums = [1,1,1,1,1], k = 1
 * Output: 1
 *
 * Example 4 (boundary - k = n means "the smallest element"):
 * Input: nums = [1,2,3,4,5], k = 5
 * Output: 1
 *
 * Constraints:
 * - 1 &lt;= k &lt;= nums.length &lt;= 10^5
 * - -10^4 &lt;= nums[i] &lt;= 10^4
 * </pre>
 */
public class Q007_KthLargestElementInAnArray {

    /**
     * @implNote TODO: implement.
     * Target approach: Quickselect - the same partitioning step as
     * {@link Q004_SA_QuickSort}, but only recurse into the ONE side that
     * can contain the answer instead of both, since the "kth largest"
     * is really "the element at index (n - k) once fully sorted
     * ascending". Partition the array around a pivot; if the pivot's
     * final index equals target = n - k, it IS the answer; otherwise
     * recurse only into the left partition (if pivotIndex &gt; target) or
     * the right partition (if pivotIndex &lt; target). A random pivot
     * choice keeps this O(n) average case even on adversarial input.
     * <p>
     * Target Time Complexity: O(n) average case, O(n^2) worst case
     * (mitigated by randomized pivot selection) - contrast with the
     * simpler O(n log n) full-sort-then-index approach, or O(n log k)
     * using a min-heap of size k.
     * <br>
     * Target Space Complexity: O(1) auxiliary (O(log n) average
     * recursion stack).
     */
    public int findKthLargest(int[] nums, int k) {
        // TODO: implement
        return -1;
    }
}
