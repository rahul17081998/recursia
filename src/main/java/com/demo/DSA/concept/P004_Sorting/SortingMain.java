package com.demo.DSA.concept.P004_Sorting;

import java.util.Arrays;
import java.util.LinkedHashMap;
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
 * Driver for the entire sorting question package, parallel to
 * {@code P002_Graph.GraphMain}, {@code P001_Tree.TreeMain}, and
 * {@code P003_LinkedList.LinkedListMain} - calls every QXXX question with
 * real example input (pulled straight from that file's own javadoc), prints
 * input/output, and asserts the actual result against the documented
 * expected value.
 * <p>
 * Unlike Graph and LinkedList, nothing here is rendered to a diagram -
 * every question's input is a plain array (or a list of intervals), which
 * doesn't have a natural node-and-edge or chain shape the way a graph or
 * linked list does, so there's no Graphviz step in this driver.
 * <p>
 * <strong>Structure.</strong> Mirrors GraphMain/LinkedListMain: for question
 * QXXX, a record {@code CaseXXX} holds one example's fields, a static
 * {@code qXXXCases()} supplies the example rows, a single
 * {@code @ParameterizedTest} method contains the one copy of the actual
 * check logic, and a no-arg {@code qXXX_..._all()} feeds every row through
 * {@code assertAll(...)} for the CLI path. Shared, domain-agnostic
 * scaffolding (CLI dispatch, {@code runFromArgs}, {@code header}) lives in
 * {@link com.demo.DSA.concept.QuestionDriverSupport} so it isn't duplicated
 * across all four drivers.
 * <p>
 * Two ways to run a subset instead of everything, both in this one class:
 * <ul>
 *   <li><strong>From the command line / {@code main}</strong>: no program
 *   arguments runs every question; one or more question numbers runs only
 *   those, e.g. {@code java SortingMain 4} or {@code java SortingMain 4 5}.
 *   A failing question doesn't halt the run - it's caught, logged, and the
 *   rest still execute; a summary line reports how many passed.</li>
 *   <li><strong>From the IDE</strong>: every {@code qXXX_...} method is a
 *   {@code @ParameterizedTest}, so IntelliJ shows a gutter run icon on the
 *   method (runs every example as child nodes) and on each individual
 *   example once expanded - click either to run/debug just that much, no
 *   run-configuration args needed.</li>
 * </ul>
 */
public class SortingMain {

    private final Map<Integer, QuestionRunner> questions = new LinkedHashMap<>();

    public SortingMain() {
        questions.put(1, this::q001_BubbleSort_all);
        questions.put(2, this::q002_InsertionSort_all);
        questions.put(3, this::q003_MergeSort_all);
        questions.put(4, this::q004_QuickSort_all);
        questions.put(5, this::q005_HeapSort_all);
        questions.put(6, this::q006_SortColors_all);
        questions.put(7, this::q007_KthLargestElementInAnArray_all);
        questions.put(8, this::q008_MergeIntervals_all);
        questions.put(9, this::q009_LargestNumber_all);
    }

    public static void main(String[] args) {
        runFromArgs(args, new SortingMain().questions);
    }

    // =========================================================================
    // Q001 - Bubble Sort
    // =========================================================================

    private record CaseSort(String variant, int[] arr, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseSort> q001Cases() {
        return Stream.of(
                new CaseSort("A", new int[]{5, 1, 4, 2, 8}, new int[]{1, 2, 4, 5, 8}),
                new CaseSort("B", new int[]{1, 2, 3}, new int[]{1, 2, 3}),
                new CaseSort("C", new int[]{3, 2, 1}, new int[]{1, 2, 3}),
                new CaseSort("D", new int[]{7}, new int[]{7})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q001Cases")
    void q001_BubbleSort(CaseSort c) {
        Q001_SA_BubbleSort q = new Q001_SA_BubbleSort();
        System.out.println(c + ": input=" + Arrays.toString(c.arr()));
        int[] actual = q.bubbleSort(Arrays.copyOf(c.arr(), c.arr().length));
        System.out.println("bubbleSort -> " + Arrays.toString(actual));
        assertArrayEquals(c.expected(), actual, c.toString());
    }

    private void q001_BubbleSort_all() {
        header(Q001_SA_BubbleSort.class);
        assertAll("Q001_BubbleSort", toExecutables(q001Cases(), this::q001_BubbleSort));
    }

    // =========================================================================
    // Q002 - Insertion Sort
    // =========================================================================

    private static Stream<CaseSort> q002Cases() {
        return Stream.of(
                new CaseSort("A", new int[]{12, 11, 13, 5, 6}, new int[]{5, 6, 11, 12, 13}),
                new CaseSort("B", new int[]{5, 4, 3, 2, 1}, new int[]{1, 2, 3, 4, 5}),
                new CaseSort("C", new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5}),
                new CaseSort("D", new int[]{1}, new int[]{1})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q002Cases")
    void q002_InsertionSort(CaseSort c) {
        Q002_SA_InsertionSort q = new Q002_SA_InsertionSort();
        System.out.println(c + ": input=" + Arrays.toString(c.arr()));
        int[] actual = q.insertionSort(Arrays.copyOf(c.arr(), c.arr().length));
        System.out.println("insertionSort -> " + Arrays.toString(actual));
        assertArrayEquals(c.expected(), actual, c.toString());
    }

    private void q002_InsertionSort_all() {
        header(Q002_SA_InsertionSort.class);
        assertAll("Q002_InsertionSort", toExecutables(q002Cases(), this::q002_InsertionSort));
    }

    // =========================================================================
    // Q003 - Merge Sort (Sort an Array)
    // =========================================================================

    private static Stream<CaseSort> q003Cases() {
        return Stream.of(
                new CaseSort("A", new int[]{5, 2, 3, 1}, new int[]{1, 2, 3, 5}),
                new CaseSort("B", new int[]{5, 1, 1, 2, 0, 0}, new int[]{0, 0, 1, 1, 2, 5}),
                new CaseSort("C", new int[]{-2, -5, -45, 0, 11}, new int[]{-45, -5, -2, 0, 11}),
                new CaseSort("D", new int[]{1}, new int[]{1})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q003Cases")
    void q003_MergeSort(CaseSort c) {
        Q003_SA_MergeSort q = new Q003_SA_MergeSort();
        System.out.println(c + ": input=" + Arrays.toString(c.arr()));
        int[] actual = q.sortArray(Arrays.copyOf(c.arr(), c.arr().length));
        System.out.println("sortArray -> " + Arrays.toString(actual));
        assertArrayEquals(c.expected(), actual, c.toString());
    }

    private void q003_MergeSort_all() {
        header(Q003_SA_MergeSort.class);
        assertAll("Q003_MergeSort", toExecutables(q003Cases(), this::q003_MergeSort));
    }

    // =========================================================================
    // Q004 - Quick Sort
    // =========================================================================

    private static Stream<CaseSort> q004Cases() {
        return Stream.of(
                new CaseSort("A", new int[]{10, 7, 8, 9, 1, 5}, new int[]{1, 5, 7, 8, 9, 10}),
                new CaseSort("B", new int[]{5, 4, 3, 2, 1}, new int[]{1, 2, 3, 4, 5}),
                new CaseSort("C", new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5}),
                new CaseSort("D", new int[]{3, 7, 3, 1, 7}, new int[]{1, 3, 3, 7, 7})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q004Cases")
    void q004_QuickSort(CaseSort c) {
        Q004_SA_QuickSort q = new Q004_SA_QuickSort();
        System.out.println(c + ": input=" + Arrays.toString(c.arr()));
        int[] actual = q.quickSort(Arrays.copyOf(c.arr(), c.arr().length));
        System.out.println("quickSort -> " + Arrays.toString(actual));
        assertArrayEquals(c.expected(), actual, c.toString());
    }

    private void q004_QuickSort_all() {
        header(Q004_SA_QuickSort.class);
        assertAll("Q004_QuickSort", toExecutables(q004Cases(), this::q004_QuickSort));
    }

    // =========================================================================
    // Q005 - Heap Sort
    // =========================================================================

    private static Stream<CaseSort> q005Cases() {
        return Stream.of(
                new CaseSort("A", new int[]{4, 10, 3, 5, 1}, new int[]{1, 3, 4, 5, 10}),
                new CaseSort("B", new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5}),
                new CaseSort("C", new int[]{5, 4, 3, 2, 1}, new int[]{1, 2, 3, 4, 5}),
                new CaseSort("D", new int[]{4, 4, 1, 4}, new int[]{1, 4, 4, 4})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q005Cases")
    void q005_HeapSort(CaseSort c) {
        Q005_SA_HeapSort q = new Q005_SA_HeapSort();
        System.out.println(c + ": input=" + Arrays.toString(c.arr()));
        int[] actual = q.heapSort(Arrays.copyOf(c.arr(), c.arr().length));
        System.out.println("heapSort -> " + Arrays.toString(actual));
        assertArrayEquals(c.expected(), actual, c.toString());
    }

    private void q005_HeapSort_all() {
        header(Q005_SA_HeapSort.class);
        assertAll("Q005_HeapSort", toExecutables(q005Cases(), this::q005_HeapSort));
    }

    // =========================================================================
    // Q006 - Sort Colors
    // =========================================================================

    private record CaseSortColors(String variant, int[] nums, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseSortColors> q006Cases() {
        return Stream.of(
                new CaseSortColors("A", new int[]{2, 0, 2, 1, 1, 0}, new int[]{0, 0, 1, 1, 2, 2}),
                new CaseSortColors("B", new int[]{2, 0, 1}, new int[]{0, 1, 2}),
                new CaseSortColors("C", new int[]{1, 1, 1}, new int[]{1, 1, 1}),
                new CaseSortColors("D", new int[]{1, 0, 1, 0}, new int[]{0, 0, 1, 1})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q006Cases")
    void q006_SortColors(CaseSortColors c) {
        Q006_SortColors q = new Q006_SortColors();
        int[] nums = Arrays.copyOf(c.nums(), c.nums().length);
        System.out.println(c + ": input=" + Arrays.toString(nums));
        q.sortColors(nums);
        System.out.println("sortColors -> " + Arrays.toString(nums));
        assertArrayEquals(c.expected(), nums, c.toString());
    }

    private void q006_SortColors_all() {
        header(Q006_SortColors.class);
        assertAll("Q006_SortColors", toExecutables(q006Cases(), this::q006_SortColors));
    }

    // =========================================================================
    // Q007 - Kth Largest Element in an Array
    // =========================================================================

    private record CaseKthLargest(String variant, int[] nums, int k, int expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseKthLargest> q007Cases() {
        return Stream.of(
                new CaseKthLargest("A", new int[]{3, 2, 1, 5, 6, 4}, 2, 5),
                new CaseKthLargest("B", new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4, 4),
                new CaseKthLargest("C", new int[]{1, 1, 1, 1, 1}, 1, 1),
                new CaseKthLargest("D", new int[]{1, 2, 3, 4, 5}, 5, 1)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q007Cases")
    void q007_KthLargestElementInAnArray(CaseKthLargest c) {
        Q007_KthLargestElementInAnArray q = new Q007_KthLargestElementInAnArray();
        System.out.println(c + ": nums=" + Arrays.toString(c.nums()) + ", k=" + c.k());
        int actual = q.findKthLargest(Arrays.copyOf(c.nums(), c.nums().length), c.k());
        System.out.println("findKthLargest -> " + actual);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q007_KthLargestElementInAnArray_all() {
        header(Q007_KthLargestElementInAnArray.class);
        assertAll("Q007_KthLargestElementInAnArray", toExecutables(q007Cases(), this::q007_KthLargestElementInAnArray));
    }

    // =========================================================================
    // Q008 - Merge Intervals
    // =========================================================================

    private record CaseMergeIntervals(String variant, int[][] intervals, int[][] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseMergeIntervals> q008Cases() {
        return Stream.of(
                new CaseMergeIntervals("A",
                        new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}},
                        new int[][]{{1, 6}, {8, 10}, {15, 18}}),
                new CaseMergeIntervals("B",
                        new int[][]{{1, 4}, {4, 5}},
                        new int[][]{{1, 5}}),
                new CaseMergeIntervals("C",
                        new int[][]{{1, 2}, {3, 4}, {5, 6}},
                        new int[][]{{1, 2}, {3, 4}, {5, 6}}),
                new CaseMergeIntervals("D",
                        new int[][]{{1, 10}, {2, 3}, {4, 5}, {6, 7}},
                        new int[][]{{1, 10}})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q008Cases")
    void q008_MergeIntervals(CaseMergeIntervals c) {
        Q008_MergeIntervals q = new Q008_MergeIntervals();
        System.out.println(c + ": intervals=" + Arrays.deepToString(c.intervals()));
        int[][] actual = q.merge(c.intervals());
        System.out.println("merge -> " + Arrays.deepToString(actual));
        assertEquals(Arrays.deepToString(c.expected()), Arrays.deepToString(actual), c.toString());
    }

    private void q008_MergeIntervals_all() {
        header(Q008_MergeIntervals.class);
        assertAll("Q008_MergeIntervals", toExecutables(q008Cases(), this::q008_MergeIntervals));
    }

    // =========================================================================
    // Q009 - Largest Number
    // =========================================================================

    private record CaseLargestNumber(String variant, int[] nums, String expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseLargestNumber> q009Cases() {
        return Stream.of(
                new CaseLargestNumber("A", new int[]{10, 2}, "210"),
                new CaseLargestNumber("B", new int[]{3, 30, 34, 5, 9}, "9534330"),
                new CaseLargestNumber("C", new int[]{34, 3}, "343"),
                new CaseLargestNumber("D", new int[]{0, 0}, "0")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q009Cases")
    void q009_LargestNumber(CaseLargestNumber c) {
        Q009_LargestNumber q = new Q009_LargestNumber();
        System.out.println(c + ": nums=" + Arrays.toString(c.nums()));
        String actual = q.largestNumber(Arrays.copyOf(c.nums(), c.nums().length));
        System.out.println("largestNumber -> " + actual);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q009_LargestNumber_all() {
        header(Q009_LargestNumber.class);
        assertAll("Q009_LargestNumber", toExecutables(q009Cases(), this::q009_LargestNumber));
    }
}
