package com.demo.DSA.concept.P005_TwoPointersSlidingWindow;

/**
 * Q010. Longest Repeating Character Replacement
 * https://leetcode.com/problems/longest-repeating-character-replacement/
 * <p>
 * You are given a string {@code s} of uppercase English letters and an
 * integer {@code k}. You can choose any character of the string and change
 * it to any other uppercase letter, at most k times in total. Return the
 * length of the longest substring containing the same letter you can get
 * after performing these operations.
 *
 * <pre>
 * Example 1:
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with 'B's (or vice versa).
 *
 * Example 2:
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the 'A' in the middle: "AABBBBA" - "BBBB" has length 4.
 *
 * Example 3 (k = 0 - no changes allowed, answer is the longest existing run):
 * Input: s = "AAAA", k = 0
 * Output: 4
 *
 * Example 4 (all letters different):
 * Input: s = "ABCDE", k = 1
 * Output: 2
 *
 * Example 5 (enough budget to cover the whole string):
 * Input: s = "ABBB", k = 2
 * Output: 4
 *
 * Constraints:
 * - 1 &lt;= s.length &lt;= 10^5
 * - s consists of only uppercase English letters.
 * - 0 &lt;= k &lt;= s.length
 * </pre>
 */
public class Q010_LongestRepeatingCharacterReplacement {

    /**
     * @implNote TODO: implement.
     * Target approach: A "budget" window: inside a window, keep the most frequent letter and
     * replace everything else, which costs {@code windowLen - maxFreq} changes.
     * The window is valid while that cost is {@code <= k}. Keep a 26-entry
     * count array and {@code maxFreq}; grow {@code right}, update counts and
     * {@code maxFreq}; if {@code (right - left + 1) - maxFreq > k}, shrink by
     * one ({@code count[s[left]]--}, {@code left++}). The answer is the largest
     * valid window length. (You don't need to lower {@code maxFreq} when
     * shrinking - a stale, too-high value never produces a wrong larger answer.)
     * <p>
     * Target Time Complexity: O(n).
     * <br>
     * Target Space Complexity: O(1) - 26 counters.
     */
    public int characterReplacement(String s, int k) {
        // TODO: implement
        return 0;
    }
}
