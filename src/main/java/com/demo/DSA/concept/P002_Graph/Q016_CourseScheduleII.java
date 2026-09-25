package com.demo.DSA.concept.P002_Graph;

/**
 * Q016. Course Schedule II
 * https://leetcode.com/problems/course-schedule-ii/
 * <p>
 * There are numCourses courses labeled 0 to numCourses-1, with
 * prerequisites[i] = [a, b] meaning course b must be taken before course
 * a. Return any valid ordering of courses to finish all of them, or an
 * empty array if it's impossible.
 *
 * <pre>
 * Example 1:
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] (or [0,2,1,3] - either is valid)
 *
 * Example 2:
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: []
 * Explanation: A cycle makes it impossible to finish all courses.
 *
 * Constraints:
 * - 1 &lt;= numCourses &lt;= 2000
 * - 0 &lt;= prerequisites.length &lt;= numCourses * (numCourses - 1)
 * - 0 &lt;= a, b &lt; numCourses, a != b
 * - All prerequisite pairs are unique.
 * </pre>
 */
public class Q016_CourseScheduleII {

    /**
     * @implNote TODO: implement.
     * Target approach: Kahn's algorithm (BFS topological sort) - build the
     * graph (edge b -&gt; a) and each node's in-degree; seed a queue with
     * every 0-in-degree node, then repeatedly poll a node, append it to
     * the result, and decrement its neighbors' in-degrees, enqueuing any
     * that drop to 0. If the result ends up shorter than numCourses, a
     * cycle exists - return an empty array instead.
     * <p>
     * Target Time Complexity: O(V + E).
     * <br>
     * Target Space Complexity: O(V + E) - adjacency list, in-degree
     * array, and queue.
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // TODO: implement
        return new int[0];
    }
}
