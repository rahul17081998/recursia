package com.demo.DSA.concept.P005_TwoPointersSlidingWindow;

/**
 * Q002. Sliding Window - Fixed Size (Maximum Sum Subarray of Size K)
 * https://leetcode.com/problems/maximum-average-subarray-i/
 * <p>
 * Standard algorithm: a sliding window whose size never changes. Worked
 * example: given an integer array {@code nums} and an integer {@code k},
 * return the maximum sum of any contiguous subarray of length exactly
 * {@code k}. (LeetCode 643 asks for the maximum <em>average</em> - that's
 * just this sum divided by k.)
 *
 * <pre>
 * Example 1:
 * Input: nums = [2,1,5,1,3,2], k = 3
 * Output: 9
 * Explanation: Subarray [5,1,3] has the largest sum, 9.
 *
 * Example 2:
 * Input: nums = [2,3,4,1,5], k = 2
 * Output: 7
 * Explanation: Subarray [3,4].
 *
 * Example 3 (k equals the array length - the whole array is the only window):
 * Input: nums = [1,2,3], k = 3
 * Output: 6
 *
 * Example 4 (all negative - answer is negative, so don't start the max at 0):
 * Input: nums = [-1,-2,-3,-4], k = 2
 * Output: -3
 *
 * Example 5 (best window is the very last one):
 * Input: nums = [1,1,1,9,9], k = 2
 * Output: 18
 *
 * Constraints:
 * - 1 &lt;= k &lt;= nums.length &lt;= 10^5
 * - -10^4 &lt;= nums[i] &lt;= 10^4
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * Recomputing every window's sum from scratch costs O(k) per window, O(n*k)
 * in total. Instead, compute the first window's sum once, then
 * <strong>slide</strong>: when the window moves one step right, exactly one
 * element enters ({@code nums[i]}) and exactly one leaves
 * ({@code nums[i - k]}), so the new sum is {@code sum + nums[i] - nums[i - k]}
 * - O(1) per step. Track the best sum seen.
 * <p>
 * Every fixed-size window problem has this shape: <em>add the new element,
 * remove the element that fell out, update the answer</em>. What changes
 * between problems is only the "window state" - here a running sum, in
 * {@link Q011_FindAllAnagramsInAString} a letter-count array, in
 * {@link Q013_SlidingWindowMaximum} a deque.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://leetcode.com/problems/maximum-average-subarray-i/">LeetCode 643 - Maximum Average Subarray I</a>
 *   - the LeetCode version of this exact template.</li>
 *   <li><a href="https://leetcode.com/problems/find-all-anagrams-in-a-string/">LeetCode 438 - Find All Anagrams in a String</a>
 *   - the same fixed window, but the state is a count array instead of a sum.</li>
 * </ul>
 */
public class Q002_SA_SlidingWindowFixedSize {

    /**
     * @implNote TODO: implement.
     * Target approach: Sum {@code nums[0..k-1]} into {@code windowSum} and set
     * {@code best = windowSum}. For {@code i} from {@code k} to {@code n - 1}:
     * {@code windowSum += nums[i] - nums[i - k]}; {@code best = max(best, windowSum)}.
     * Return {@code best}.
     * <p>
     * Target Time Complexity: O(n) - one pass, O(1) work per slide.
     * <br>
     * Target Space Complexity: O(1).
     */
    public int maxSumSubarrayOfSizeK(int[] nums, int k) {
        // TODO: implement
        return 0;
    }
}
