package com.demo.DSA.concept.P002_Graph;

import java.util.*;

/**
 * Q043. Alien Dictionary
 * https://leetcode.com/problems/alien-dictionary/ (premium; also on GFG as
 * "Alien Dictionary") - a very common top-company interview question.
 * <p>
 * There is a new alien language that uses the English alphabet, but the
 * order among letters is unknown. You are given a list of words FROM
 * THIS LANGUAGE'S DICTIONARY, sorted lexicographically by the rules of
 * this language. Derive one valid order of the letters, or determine
 * that no valid order exists (the input is invalid).
 *
 * <pre>
 * Example 1:
 * Input: words = ["wrt","wrf","er","ett","rftt"]
 * Output: "wertf"
 * Explanation: From "wrt" &lt; "wrf": t comes before f. From "wrt" &lt; "er":
 * w comes before e. From "er" &lt; "ett": r comes before t. From "ett" &lt;
 * "rftt": e comes before r. Combined: w &lt; e &lt; r &lt; t &lt; f.
 *
 * Example 2:
 * Input: words = ["z","x"]
 * Output: "zx"
 *
 * Example 3 (invalid input):
 * Input: words = ["abc","ab"]
 * Output: "" (no valid order - "abc" cannot come before its own prefix
 * "ab" in any consistent ordering)
 *
 * Constraints:
 * - 1 &lt;= words.length &lt;= 100
 * - words[i] consists of lowercase English letters.
 * </pre>
 */
public class Q043_AlienDictionary {

    /**
     * @implNote TODO: implement.
     * Target approach: Compare every pair of ADJACENT words to derive
     * ordering constraints - find the first index where they differ; that
     * pair of letters gives a directed edge (earlier letter -&gt; later
     * letter) in a graph over the 26 possible letters. Special case: if
     * word[i] is longer than word[i+1] AND word[i+1] is a prefix of
     * word[i] (no differing character found), the input is invalid (a
     * longer word can never lexically precede its own prefix) - return
     * "". Once the letter graph is built, run a topological sort (Kahn's
     * BFS, as in {@link Q017_SA_TopologicalSortKahnsBFS}) over just the
     * letters that actually appear in the input; if the sort can't
     * include all of them (a cycle exists), return "".
     * <p>
     * Target Time Complexity: O(C) where C is the total length of all
     * words (building the graph), plus O(V + E) for the topological sort
     * over at most 26 letters.
     * <br>
     * Target Space Complexity: O(1) effectively - at most 26 letters, so
     * the graph and in-degree structures are bounded by a small constant.
     */
    public String alienOrder(String[] words) {
        int n= words.length;
        List<List<Character>> edges= new ArrayList<>();
        for(int i=0; i<n-1; i++){
            List<Character> edge = getEdge(words[i], words[i+1]);
            if(edge.isEmpty()){
                if(words[i].length()>words[i+1].length()) return "";
            }else
                edges.add(edge);
        }

        Set<Character> letters = new HashSet<>();
        for(String word: words){
            for(char c: word.toCharArray()) letters.add(c);
        }

//        Step 2 — assign each letter a compact index 0..V-1, and remember both directions:
        int[] charToIndex=new int[26];
        Arrays.fill(charToIndex, -1);
        List<Character> indexToChar = new ArrayList<>();
        int countChar=0;
        for(char c: letters){
            charToIndex[c-'a']=countChar;
            countChar++;
            indexToChar.add(c);
        }

//        Step 3 — build an empty adjacency list of size V

        int V = indexToChar.size();
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<V; i++) adj.add(new ArrayList<>());

//        Step 4 — translate your existing edges (characters) into adj (integers):
        for(List<Character> edge: edges){
            int u=charToIndex[edge.get(0)-'a'];
            int v=charToIndex[edge.get(1)-'a'];
            adj.get(u).add(v);
        }
//        Step 5 — now adj and V are exactly what Q017_SA_TopologicalSortKahnsBFS.topoSort(V, adj) expects. Run it, check order.size() != V for the cycle case (return ""), otherwise you have a List<Integer> of indices in valid order.
        List<Integer> integerDictionaryList = new Q017_SA_TopologicalSortKahnsBFS().topoSort(V, adj);
        if(integerDictionaryList.size()!=V) return "";// cycle found

//        Step 6 — translate the integer order back to letters:

        StringBuilder sb = new StringBuilder();
        for(Integer index: integerDictionaryList){
            sb.append(indexToChar.get(index));
        }

        return sb.toString();
    }

    private List<Character> getEdge(String word1, String word2) {
        for(int i=0; i<Math.min(word1.length(), word2.length()); i++){
            if(word1.charAt(i)==word2.charAt(i)) continue;
            return new ArrayList<>(Arrays.asList(word1.charAt(i), word2.charAt(i)));
        }
        return new ArrayList<>();
    }
}
