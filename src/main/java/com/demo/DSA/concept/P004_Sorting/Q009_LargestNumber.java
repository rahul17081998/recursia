package com.demo.DSA.concept.P004_Sorting;

/**
 * Q009. Largest Number
 * https://leetcode.com/problems/largest-number/
 * https://www.geeksforgeeks.org/dsa/arrange-given-numbers-to-form-the-biggest-number-set-1/
 * <p>
 * Given a list of non-negative integers nums, arrange them such that
 * they form the largest number possible, and return it as a string
 * (since the result may be very large).
 *
 * <pre>
 * Example 1 (LeetCode's own example):
 * Input: nums = [10,2]
 * Output: "210"
 *
 * Example 2 (LeetCode's own example):
 * Input: nums = [3,30,34,5,9]
 * Output: "9534330"
 *
 * Example 3 (tricky - the classic counter-example to sorting by plain
 * string value: comparing "3" &lt; "34" as strings would wrongly place 3
 * before 34, but concatenating "34"+"3"="343" beats "3"+"34"="334", so
 * 34 must come first):
 * Input: nums = [34,3]
 * Output: "343"
 *
 * Example 4 (tricky - every number is 0; the numeric result is 0, so
 * the answer must be "0", not "000"):
 * Input: nums = [0,0]
 * Output: "0"
 *
 * Constraints:
 * - 1 &lt;= nums.length &lt;= 100
 * - 0 &lt;= nums[i] &lt;= 10^9
 * </pre>
 */
public class Q009_LargestNumber {

    /**
     * @implNote TODO: implement.
     * Target approach: Sorting with a custom comparator - convert every
     * number to a String, then sort descending using the comparator
     * (a, b) -&gt; (b + a).compareTo(a + b) (i.e. whichever concatenation
     * order produces the lexicographically larger string wins - this is
     * exactly what determines the numerically larger arrangement here,
     * per Example 3). Concatenate the sorted strings; as a final step,
     * if the result starts with '0' (which only happens when every
     * number is 0, per Example 4), return "0" instead of a string of
     * zeros.
     * <p>
     * Target Time Complexity: O(n log n * m) where m = max digit length
     * of any number (each comparison does an O(m) string comparison).
     * <br>
     * Target Space Complexity: O(n * m) for the string conversions and
     * output.
     */
    public String largestNumber(int[] nums) {
        // TODO: implement
        return "";
    }
}
