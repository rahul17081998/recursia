package com.demo.DSA.concept.P005_TwoPointersSlidingWindow;

/**
 * Q007. Remove Duplicates from Sorted Array
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * <p>
 * Given an integer array {@code nums} sorted in non-decreasing order, remove
 * the duplicates <strong>in-place</strong> so that each unique element
 * appears only once, keeping their relative order. Return k, the number of
 * unique elements. The first k elements of {@code nums} must hold the unique
 * elements in order; what's left after index k - 1 doesn't matter.
 *
 * <pre>
 * Example 1:
 * Input: nums = [1,1,2]
 * Output: 2, nums = [1,2,_]
 *
 * Example 2:
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 *
 * Example 3 (single element):
 * Input: nums = [1]
 * Output: 1, nums = [1]
 *
 * Example 4 (no duplicates - nothing moves):
 * Input: nums = [1,2,3]
 * Output: 3, nums = [1,2,3]
 *
 * Example 5 (all the same value):
 * Input: nums = [7,7,7,7]
 * Output: 1, nums = [7,_,_,_]
 *
 * Constraints:
 * - 1 &lt;= nums.length &lt;= 3 * 10^4
 * - -100 &lt;= nums[i] &lt;= 100
 * - nums is sorted in non-decreasing order.
 * </pre>
 */
public class Q007_RemoveDuplicatesFromSortedArray {

    /**
     * @implNote TODO: implement.
     * Target approach: Two pointers moving in the <strong>same direction</strong>: a slow
     * {@code write} pointer (next slot for a unique value, starts at 1) and a
     * fast {@code read} pointer scanning from 1. Whenever
     * {@code nums[read] != nums[write - 1]}, it's a new value: copy it to
     * {@code nums[write]} and {@code write++}. Return {@code write}.
     * <p>
     * Target Time Complexity: O(n) - one pass.
     * <br>
     * Target Space Complexity: O(1) - in place.
     */
    public int removeDuplicates(int[] nums) {
        // TODO: implement
        return 0;
    }
}
