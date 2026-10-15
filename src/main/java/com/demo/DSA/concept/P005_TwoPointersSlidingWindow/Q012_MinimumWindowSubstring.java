package com.demo.DSA.concept.P005_TwoPointersSlidingWindow;

/**
 * Q012. Minimum Window Substring
 * https://leetcode.com/problems/minimum-window-substring/
 * <p>
 * Given two strings {@code s} and {@code t}, return the minimum window
 * substring of {@code s} such that every character in {@code t}
 * (<strong>including duplicates</strong>) is included in the window. If
 * there is no such substring, return the empty string {@code ""}. The
 * answer is unique.
 *
 * <pre>
 * Example 1:
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 *
 * Example 2:
 * Input: s = "a", t = "a"
 * Output: "a"
 *
 * Example 3 (t needs two 'a's but s has only one):
 * Input: s = "a", t = "aa"
 * Output: ""
 *
 * Example 4 (the whole string is the answer):
 * Input: s = "aa", t = "aa"
 * Output: "aa"
 *
 * Example 5 (window must shrink past a duplicate on the left):
 * Input: s = "bba", t = "ab"
 * Output: "ba"
 *
 * Constraints:
 * - 1 &lt;= s.length, t.length &lt;= 10^5
 * - s and t consist of uppercase and lowercase English letters.
 * </pre>
 */
public class Q012_MinimumWindowSubstring {

    /**
     * @implNote TODO: implement.
     * Target approach: Variable window ({@link Q003_SA_SlidingWindowVariableSize}) with a
     * {@code need} count for {@code t} and a {@code have} counter of how many
     * distinct characters currently meet their required count. Grow
     * {@code right}; when a character's window count reaches its need, increment
     * {@code have}. While {@code have} equals the number of distinct needed
     * characters, the window is valid: record it if it's the shortest so far,
     * then shrink from the left (decrementing {@code have} when a count drops
     * below its need). Return the recorded window, or {@code ""}.
     * <p>
     * Target Time Complexity: O(|s| + |t|).
     * <br>
     * Target Space Complexity: O(1) - counts over a fixed alphabet (128 ASCII).
     */
    public String minWindow(String s, String t) {
        // TODO: implement
        return "";
    }
}
