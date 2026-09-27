package com.demo.DSA.concept.P002_Graph;

import java.util.*;

/**
 * Q049. Find All Possible Recipes from Given Supplies
 * https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/description/
 * https://algomaster.io/learn/dsa/find-all-possible-recipes-from-given-supplies
 * <p>
 * You have `n` recipes: {@code recipes[i]} names the recipe, and
 * {@code ingredients[i]} lists everything it needs. A recipe can be made
 * once every one of its ingredients is available - and an ingredient can
 * itself be another recipe you've already made (recipes can chain). You
 * start with an unlimited supply of everything in {@code supplies}.
 * Return every recipe you're able to make, in any order.
 *
 * <pre>
 * Example 1:
 * Input: recipes = ["bread"], ingredients = [["yeast","flour"]],
 *        supplies = ["yeast","flour","corn"]
 * Output: ["bread"]
 * Explanation: Both ingredients for "bread" are directly in supplies.
 *
 * Example 2:
 * Input: recipes = ["bread","sandwich"],
 *        ingredients = [["yeast","flour"],["bread","meat"]],
 *        supplies = ["yeast","flour","meat"]
 * Output: ["bread","sandwich"]
 * Explanation: "bread" can be made from supplies alone; once it exists,
 * "sandwich" can be made too (it needs "meat", a supply, and "bread",
 * the recipe you just unlocked).
 *
 * Example 3:
 * Input: recipes = ["bread","sandwich","burger"],
 *        ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]],
 *        supplies = ["yeast","flour","meat"]
 * Output: ["bread","sandwich","burger"]
 * Explanation: A chain three deep - bread unlocks sandwich, and having
 * both sandwich and bread (plus the meat supply) unlocks burger.
 *
 * Constraints:
 * - n == recipes.length == ingredients.length, 1 &lt;= n &lt;= 100
 * - 1 &lt;= ingredients[i].length, supplies.length &lt;= 100
 * - 1 &lt;= recipes[i].length, ingredients[i][j].length, supplies[k].length &lt;= 10
 * - All strings consist of lowercase English letters.
 * - All values in recipes and supplies combined are unique (no name reused).
 * - Each ingredients[i] has no duplicate values.
 * </pre>
 *
 * <h3>How it works</h3>
 * <p>
 * The same Kahn's-BFS shape as {@link Q017_SA_TopologicalSortKahnsBFS}, just
 * with the roles reassigned: draw an edge ingredient -&gt; recipe for every
 * (ingredient, recipe) pair, and set each recipe's in-degree to how many
 * ingredients it needs. The twist is what seeds the queue - instead of
 * "every vertex with in-degree 0", it's <strong>everything already in
 * {@code supplies}</strong> (those are trivially "ready" without needing
 * any in-degree at all). Peel off ready items one at a time; whenever an
 * item becomes ready, decrement the in-degree of every recipe that lists
 * it as an ingredient, and if one hits 0, that recipe is now ready too -
 * record it in the answer and feed it back into the queue, since it might
 * itself be an ingredient for something further down the chain (exactly
 * how "bread" unlocks "sandwich" which unlocks "burger" in Example 3).
 * <p>
 * Because vertices are arbitrary strings here rather than a small fixed
 * alphabet, the char-to-index trick from {@link Q043_AlienDictionary}
 * doesn't fit - use a {@code Map<String, Integer>} (recipe/ingredient name
 * -&gt; compact index) instead of a fixed-size array, built only from the
 * recipe names (supplies never need an index - they're never the *target*
 * of an edge, only ever a source).
 *
 * <h3>Best sources to go deeper</h3>
 * <ul>
 *   <li><a href="https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/description/">LeetCode 2115 - Find All Possible Recipes from Given Supplies</a>
 *   - the canonical version of this problem; the discussion tab has several Kahn's-BFS writeups.</li>
 *   <li><a href="https://algomaster.io/learn/dsa/find-all-possible-recipes-from-given-supplies">AlgoMaster - Find All Possible Recipes from Given Supplies</a>
 *   - same problem, presented alongside the rest of AlgoMaster's topological-sort unit.</li>
 * </ul>
 */
