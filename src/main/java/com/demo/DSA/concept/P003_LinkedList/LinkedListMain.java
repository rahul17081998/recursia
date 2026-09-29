package com.demo.DSA.concept.P003_LinkedList;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.demo.DSA.concept.QuestionDriverSupport;
import com.demo.DSA.concept.QuestionDriverSupport.QuestionRunner;

import static com.demo.DSA.concept.QuestionDriverSupport.header;
import static com.demo.DSA.concept.QuestionDriverSupport.runFromArgs;
import static com.demo.DSA.concept.QuestionDriverSupport.toExecutables;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Driver for the entire linked-list question package, parallel to
 * {@code P002_Graph.GraphMain} and {@code P001_Tree.TreeMain} - calls every
 * QXXX question with real example input (pulled straight from that file's
 * own javadoc), prints input/output, and for questions whose input is
 * naturally a single node chain, renders it to a PNG via Graphviz. Each PNG
 * is named after its question class ({@code Q0XX_ClassName.png}); when a
 * question has more than one worked example, they're suffixed _A, _B, _C in
 * javadoc order, and when a question takes more than one input list, each
 * gets its own suffixed PNG (e.g. {@code _A_L1}, {@code _A_L2}).
 * <p>
 * Not rendered: {@link Q010_MergeKSortedLists} (input is a whole array of
 * lists, not one or two chains), {@link Q021_CopyListWithRandomPointer} and
 * {@link Q022_FlattenAMultilevelDoublyLinkedList} (both use their own
 * {@code Node} type with extra fields a plain chain diagram can't show), and
 * {@link Q023_LRUCache} / {@link Q025_DesignLinkedList} (design questions
 * exercised via an operation sequence, not a single fixed list).
 * <p>
 * Rendered files land in ./output/P003_LinkedList/ under the working
 * directory this is run from - a sibling of {@code P002_Graph}'s own
 * ./output/P002_Graph/, both under one shared ./output/ root instead of
 * each package inventing its own top-level folder. Requires Graphviz on
 * PATH (`brew install graphviz`).
 * <p>
 * <strong>Structure.</strong> Mirrors GraphMain: for question QXXX with
 * multiple worked examples, a record {@code CaseXXX} holds one example's
 * fields, a static {@code qXXXCases()} supplies the example rows, a single
 * {@code @ParameterizedTest} method contains the one copy of the actual
 * check logic, and a no-arg {@code qXXX_..._all()} feeds every row through
 * {@code assertAll(...)} for the CLI path. Questions with exactly one
 * natural worked example (the two design questions, Q023 and Q025) are a
 * single plain {@code @Test} instead, same rationale as TreeMain. Shared,
 * domain-agnostic scaffolding (CLI dispatch, {@code runFromArgs},
 * {@code header}, the Graphviz PNG shell-out) lives in
 * {@link com.demo.DSA.concept.QuestionDriverSupport} so it isn't duplicated
 * across all three drivers.
 * <p>
 * Two ways to run a subset instead of everything, both in this one class:
 * <ul>
 *   <li><strong>From the command line / {@code main}</strong>: no program
 *   arguments runs every question; one or more question numbers runs only
 *   those, e.g. {@code java LinkedListMain 4} or {@code java LinkedListMain 4 5}.
 *   A failing question doesn't halt the run - it's caught, logged, and the
 *   rest still execute; a summary line reports how many passed.</li>
 *   <li><strong>From the IDE</strong>: every test method has a gutter run
 *   icon - click to run/debug just that much, no run-configuration args
 *   needed.</li>
 * </ul>
 */
public class LinkedListMain {

    private static final File OUT_DIR = new File("output/P003_LinkedList");
    private static final String ENGINE = "dot";

    static {
        // Created here (not just in main()) so IDE gutter-icon test runs -
        // which never call main() - can still write PNGs on their own.
        OUT_DIR.mkdirs();
    }

    private final Map<Integer, QuestionRunner> questions = new LinkedHashMap<>();

    public LinkedListMain() {
        questions.put(1, this::q001_ReverseLinkedList_all);
        questions.put(2, this::q002_ReverseLinkedListII_all);
        questions.put(3, this::q003_ReverseNodesInKGroup_all);
        questions.put(4, this::q004_DetectCycleInLinkedList_all);
        questions.put(5, this::q005_LinkedListCycleII_all);
        questions.put(6, this::q006_FindMiddleOfLinkedList_all);
        questions.put(7, this::q007_PalindromeLinkedList_all);
        questions.put(8, this::q008_ReorderList_all);
        questions.put(9, this::q009_MergeTwoSortedLists_all);
        questions.put(10, this::q010_MergeKSortedLists_all);
        questions.put(11, this::q011_SortList_all);
        questions.put(12, this::q012_RemoveNthNodeFromEndOfList_all);
        questions.put(13, this::q013_RotateList_all);
        questions.put(14, this::q014_OddEvenLinkedList_all);
        questions.put(15, this::q015_PartitionList_all);
        questions.put(16, this::q016_SwapNodesInPairs_all);
        questions.put(17, this::q017_AddTwoNumbers_all);
        questions.put(18, this::q018_IntersectionOfTwoLinkedLists_all);
        questions.put(19, this::q019_RemoveDuplicatesFromSortedList_all);
        questions.put(20, this::q020_RemoveDuplicatesFromSortedListII_all);
        questions.put(21, this::q021_CopyListWithRandomPointer_all);
        questions.put(22, this::q022_FlattenAMultilevelDoublyLinkedList_all);
        questions.put(23, this::q023_LRUCache);
        questions.put(24, this::q024_DeleteNodeInALinkedList_all);
        questions.put(25, this::q025_DesignLinkedList);
        questions.put(26, this::q026_MaximumTwinSumOfLinkedList_all);
        questions.put(27, this::q027_RemoveLinkedListElements_all);
        questions.put(28, this::q028_NextGreaterNodeInLinkedList_all);
    }

    public static void main(String[] args) {
        OUT_DIR.mkdirs();
        runFromArgs(args, new LinkedListMain().questions);
    }

    // =========================================================================
    // Q001 - Reverse Linked List
    // =========================================================================

    private record CaseReverse(String variant, int[] values, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseReverse> q001Cases() {
        return Stream.of(
                new CaseReverse("A", new int[]{1, 2, 3, 4, 5}, new int[]{5, 4, 3, 2, 1}),
                new CaseReverse("B", new int[]{1, 2}, new int[]{2, 1}),
                new CaseReverse("C", new int[]{}, new int[]{}),
                new CaseReverse("D", new int[]{42}, new int[]{42})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q001Cases")
    void q001_ReverseLinkedList(CaseReverse c) throws IOException {
        Q001_SA_ReverseLinkedList q = new Q001_SA_ReverseLinkedList();
        ListNode head = LinkedListUtil.buildList(c.values());
        System.out.println(c + ": input:"); LinkedListUtil.printList(head);
        render(Q001_SA_ReverseLinkedList.class, c.variant(), head);
        ListNode actual = q.reverseList(head);
        System.out.print("reverseList -> "); LinkedListUtil.printList(actual);
        assertListEquals(c.expected(), actual, c.toString());
    }

    private void q001_ReverseLinkedList_all() {
        header(Q001_SA_ReverseLinkedList.class);
        assertAll("Q001_ReverseLinkedList", toExecutables(q001Cases(), this::q001_ReverseLinkedList));
    }

    // =========================================================================
    // Q002 - Reverse Linked List II
    // =========================================================================

    private record CaseReverseII(String variant, int[] values, int left, int right, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseReverseII> q002Cases() {
        return Stream.of(
                new CaseReverseII("A", new int[]{1, 2, 3, 4, 5}, 2, 4, new int[]{1, 4, 3, 2, 5}),
                new CaseReverseII("B", new int[]{5}, 1, 1, new int[]{5}),
                new CaseReverseII("C", new int[]{3, 5}, 1, 2, new int[]{5, 3}),
                new CaseReverseII("D", new int[]{1, 2, 3, 4, 5}, 1, 3, new int[]{3, 2, 1, 4, 5})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q002Cases")
    void q002_ReverseLinkedListII(CaseReverseII c) throws IOException {
        Q002_ReverseLinkedListII q = new Q002_ReverseLinkedListII();
        ListNode head = LinkedListUtil.buildList(c.values());
        System.out.println(c + ": input (left=" + c.left() + ", right=" + c.right() + "):"); LinkedListUtil.printList(head);
        render(Q002_ReverseLinkedListII.class, c.variant(), head);
        ListNode actual = q.reverseBetween(head, c.left(), c.right());
        System.out.print("reverseBetween -> "); LinkedListUtil.printList(actual);
        assertListEquals(c.expected(), actual, c.toString());
    }

    private void q002_ReverseLinkedListII_all() {
        header(Q002_ReverseLinkedListII.class);
        assertAll("Q002_ReverseLinkedListII", toExecutables(q002Cases(), this::q002_ReverseLinkedListII));
    }

    // =========================================================================
    // Q003 - Reverse Nodes in k-Group
    // =========================================================================

    private record CaseKGroup(String variant, int[] values, int k, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseKGroup> q003Cases() {
        return Stream.of(
                new CaseKGroup("A", new int[]{1, 2, 3, 4, 5}, 2, new int[]{2, 1, 4, 3, 5}),
                new CaseKGroup("B", new int[]{1, 2, 3, 4, 5}, 3, new int[]{3, 2, 1, 4, 5}),
                new CaseKGroup("C", new int[]{1, 2, 3, 4, 5}, 5, new int[]{5, 4, 3, 2, 1}),
                new CaseKGroup("D", new int[]{1, 2, 3}, 1, new int[]{1, 2, 3})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q003Cases")
    void q003_ReverseNodesInKGroup(CaseKGroup c) throws IOException {
        Q003_ReverseNodesInKGroup q = new Q003_ReverseNodesInKGroup();
        ListNode head = LinkedListUtil.buildList(c.values());
        System.out.println(c + ": input (k=" + c.k() + "):"); LinkedListUtil.printList(head);
        render(Q003_ReverseNodesInKGroup.class, c.variant(), head);
        ListNode actual = q.reverseKGroup(head, c.k());
        System.out.print("reverseKGroup -> "); LinkedListUtil.printList(actual);
        assertListEquals(c.expected(), actual, c.toString());
    }

    private void q003_ReverseNodesInKGroup_all() {
        header(Q003_ReverseNodesInKGroup.class);
        assertAll("Q003_ReverseNodesInKGroup", toExecutables(q003Cases(), this::q003_ReverseNodesInKGroup));
    }

    // =========================================================================
    // Q004 - Linked List Cycle (detect)
    // =========================================================================

    private record CaseCycle(String variant, int[] values, int pos, boolean expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseCycle> q004Cases() {
        return Stream.of(
                new CaseCycle("A", new int[]{3, 2, 0, -4}, 1, true),
                new CaseCycle("B", new int[]{1, 2}, 0, true),
                new CaseCycle("C", new int[]{1}, -1, false),
                new CaseCycle("D", new int[]{1}, 0, true)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q004Cases")
    void q004_DetectCycleInLinkedList(CaseCycle c) throws IOException {
        Q004_SA_DetectCycleInLinkedList q = new Q004_SA_DetectCycleInLinkedList();
        ListNode head = buildCyclicNodes(c.values(), c.pos())[0];
        System.out.println(c + ": input values=" + Arrays.toString(c.values()) + ", pos=" + c.pos());
        render(Q004_SA_DetectCycleInLinkedList.class, c.variant(), head);
        boolean actual = q.hasCycle(head);
        System.out.println("hasCycle -> " + actual);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q004_DetectCycleInLinkedList_all() {
        header(Q004_SA_DetectCycleInLinkedList.class);
        assertAll("Q004_DetectCycleInLinkedList", toExecutables(q004Cases(), this::q004_DetectCycleInLinkedList));
    }

    // =========================================================================
    // Q005 - Linked List Cycle II (find the start node)
    // =========================================================================

    private record CaseCycleII(String variant, int[] values, int pos) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseCycleII> q005Cases() {
        return Stream.of(
                new CaseCycleII("A", new int[]{3, 2, 0, -4}, 1),
                new CaseCycleII("B", new int[]{1, 2}, 0),
                new CaseCycleII("C", new int[]{1}, -1),
                new CaseCycleII("D", new int[]{1}, 0)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q005Cases")
    void q005_LinkedListCycleII(CaseCycleII c) throws IOException {
        Q005_LinkedListCycleII q = new Q005_LinkedListCycleII();
        ListNode[] nodes = buildCyclicNodes(c.values(), c.pos());
        System.out.println(c + ": input values=" + Arrays.toString(c.values()) + ", pos=" + c.pos());
        render(Q005_LinkedListCycleII.class, c.variant(), nodes[0]);
        ListNode expected = c.pos() == -1 ? null : nodes[c.pos()];
        ListNode actual = q.detectCycle(nodes[0]);
        System.out.println("detectCycle -> " + (actual == null ? "null" : actual.val));
        assertSame(expected, actual, c.toString());
    }

    private void q005_LinkedListCycleII_all() {
        header(Q005_LinkedListCycleII.class);
        assertAll("Q005_LinkedListCycleII", toExecutables(q005Cases(), this::q005_LinkedListCycleII));
    }

    // =========================================================================
    // Q006 - Middle of the Linked List
    // =========================================================================

    private record CaseMiddle(String variant, int[] values, int expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseMiddle> q006Cases() {
        return Stream.of(
                new CaseMiddle("A", new int[]{1, 2, 3, 4, 5}, 3),
                new CaseMiddle("B", new int[]{1, 2, 3, 4, 5, 6}, 4),
                new CaseMiddle("C", new int[]{1}, 1),
                new CaseMiddle("D", new int[]{1, 2}, 2)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q006Cases")
    void q006_FindMiddleOfLinkedList(CaseMiddle c) throws IOException {
        Q006_SA_FindMiddleOfLinkedList q = new Q006_SA_FindMiddleOfLinkedList();
        ListNode head = LinkedListUtil.buildList(c.values());
        System.out.println(c + ": input:"); LinkedListUtil.printList(head);
        render(Q006_SA_FindMiddleOfLinkedList.class, c.variant(), head);
        ListNode actual = q.middleNode(head);
        System.out.println("middleNode -> " + (actual == null ? "null" : actual.val));
        assertEquals(c.expected(), actual == null ? null : actual.val, c.toString());
    }

    private void q006_FindMiddleOfLinkedList_all() {
        header(Q006_SA_FindMiddleOfLinkedList.class);
        assertAll("Q006_FindMiddleOfLinkedList", toExecutables(q006Cases(), this::q006_FindMiddleOfLinkedList));
    }

    // =========================================================================
    // Q007 - Palindrome Linked List
    // =========================================================================

    private record CasePalindrome(String variant, int[] values, boolean expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CasePalindrome> q007Cases() {
        return Stream.of(
                new CasePalindrome("A", new int[]{1, 2, 2, 1}, true),
                new CasePalindrome("B", new int[]{1, 2}, false),
                new CasePalindrome("C", new int[]{1, 2, 3, 2, 1}, true),
                new CasePalindrome("D", new int[]{7}, true)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q007Cases")
    void q007_PalindromeLinkedList(CasePalindrome c) throws IOException {
        Q007_PalindromeLinkedList q = new Q007_PalindromeLinkedList();
        ListNode head = LinkedListUtil.buildList(c.values());
        System.out.println(c + ": input:"); LinkedListUtil.printList(head);
        render(Q007_PalindromeLinkedList.class, c.variant(), head);
        boolean actual = q.isPalindrome(head);
        System.out.println("isPalindrome -> " + actual);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q007_PalindromeLinkedList_all() {
        header(Q007_PalindromeLinkedList.class);
        assertAll("Q007_PalindromeLinkedList", toExecutables(q007Cases(), this::q007_PalindromeLinkedList));
    }

    // =========================================================================
    // Q008 - Reorder List
    // =========================================================================

    private record CaseReorder(String variant, int[] values, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseReorder> q008Cases() {
        return Stream.of(
                new CaseReorder("A", new int[]{1, 2, 3, 4}, new int[]{1, 4, 2, 3}),
                new CaseReorder("B", new int[]{1, 2, 3, 4, 5}, new int[]{1, 5, 2, 4, 3}),
                new CaseReorder("C", new int[]{1}, new int[]{1}),
                new CaseReorder("D", new int[]{1, 2}, new int[]{1, 2})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q008Cases")
    void q008_ReorderList(CaseReorder c) throws IOException {
        Q008_ReorderList q = new Q008_ReorderList();
        ListNode head = LinkedListUtil.buildList(c.values());
        System.out.println(c + ": input:"); LinkedListUtil.printList(head);
        render(Q008_ReorderList.class, c.variant(), head);
        q.reorderList(head);
        System.out.print("reorderList -> "); LinkedListUtil.printList(head);
        assertListEquals(c.expected(), head, c.toString());
    }

    private void q008_ReorderList_all() {
        header(Q008_ReorderList.class);
        assertAll("Q008_ReorderList", toExecutables(q008Cases(), this::q008_ReorderList));
    }

    // =========================================================================
    // Q009 - Merge Two Sorted Lists
    // =========================================================================

    private record CaseMerge2(String variant, int[] list1, int[] list2, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseMerge2> q009Cases() {
        return Stream.of(
                new CaseMerge2("A", new int[]{1, 2, 4}, new int[]{1, 3, 4}, new int[]{1, 1, 2, 3, 4, 4}),
                new CaseMerge2("B", new int[]{}, new int[]{0}, new int[]{0}),
                new CaseMerge2("C", new int[]{}, new int[]{}, new int[]{}),
                new CaseMerge2("D", new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{1, 2, 3, 4, 5, 6})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q009Cases")
    void q009_MergeTwoSortedLists(CaseMerge2 c) throws IOException {
        Q009_SA_MergeTwoSortedLists q = new Q009_SA_MergeTwoSortedLists();
        ListNode l1 = LinkedListUtil.buildList(c.list1());
        ListNode l2 = LinkedListUtil.buildList(c.list2());
        System.out.println(c + ": list1=" + Arrays.toString(c.list1()) + ", list2=" + Arrays.toString(c.list2()));
        render(Q009_SA_MergeTwoSortedLists.class, c.variant() + "_L1", l1);
        render(Q009_SA_MergeTwoSortedLists.class, c.variant() + "_L2", l2);
        ListNode actual = q.mergeTwoLists(l1, l2);
        System.out.print("mergeTwoLists -> "); LinkedListUtil.printList(actual);
        assertListEquals(c.expected(), actual, c.toString());
    }

    private void q009_MergeTwoSortedLists_all() {
        header(Q009_SA_MergeTwoSortedLists.class);
        assertAll("Q009_MergeTwoSortedLists", toExecutables(q009Cases(), this::q009_MergeTwoSortedLists));
    }

    // =========================================================================
    // Q010 - Merge k Sorted Lists
    // =========================================================================

    private record CaseMergeK(String variant, int[][] lists, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseMergeK> q010Cases() {
        return Stream.of(
                new CaseMergeK("A", new int[][]{{1, 4, 5}, {1, 3, 4}, {2, 6}}, new int[]{1, 1, 2, 3, 4, 4, 5, 6}),
                new CaseMergeK("B", new int[][]{}, new int[]{}),
                new CaseMergeK("C", new int[][]{{}}, new int[]{}),
                new CaseMergeK("D", new int[][]{{1, 2, 3}}, new int[]{1, 2, 3})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q010Cases")
    void q010_MergeKSortedLists(CaseMergeK c) {
        Q010_MergeKSortedLists q = new Q010_MergeKSortedLists();
        ListNode[] lists = new ListNode[c.lists().length];
        for (int i = 0; i < c.lists().length; i++) lists[i] = LinkedListUtil.buildList(c.lists()[i]);
        System.out.println(c + ": lists=" + Arrays.deepToString(c.lists()));
        ListNode actual = q.mergeKLists(lists);
        System.out.print("mergeKLists -> "); LinkedListUtil.printList(actual);
        assertListEquals(c.expected(), actual, c.toString());
    }

    private void q010_MergeKSortedLists_all() {
        header(Q010_MergeKSortedLists.class);
        assertAll("Q010_MergeKSortedLists", toExecutables(q010Cases(), this::q010_MergeKSortedLists));
    }

    // =========================================================================
    // Q011 - Sort List (merge sort)
    // =========================================================================

    private record CaseSort(String variant, int[] values, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseSort> q011Cases() {
        return Stream.of(
                new CaseSort("A", new int[]{4, 2, 1, 3}, new int[]{1, 2, 3, 4}),
                new CaseSort("B", new int[]{-1, 5, 3, 4, 0}, new int[]{-1, 0, 3, 4, 5}),
                new CaseSort("C", new int[]{}, new int[]{}),
                new CaseSort("D", new int[]{5}, new int[]{5})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q011Cases")
    void q011_SortList(CaseSort c) throws IOException {
        Q011_SA_SortList q = new Q011_SA_SortList();
        ListNode head = LinkedListUtil.buildList(c.values());
        System.out.println(c + ": input:"); LinkedListUtil.printList(head);
        render(Q011_SA_SortList.class, c.variant(), head);
        ListNode actual = q.sortList(head);
        System.out.print("sortList -> "); LinkedListUtil.printList(actual);
        assertListEquals(c.expected(), actual, c.toString());
    }

    private void q011_SortList_all() {
        header(Q011_SA_SortList.class);
        assertAll("Q011_SortList", toExecutables(q011Cases(), this::q011_SortList));
    }

    // =========================================================================
    // Q012 - Remove Nth Node From End of List
    // =========================================================================

    private record CaseRemoveNth(String variant, int[] values, int n, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseRemoveNth> q012Cases() {
        return Stream.of(
                new CaseRemoveNth("A", new int[]{1, 2, 3, 4, 5}, 2, new int[]{1, 2, 3, 5}),
                new CaseRemoveNth("B", new int[]{1}, 1, new int[]{}),
                new CaseRemoveNth("C", new int[]{1, 2}, 2, new int[]{2}),
                new CaseRemoveNth("D", new int[]{1, 2, 3}, 1, new int[]{1, 2})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q012Cases")
    void q012_RemoveNthNodeFromEndOfList(CaseRemoveNth c) throws IOException {
        Q012_RemoveNthNodeFromEndOfList q = new Q012_RemoveNthNodeFromEndOfList();
        ListNode head = LinkedListUtil.buildList(c.values());
        System.out.println(c + ": input (n=" + c.n() + "):"); LinkedListUtil.printList(head);
        render(Q012_RemoveNthNodeFromEndOfList.class, c.variant(), head);
        ListNode actual = q.removeNthFromEnd(head, c.n());
        System.out.print("removeNthFromEnd -> "); LinkedListUtil.printList(actual);
        assertListEquals(c.expected(), actual, c.toString());
    }

    private void q012_RemoveNthNodeFromEndOfList_all() {
        header(Q012_RemoveNthNodeFromEndOfList.class);
        assertAll("Q012_RemoveNthNodeFromEndOfList", toExecutables(q012Cases(), this::q012_RemoveNthNodeFromEndOfList));
    }

    // =========================================================================
    // Q013 - Rotate List
    // =========================================================================

    private record CaseRotate(String variant, int[] values, int k, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseRotate> q013Cases() {
        return Stream.of(
                new CaseRotate("A", new int[]{1, 2, 3, 4, 5}, 2, new int[]{4, 5, 1, 2, 3}),
                new CaseRotate("B", new int[]{0, 1, 2}, 4, new int[]{2, 0, 1}),
                new CaseRotate("C", new int[]{1, 2, 3}, 3, new int[]{1, 2, 3}),
                new CaseRotate("D", new int[]{1}, 5, new int[]{1})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q013Cases")
    void q013_RotateList(CaseRotate c) throws IOException {
        Q013_RotateList q = new Q013_RotateList();
        ListNode head = LinkedListUtil.buildList(c.values());
        System.out.println(c + ": input (k=" + c.k() + "):"); LinkedListUtil.printList(head);
        render(Q013_RotateList.class, c.variant(), head);
        ListNode actual = q.rotateRight(head, c.k());
        System.out.print("rotateRight -> "); LinkedListUtil.printList(actual);
        assertListEquals(c.expected(), actual, c.toString());
    }

    private void q013_RotateList_all() {
        header(Q013_RotateList.class);
        assertAll("Q013_RotateList", toExecutables(q013Cases(), this::q013_RotateList));
    }

    // =========================================================================
    // Q014 - Odd Even Linked List
    // =========================================================================

    private record CaseOddEven(String variant, int[] values, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseOddEven> q014Cases() {
        return Stream.of(
                new CaseOddEven("A", new int[]{1, 2, 3, 4, 5}, new int[]{1, 3, 5, 2, 4}),
                new CaseOddEven("B", new int[]{2, 1, 3, 5, 6, 4, 7}, new int[]{2, 3, 6, 7, 1, 5, 4}),
                new CaseOddEven("C", new int[]{}, new int[]{}),
                new CaseOddEven("D", new int[]{1, 2}, new int[]{1, 2})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q014Cases")
    void q014_OddEvenLinkedList(CaseOddEven c) throws IOException {
        Q014_OddEvenLinkedList q = new Q014_OddEvenLinkedList();
        ListNode head = LinkedListUtil.buildList(c.values());
        System.out.println(c + ": input:"); LinkedListUtil.printList(head);
        render(Q014_OddEvenLinkedList.class, c.variant(), head);
        ListNode actual = q.oddEvenList(head);
        System.out.print("oddEvenList -> "); LinkedListUtil.printList(actual);
        assertListEquals(c.expected(), actual, c.toString());
    }

    private void q014_OddEvenLinkedList_all() {
        header(Q014_OddEvenLinkedList.class);
        assertAll("Q014_OddEvenLinkedList", toExecutables(q014Cases(), this::q014_OddEvenLinkedList));
    }

    // =========================================================================
    // Q015 - Partition List
    // =========================================================================

    private record CasePartition(String variant, int[] values, int x, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CasePartition> q015Cases() {
        return Stream.of(
                new CasePartition("A", new int[]{1, 4, 3, 2, 5, 2}, 3, new int[]{1, 2, 2, 4, 3, 5}),
                new CasePartition("B", new int[]{2, 1}, 2, new int[]{1, 2}),
                new CasePartition("C", new int[]{1, 2, 3}, 4, new int[]{1, 2, 3}),
                new CasePartition("D", new int[]{3, 4, 5}, 1, new int[]{3, 4, 5})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q015Cases")
    void q015_PartitionList(CasePartition c) throws IOException {
        Q015_PartitionList q = new Q015_PartitionList();
        ListNode head = LinkedListUtil.buildList(c.values());
        System.out.println(c + ": input (x=" + c.x() + "):"); LinkedListUtil.printList(head);
        render(Q015_PartitionList.class, c.variant(), head);
        ListNode actual = q.partition(head, c.x());
        System.out.print("partition -> "); LinkedListUtil.printList(actual);
        assertListEquals(c.expected(), actual, c.toString());
    }

    private void q015_PartitionList_all() {
        header(Q015_PartitionList.class);
        assertAll("Q015_PartitionList", toExecutables(q015Cases(), this::q015_PartitionList));
    }

    // =========================================================================
    // Q016 - Swap Nodes in Pairs
    // =========================================================================

    private record CaseSwapPairs(String variant, int[] values, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseSwapPairs> q016Cases() {
        return Stream.of(
                new CaseSwapPairs("A", new int[]{1, 2, 3, 4}, new int[]{2, 1, 4, 3}),
                new CaseSwapPairs("B", new int[]{}, new int[]{}),
                new CaseSwapPairs("C", new int[]{1, 2, 3}, new int[]{2, 1, 3}),
                new CaseSwapPairs("D", new int[]{1}, new int[]{1})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q016Cases")
    void q016_SwapNodesInPairs(CaseSwapPairs c) throws IOException {
        Q016_SwapNodesInPairs q = new Q016_SwapNodesInPairs();
        ListNode head = LinkedListUtil.buildList(c.values());
        System.out.println(c + ": input:"); LinkedListUtil.printList(head);
        render(Q016_SwapNodesInPairs.class, c.variant(), head);
        ListNode actual = q.swapPairs(head);
        System.out.print("swapPairs -> "); LinkedListUtil.printList(actual);
        assertListEquals(c.expected(), actual, c.toString());
    }

    private void q016_SwapNodesInPairs_all() {
        header(Q016_SwapNodesInPairs.class);
        assertAll("Q016_SwapNodesInPairs", toExecutables(q016Cases(), this::q016_SwapNodesInPairs));
    }

    // =========================================================================
    // Q017 - Add Two Numbers
    // =========================================================================

    private record CaseAddTwo(String variant, int[] l1, int[] l2, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseAddTwo> q017Cases() {
        return Stream.of(
                new CaseAddTwo("A", new int[]{2, 4, 3}, new int[]{5, 6, 4}, new int[]{7, 0, 8}),
                new CaseAddTwo("B", new int[]{0}, new int[]{0}, new int[]{0}),
                new CaseAddTwo("C", new int[]{9, 9, 9, 9, 9, 9, 9}, new int[]{9, 9, 9, 9}, new int[]{8, 9, 9, 9, 0, 0, 0, 1}),
                new CaseAddTwo("D", new int[]{5}, new int[]{5}, new int[]{0, 1})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q017Cases")
    void q017_AddTwoNumbers(CaseAddTwo c) throws IOException {
        Q017_AddTwoNumbers q = new Q017_AddTwoNumbers();
        ListNode l1 = LinkedListUtil.buildList(c.l1());
        ListNode l2 = LinkedListUtil.buildList(c.l2());
        System.out.println(c + ": l1=" + Arrays.toString(c.l1()) + ", l2=" + Arrays.toString(c.l2()));
        render(Q017_AddTwoNumbers.class, c.variant() + "_L1", l1);
        render(Q017_AddTwoNumbers.class, c.variant() + "_L2", l2);
        ListNode actual = q.addTwoNumbers(l1, l2);
        System.out.print("addTwoNumbers -> "); LinkedListUtil.printList(actual);
        assertListEquals(c.expected(), actual, c.toString());
    }

    private void q017_AddTwoNumbers_all() {
        header(Q017_AddTwoNumbers.class);
        assertAll("Q017_AddTwoNumbers", toExecutables(q017Cases(), this::q017_AddTwoNumbers));
    }

    // =========================================================================
    // Q018 - Intersection of Two Linked Lists
    // =========================================================================

    private record CaseIntersection(String variant, int[] prefixA, int[] prefixB, int[] shared) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseIntersection> q018Cases() {
        return Stream.of(
                new CaseIntersection("A", new int[]{4, 1}, new int[]{5, 6, 1}, new int[]{8, 4, 5}),
                new CaseIntersection("B", new int[]{1, 9, 1}, new int[]{3}, new int[]{2, 4}),
                new CaseIntersection("C", new int[]{2, 6, 4}, new int[]{1, 5}, new int[]{}),
                new CaseIntersection("D", new int[]{}, new int[]{}, new int[]{1, 2, 3})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q018Cases")
    void q018_IntersectionOfTwoLinkedLists(CaseIntersection c) throws IOException {
        Q018_IntersectionOfTwoLinkedLists q = new Q018_IntersectionOfTwoLinkedLists();
        ListNode sharedHead = LinkedListUtil.buildList(c.shared());
        ListNode headA = appendPrefix(c.prefixA(), sharedHead);
        ListNode headB = appendPrefix(c.prefixB(), sharedHead);
        System.out.println(c + ": listA prefix=" + Arrays.toString(c.prefixA()) + ", listB prefix=" + Arrays.toString(c.prefixB()) + ", shared tail=" + Arrays.toString(c.shared()));
        render(Q018_IntersectionOfTwoLinkedLists.class, c.variant() + "_A", headA);
        render(Q018_IntersectionOfTwoLinkedLists.class, c.variant() + "_B", headB);
        ListNode expected = c.shared().length > 0 ? sharedHead : null;
        ListNode actual = q.getIntersectionNode(headA, headB);
        System.out.println("getIntersectionNode -> " + (actual == null ? "null" : actual.val));
        assertSame(expected, actual, c.toString());
    }

    private void q018_IntersectionOfTwoLinkedLists_all() {
        header(Q018_IntersectionOfTwoLinkedLists.class);
        assertAll("Q018_IntersectionOfTwoLinkedLists", toExecutables(q018Cases(), this::q018_IntersectionOfTwoLinkedLists));
    }

    // =========================================================================
    // Q019 - Remove Duplicates from Sorted List
    // =========================================================================

    private record CaseDedupI(String variant, int[] values, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseDedupI> q019Cases() {
        return Stream.of(
                new CaseDedupI("A", new int[]{1, 1, 2}, new int[]{1, 2}),
                new CaseDedupI("B", new int[]{1, 1, 2, 3, 3}, new int[]{1, 2, 3}),
                new CaseDedupI("C", new int[]{1}, new int[]{1}),
                new CaseDedupI("D", new int[]{1, 1, 1, 1}, new int[]{1})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q019Cases")
    void q019_RemoveDuplicatesFromSortedList(CaseDedupI c) throws IOException {
        Q019_RemoveDuplicatesFromSortedList q = new Q019_RemoveDuplicatesFromSortedList();
        ListNode head = LinkedListUtil.buildList(c.values());
        System.out.println(c + ": input:"); LinkedListUtil.printList(head);
        render(Q019_RemoveDuplicatesFromSortedList.class, c.variant(), head);
        ListNode actual = q.deleteDuplicates(head);
        System.out.print("deleteDuplicates -> "); LinkedListUtil.printList(actual);
        assertListEquals(c.expected(), actual, c.toString());
    }

    private void q019_RemoveDuplicatesFromSortedList_all() {
        header(Q019_RemoveDuplicatesFromSortedList.class);
        assertAll("Q019_RemoveDuplicatesFromSortedList", toExecutables(q019Cases(), this::q019_RemoveDuplicatesFromSortedList));
    }

    // =========================================================================
    // Q020 - Remove Duplicates from Sorted List II
    // =========================================================================

    private record CaseDedupII(String variant, int[] values, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseDedupII> q020Cases() {
        return Stream.of(
                new CaseDedupII("A", new int[]{1, 2, 3, 3, 4, 4, 5}, new int[]{1, 2, 5}),
                new CaseDedupII("B", new int[]{1, 1, 1, 2, 3}, new int[]{2, 3}),
                new CaseDedupII("C", new int[]{1, 2, 3}, new int[]{1, 2, 3}),
                new CaseDedupII("D", new int[]{1, 1}, new int[]{})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q020Cases")
    void q020_RemoveDuplicatesFromSortedListII(CaseDedupII c) throws IOException {
        Q020_RemoveDuplicatesFromSortedListII q = new Q020_RemoveDuplicatesFromSortedListII();
        ListNode head = LinkedListUtil.buildList(c.values());
        System.out.println(c + ": input:"); LinkedListUtil.printList(head);
        render(Q020_RemoveDuplicatesFromSortedListII.class, c.variant(), head);
        ListNode actual = q.deleteDuplicates(head);
        System.out.print("deleteDuplicates -> "); LinkedListUtil.printList(actual);
        assertListEquals(c.expected(), actual, c.toString());
    }

    private void q020_RemoveDuplicatesFromSortedListII_all() {
        header(Q020_RemoveDuplicatesFromSortedListII.class);
        assertAll("Q020_RemoveDuplicatesFromSortedListII", toExecutables(q020Cases(), this::q020_RemoveDuplicatesFromSortedListII));
    }

    // =========================================================================
    // Q021 - Copy List with Random Pointer
    // =========================================================================

    private record CaseRandomCopy(String variant, int[][] pairs) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseRandomCopy> q021Cases() {
        return Stream.of(
                new CaseRandomCopy("A", new int[][]{{7, -1}, {13, 0}, {11, 4}, {10, 2}, {1, 0}}),
                new CaseRandomCopy("B", new int[][]{{1, 1}, {2, 1}}),
                new CaseRandomCopy("C", new int[][]{}),
                new CaseRandomCopy("D", new int[][]{{1, 0}})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q021Cases")
    void q021_CopyListWithRandomPointer(CaseRandomCopy c) {
        Q021_CopyListWithRandomPointer q = new Q021_CopyListWithRandomPointer();
        Q021_CopyListWithRandomPointer.Node original = buildRandomList(c.pairs());
        System.out.println(c + ": input [val,randomIdx] pairs=" + Arrays.deepToString(c.pairs()));
        Q021_CopyListWithRandomPointer.Node copy = q.copyRandomList(original);
        int[][] actualPairs = toValRandomPairs(copy);
        System.out.println("copyRandomList -> " + Arrays.deepToString(actualPairs));
        assertEquals(Arrays.deepToString(c.pairs()), Arrays.deepToString(actualPairs), c + " (val/random structure)");

        Q021_CopyListWithRandomPointer.Node o = original, cl = copy;
        while (o != null) {
            assertNotSame(o, cl, c + " - clone must not reuse original node instances");
            o = o.next;
            cl = cl.next;
        }
    }

    private void q021_CopyListWithRandomPointer_all() {
        header(Q021_CopyListWithRandomPointer.class);
        assertAll("Q021_CopyListWithRandomPointer", toExecutables(q021Cases(), this::q021_CopyListWithRandomPointer));
    }

    // =========================================================================
    // Q022 - Flatten a Multilevel Doubly Linked List
    // =========================================================================

    private void q022_ExampleA() {
        // Main: 1-2-3-4-5-6 (3 has child 7-8-9-10, 8 has child 11-12)
        Q022_FlattenAMultilevelDoublyLinkedList.Node[] main = doublyChain(1, 2, 3, 4, 5, 6);
        Q022_FlattenAMultilevelDoublyLinkedList.Node[] child1 = doublyChain(7, 8, 9, 10);
        Q022_FlattenAMultilevelDoublyLinkedList.Node[] child2 = doublyChain(11, 12);
        main[2].child = child1[0];
        child1[1].child = child2[0];

        Q022_FlattenAMultilevelDoublyLinkedList q = new Q022_FlattenAMultilevelDoublyLinkedList();
        System.out.println("Example A: main 1-2-3(child 7-8(child 11-12)-9-10)-4-5-6");
        Q022_FlattenAMultilevelDoublyLinkedList.Node actual = q.flatten(main[0]);
        assertFlattenedValuesAndLinks(new int[]{1, 2, 3, 7, 8, 11, 12, 9, 10, 4, 5, 6}, actual, "Q022 Example A");
    }

    private void q022_ExampleB() {
        // Main: 1-2 (1 has child 3)
        Q022_FlattenAMultilevelDoublyLinkedList.Node[] main = doublyChain(1, 2);
        Q022_FlattenAMultilevelDoublyLinkedList.Node[] child = doublyChain(3);
        main[0].child = child[0];

        Q022_FlattenAMultilevelDoublyLinkedList q = new Q022_FlattenAMultilevelDoublyLinkedList();
        System.out.println("Example B: main 1(child 3)-2");
        Q022_FlattenAMultilevelDoublyLinkedList.Node actual = q.flatten(main[0]);
        assertFlattenedValuesAndLinks(new int[]{1, 3, 2}, actual, "Q022 Example B");
    }

    private void q022_ExampleC() {
        // Main: 1-2-3, no child pointers anywhere - flattening a plain flat list is a no-op.
        Q022_FlattenAMultilevelDoublyLinkedList.Node[] main = doublyChain(1, 2, 3);

        Q022_FlattenAMultilevelDoublyLinkedList q = new Q022_FlattenAMultilevelDoublyLinkedList();
        System.out.println("Example C: main 1-2-3, no children");
        Q022_FlattenAMultilevelDoublyLinkedList.Node actual = q.flatten(main[0]);
        assertFlattenedValuesAndLinks(new int[]{1, 2, 3}, actual, "Q022 Example C");
    }

    private void q022_FlattenAMultilevelDoublyLinkedList_all() {
        header(Q022_FlattenAMultilevelDoublyLinkedList.class);
        assertAll("Q022_FlattenAMultilevelDoublyLinkedList", this::q022_ExampleA, this::q022_ExampleB, this::q022_ExampleC);
    }

    // =========================================================================
    // Q023 - LRU Cache
    // =========================================================================

    @Test
    void q023_LRUCache() {
        header(Q023_LRUCache.class);
        System.out.println("Ops: put(1,1), put(2,2), get(1), put(3,3), get(2), put(4,4), get(1), get(3), get(4)");
        Q023_LRUCache cache = new Q023_LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1), "get(1) right after inserting it");
        cache.put(3, 3); // evicts key 2 (LRU)
        assertEquals(-1, cache.get(2), "key 2 should have been evicted");
        cache.put(4, 4); // evicts key 1 (LRU)
        assertEquals(-1, cache.get(1), "key 1 should have been evicted");
        assertEquals(3, cache.get(3), "key 3 should still be present");
        assertEquals(4, cache.get(4), "key 4 should still be present");

        // Tricky base case: capacity = 1 - every put after the first must evict immediately.
        System.out.println("Tricky case (capacity=1): put(10,10), put(20,20), get(10), get(20)");
        Q023_LRUCache tiny = new Q023_LRUCache(1);
        tiny.put(10, 10);
        tiny.put(20, 20); // evicts key 10 right away, since capacity is 1
        assertEquals(-1, tiny.get(10), "capacity=1: the first key should already be evicted");
        assertEquals(20, tiny.get(20), "capacity=1: the second key should still be present");
    }

    // =========================================================================
    // Q024 - Delete Node in a Linked List (no head access)
    // =========================================================================

    private record CaseDeleteNode(String variant, int[] values, int targetIndex, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseDeleteNode> q024Cases() {
        return Stream.of(
                new CaseDeleteNode("A", new int[]{4, 5, 1, 9}, 1, new int[]{4, 1, 9}),
                new CaseDeleteNode("B", new int[]{4, 5, 1, 9}, 2, new int[]{4, 5, 9}),
                new CaseDeleteNode("C", new int[]{4, 5, 1, 9}, 0, new int[]{5, 1, 9}),
                new CaseDeleteNode("D", new int[]{4, 9}, 0, new int[]{9})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q024Cases")
    void q024_DeleteNodeInALinkedList(CaseDeleteNode c) throws IOException {
        Q024_DeleteNodeInALinkedList q = new Q024_DeleteNodeInALinkedList();
        ListNode[] nodes = plainNodes(c.values());
        System.out.println(c + ": input=" + Arrays.toString(c.values()) + ", deleting node at index " + c.targetIndex() + " (val=" + nodes[c.targetIndex()].val + ")");
        render(Q024_DeleteNodeInALinkedList.class, c.variant(), nodes[0]);
        q.deleteNode(nodes[c.targetIndex()]);
        System.out.print("after deleteNode -> "); LinkedListUtil.printList(nodes[0]);
        assertListEquals(c.expected(), nodes[0], c.toString());
    }

    private void q024_DeleteNodeInALinkedList_all() {
        header(Q024_DeleteNodeInALinkedList.class);
        assertAll("Q024_DeleteNodeInALinkedList", toExecutables(q024Cases(), this::q024_DeleteNodeInALinkedList));
    }

    // =========================================================================
    // Q025 - Design Linked List
    // =========================================================================

    @Test
    void q025_DesignLinkedList() {
        header(Q025_DesignLinkedList.class);
        System.out.println("Ops: addAtHead(1), addAtTail(3), addAtIndex(1,2), get(1), deleteAtIndex(1), get(1)");
        Q025_DesignLinkedList list = new Q025_DesignLinkedList();
        list.addAtHead(1);          // list: 1
        list.addAtTail(3);          // list: 1 -> 3
        list.addAtIndex(1, 2);      // list: 1 -> 2 -> 3
        assertEquals(2, list.get(1), "get(1) after addAtIndex(1,2)");
        list.deleteAtIndex(1);      // list: 1 -> 3
        assertEquals(3, list.get(1), "get(1) after deleteAtIndex(1)");

        // Tricky boundary cases the LeetCode walkthrough itself doesn't exercise, but the
        // problem statement explicitly specifies: out-of-range addAtIndex is a no-op, and a
        // negative index means "insert at head".
        System.out.println("Boundary ops: addAtIndex(100,99) [out of range, ignored], addAtIndex(-1,0) [negative -> head]");
        list.addAtIndex(100, 99);   // index far past the end (list has 2 nodes) -> ignored
        assertEquals(-1, list.get(100), "addAtIndex past the end must not insert anything");
        list.addAtIndex(-1, 0);     // negative index -> insert at head; list: 0 -> 1 -> 3
        assertEquals(0, list.get(0), "addAtIndex with a negative index should insert at the head");
    }

    // =========================================================================
    // Q026 - Maximum Twin Sum of a Linked List
    // =========================================================================

    private record CaseTwinSum(String variant, int[] values, int expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseTwinSum> q026Cases() {
        return Stream.of(
                new CaseTwinSum("A", new int[]{5, 4, 2, 1}, 6),
                new CaseTwinSum("B", new int[]{4, 2, 2, 3}, 7),
                new CaseTwinSum("C", new int[]{1, 100000}, 100001),
                new CaseTwinSum("D", new int[]{3, 2, 1, 8}, 11)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q026Cases")
    void q026_MaximumTwinSumOfLinkedList(CaseTwinSum c) throws IOException {
        Q026_MaximumTwinSumOfLinkedList q = new Q026_MaximumTwinSumOfLinkedList();
        ListNode head = LinkedListUtil.buildList(c.values());
        System.out.println(c + ": input:"); LinkedListUtil.printList(head);
        render(Q026_MaximumTwinSumOfLinkedList.class, c.variant(), head);
        int actual = q.pairSum(head);
        System.out.println("pairSum -> " + actual);
        assertEquals(c.expected(), actual, c.toString());
    }

    private void q026_MaximumTwinSumOfLinkedList_all() {
        header(Q026_MaximumTwinSumOfLinkedList.class);
        assertAll("Q026_MaximumTwinSumOfLinkedList", toExecutables(q026Cases(), this::q026_MaximumTwinSumOfLinkedList));
    }

    // =========================================================================
    // Q027 - Remove Linked List Elements
    // =========================================================================

    private record CaseRemoveVal(String variant, int[] values, int val, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseRemoveVal> q027Cases() {
        return Stream.of(
                new CaseRemoveVal("A", new int[]{1, 2, 6, 3, 4, 5, 6}, 6, new int[]{1, 2, 3, 4, 5}),
                new CaseRemoveVal("B", new int[]{}, 1, new int[]{}),
                new CaseRemoveVal("C", new int[]{7, 7, 7, 7}, 7, new int[]{}),
                new CaseRemoveVal("D", new int[]{1, 2, 3, 4, 5}, 6, new int[]{1, 2, 3, 4, 5})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q027Cases")
    void q027_RemoveLinkedListElements(CaseRemoveVal c) throws IOException {
        Q027_RemoveLinkedListElements q = new Q027_RemoveLinkedListElements();
        ListNode head = LinkedListUtil.buildList(c.values());
        System.out.println(c + ": input=" + Arrays.toString(c.values()) + ", val=" + c.val());
        render(Q027_RemoveLinkedListElements.class, c.variant(), head);
        ListNode actual = q.removeElements(head, c.val());
        System.out.print("removeElements -> "); LinkedListUtil.printList(actual);
        assertListEquals(c.expected(), actual, c.toString());
    }

    private void q027_RemoveLinkedListElements_all() {
        header(Q027_RemoveLinkedListElements.class);
        assertAll("Q027_RemoveLinkedListElements", toExecutables(q027Cases(), this::q027_RemoveLinkedListElements));
    }

    // =========================================================================
    // Q028 - Next Greater Node In Linked List
    // =========================================================================

    private record CaseNextGreater(String variant, int[] values, int[] expected) {
        @Override public String toString() { return "Example " + variant; }
    }

    private static Stream<CaseNextGreater> q028Cases() {
        return Stream.of(
                new CaseNextGreater("A", new int[]{2, 1, 5}, new int[]{5, 5, 0}),
                new CaseNextGreater("B", new int[]{2, 7, 4, 3, 5}, new int[]{7, 0, 5, 5, 0}),
                new CaseNextGreater("C", new int[]{5, 4, 3, 2, 1}, new int[]{0, 0, 0, 0, 0}),
                new CaseNextGreater("D", new int[]{1, 2, 3, 4, 5}, new int[]{2, 3, 4, 5, 0})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("q028Cases")
    void q028_NextGreaterNodeInLinkedList(CaseNextGreater c) throws IOException {
        Q028_NextGreaterNodeInLinkedList q = new Q028_NextGreaterNodeInLinkedList();
        ListNode head = LinkedListUtil.buildList(c.values());
        System.out.println(c + ": input:"); LinkedListUtil.printList(head);
        render(Q028_NextGreaterNodeInLinkedList.class, c.variant(), head);
        int[] actual = q.nextLargerNodes(head);
        System.out.println("nextLargerNodes -> " + Arrays.toString(actual));
        assertArrayEquals(c.expected(), actual, c.toString());
    }

    private void q028_NextGreaterNodeInLinkedList_all() {
        header(Q028_NextGreaterNodeInLinkedList.class);
        assertAll("Q028_NextGreaterNodeInLinkedList", toExecutables(q028Cases(), this::q028_NextGreaterNodeInLinkedList));
    }

    // =========================================================================
    // shared build/assert helpers
    // =========================================================================

    /** Builds a plain (non-cyclic) chain and returns every node, so a specific one can be targeted by index. */
    private static ListNode[] plainNodes(int[] values) {
        ListNode[] nodes = new ListNode[values.length];
        for (int i = 0; i < values.length; i++) nodes[i] = new ListNode(values[i]);
        for (int i = 0; i < values.length - 1; i++) nodes[i].next = nodes[i + 1];
        return nodes;
    }

    /** Same shape as {@link LinkedListUtil#buildListWithCycle}, but returns every node so the cycle-start reference can be captured. */
    private static ListNode[] buildCyclicNodes(int[] values, int pos) {
        ListNode[] nodes = new ListNode[values.length];
        for (int i = 0; i < values.length; i++) nodes[i] = new ListNode(values[i]);
        for (int i = 0; i < values.length - 1; i++) nodes[i].next = nodes[i + 1];
        if (pos >= 0) nodes[values.length - 1].next = nodes[pos];
        return nodes;
    }

    /** Builds a plain prefix chain whose tail links into a (possibly null/shared) existing chain. */
    private static ListNode appendPrefix(int[] prefix, ListNode tail) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        for (int v : prefix) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }
        curr.next = tail;
        return dummy.next;
    }

    private static Q021_CopyListWithRandomPointer.Node buildRandomList(int[][] pairs) {
        int n = pairs.length;
        if (n == 0) return null;
        Q021_CopyListWithRandomPointer.Node[] nodes = new Q021_CopyListWithRandomPointer.Node[n];
        for (int i = 0; i < n; i++) nodes[i] = new Q021_CopyListWithRandomPointer.Node(pairs[i][0]);
        for (int i = 0; i < n; i++) {
            if (i < n - 1) nodes[i].next = nodes[i + 1];
            if (pairs[i][1] >= 0) nodes[i].random = nodes[pairs[i][1]];
        }
        return nodes[0];
    }

    /** Converts a random-pointer list back to [val, randomIndex] pairs (randomIndex=-1 if null) for comparison. */
    private static int[][] toValRandomPairs(Q021_CopyListWithRandomPointer.Node head) {
        List<Q021_CopyListWithRandomPointer.Node> order = new ArrayList<>();
        Map<Q021_CopyListWithRandomPointer.Node, Integer> idx = new IdentityHashMap<>();
        for (Q021_CopyListWithRandomPointer.Node curr = head; curr != null; curr = curr.next) {
            idx.put(curr, order.size());
            order.add(curr);
        }
        int[][] out = new int[order.size()][2];
        for (int i = 0; i < order.size(); i++) {
            Q021_CopyListWithRandomPointer.Node n = order.get(i);
            out[i][0] = n.val;
            out[i][1] = n.random == null ? -1 : idx.get(n.random);
        }
        return out;
    }

    /** Builds a doubly linked chain (next/prev both wired) of the given values, returning every node. */
    private static Q022_FlattenAMultilevelDoublyLinkedList.Node[] doublyChain(int... values) {
        Q022_FlattenAMultilevelDoublyLinkedList.Node[] nodes = new Q022_FlattenAMultilevelDoublyLinkedList.Node[values.length];
        for (int i = 0; i < values.length; i++) nodes[i] = new Q022_FlattenAMultilevelDoublyLinkedList.Node(values[i]);
        for (int i = 0; i < values.length; i++) {
            if (i < values.length - 1) nodes[i].next = nodes[i + 1];
            if (i > 0) nodes[i].prev = nodes[i - 1];
        }
        return nodes;
    }

    /** Verifies a flattened multilevel list's value order, next/prev consistency, and that every child pointer was cleared. */
    private static void assertFlattenedValuesAndLinks(int[] expectedValues, Q022_FlattenAMultilevelDoublyLinkedList.Node head, String what) {
        List<Integer> actualValues = new ArrayList<>();
        Q022_FlattenAMultilevelDoublyLinkedList.Node prev = null;
        Q022_FlattenAMultilevelDoublyLinkedList.Node curr = head;
        while (curr != null) {
            actualValues.add(curr.val);
            assertTrue(curr.child == null, what + " - node " + curr.val + " still has a child pointer after flatten");
            assertSame(prev, curr.prev, what + " - node " + curr.val + "'s prev pointer is inconsistent");
            prev = curr;
            curr = curr.next;
        }
        List<Integer> expectedList = new ArrayList<>();
        for (int v : expectedValues) expectedList.add(v);
        assertEquals(expectedList, actualValues, what + " (value order)");
    }

    private static void assertListEquals(int[] expected, ListNode actualHead, String what) {
        List<Integer> actual = LinkedListUtil.toList(actualHead);
        List<Integer> expectedList = new ArrayList<>();
        for (int v : expected) expectedList.add(v);
        assertEquals(expectedList, actual, what);
    }

    private static String pngName(Class<?> qClass, String variant) {
        return qClass.getSimpleName() + (variant == null ? "" : "_" + variant);
    }

    private static void render(Class<?> qClass, String variant, ListNode head) throws IOException {
        QuestionDriverSupport.renderToPng(OUT_DIR, ENGINE, LinkedListUtil.toDot(head), pngName(qClass, variant));
    }
}
