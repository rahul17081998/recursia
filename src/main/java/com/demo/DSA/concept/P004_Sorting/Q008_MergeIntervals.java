package com.demo.DSA.concept.P004_Sorting;

/**
 * Q008. Merge Intervals
 * https://leetcode.com/problems/merge-intervals/
 * https://www.geeksforgeeks.org/dsa/merging-intervals/
 * <p>
 * Given an array of intervals where intervals[i] = [starti, endi], merge
 * all overlapping intervals, and return an array of the non-overlapping
 * intervals that cover all the intervals in the input.
 *
 * <pre>
 * Example 1 (LeetCode's own example):
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: [1,3] and [2,6] overlap, merge into [1,6].
 *
 * Example 2 (LeetCode's own example - touching endpoints count as
 * overlapping):
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 *
 * Example 3 (no overlaps at all - nothing to merge, base case):
 * Input: intervals = [[1,2],[3,4],[5,6]]
 * Output: [[1,2],[3,4],[5,6]]
 *
 * Example 4 (tricky - one interval swallows several later ones in a
 * row; merging must keep extending the current merged interval across
 * more than one neighbor, not just the immediately next one):
 * Input: intervals = [[1,10],[2,3],[4,5],[6,7]]
 * Output: [[1,10]]
 *
 * Constraints:
 * - 1 &lt;= intervals.length &lt;= 10^4
 * - intervals[i].length == 2
 * - 0 &lt;= starti &lt;= endi &lt;= 10^4
 * </pre>
 */
public class Q008_MergeIntervals {

    /**
     * @implNote TODO: implement.
     * Target approach: Sort the intervals by start time (this is the
     * "sorting as pre-processing" pattern - once sorted, any interval
     * that overlaps the current merged one must appear immediately next,
     * never later). Walk the sorted intervals, keeping a "current merged
     * interval"; if the next interval's start is &lt;= the current merged
     * interval's end, they overlap - extend the current end to
     * max(currentEnd, nextEnd). Otherwise, the current merged interval
     * is finalized - add it to the result and start a new current
     * interval from the next one.
     * <p>
     * Target Time Complexity: O(n log n) - dominated by the sort; the
     * merge walk itself is O(n).
     * <br>
     * Target Space Complexity: O(n) for the output (O(log n) to O(n)
     * for the sort's own auxiliary space, depending on the sort used).
     */
    public int[][] merge(int[][] intervals) {
        // TODO: implement
        return intervals;
    }
}
