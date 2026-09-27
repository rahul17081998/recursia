package com.demo.DSA.concept.P002_Graph;

import java.util.*;

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

    public static class NodeDetails{
        int walk;
        int vertex;
        NodeDetails(int vertex, int walk){
            this.vertex=vertex;
            this.walk=walk;
        }
    }
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
        if(beginWord.equals(endWord)) return 1;
        Set<String> wordSet = new LinkedHashSet<>();
        wordSet.add(beginWord);
        for(String word: wordList){
            if(!wordSet.contains(word))wordSet.add(word);
        }
        if(!wordSet.contains(endWord)) return 0;
        int V = wordSet.size();

        List<List<String>> edgesStr = getAllValidEdges(wordSet);


        Map<String, Integer> stringIntegerMap = new HashMap<>();
        Map<Integer, String> integerStringMap = new HashMap<>();
        int idx = 0;
        for (String word : wordSet) {
            stringIntegerMap.put(word, idx);
            integerStringMap.put(idx, word);
            idx++;
        }

        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<V; i++) adj.add(new ArrayList<>());
        for(List<String> edge: edgesStr){
            int u=stringIntegerMap.get(edge.get(0));
            int v=stringIntegerMap.get(edge.get(1));
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        return solve(stringIntegerMap.get(beginWord), stringIntegerMap.get(endWord), adj, V);
    }

    private List<List<String>> getAllValidEdges(Set<String> wordSet) {
        List<List<String>> edges = new ArrayList<>();

        List<String> allWords = new ArrayList<>();
        for(String w: wordSet){
            allWords.add(w);
        }
        //System.out.println("--> all words are: "+allWords);

        for(int i=0; i< allWords.size(); i++){
            for(int j=i+1; j< allWords.size(); j++){
                String word1 = allWords.get(i);
                String word2 = allWords.get(j);
                if(charDifferenceIsOne(word1, word2)){
                    edges.add(new ArrayList<>(Arrays.asList(word1, word2)));// it will cover both direction
                }
            }
        }

        //System.out.println("-----all edges are : "+edges);

        return edges;
    }

    private boolean charDifferenceIsOne(String word1, String word2) {
        int dif=0;
        for(int i=0; i<Math.min(word1.length(), word2.length()); i++){
            if(word1.charAt(i)!=word2.charAt(i)) dif++;
        }

        //System.out.println("word1= "+word1+"  word2= "+word2+" diff="+dif);
        return dif==1;
    }

    private int solve(Integer startNode, Integer endNode, List<List<Integer>> adj, int V) {
        Queue<NodeDetails> q= new LinkedList<>();
        boolean[] vis = new boolean[V];

        q.offer(new NodeDetails(startNode, 0));

        while(!q.isEmpty()){
            NodeDetails curr = q.poll();
            if(curr.vertex==endNode) return curr.walk+1;
            for(Integer neighbour: adj.get(curr.vertex)){
                if(!vis[neighbour]){
                    q.offer(new NodeDetails(neighbour, curr.walk+1));
                    vis[neighbour]=true;
                }
            }
        }

        return 0;

    }
}
