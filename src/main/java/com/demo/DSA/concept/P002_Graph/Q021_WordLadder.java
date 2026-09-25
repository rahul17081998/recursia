package com.demo.DSA.concept.P002_Graph;

import java.util.List;

/**
 * Q021. Word Ladder
 * https://leetcode.com/problems/word-ladder/
 * <p>
 * Given beginWord, endWord, and a wordList, return the length of the
 * shortest transformation sequence from beginWord to endWord such that
 * only one letter is changed at a time, and every intermediate word must
 * exist in wordList. Return 0 if no such sequence exists.
 *
 * <pre>
 * Example 1:
 * Input: beginWord = "hit", endWord = "cog",
 *        wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: "hit" -&gt; "hot" -&gt; "dot" -&gt; "dog" -&gt; "cog" (5 words,
 * including both ends).
 *
 * Example 2:
 * Input: beginWord = "hit", endWord = "cog",
 *        wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: endWord "cog" is not in wordList, so no sequence exists.
 *
 * Constraints:
 * - 1 &lt;= beginWord.length &lt;= 10
 * - endWord.length == beginWord.length
 * - 1 &lt;= wordList.length &lt;= 5000
 * - All words consist of lowercase English letters and have the same
 *   length.
 * - beginWord != endWord, and all words in wordList are distinct.
 * </pre>
 */
public class Q021_WordLadder {

    /**
     * @implNote TODO: implement.
     * Target approach: Treat each word as a graph node, with an implicit
     * edge between two words that differ by exactly one letter. This is
     * an unweighted shortest-path problem, so BFS from beginWord - at
     * each step, for every possible single-letter substitution of the
     * current word, check whether the resulting word is in the (mutable)
     * word set; if so, it's a valid neighbor, remove it from the set (to
     * avoid revisiting) and enqueue it. The BFS depth at which endWord is
     * first reached is the answer.
     * <p>
     * Target Time Complexity: O(N * L^2) where N = wordList.length, L =
     * word length - for each word dequeued, generating all L * 26
     * substitutions and hashing each costs O(L).
     * <br>
     * Target Space Complexity: O(N * L) - the word set plus BFS queue.
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // TODO: implement
        return 0;
    }
}
