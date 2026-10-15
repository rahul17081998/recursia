package com.demo.DSA.concept.P005_TwoPointersSlidingWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Q011. Find All Anagrams in a String
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * <p>
 * Given two strings {@code s} and {@code p}, return a list of all the start
 * indices of {@code p}'s anagrams in {@code s}. An anagram uses exactly the
 * same letters, the same number of times, in any order. You may return the
 * answer in any order.
 *
 * <pre>
 * Example 1:
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation: "cba" starts at 0 and "bac" starts at 6.
 *
 * Example 2:
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 *
 * Example 3 (p longer than s - no window fits):
 * Input: s = "a", p = "ab"
 * Output: []
 *
 * Example 4 (overlapping matches):
 * Input: s = "aaaa", p = "aa"
 * Output: [0,1,2]
 *
 * Example 5 (only the last window matches):
 * Input: s = "baa", p = "aa"
 * Output: [1]
 *
 * Constraints:
 * - 1 &lt;= s.length, p.length &lt;= 3 * 10^4
 * - s and p consist of lowercase English letters.
 * </pre>
 */
public class Q011_FindAllAnagramsInAString {

    /**
     * @implNote TODO: implement.
     * Target approach: A fixed window of size {@code p.length()}
     * ({@link Q002_SA_SlidingWindowFixedSize}) where the window state is a
     * 26-entry letter count instead of a sum. Count {@code p} once. Slide over
     * {@code s}: add the entering letter, remove the letter that fell out, and
     * whenever the window's counts equal {@code p}'s counts, record the start
     * index. (Comparing two 26-arrays is O(26) = O(1); a "number of matching
     * letters" counter makes it strictly O(1) per step.)
     * <p>
     * Target Time Complexity: O(n) for n = s.length (times 26 if you compare arrays each step).
     * <br>
     * Target Space Complexity: O(1) - two 26-entry arrays.
     */
    public List<Integer> findAnagrams(String s, String p) {
        // TODO: implement
        return new ArrayList<>();
    }
}
