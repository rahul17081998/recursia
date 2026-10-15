package com.demo.DSA.concept.P005_TwoPointersSlidingWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Q004. 3Sum
 * https://leetcode.com/problems/3sum/
 * <p>
 * Given an integer array {@code nums}, return all the triplets
 * {@code [nums[i], nums[j], nums[k]]} such that i, j and k are distinct
 * indices and {@code nums[i] + nums[j] + nums[k] == 0}. The solution set
 * must not contain duplicate triplets. The triplets (and the numbers inside
 * each triplet) may be returned in any order.
 *
 * <pre>
 * Example 1:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation: [-1,0,1] can be formed two ways (two different -1s), but it
 * appears only once in the answer.
 *
 * Example 2:
 * Input: nums = [0,1,1]
 * Output: []
 *
 * Example 3:
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 *
 * Example 4 (many duplicates - must still return [0,0,0] only once):
 * Input: nums = [0,0,0,0]
 * Output: [[0,0,0]]
 *
 * Example 5 (same first number in two different triplets):
 * Input: nums = [-2,0,1,1,2]
 * Output: [[-2,0,2],[-2,1,1]]
 *
 * Constraints:
 * - 3 &lt;= nums.length &lt;= 3000
 * - -10^5 &lt;= nums[i] &lt;= 10^5
 * </pre>
 */
public class Q004_ThreeSum {

    /**
     * @implNote TODO: implement.
     * Target approach: Sort {@code nums}. For each index {@code i} (skipping
     * {@code nums[i]} if it equals {@code nums[i - 1]} - same first number,
     * same triplets), run {@link Q001_SA_TwoPointersOppositeEnds}'s two
     * pointers on {@code i + 1 .. n - 1} looking for sum {@code -nums[i]}.
     * When a triplet is found, add it, then move both pointers past any
     * equal values so the same pair isn't recorded twice. Stop early once
     * {@code nums[i] > 0} (three positives can't sum to 0).
     * <p>
     * Target Time Complexity: O(n^2) - O(n log n) sort plus an O(n) two-pointer scan per i.
     * <br>
     * Target Space Complexity: O(1) extra besides the output (ignoring the sort's own space).
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // TODO: implement
        return new ArrayList<>();
    }
}
