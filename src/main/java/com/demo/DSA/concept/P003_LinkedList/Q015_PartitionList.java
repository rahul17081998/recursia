package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q015. Partition List
 * https://leetcode.com/problems/partition-list/
 * https://algomaster.io/learn/dsa/linked-list/partition-list
 * <p>
 * Given the head of a linked list and a value x, partition it such that
 * all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each
 * of the two partitions.
 *
 * <pre>
 * Example 1:
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 *
 * Example 2:
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 *
 * Example 3 (every value is already &lt; x - the "greater-or-equal" bucket
 * ends up empty):
 * Input: head = [1,2,3], x = 4
 * Output: [1,2,3]
 *
 * Example 4 (every value is &gt;= x - the "less" bucket ends up empty, the
 * opposite boundary from Example 3):
 * Input: head = [3,4,5], x = 1
 * Output: [3,4,5]
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 200].
 * - -100 &lt;= Node.val &lt;= 100
 * - -200 &lt;= x &lt;= 200
 * </pre>
 */
public class Q015_PartitionList {

    /**
     * @implNote TODO: implement.
     * Target approach: Build two separate chains while walking the
     * original list once - a "less" chain (values &lt; x) and a
     * "greaterOrEqual" chain (values &gt;= x), each anchored by its own
     * dummy head so appending never needs a null-check. Every node from
     * the original list gets appended to exactly one of the two chains,
     * preserving relative order automatically. Finally splice
     * lessTail.next = greaterOrEqualDummy.next and make sure the combined
     * tail's next is null (the original greaterOrEqual tail may still
     * point into the old list).
     * <p>
     * Target Time Complexity: O(n).
     * <br>
     * Target Space Complexity: O(1) - nodes are relinked, not copied.
     */
    public ListNode partition(ListNode head, int x) {
        // TODO: implement
        return null;
    }
}
