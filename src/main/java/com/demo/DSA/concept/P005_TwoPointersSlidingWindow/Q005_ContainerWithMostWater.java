package com.demo.DSA.concept.P005_TwoPointersSlidingWindow;

/**
 * Q005. Container With Most Water
 * https://leetcode.com/problems/container-with-most-water/
 * <p>
 * You are given an integer array {@code height} of length n. There are n
 * vertical lines drawn such that the two endpoints of the i-th line are
 * {@code (i, 0)} and {@code (i, height[i])}. Find two lines that together
 * with the x-axis form a container that holds the most water, and return
 * that maximum amount of water. You may not slant the container.
 *
 * <pre>
 * Example 1:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: Lines at index 1 (height 8) and index 8 (height 7):
 * width 7 * min(8, 7) = 49.
 *
 * Example 2:
 * Input: height = [1,1]
 * Output: 1
 *
 * Example 3 (the two outermost lines are the best):
 * Input: height = [4,3,2,1,4]
 * Output: 16
 *
 * Example 4 (tallest line in the middle doesn't help - it's limited by its partner):
 * Input: height = [1,2,1]
 * Output: 2
 *
 * Example 5 (best pair is not the two tallest lines):
 * Input: height = [1,2,4,3]
 * Output: 4
 *
 * Constraints:
 * - n == height.length
 * - 2 &lt;= n &lt;= 10^5
 * - 0 &lt;= height[i] &lt;= 10^4
 * </pre>
 */
public class Q005_ContainerWithMostWater {

    /**
     * @implNote TODO: implement.
     * Target approach: Two pointers at both ends. Area = {@code (right - left) * min(height[left], height[right])};
     * track the max. Then move the pointer at the <strong>shorter</strong> line
     * inward: the shorter line limits the water, and moving the taller one
     * can only make the width smaller without raising that limit, so it can
     * never give a bigger area. (Ties: moving either is fine.)
     * <p>
     * Target Time Complexity: O(n) - each step moves one pointer.
     * <br>
     * Target Space Complexity: O(1).
     */
    public int maxArea(int[] height) {
        // TODO: implement
        return 0;
    }
}
