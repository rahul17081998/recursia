package com.demo.DSA.concept.P005_TwoPointersSlidingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.demo.DSA.concept.QuestionDriverSupport.QuestionRunner;

import static com.demo.DSA.concept.QuestionDriverSupport.header;
import static com.demo.DSA.concept.QuestionDriverSupport.runFromArgs;
import static com.demo.DSA.concept.QuestionDriverSupport.toExecutables;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Driver for the two-pointers / sliding-window question package, parallel to
 * {@code P002_Graph.GraphMain}, {@code P003_LinkedList.LinkedListMain} and
 * {@code P004_Sorting.SortingMain} - calls every QXXX question with real
 * example input (pulled straight from that file's own javadoc), prints
 * input/output, and asserts the actual result against the documented
 * expected value.
 * <p>
 * Like SortingMain, nothing here is rendered to a diagram - every input is a
 * plain array or string, so there's no Graphviz step.
 * <p>
 * <strong>Structure.</strong> Mirrors the other drivers: for question QXXX, a
 * record {@code CaseXXX} holds one example's fields, a static
 * {@code qXXXCases()} supplies the example rows (A, B, ... in the same order
 * as the javadoc's Example 1, 2, ...), a single {@code @ParameterizedTest}
 * method contains the one copy of the actual check logic, and a no-arg
 * {@code qXXX_..._all()} feeds every row through {@code assertAll(...)} for
 * the CLI path. Input arrays are copied before each call so a solution that
 * modifies its input in place can't break the next example.
 * <p>
 * Questions whose answer may come back "in any order" (Q004 3Sum, Q011 Find
 * All Anagrams) are compared order-independently.
 * <p>
 * Two ways to run a subset instead of everything, both in this one class:
 * <ul>
 *   <li><strong>From the command line / {@code main}</strong>: no program
 *   arguments runs every question; one or more question numbers runs only
 *   those, e.g. {@code java TwoPointersSlidingWindowMain 4} or
 *   {@code java TwoPointersSlidingWindowMain 4 12}. A failing question
 *   doesn't halt the run - it's caught, logged, and the rest still
 *   execute.</li>
 *   <li><strong>From the IDE</strong>: every {@code qXXX_...} method is a
 *   {@code @ParameterizedTest}, so IntelliJ shows a gutter run icon on the
 *   method (runs every example) and on each individual example once
 *   expanded.</li>
 * </ul>
 */
public class TwoPointersSlidingWindowMain {

    private final Map<Integer, QuestionRunner> questions = new LinkedHashMap<>();

    public TwoPointersSlidingWindowMain() {
        questions.put(1, this::q001_TwoPointersOppositeEnds_all);
        questions.put(2, this::q002_SlidingWindowFixedSize_all);
        questions.put(3, this::q003_SlidingWindowVariableSize_all);
        questions.put(4, this::q004_ThreeSum_all);
        questions.put(5, this::q005_ContainerWithMostWater_all);
        questions.put(6, this::q006_TrappingRainWater_all);
        questions.put(7, this::q007_RemoveDuplicatesFromSortedArray_all);
        questions.put(8, this::q008_MergeSortedArray_all);
        questions.put(9, this::q009_LongestSubstringWithoutRepeatingCharacters_all);
        questions.put(10, this::q010_LongestRepeatingCharacterReplacement_all);
        questions.put(11, this::q011_FindAllAnagramsInAString_all);
        questions.put(12, this::q012_MinimumWindowSubstring_all);
        questions.put(13, this::q013_SlidingWindowMaximum_all);
        questions.put(14, this::q014_SubarraysWithKDifferentIntegers_all);
    }

    public static void main(String[] args) {
        runFromArgs(args, new TwoPointersSlidingWindowMain().questions);
    }

    // =========================================================================
    // Q001 - Two Pointers, Opposite Ends (Two Sum II)
    // =========================================================================

    private record Case001(String variant, int[] numbers, int target, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<Case001> q001Cases() {
        return Stream.of(
                new Case001("A", new int[]{2, 7, 11, 15}, 9, new int[]{1, 2}),
                new Case001("B", new int[]{2, 3, 4}, 6, new int[]{1, 3}),
                new Case001("C", new int[]{-1, 0}, -1, new int[]{1, 2}),
                // duplicates: the pair is two equal values (4 + 4)
                new Case001("D", new int[]{1, 2, 3, 4, 4, 9, 56, 90}, 8, new int[]{4, 5}),
                // answer is the last two elements
                new Case001("E", new int[]{5, 25, 75}, 100, new int[]{2, 3})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q001Cases")
    void q001_TwoPointersOppositeEnds(Case001 c) {
        Q001_SA_TwoPointersOppositeEnds q = new Q001_SA_TwoPointersOppositeEnds();
        System.out.println(c + ": numbers=" + Arrays.toString(c.numbers()) + ", target=" + c.target());
        int[] actual = q.twoSum(c.numbers().clone(), c.target());
        System.out.println("twoSum -> " + Arrays.toString(actual));
        assertArrayEquals(c.expected(), actual, c.toString());
    }

    private void q001_TwoPointersOppositeEnds_all() {
        header(Q001_SA_TwoPointersOppositeEnds.class);
        assertAll("Q001_TwoPointersOppositeEnds", toExecutables(q001Cases(), this::q001_TwoPointersOppositeEnds));
    }

    // =========================================================================
    // Q002 - Sliding Window, Fixed Size (Maximum Sum Subarray of Size K)
    // =========================================================================

    private record Case002(String variant, int[] nums, int k, int expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<Case002> q002Cases() {
        return Stream.of(
                new Case002("A", new int[]{2, 1, 5, 1, 3, 2}, 3, 9),
                new Case002("B", new int[]{2, 3, 4, 1, 5}, 2, 7),
                // k == n: the whole array is the only window
                new Case002("C", new int[]{1, 2, 3}, 3, 6),
                // all negative: starting the max at 0 would wrongly return 0
                new Case002("D", new int[]{-1, -2, -3, -4}, 2, -3),
                // best window is the very last one
                new Case002("E", new int[]{1, 1, 1, 9, 9}, 2, 18)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q002Cases")
    void q002_SlidingWindowFixedSize(Case002 c) {
        Q002_SA_SlidingWindowFixedSize q = new Q002_SA_SlidingWindowFixedSize();
        System.out.println(c + ": nums=" + Arrays.toString(c.nums()) + ", k=" + c.k());
        int actual = q.maxSumSubarrayOfSizeK(c.nums().clone(), c.k());
        System.out.println("maxSumSubarrayOfSizeK -> " + actual);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q002_SlidingWindowFixedSize_all() {
        header(Q002_SA_SlidingWindowFixedSize.class);
        assertAll("Q002_SlidingWindowFixedSize", toExecutables(q002Cases(), this::q002_SlidingWindowFixedSize));
    }

    // =========================================================================
    // Q003 - Sliding Window, Variable Size (Minimum Size Subarray Sum)
    // =========================================================================

    private record Case003(String variant, int target, int[] nums, int expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<Case003> q003Cases() {
        return Stream.of(
                new Case003("A", 7, new int[]{2, 3, 1, 2, 4, 3}, 2),
                new Case003("B", 4, new int[]{1, 4, 4}, 1),
                // no subarray reaches the target -> 0
                new Case003("C", 11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}, 0),
                // only the whole array works
                new Case003("D", 15, new int[]{1, 2, 3, 4, 5}, 5),
                // window must shrink several steps at once ([3,4,5])
                new Case003("E", 11, new int[]{1, 2, 3, 4, 5}, 3)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q003Cases")
    void q003_SlidingWindowVariableSize(Case003 c) {
        Q003_SA_SlidingWindowVariableSize q = new Q003_SA_SlidingWindowVariableSize();
        System.out.println(c + ": target=" + c.target() + ", nums=" + Arrays.toString(c.nums()));
        int actual = q.minSubArrayLen(c.target(), c.nums().clone());
        System.out.println("minSubArrayLen -> " + actual);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q003_SlidingWindowVariableSize_all() {
        header(Q003_SA_SlidingWindowVariableSize.class);
        assertAll("Q003_SlidingWindowVariableSize", toExecutables(q003Cases(), this::q003_SlidingWindowVariableSize));
    }

    // =========================================================================
    // Q004 - 3Sum
    // =========================================================================

    private record Case004(String variant, int[] nums, List<List<Integer>> expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<Case004> q004Cases() {
        return Stream.of(
                new Case004("A", new int[]{-1, 0, 1, 2, -1, -4}, List.of(List.of(-1, -1, 2), List.of(-1, 0, 1))),
                new Case004("B", new int[]{0, 1, 1}, List.of()),
                new Case004("C", new int[]{0, 0, 0}, List.of(List.of(0, 0, 0))),
                // many duplicates - [0,0,0] must appear only once
                new Case004("D", new int[]{0, 0, 0, 0}, List.of(List.of(0, 0, 0))),
                // same first number (-2) in two different triplets
                new Case004("E", new int[]{-2, 0, 1, 1, 2}, List.of(List.of(-2, 0, 2), List.of(-2, 1, 1)))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q004Cases")
    void q004_ThreeSum(Case004 c) {
        Q004_ThreeSum q = new Q004_ThreeSum();
        System.out.println(c + ": nums=" + Arrays.toString(c.nums()));
        List<List<Integer>> actual = q.threeSum(c.nums().clone());
        System.out.println("threeSum -> " + actual);
        // "any order" - for both the triplets and the numbers inside each triplet
        assertEquals(normalizeTriplets(c.expected()), normalizeTriplets(actual), c.toString());
    }

    private static List<List<Integer>> normalizeTriplets(List<List<Integer>> triplets) {
        List<List<Integer>> out = new ArrayList<>();
        for (List<Integer> t : triplets) {
            List<Integer> sorted = new ArrayList<>(t);
            sorted.sort(null);
            out.add(sorted);
        }
        out.sort(Comparator.comparing(Object::toString));
        return out;
    }

    private void q004_ThreeSum_all() {
        header(Q004_ThreeSum.class);
        assertAll("Q004_ThreeSum", toExecutables(q004Cases(), this::q004_ThreeSum));
    }

    // =========================================================================
    // Q005 - Container With Most Water
    // =========================================================================

    private record Case005(String variant, int[] height, int expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<Case005> q005Cases() {
        return Stream.of(
                new Case005("A", new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}, 49),
                new Case005("B", new int[]{1, 1}, 1),
                // the two outermost lines are the best pair
                new Case005("C", new int[]{4, 3, 2, 1, 4}, 16),
                // tallest line in the middle doesn't help
                new Case005("D", new int[]{1, 2, 1}, 2),
                // best pair is not the two tallest lines
                new Case005("E", new int[]{1, 2, 4, 3}, 4)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q005Cases")
    void q005_ContainerWithMostWater(Case005 c) {
        Q005_ContainerWithMostWater q = new Q005_ContainerWithMostWater();
        System.out.println(c + ": height=" + Arrays.toString(c.height()));
        int actual = q.maxArea(c.height().clone());
        System.out.println("maxArea -> " + actual);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q005_ContainerWithMostWater_all() {
        header(Q005_ContainerWithMostWater.class);
        assertAll("Q005_ContainerWithMostWater", toExecutables(q005Cases(), this::q005_ContainerWithMostWater));
    }

    // =========================================================================
    // Q006 - Trapping Rain Water
    // =========================================================================

    private record Case006(String variant, int[] height, int expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<Case006> q006Cases() {
        return Stream.of(
                new Case006("A", new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 6),
                new Case006("B", new int[]{4, 2, 0, 3, 2, 5}, 9),
                // strictly increasing - nothing trapped
                new Case006("C", new int[]{1, 2, 3, 4}, 0),
                // a single dip
                new Case006("D", new int[]{2, 0, 2}, 2),
                // lower right wall decides the water level
                new Case006("E", new int[]{5, 4, 1, 2}, 1)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q006Cases")
    void q006_TrappingRainWater(Case006 c) {
        Q006_TrappingRainWater q = new Q006_TrappingRainWater();
        System.out.println(c + ": height=" + Arrays.toString(c.height()));
        int actual = q.trap(c.height().clone());
        System.out.println("trap -> " + actual);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q006_TrappingRainWater_all() {
        header(Q006_TrappingRainWater.class);
        assertAll("Q006_TrappingRainWater", toExecutables(q006Cases(), this::q006_TrappingRainWater));
    }

    // =========================================================================
    // Q007 - Remove Duplicates from Sorted Array
    // =========================================================================

    private record Case007(String variant, int[] nums, int[] expectedUnique) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<Case007> q007Cases() {
        return Stream.of(
                new Case007("A", new int[]{1, 1, 2}, new int[]{1, 2}),
                new Case007("B", new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}, new int[]{0, 1, 2, 3, 4}),
                // single element
                new Case007("C", new int[]{1}, new int[]{1}),
                // no duplicates - nothing should move
                new Case007("D", new int[]{1, 2, 3}, new int[]{1, 2, 3}),
                // all the same value
                new Case007("E", new int[]{7, 7, 7, 7}, new int[]{7})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q007Cases")
    void q007_RemoveDuplicatesFromSortedArray(Case007 c) {
        Q007_RemoveDuplicatesFromSortedArray q = new Q007_RemoveDuplicatesFromSortedArray();
        System.out.println(c + ": nums=" + Arrays.toString(c.nums()));
        int[] nums = c.nums().clone();
        int k = q.removeDuplicates(nums);
        System.out.println("removeDuplicates -> " + k + ", nums=" + Arrays.toString(nums));
        assertEquals(c.expectedUnique().length, k, c + " (k)");
        // only the first k slots are checked - the rest may hold anything
        assertArrayEquals(c.expectedUnique(), Arrays.copyOf(nums, c.expectedUnique().length), c + " (first k elements)");
    }

    private void q007_RemoveDuplicatesFromSortedArray_all() {
        header(Q007_RemoveDuplicatesFromSortedArray.class);
        assertAll("Q007_RemoveDuplicatesFromSortedArray", toExecutables(q007Cases(), this::q007_RemoveDuplicatesFromSortedArray));
    }

    // =========================================================================
    // Q008 - Merge Sorted Array
    // =========================================================================

    private record Case008(String variant, int[] nums1, int m, int[] nums2, int n, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<Case008> q008Cases() {
        return Stream.of(
                new Case008("A", new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3, new int[]{1, 2, 2, 3, 5, 6}),
                new Case008("B", new int[]{1}, 1, new int[]{}, 0, new int[]{1}),
                // nums1 has no real elements
                new Case008("C", new int[]{0}, 0, new int[]{1}, 1, new int[]{1}),
                // every nums2 value is smaller - all of nums1 must shift right
                new Case008("D", new int[]{4, 5, 6, 0, 0, 0}, 3, new int[]{1, 2, 3}, 3, new int[]{1, 2, 3, 4, 5, 6}),
                new Case008("E", new int[]{2, 0}, 1, new int[]{1}, 1, new int[]{1, 2})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q008Cases")
    void q008_MergeSortedArray(Case008 c) {
        Q008_MergeSortedArray q = new Q008_MergeSortedArray();
        System.out.println(c + ": nums1=" + Arrays.toString(c.nums1()) + ", m=" + c.m()
                + ", nums2=" + Arrays.toString(c.nums2()) + ", n=" + c.n());
        int[] nums1 = c.nums1().clone();
        q.merge(nums1, c.m(), c.nums2().clone(), c.n());
        System.out.println("merge -> nums1=" + Arrays.toString(nums1));
        assertArrayEquals(c.expected(), nums1, c.toString());
    }

    private void q008_MergeSortedArray_all() {
        header(Q008_MergeSortedArray.class);
        assertAll("Q008_MergeSortedArray", toExecutables(q008Cases(), this::q008_MergeSortedArray));
    }

    // =========================================================================
    // Q009 - Longest Substring Without Repeating Characters
    // =========================================================================

    private record Case009(String variant, String s, int expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<Case009> q009Cases() {
        return Stream.of(
                new Case009("A", "abcabcbb", 3),
                new Case009("B", "bbbbb", 1),
                new Case009("C", "pwwkew", 3),
                // empty string
                new Case009("D", "", 0),
                // repeat is before the window - left must not move backwards
                new Case009("E", "abba", 2)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q009Cases")
    void q009_LongestSubstringWithoutRepeatingCharacters(Case009 c) {
        Q009_LongestSubstringWithoutRepeatingCharacters q = new Q009_LongestSubstringWithoutRepeatingCharacters();
        System.out.println(c + ": s=\"" + c.s() + "\"");
        int actual = q.lengthOfLongestSubstring(c.s());
        System.out.println("lengthOfLongestSubstring -> " + actual);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q009_LongestSubstringWithoutRepeatingCharacters_all() {
        header(Q009_LongestSubstringWithoutRepeatingCharacters.class);
        assertAll("Q009_LongestSubstringWithoutRepeatingCharacters",
                toExecutables(q009Cases(), this::q009_LongestSubstringWithoutRepeatingCharacters));
    }

    // =========================================================================
    // Q010 - Longest Repeating Character Replacement
    // =========================================================================

    private record Case010(String variant, String s, int k, int expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<Case010> q010Cases() {
        return Stream.of(
                new Case010("A", "ABAB", 2, 4),
                new Case010("B", "AABABBA", 1, 4),
                // k = 0 - longest existing run
                new Case010("C", "AAAA", 0, 4),
                // all letters different
                new Case010("D", "ABCDE", 1, 2),
                // enough budget to cover the whole string
                new Case010("E", "ABBB", 2, 4)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q010Cases")
    void q010_LongestRepeatingCharacterReplacement(Case010 c) {
        Q010_LongestRepeatingCharacterReplacement q = new Q010_LongestRepeatingCharacterReplacement();
        System.out.println(c + ": s=\"" + c.s() + "\", k=" + c.k());
        int actual = q.characterReplacement(c.s(), c.k());
        System.out.println("characterReplacement -> " + actual);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q010_LongestRepeatingCharacterReplacement_all() {
        header(Q010_LongestRepeatingCharacterReplacement.class);
        assertAll("Q010_LongestRepeatingCharacterReplacement",
                toExecutables(q010Cases(), this::q010_LongestRepeatingCharacterReplacement));
    }

    // =========================================================================
    // Q011 - Find All Anagrams in a String
    // =========================================================================

    private record Case011(String variant, String s, String p, List<Integer> expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<Case011> q011Cases() {
        return Stream.of(
                new Case011("A", "cbaebabacd", "abc", List.of(0, 6)),
                new Case011("B", "abab", "ab", List.of(0, 1, 2)),
                // p longer than s
                new Case011("C", "a", "ab", List.of()),
                // overlapping matches
                new Case011("D", "aaaa", "aa", List.of(0, 1, 2)),
                // only the last window matches
                new Case011("E", "baa", "aa", List.of(1))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q011Cases")
    void q011_FindAllAnagramsInAString(Case011 c) {
        Q011_FindAllAnagramsInAString q = new Q011_FindAllAnagramsInAString();
        System.out.println(c + ": s=\"" + c.s() + "\", p=\"" + c.p() + "\"");
        List<Integer> actual = q.findAnagrams(c.s(), c.p());
        System.out.println("findAnagrams -> " + actual);
        // "any order"
        List<Integer> sorted = new ArrayList<>(actual);
        sorted.sort(null);
        assertEquals(c.expected(), sorted, c.toString());
    }

    private void q011_FindAllAnagramsInAString_all() {
        header(Q011_FindAllAnagramsInAString.class);
        assertAll("Q011_FindAllAnagramsInAString", toExecutables(q011Cases(), this::q011_FindAllAnagramsInAString));
    }

    // =========================================================================
    // Q012 - Minimum Window Substring
    // =========================================================================

    private record Case012(String variant, String s, String t, String expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<Case012> q012Cases() {
        return Stream.of(
                new Case012("A", "ADOBECODEBANC", "ABC", "BANC"),
                new Case012("B", "a", "a", "a"),
                // t needs two 'a's but s has only one
                new Case012("C", "a", "aa", ""),
                // the whole string is the answer
                new Case012("D", "aa", "aa", "aa"),
                // must shrink past the duplicate 'b' on the left
                new Case012("E", "bba", "ab", "ba")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q012Cases")
    void q012_MinimumWindowSubstring(Case012 c) {
        Q012_MinimumWindowSubstring q = new Q012_MinimumWindowSubstring();
        System.out.println(c + ": s=\"" + c.s() + "\", t=\"" + c.t() + "\"");
        String actual = q.minWindow(c.s(), c.t());
        System.out.println("minWindow -> \"" + actual + "\"");
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q012_MinimumWindowSubstring_all() {
        header(Q012_MinimumWindowSubstring.class);
        assertAll("Q012_MinimumWindowSubstring", toExecutables(q012Cases(), this::q012_MinimumWindowSubstring));
    }

    // =========================================================================
    // Q013 - Sliding Window Maximum
    // =========================================================================

    private record Case013(String variant, int[] nums, int k, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<Case013> q013Cases() {
        return Stream.of(
                new Case013("A", new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3, new int[]{3, 3, 5, 5, 6, 7}),
                new Case013("B", new int[]{1}, 1, new int[]{1}),
                // decreasing - max is always the window's first element
                new Case013("C", new int[]{9, 8, 7, 6}, 2, new int[]{9, 8, 7}),
                // increasing - max is always the newest element
                new Case013("D", new int[]{1, 2, 3, 4}, 2, new int[]{2, 3, 4}),
                // equal values - duplicates must not be dropped too early
                new Case013("E", new int[]{4, 4, 4}, 2, new int[]{4, 4})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q013Cases")
    void q013_SlidingWindowMaximum(Case013 c) {
        Q013_SlidingWindowMaximum q = new Q013_SlidingWindowMaximum();
        System.out.println(c + ": nums=" + Arrays.toString(c.nums()) + ", k=" + c.k());
        int[] actual = q.maxSlidingWindow(c.nums().clone(), c.k());
        System.out.println("maxSlidingWindow -> " + Arrays.toString(actual));
        assertArrayEquals(c.expected(), actual, c.toString());
    }

    private void q013_SlidingWindowMaximum_all() {
        header(Q013_SlidingWindowMaximum.class);
        assertAll("Q013_SlidingWindowMaximum", toExecutables(q013Cases(), this::q013_SlidingWindowMaximum));
    }

    // =========================================================================
    // Q014 - Subarrays with K Different Integers
    // =========================================================================

    private record Case014(String variant, int[] nums, int k, int expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<Case014> q014Cases() {
        return Stream.of(
                new Case014("A", new int[]{1, 2, 1, 2, 3}, 2, 7),
                new Case014("B", new int[]{1, 2, 1, 3, 4}, 3, 3),
                // all equal, k = 1 - every subarray counts (3 + 2 + 1)
                new Case014("C", new int[]{1, 1, 1}, 1, 6),
                // only the whole array has k distinct values
                new Case014("D", new int[]{1, 2, 3}, 3, 1),
                // k larger than the number of distinct values
                new Case014("E", new int[]{1, 2}, 3, 0)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q014Cases")
    void q014_SubarraysWithKDifferentIntegers(Case014 c) {
        Q014_SubarraysWithKDifferentIntegers q = new Q014_SubarraysWithKDifferentIntegers();
        System.out.println(c + ": nums=" + Arrays.toString(c.nums()) + ", k=" + c.k());
        int actual = q.subarraysWithKDistinct(c.nums().clone(), c.k());
        System.out.println("subarraysWithKDistinct -> " + actual);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q014_SubarraysWithKDifferentIntegers_all() {
        header(Q014_SubarraysWithKDifferentIntegers.class);
        assertAll("Q014_SubarraysWithKDifferentIntegers",
                toExecutables(q014Cases(), this::q014_SubarraysWithKDifferentIntegers));
    }
}
