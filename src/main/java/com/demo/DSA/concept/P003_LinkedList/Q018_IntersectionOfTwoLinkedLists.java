package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q018. Intersection of Two Linked Lists
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 * https://algomaster.io/learn/dsa/linked-list/intersection-of-two-linked-lists
 * <p>
 * Given the heads of two singly linked lists, return the node at which
 * the two lists intersect (the exact same node object, by reference, not
 * merely equal value), or null if they do not intersect. After the
 * intersection point, both lists share the same nodes all the way to the
 * end.
 *
 * <pre>
 * Example 1:
 * Input: listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], they share the tail
 * starting at node with value 8 (skipA = 2, skipB = 3)
 * Output: node with value 8
 *
 * Example 2:
 * Input: listA = [1,9,1,2,4], listB = [3,2,4], they share the tail
 * starting at node with value 2 (skipA = 3, skipB = 1)
 * Output: node with value 2
 *
 * Example 3 (no intersection):
 * Input: listA = [2,6,4], listB = [1,5]
 * Output: null (the two lists are completely disjoint)
 *
 * Example 4 (tricky - both heads are literally the same node, i.e. the
 * two "different" lists are actually one and the same list; intersection
 * happens immediately, at position 0):
 * Input: listA = listB = [1,2,3]
 * Output: node with value 1 (the shared head itself)
 *
 * Constraints:
 * - The number of nodes in listA is in the range [1, 3 * 10^4].
 * - The number of nodes in listB is in the range [1, 3 * 10^4].
 * - 1 &lt;= Node.val &lt;= 10^5
 * - The two lists must retain their original structure after the
 *   function returns.
 * </pre>
 */
public class Q018_IntersectionOfTwoLinkedLists {

    /**
     * @implNote TODO: implement.
     * Target approach: Two-pointer length-equalization trick, O(1) extra
     * space. Run pointer pA down listA and pB down listB; when either
     * pointer reaches the end (null), redirect it to the OTHER list's
     * head instead of stopping. Because pA travels
     * (lenA - intersection) + intersection + (lenB - intersection) total
     * steps before potentially reaching the intersection node on its
     * second pass, and pB travels the same total distance by symmetry,
     * both pointers arrive at the intersection node (or both become null
     * simultaneously, if there's no intersection) at exactly the same
     * step.
     * <p>
     * Target Time Complexity: O(n + m).
     * <br>
     * Target Space Complexity: O(1) (contrast with the O(n)-space
     * HashSet-of-visited-nodes approach).
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // TODO: implement
        return null;
    }
}
