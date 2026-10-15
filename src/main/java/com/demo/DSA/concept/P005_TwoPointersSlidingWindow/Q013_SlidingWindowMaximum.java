package com.demo.DSA.concept.P005_TwoPointersSlidingWindow;

/**
 * Q013. Sliding Window Maximum
 * https://leetcode.com/problems/sliding-window-maximum/
 * <p>
 * You are given an array of integers {@code nums} and a window of size
 * {@code k} that moves from the very left of the array to the very right,
 * one position at a time. You can only see the k numbers in the window.
 * Return an array with the maximum of each window.
 *
 * <pre>
 * Example 1:
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 *
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * Example 3 (decreasing - the max is always the window's first element):
 * Input: nums = [9,8,7,6], k = 2
 * Output: [9,8,7]
 *
 * Example 4 (increasing - the max is always the newest element):
 * Input: nums = [1,2,3,4], k = 2
 * Output: [2,3,4]
 *
 * Example 5 (equal values - duplicates must not be dropped too early):
 * Input: nums = [4,4,4], k = 2
 * Output: [4,4]
 *
 * Constraints:
 * - 1 &lt;= nums.length &lt;= 10^5
 * - -10^4 &lt;= nums[i] &lt;= 10^4
 * - 1 &lt;= k &lt;= nums.length
 * </pre>
 */
public class Q013_SlidingWindowMaximum {

    /**
     * @implNote TODO: implement.
     * Target approach: A fixed window ({@link Q002_SA_SlidingWindowFixedSize}) whose state is
     * a <strong>monotonic deque</strong> of indices with decreasing values.
     * For each {@code i}: pop indices from the front that are outside the
     * window ({@code <= i - k}); pop from the back while
     * {@code nums[back] < nums[i]} (they can never be a max again - a bigger,
     * newer value exists); push {@code i}. Once {@code i >= k - 1}, the front
     * of the deque is the window's max. A heap would be O(n log k); the deque
     * is O(n).
     * <p>
     * Target Time Complexity: O(n) - each index is pushed and popped at most once.
     * <br>
     * Target Space Complexity: O(k) for the deque.
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // TODO: implement
        return new int[0];
    }
}