public class Q049_FindAllPossibleRecipesFromGivenSupplies {

    /**
     * @implNote TODO: implement.
     * Target approach: Build a Map&lt;String, Integer&gt; assigning each
     * recipe name a compact index, an adjacency list of size
     * recipes.length (edges ingredient-name -&gt; recipe-index, but only
     * recorded when the ingredient is ITSELF a recipe - plain supplies
     * never need to be a graph node), and an in-degree array where
     * in-degree[r] = ingredients[r].length. Seed a queue with every raw
     * supply name. Kahn's BFS: poll a name, and for every recipe that
     * lists it as an ingredient, decrement that recipe's in-degree; if it
     * hits 0, add the recipe's name to the answer and enqueue the recipe's
     * own name too (so later recipes can depend on it). An ingredient that
     * is neither a supply nor ever unlocked as a recipe simply never gets
     * polled, so anything depending on it naturally never reaches
     * in-degree 0 - no separate "impossible" bookkeeping needed.
     * <p>
     * Target Time Complexity: O(n + total ingredients length) - each
     * recipe and each (ingredient, recipe) edge processed once.
     * <br>
     * Target Space Complexity: O(n + total ingredients length) - the
     * name-to-index map, adjacency list, and in-degree array.
     */
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {

        Set<String> suppliesList = new HashSet<>(Arrays.asList(supplies));
        Set<String> recipeList = new HashSet<>(Arrays.asList(recipes));

        Set<String> allNames = new LinkedHashSet<>(recipeList);

        for (List<String> ingredientList : ingredients) {
            for (String ing : ingredientList) {
                if (!suppliesList.contains(ing) && !recipeList.contains(ing)) allNames.add(ing); // either recipe or something which is unknown
            }
        }

        int V = allNames.size();
        Map<String, Integer> stringIntegerMap = new HashMap<>();
        Map<Integer, String> integerStringMap = new HashMap<>();
        int idx = 0;
        for (String name : allNames) {
            stringIntegerMap.put(name, idx);
            integerStringMap.put(idx, name);
            idx++;
        }

        List<List<String>> edgesStr = getEdgeGraph(recipes, ingredients, suppliesList, recipeList);

        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<V; i++) adj.add(new ArrayList<>());
        for(List<String> edge: edgesStr){
            int u=stringIntegerMap.get(edge.get(0));
            int v=stringIntegerMap.get(edge.get(1));
            adj.get(u).add(v);
        }

        Q017_SA_TopologicalSortKahnsBFS topologicalSortKahnsBFS = new Q017_SA_TopologicalSortKahnsBFS();
        List<Integer> recipesOrder = topologicalSortKahnsBFS.topoSort(V, adj);

        List<String> recipesOrderList = new ArrayList<>();
        for(Integer i: recipesOrder){
            String name = integerStringMap.get(i);
            // skipping non-recipe/non-supply item?
            if (recipeList.contains(name)) recipesOrderList.add(name);
        }
        return recipesOrderList;
    }

    private List<List<String>> getEdgeGraph(String[] recipes, List<List<String>> ingredients, Set<String> suppliesList, Set<String> recipeList) {

        List<List<String>> edges=new ArrayList<>();

        for(int i=0; i< recipes.length; i++){
            String v = recipes[i];
            for(String ing: ingredients.get(i)){
                if (suppliesList.contains(ing)) continue; // trivially available, no edge needed

                edges.add(new ArrayList<>(Arrays.asList(ing, v)));
                if (!recipeList.contains(ing)) {
                    // ing is neither a supply nor a recipe, so it can never be produced.
                    // A self-loop keeps its in-degree above 0 forever, so Kahn's BFS never treats it as "ready" - correctly blocking anything that depends on it.
                    edges.add(new ArrayList<>(Arrays.asList(ing, ing)));
                }
            }
        }
        return edges;
    }
}
