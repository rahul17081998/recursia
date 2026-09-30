package com.demo.DSA.concept.P004_Sorting;

/**
 * Q006. Sort Colors
 * https://leetcode.com/problems/sort-colors/
 * https://www.geeksforgeeks.org/dsa/sort-an-array-of-0s-1s-and-2s/
 * <p>
 * Given an array nums with n objects colored red, white, or blue,
 * represented by the integers 0, 1, and 2 respectively, sort them
 * in-place so that objects of the same color are adjacent, in the order
 * red, white, and blue - without using a library sort function.
 *
 * <pre>
 * Example 1 (LeetCode's own example):
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 *
 * Example 2 (LeetCode's own example):
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 *
 * Example 3 (tricky - only one color present at all):
 * Input: nums = [1,1,1]
 * Output: [1,1,1]
 *
 * Example 4 (tricky - one color entirely missing; the algorithm must
 * not assume all three colors always appear):
 * Input: nums = [1,0,1,0]
 * Output: [0,0,1,1]
 *
 * Constraints:
 * - n == nums.length
 * - 1 &lt;= n &lt;= 300
 * - nums[i] is 0, 1, or 2.
 * </pre>
 */
public class Q006_SortColors {

    /**
     * @implNote TODO: implement.
     * Target approach: This is the Dutch National Flag problem - a
     * direct application of {@link Q004_SA_QuickSort}'s 3-way
     * partitioning idea (partition around the "pivot" value 1, but
     * without needing recursion, since there are only 3 possible
     * values). Maintain three pointers: low (boundary of the 0s region),
     * mid (current element being examined), high (boundary of the 2s
     * region). While mid &lt;= high: if nums[mid]==0, swap with nums[low],
     * advance both low and mid; if nums[mid]==2, swap with nums[high],
     * advance only high (the swapped-in element at mid still needs
     * examining); if nums[mid]==1, just advance mid.
     * <p>
     * Target Time Complexity: O(n) - single pass.
     * <br>
     * Target Space Complexity: O(1) - sorts in place (contrast with the
     * simpler but two-pass counting-sort approach: count 0s/1s/2s, then
     * overwrite the array).
     */
    public void sortColors(int[] nums) {
        // TODO: implement
    }
}
