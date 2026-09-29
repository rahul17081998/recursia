package com.demo.DSA.concept.P003_LinkedList;

/**
 * Q023. LRU Cache
 * https://leetcode.com/problems/lru-cache/
 * https://algomaster.io/learn/dsa/linked-list/lru-cache
 * https://www.geeksforgeeks.org/dsa/lru-cache-implementation/
 * <p>
 * Design a data structure that follows the constraints of a Least
 * Recently Used (LRU) cache. Implement the LRUCache class:
 * <ul>
 *   <li>{@code LRUCache(int capacity)} - initialize the cache with a
 *   positive size capacity.</li>
 *   <li>{@code int get(int key)} - return the value of the key if it
 *   exists, otherwise -1. Counts as a "use" of that key.</li>
 *   <li>{@code void put(int key, int value)} - update the value of the
 *   key if it exists, otherwise add the key-value pair. If the number of
 *   keys exceeds capacity, evict the least recently used key.</li>
 * </ul>
 * Both get and put must run in O(1) average time complexity.
 *
 * <pre>
 * Example (LeetCode's own walkthrough):
 * Input:
 *   ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 *   [[2], [1,1], [2,2], [1], [3,3], [2], [4,4], [1], [3], [4]]
 * Output:
 *   [null, null, null, 1, null, -1, null, -1, 3, 4]
 * Explanation:
 *   LRUCache cache = new LRUCache(2);
 *   cache.put(1, 1);          // cache: {1=1}
 *   cache.put(2, 2);          // cache: {1=1, 2=2}
 *   cache.get(1);              // returns 1, cache: {2=2, 1=1} (1 now MRU)
 *   cache.put(3, 3);          // evicts key 2 (LRU), cache: {1=1, 3=3}
 *   cache.get(2);              // returns -1 (not found)
 *   cache.put(4, 4);          // evicts key 1 (LRU), cache: {3=3, 4=4}
 *   cache.get(1);              // returns -1 (not found)
 *   cache.get(3);              // returns 3
 *   cache.get(4);              // returns 4
 *
 * Constraints:
 * - 1 &lt;= capacity &lt;= 3000
 * - 0 &lt;= key &lt;= 10^4
 * - 0 &lt;= value &lt;= 10^5
 * - At most 2 * 10^5 calls will be made to get and put.
 * </pre>
 */
public class Q023_LRUCache {

    /** Doubly linked node holding one cache entry - needs both prev and next for O(1) removal from the middle. */
    private static class DNode {
        int key, value;
        DNode prev, next;
        DNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * @implNote TODO: implement.
     * Target approach: A HashMap&lt;Integer, DNode&gt; for O(1) key lookup,
     * combined with a doubly linked list (with permanent dummy head/tail
     * sentinels, so add/remove never null-checks the ends) that keeps
     * nodes ordered from least-recently-used (right after head) to
     * most-recently-used (right before tail). {@code get}: look up the
     * node in the map (miss -&gt; -1), then unlink it and re-insert it right
     * before tail (marking it as just-used). {@code put}: if the key
     * exists, update its value and move it to just-before-tail same as
     * get; otherwise create a new node, insert it just-before-tail, add
     * it to the map, and if size now exceeds capacity, evict the node
     * right after head (the true LRU) from both the list and the map.
     * The doubly linked list is exactly why this package's node type
     * needs both directions - a singly linked list can't unlink an
     * arbitrary middle node in O(1) without already having its
     * predecessor in hand.
     * <p>
     * Target Time Complexity: O(1) average for both get and put.
     * <br>
     * Target Space Complexity: O(capacity).
     */
    public Q023_LRUCache(int capacity) {
        // TODO: implement
    }

    public int get(int key) {
        // TODO: implement
        return -1;
    }

    public void put(int key, int value) {
        // TODO: implement
    }
}
