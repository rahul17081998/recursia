package com.demo.DSA.concept.P005_TwoPointersSlidingWindow;

/**
 * Q009. Longest Substring Without Repeating Characters
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * <p>
 * Given a string {@code s}, find the length of the longest substring
 * without duplicate characters.
 *
 * <pre>
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: "abc".
 *
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 *
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: "wke". Note "pwke" is a subsequence, not a substring.
 *
 * Example 4 (empty string):
 * Input: s = ""
 * Output: 0
 *
 * Example 5 (the repeat is BEFORE the current window - left must not move backwards):
 * Input: s = "abba"
 * Output: 2
 *
 * Constraints:
 * - 0 &lt;= s.length &lt;= 5 * 10^4
 * - s consists of English letters, digits, symbols and spaces.
 * </pre>
 */
public class Q009_LongestSubstringWithoutRepeatingCharacters {

    /**
     * @implNote TODO: implement.
     * Target approach: Variable window ({@link Q003_SA_SlidingWindowVariableSize}) where
     * "valid" means no repeated character. Keep a map from character to the
     * index where it was last seen. For each {@code right}: if
     * {@code s[right]} was seen at index {@code >= left}, jump
     * {@code left = lastSeen + 1} (use {@code max} so {@code left} never moves
     * backwards - Example 5). Update {@code lastSeen}, then
     * {@code best = max(best, right - left + 1)}.
     * <p>
     * Target Time Complexity: O(n).
     * <br>
     * Target Space Complexity: O(min(n, alphabet size)) for the map.
     */
    public int lengthOfLongestSubstring(String s) {
        // TODO: implement
        return 0;
    }
}
