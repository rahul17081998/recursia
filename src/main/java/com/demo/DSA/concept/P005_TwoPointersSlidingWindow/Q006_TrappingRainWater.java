package com.demo.DSA.concept.P005_TwoPointersSlidingWindow;

/**
 * Q006. Trapping Rain Water
 * https://leetcode.com/problems/trapping-rain-water/
 * <p>
 * Given n non-negative integers representing an elevation map where the
 * width of each bar is 1, compute how much water it can trap after
 * raining.
 *
 * <pre>
 * Example 1:
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * Example 2:
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 *
 * Example 3 (strictly increasing - water just runs off, nothing trapped):
 * Input: height = [1,2,3,4]
 * Output: 0
 *
 * Example 4 (a single dip):
 * Input: height = [2,0,2]
 * Output: 2
 *
 * Example 5 (right wall lower than left - the lower wall decides the level):
 * Input: height = [5,4,1,2]
 * Output: 1
 *
 * Constraints:
 * - n == height.length
 * - 1 &lt;= n &lt;= 2 * 10^4
 * - 0 &lt;= height[i] &lt;= 10^5
 * </pre>
 */
public class Q006_TrappingRainWater {

    /**
     * @implNote TODO: implement.
     * Target approach: Water above bar i = {@code min(maxLeft(i), maxRight(i)) - height[i]}.
     * Two pointers {@code left}, {@code right} with running {@code leftMax}
     * and {@code rightMax}: whichever side has the smaller max is the side
     * whose water level is already known (the other side has something at
     * least that tall), so process that side - add {@code leftMax - height[left]}
     * (or the right equivalent) and move inward. This replaces the O(n)-space
     * "precompute leftMax[] and rightMax[] arrays" solution with O(1) space.
     * <p>
     * Target Time Complexity: O(n) - one pass from both ends.
     * <br>
     * Target Space Complexity: O(1) (O(n) for the two-array version).
     */
    public int trap(int[] height) {
        // TODO: implement
        return 0;
    }
}
