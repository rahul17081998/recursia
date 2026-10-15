package com.demo.DSA.concept.P005_TwoPointersSlidingWindow;

/**
 * Q001. Two Pointers - Opposite Ends (Two Sum II - Input Array Is Sorted)
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 * <p>
 * Standard algorithm: two pointers that start at OPPOSITE ends of a sorted
 * array and walk toward each other. Worked example: given a 1-indexed array
 * of integers {@code numbers} sorted in non-decreasing order, find two
 * numbers that add up to {@code target} and return their indices
 * {@code [index1, index2]} (1-indexed, index1 &lt; index2). Exactly one
 * solution exists, and the same element may not be used twice. Use only
 * O(1) extra space.
 *
 * <pre>
 * Example 1:
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: 2 + 7 = 9, so index1 = 1, index2 = 2.
 *
 * Example 2:
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 *
 * Example 3 (negative numbers):
 * Input: numbers = [-1,0], target = -1
 * Output: [1,2]
 *
 * Example 4 (duplicates - the answer uses two equal values):
 * Input: numbers = [1,2,3,4,4,9,56,90], target = 8
 * Output: [4,5]
 *
 * Example 5 (answer is the last two elements):
 * Input: numbers = [5,25,75], target = 100
 * Output: [2,3]
 *
 * Constraints:
 * - 2 &lt;= numbers.length &lt;= 3 * 10^4
 * - -1000 &lt;= numbers[i] &lt;= 1000
 * - numbers is sorted in non-decreasing order.
 * - -1000 &lt;= target &lt;= 1000
 * - Exactly one solution exists.
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * Put {@code left} at index 0 and {@code right} at the last index. Because
 * the array is sorted, the sum {@code numbers[left] + numbers[right]} tells
 * you which way to move: too small means the only way to grow it is to move
 * {@code left} right (to a bigger value); too big means move {@code right}
 * left (to a smaller value); equal means you've found the pair. Each step
 * throws away one element that can never be part of the answer, so the two
 * pointers meet after at most n steps - O(n) time with no extra memory,
 * versus O(n^2) for checking every pair or O(n) extra space for a HashMap.
 * <p>
 * The same "shrink from both ends using sorted order" idea powers
 * {@link Q004_ThreeSum}, {@link Q005_ContainerWithMostWater} and
 * {@link Q006_TrappingRainWater} - each adds one twist on top.
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/editorial/">LeetCode 167 - Two Sum II (editorial)</a>
 *   - walks through why moving the pointer on the 'wrong' side can never skip the answer.</li>
 *   <li><a href="https://leetcode.com/problems/3sum/">LeetCode 15 - 3Sum</a>
 *   - the natural next step: fix one number and run this exact template on the rest.</li>
 * </ul>
 */
public class Q001_SA_TwoPointersOppositeEnds {

    /**
     * @implNote TODO: implement.
     * Target approach: Two pointers {@code left = 0}, {@code right = n - 1}. While
     * {@code left < right}: compute the sum; if it equals target return
     * {@code [left + 1, right + 1]}; if it's smaller move {@code left++};
     * otherwise move {@code right--}.
     * <p>
     * Target Time Complexity: O(n) - each step moves one pointer inward.
     * <br>
     * Target Space Complexity: O(1).
     */
    public int[] twoSum(int[] numbers, int target) {
        // TODO: implement
        return new int[0];
    }
}
