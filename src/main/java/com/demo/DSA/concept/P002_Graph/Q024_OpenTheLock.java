package com.demo.DSA.concept.P002_Graph;

import java.util.List;

/**
 * Q024. Open the Lock
 * https://leetcode.com/problems/open-the-lock/
 * <p>
 * A lock has 4 wheels, each with digits 0-9, starting at "0000". Each
 * move turns one wheel one slot (wraps 9-&gt;0 or 0-&gt;9). Given a list of
 * deadends (states the lock must never land on, including possibly the
 * start) and a target, return the minimum number of turns to reach
 * target, or -1 if impossible.
 *
 * <pre>
 * Example 1:
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 *
 * Example 2:
 * Input: deadends = ["8888"], target = "0009"
 * Output: 1
 * Explanation: Turning the last wheel from 0 to 9 reaches "0009" directly.
 *
 * Constraints:
 * - 1 &lt;= deadends.length &lt;= 500
 * - deadends[i].length == 4, target.length == 4
 * - target is not in deadends.
 * - target and every deadend consist of digits only.
 * </pre>
 */
public class Q024_OpenTheLock {

    /**
     * @implNote TODO: implement.
     * Target approach: BFS over the state space of all 10^4 possible
     * 4-digit combinations, starting from "0000". Each state has 8
     * neighbors (each of the 4 wheels turned +1 or -1, with wraparound).
     * Put all deadends into a visited set up front (so they're never
     * enqueued - including "0000" itself, which should immediately return
     * -1 if it's a deadend). BFS depth at which target is first dequeued
     * is the answer.
     * <p>
     * Target Time Complexity: O(10^4) - bounded state space, 8 neighbor
     * checks per state.
     * <br>
     * Target Space Complexity: O(10^4) - visited set plus BFS queue.
     */
    public int openLock(String[] deadends, String target) {
        // TODO: implement
        return -1;
    }
}
