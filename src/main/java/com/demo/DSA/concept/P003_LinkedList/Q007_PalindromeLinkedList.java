package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q007. Palindrome Linked List
 * https://leetcode.com/problems/palindrome-linked-list/
 * https://algomaster.io/learn/dsa/linked-list/palindrome-linked-list
 * <p>
 * Given the head of a singly linked list, return true if it is a
 * palindrome, or false otherwise.
 *
 * <pre>
 * Example 1:
 * Input: head = [1,2,2,1]
 * Output: true
 *
 * Example 2:
 * Input: head = [1,2]
 * Output: false
 *
 * Example 3 (odd length):
 * Input: head = [1,2,3,2,1]
 * Output: true
 *
 * Example 4 (single node - trivially a palindrome):
 * Input: head = [7]
 * Output: true
 *
 * Constraints:
 * - The number of nodes in the list is in the range [1, 10^5].
 * - 0 &lt;= Node.val &lt;= 9
 * </pre>
 */
public class Q007_PalindromeLinkedList {

    /**
     * @implNote TODO: implement.
     * Target approach: For O(1) extra space, find the middle with
     * {@link Q006_SA_FindMiddleOfLinkedList}'s slow/fast pointers, reverse
     * the second half in place with {@link Q001_SA_ReverseLinkedList}'s
     * technique, then walk the first half and the reversed second half in
     * lockstep comparing values. (Optionally reverse the second half back
     * afterward to restore the original list - not required by the
     * problem, but good practice for not mutating input unexpectedly.)
     * <p>
     * Target Time Complexity: O(n).
     * <br>
     * Target Space Complexity: O(1) with the reverse-half trick (O(n) if
     * implemented by simply copying values into an array/list first).
     */
    public boolean isPalindrome(ListNode head) {
        // TODO: implement
        return false;
    }
}
