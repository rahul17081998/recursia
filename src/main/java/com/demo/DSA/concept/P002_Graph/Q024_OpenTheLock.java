package com.demo.DSA.concept.P002_Graph;

import java.util.*;

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
        //each state have 8 neighbour
        Queue<String> q = new LinkedList<>();
        Set<String> deadEndsSet = new HashSet<>(Arrays.asList(deadends));
        if (deadEndsSet.contains(target) || deadEndsSet.contains("0000")) return -1;
        if ("0000".equals(target)) return 0;

        int wheelTurn = 0;
        Set<String> vis = new HashSet<>();
        vis.add("0000");
        q.offer("0000");

        while (!q.isEmpty()) {
            int size = q.size();

            while (size > 0) {
                String currWheel = q.poll();
                if (currWheel.equals(target)) return wheelTurn;

                List<String> listOfWheelFromCurrState = getAllEightNewWheels(currWheel);
                for (String neighbourWheel : listOfWheelFromCurrState) {
                    if (!vis.contains(neighbourWheel) && !deadEndsSet.contains(neighbourWheel)) {
                        q.offer(neighbourWheel);
                        vis.add(neighbourWheel);
                    }
                }
                size--;
            }
            wheelTurn++;
        }
        return -1;
    }

    private List<String> getAllEightNewWheels(String currWheel) {
        List<String> ans = new ArrayList<>();

        for (int i = 0; i < currWheel.length(); i++) {
            StringBuilder sb = new StringBuilder(currWheel);
            char c = sb.charAt(i);
            sb.setCharAt(i, findNewChar(c, 1));
            ans.add(sb.toString());

            sb.setCharAt(i, findNewChar(c, -1));
            ans.add(sb.toString());
        }
        return ans;
    }

    private char findNewChar(char currChar, int val) {
        int currValue = currChar - '0';
        int updatedValue = 0;
        if (currValue == 9) {
            updatedValue = (val == 1) ? 0 : 8;
        } else if (currValue == 0) {
            updatedValue = (val == 1) ? 1 : 9;
        } else {
            updatedValue = currValue + val;
        }
        return (char) (updatedValue + '0');
    }
}
