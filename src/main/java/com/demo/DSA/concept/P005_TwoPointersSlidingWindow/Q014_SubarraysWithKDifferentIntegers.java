package com.demo.DSA.concept.P005_TwoPointersSlidingWindow;

/**
 * Q014. Subarrays with K Different Integers
 * https://leetcode.com/problems/subarrays-with-k-different-integers/
 * <p>
 * Given an integer array {@code nums} and an integer {@code k}, return the
 * number of <em>good</em> subarrays of {@code nums}. A good subarray is a
 * contiguous subarray with exactly k different integers - for example,
 * [1,2,3,1,2] has 3 different integers: 1, 2 and 3.
 *
 * <pre>
 * Example 1:
 * Input: nums = [1,2,1,2,3], k = 2
 * Output: 7
 * Explanation: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 *
 * Example 2:
 * Input: nums = [1,2,1,3,4], k = 3
 * Output: 3
 * Explanation: [1,2,1,3], [2,1,3], [1,3,4].
 *
 * Example 3 (all equal, k = 1 - every subarray counts: 3 + 2 + 1):
 * Input: nums = [1,1,1], k = 1
 * Output: 6
 *
 * Example 4 (only the whole array has k distinct values):
 * Input: nums = [1,2,3], k = 3
 * Output: 1
 *
 * Example 5 (k larger than the number of distinct values):
 * Input: nums = [1,2], k = 3
 * Output: 0
 *
 * Constraints:
 * - 1 &lt;= nums.length &lt;= 2 * 10^4
 * - 1 &lt;= nums[i], k &lt;= nums.length
 * </pre>
 */
public class Q014_SubarraysWithKDifferentIntegers {

    /**
     * @implNote TODO: implement.
     * Target approach: "Exactly k" is hard to slide directly, but "at most k" is easy: with a
     * variable window ({@link Q003_SA_SlidingWindowVariableSize}) and a count
     * map, shrink while the window has more than k distinct values; then every
     * subarray ending at {@code right} and starting anywhere in
     * {@code [left, right]} is valid, so add {@code right - left + 1}. Answer
     * = {@code atMost(k) - atMost(k - 1)}. The same trick solves "Count Number
     * of Nice Subarrays" and "Binary Subarrays With Sum".
     * <p>
     * Target Time Complexity: O(n) - two linear passes.
     * <br>
     * Target Space Complexity: O(n) for the count map (O(k) distinct keys at a time).
     */
    public int subarraysWithKDistinct(int[] nums, int k) {
        // TODO: implement
        return 0;
    }
}
