package com.demo.DSA.concept.P005_TwoPointersSlidingWindow;

/**
 * Q003. Sliding Window - Variable Size (Minimum Size Subarray Sum)
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 * <p>
 * Standard algorithm: a sliding window that grows and shrinks. Worked
 * example: given an array of positive integers {@code nums} and a positive
 * integer {@code target}, return the minimal length of a contiguous
 * subarray whose sum is greater than or equal to {@code target}. If there
 * is no such subarray, return 0.
 *
 * <pre>
 * Example 1:
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length.
 *
 * Example 2:
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 *
 * Example 3 (no subarray reaches the target):
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 *
 * Example 4 (only the whole array works):
 * Input: target = 15, nums = [1,2,3,4,5]
 * Output: 5
 *
 * Example 5 (window must shrink several steps at once):
 * Input: target = 11, nums = [1,2,3,4,5]
 * Output: 3
 *
 * Constraints:
 * - 1 &lt;= target &lt;= 10^9
 * - 1 &lt;= nums.length &lt;= 10^5
 * - 1 &lt;= nums[i] &lt;= 10^4
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * Keep a window {@code [left, right]} and its sum. Move {@code right} one
 * step at a time, adding {@code nums[right]} - the window <strong>grows</strong>.
 * As soon as the window is <em>valid</em> (sum &gt;= target), record its
 * length and <strong>shrink</strong> from the left (subtract
 * {@code nums[left]}, {@code left++}) for as long as it stays valid - every
 * shrink can only give a shorter answer. Each index enters once and leaves
 * once, so the total work is O(n) even though there's a loop inside a loop.
 * <p>
 * This works because all numbers are positive: adding an element always
 * raises the sum and removing one always lowers it, so "valid" changes in
 * one direction as the window moves. With negative numbers that breaks,
 * and you need prefix sums instead. The grow-right / shrink-left template
 * is reused by {@link Q009_LongestSubstringWithoutRepeatingCharacters},
 * {@link Q010_LongestRepeatingCharacterReplacement},
 * {@link Q012_MinimumWindowSubstring} and {@link Q014_SubarraysWithKDifferentIntegers}
 * - only the "is the window valid?" check changes.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://leetcode.com/problems/minimum-size-subarray-sum/editorial/">LeetCode 209 - Minimum Size Subarray Sum (editorial)</a>
 *   - compares brute force, prefix-sum + binary search, and this O(n) window.</li>
 *   <li><a href="https://leetcode.com/problems/minimum-window-substring/">LeetCode 76 - Minimum Window Substring</a>
 *   - the hardest well-known problem built on this same template.</li>
 * </ul>
 */
public class Q003_SA_SlidingWindowVariableSize {

    /**
     * @implNote TODO: implement.
     * Target approach: {@code left = 0}, {@code sum = 0}, {@code best = MAX}. For each
     * {@code right}: {@code sum += nums[right]}; while {@code sum >= target}:
     * {@code best = min(best, right - left + 1)}, {@code sum -= nums[left++]}.
     * Return {@code best == MAX ? 0 : best}.
     * <p>
     * Target Time Complexity: O(n) - each index is added once and removed at most once.
     * <br>
     * Target Space Complexity: O(1).
     */
    public int minSubArrayLen(int target, int[] nums) {
        // TODO: implement
        return 0;
    }
}
