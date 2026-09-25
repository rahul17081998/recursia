package com.demo.DSA.concept.P002_Graph;

/**
 * Q015. Course Schedule
 * https://leetcode.com/problems/course-schedule/
 * <p>
 * There are numCourses courses labeled 0 to numCourses-1. You are given
 * prerequisites where prerequisites[i] = [a, b] means you must take
 * course b before course a. Return true if it's possible to finish all
 * courses (i.e. the prerequisite graph has no cycle).
 *
 * <pre>
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: Take course 0, then course 1.
 *
 * Example 2:
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: 0 needs 1, and 1 needs 0 - a cycle, so it's impossible.
 *
 * Constraints:
 * - 1 &lt;= numCourses &lt;= 2000
 * - 0 &lt;= prerequisites.length &lt;= 5000
 * - prerequisites[i].length == 2
 * - 0 &lt;= a, b &lt; numCourses, a != b
 * </pre>
 */
public class Q015_CourseSchedule {

    /**
     * @implNote TODO: implement.
     * Target approach: Model courses as a directed graph (edge b -&gt; a
     * for each [a, b] prerequisite pair) and check whether it's a DAG,
     * reusing directed-cycle detection (visited[] + inRecursionStack[]
     * DFS, as in {@link Q014_SA_DetectCycleInDirectedGraph}) or Kahn's
     * topological sort (a schedule exists iff all numCourses nodes can be
     * output - see {@link Q017_SA_TopologicalSortKahnsBFS}).
     * <p>
     * Target Time Complexity: O(V + E) where V = numCourses, E =
     * prerequisites.length.
     * <br>
     * Target Space Complexity: O(V + E) - adjacency list plus
     * visited/queue structures.
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // TODO: implement
        return false;
    }
}
