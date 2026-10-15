package com.demo.DSA.concept.P005_TwoPointersSlidingWindow;

/**
 * Q008. Merge Sorted Array
 * https://leetcode.com/problems/merge-sorted-array/
 * <p>
 * You are given two integer arrays {@code nums1} and {@code nums2}, sorted
 * in non-decreasing order, and two integers m and n - the number of real
 * elements in {@code nums1} and {@code nums2}. {@code nums1} has length
 * m + n: its last n slots are 0 placeholders. Merge {@code nums2} into
 * {@code nums1} <strong>in place</strong> so that {@code nums1} ends up
 * sorted. Return nothing.
 *
 * <pre>
 * Example 1:
 * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * Output: [1,2,2,3,5,6]
 *
 * Example 2:
 * Input: nums1 = [1], m = 1, nums2 = [], n = 0
 * Output: [1]
 *
 * Example 3 (nums1 has no real elements - everything comes from nums2):
 * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
 * Output: [1]
 *
 * Example 4 (every nums2 element is smaller - nums1's values must all shift right):
 * Input: nums1 = [4,5,6,0,0,0], m = 3, nums2 = [1,2,3], n = 3
 * Output: [1,2,3,4,5,6]
 *
 * Example 5:
 * Input: nums1 = [2,0], m = 1, nums2 = [1], n = 1
 * Output: [1,2]
 *
 * Constraints:
 * - nums1.length == m + n
 * - nums2.length == n
 * - 0 &lt;= m, n &lt;= 200
 * - 1 &lt;= m + n &lt;= 200
 * - -10^9 &lt;= nums1[i], nums2[j] &lt;= 10^9
 * </pre>
 */
public class Q008_MergeSortedArray {

    /**
     * @implNote TODO: implement.
     * Target approach: Fill {@code nums1} from the <strong>back</strong>. Pointers
     * {@code i = m - 1}, {@code j = n - 1}, {@code w = m + n - 1}: place the
     * larger of {@code nums1[i]} and {@code nums2[j]} at {@code nums1[w]} and
     * move that pointer and {@code w} left. Writing from the back never
     * overwrites a {@code nums1} value you still need (the free zeros are at
     * the back). When {@code nums2} is used up you're done; when
     * {@code nums1}'s part runs out, copy the rest of {@code nums2}.
     * <p>
     * Target Time Complexity: O(m + n).
     * <br>
     * Target Space Complexity: O(1) - no extra array.
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // TODO: implement
    }
}
