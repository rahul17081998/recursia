package com.demo.DSA.concept.P001_Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.demo.DSA.concept.QuestionDriverSupport.QuestionRunner;

import static com.demo.DSA.concept.QuestionDriverSupport.assertUnorderedInts;
import static com.demo.DSA.concept.QuestionDriverSupport.header;
import static com.demo.DSA.concept.QuestionDriverSupport.runFromArgs;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Driver for the entire tree question package, parallel to
 * {@code P002_Graph.GraphMain} - calls every QXXX question with real
 * example input, prints input/output, and asserts the actual result
 * against the documented expected value. Unlike the graph package, almost
 * every tree question here has exactly one worked example (not several),
 * so most questions are a single plain {@code @Test} rather than the
 * parameterized-case pattern GraphMain uses - that pattern is only worth
 * its overhead when a question has multiple examples (see Q010, Q035).
 * Where a problem has more than one valid correct answer (Q045's balanced
 * BST shape, Q049's DLL), the assertion checks the underlying property
 * instead of pinning to one exact structure.
 * <p>
 * Shared, domain-agnostic scaffolding (the CLI dispatch table, per-question
 * runner map, {@code runFromArgs}, {@code header}, unordered-list
 * assertion) lives in {@link com.demo.DSA.concept.QuestionDriverSupport} so
 * it isn't duplicated between this class and GraphMain.
 * <p>
 * Two ways to run a subset instead of everything:
 * <ul>
 *   <li><strong>From the command line / {@code main}</strong>: no program
 *   arguments runs every question; one or more question numbers runs only
 *   those, e.g. {@code java TreeMain 14} or {@code java TreeMain 14 36}.</li>
 *   <li><strong>From the IDE</strong>: every {@code qXXX_...} method is a
 *   {@code @Test}, so IntelliJ shows a gutter run icon on each one.</li>
 * </ul>
 */
public class TreeMain {

    private final Map<Integer, QuestionRunner> questions = new LinkedHashMap<>();

    public TreeMain() {
        questions.put(1, this::q001_InorderTraversal);
        questions.put(2, this::q002_PreOrderTraversal);
        questions.put(3, this::q003_PostOrderTraversal);
        questions.put(4, this::q004_HeightOfBinaryTree);
        questions.put(5, this::q005_PrintNodeAtKthLevelFromTop);
        questions.put(6, this::q006_LevelOrderTraversalLineByLine);
        questions.put(7, this::q007_SizeOfBinaryTree);
        questions.put(8, this::q008_MaximumInBinaryTree);
        questions.put(9, this::q009_LeftViewOfBinaryTree);
        questions.put(10, this::q010_ChildrenSumInABinaryTree);
        questions.put(11, this::q011_CheckForBalancedBinaryTree);
        questions.put(12, this::q012_MaxWidthInBinaryTree);
        questions.put(13, this::q013_FindPathFromRootToAnyNode);
        questions.put(14, this::q014_LowestCommonAncestor_LCA);
        questions.put(15, this::q015_RightViewOfBinaryTree);
        questions.put(16, this::q016_IterativeInorderTraversal);
        questions.put(17, this::q017_IterativePreorderTraversal);
        questions.put(18, this::q018_IterativePostorderTraversal);
        questions.put(19, this::q019_ZigzagLevelOrderTraversal);
        questions.put(20, this::q020_BoundaryTraversal);
        questions.put(21, this::q021_VerticalOrderTraversal);
        questions.put(22, this::q022_TopViewOfBinaryTree);
        questions.put(23, this::q023_BottomViewOfBinaryTree);
        questions.put(24, this::q024_DiagonalTraversal);
        questions.put(25, this::q025_ConstructTreeFromPreorderAndInorder);
        questions.put(26, this::q026_ConstructTreeFromInorderAndPostorder);
        questions.put(27, this::q027_SerializeAndDeserializeBinaryTree);
        questions.put(28, this::q028_MorrisInorderTraversal);
        questions.put(29, this::q029_DiameterOfBinaryTree);
        questions.put(30, this::q030_BinaryTreeMaximumPathSum);
        questions.put(31, this::q031_SameTree);
        questions.put(32, this::q032_SymmetricTree);
        questions.put(33, this::q033_SubtreeOfAnotherTree);
        questions.put(34, this::q034_FlattenBinaryTreeToLinkedList);
        questions.put(35, this::q035_CheckCompletenessOfBinaryTree);
        questions.put(36, this::q036_AllNodesDistanceKInBinaryTree);
        questions.put(37, this::q037_MinTimeToBurnBinaryTree);
        questions.put(38, this::q038_PathSum);
        questions.put(39, this::q039_PathSumIII);
        questions.put(40, this::q040_CountLeafAndSingleChildNodes);
        questions.put(41, this::q041_ValidateBinarySearchTree);
        questions.put(42, this::q042_KthSmallestElementInBST);
        questions.put(43, this::q043_InsertIntoBST);
        questions.put(44, this::q044_DeleteNodeInBST);
        questions.put(45, this::q045_SortedArrayToBST);
        questions.put(46, this::q046_InorderSuccessorInBST);
        questions.put(47, this::q047_LowestCommonAncestorInBST);
        questions.put(48, this::q048_TwoSumIV_InputIsBST);
        questions.put(49, this::q049_ConvertBSTToSortedDoublyLinkedList);
        questions.put(50, this::q050_PopulateNextRightPointers);
        questions.put(51, this::q051_SearchInBST);
    }

    public static void main(String[] args) {
        runFromArgs(args, new TreeMain().questions);
    }

    // The tree reused by ~20 of these questions (see TreeNode.main's `root`).
    // A fresh copy each call, since several questions mutate their input.
    private static TreeNode sampleRoot() {
        return TreeUtil.buildTree(new Integer[]{15, 10, 23, null, 9, 18, 28, 2});
    }

    private static TreeNode sampleRoot2() {
        return TreeUtil.buildTree(new Integer[]{33, 10, 23, 7, 9, 18, 5, 1});
    }

    // =========================================================================
    // Q001-Q003 - basic recursive traversals
    // =========================================================================

    @Test
    void q001_InorderTraversal() {
        header(Q001_InorderTraversal.class);
        TreeNode root = sampleRoot();
        List<Integer> actual = new Q001_InorderTraversal().inorderTraversal(root);
        System.out.println("inorderTraversal -> " + actual);
        assertEquals(List.of(10, 2, 9, 15, 18, 23, 28), actual);
    }

    @Test
    void q002_PreOrderTraversal() {
        header(Q002_PreOrderTraversal.class);
        TreeNode root = sampleRoot();
        List<Integer> actual = new Q002_PreOrderTraversal().preorderTraversal(root);
        System.out.println("preorderTraversal -> " + actual);
        assertEquals(List.of(15, 10, 9, 2, 23, 18, 28), actual);
    }

    @Test
    void q003_PostOrderTraversal() {
        header(Q003_PostOrderTraversal.class);
        TreeNode root = sampleRoot();
        List<Integer> actual = new Q003_PostOrderTraversal().postOrderTraversal(root);
        System.out.println("postOrderTraversal -> " + actual);
        assertEquals(List.of(2, 9, 10, 18, 28, 23, 15), actual);
    }

    // =========================================================================
    // Q004-Q009 - simple structural queries
    // =========================================================================

    @Test
    void q004_HeightOfBinaryTree() {
        header(Q004_HeightOfBinaryTree.class);
        TreeNode root = sampleRoot();
        int actual = new Q004_HeightOfBinaryTree().findHeightOfBinaryTree(root);
        System.out.println("findHeightOfBinaryTree -> " + actual);
        assertEquals(4, actual);
    }

    @Test
    void q005_PrintNodeAtKthLevelFromTop() {
        header(Q005_PrintNodeAtKthLevelFromTop.class);
        TreeNode root = sampleRoot();
        List<Integer> actual = new Q005_PrintNodeAtKthLevelFromTop().getNodeAtKLevel(root, 2);
        System.out.println("getNodeAtKLevel(root, 2) -> " + actual);
        assertEquals(List.of(10, 23), actual);
    }

    @Test
    void q006_LevelOrderTraversalLineByLine() {
        header(Q006_LevelOrderTraversalLineByLine.class);
        TreeNode root = sampleRoot();
        List<List<Integer>> actual = new Q006_LevelOrderTraversalLineByLine().getLevelOrderTraversalLineByLine(root);
        System.out.println("getLevelOrderTraversalLineByLine -> " + actual);
        assertEquals(List.of(List.of(15), List.of(10, 23), List.of(9, 18, 28), List.of(2)), actual);
    }

    @Test
    void q007_SizeOfBinaryTree() {
        header(Q007_SizeOfBinaryTree.class);
        TreeNode root = sampleRoot();
        int actual = new Q007_SizeOfBinaryTree().findTotalNodesOfBinaryTree(root);
        System.out.println("findTotalNodesOfBinaryTree -> " + actual);
        assertEquals(7, actual);
    }

    @Test
    void q008_MaximumInBinaryTree() {
        header(Q008_MaximumInBinaryTree.class);
        TreeNode root = sampleRoot();
        int actual = new Q008_MaximumInBinaryTree().getLargetNodeByValue(root);
        System.out.println("getLargetNodeByValue -> " + actual);
        assertEquals(28, actual);
    }

    @Test
    void q009_LeftViewOfBinaryTree() {
        header(Q009_LeftViewOfBinaryTree.class);
        TreeNode root = sampleRoot();
        List<Integer> actual = new Q009_LeftViewOfBinaryTree().getLeftViewOfBinaryTree(root);
        System.out.println("getLeftViewOfBinaryTree -> " + actual);
        assertEquals(List.of(15, 10, 9, 2), actual);
    }

    // =========================================================================
    // Q010 - Children Sum Property (2 examples)
    // =========================================================================

    @Test
    void q010_ChildrenSumInABinaryTree() {
        header(Q010_ChildrenSumInABinaryTree.class);
        Q010_ChildrenSumInABinaryTree q = new Q010_ChildrenSumInABinaryTree();
        assertAll("Q010_ChildrenSumInABinaryTree",
                () -> {
                    boolean actual = q.isSumProperty(sampleRoot());
                    System.out.println("Example A (sampleRoot): isSumProperty -> " + actual);
                    assertFalse(actual, "Example A");
                },
                () -> {
                    boolean actual = q.isSumProperty(sampleRoot2());
                    System.out.println("Example B (sampleRoot2): isSumProperty -> " + actual);
                    assertFalse(actual, "Example B");
                }
        );
    }

    // =========================================================================
    // Q011-Q013
    // =========================================================================

    @Test
    void q011_CheckForBalancedBinaryTree() {
        header(Q011_CheckForBalancedBinaryTree.class);
        TreeNode root = sampleRoot();
        boolean actual = new Q011_CheckForBalancedBinaryTree().isBalancedTree(root);
        System.out.println("isBalancedTree -> " + actual);
        // Unbalanced at node 10: left subtree height 0, right subtree (via 9->2) height 2.
        assertFalse(actual);
    }

    @Test
    void q012_MaxWidthInBinaryTree() {
        header(Q012_MaxWidthInBinaryTree.class);
        TreeNode root = sampleRoot();
        int actual = new Q012_MaxWidthInBinaryTree().getMaxWidth(root);
        System.out.println("getMaxWidth -> " + actual);
        assertEquals(3, actual);
    }

    @Test
    void q013_FindPathFromRootToAnyNode() {
        header(Q013_FindPathFromRootToAnyNode.class);
        TreeNode root = sampleRoot();
        List<Integer> actual = new Q013_FindPathFromRootToAnyNode().getPath(root, 2);
        System.out.println("getPath(root, 2) -> " + actual);
        assertEquals(List.of(15, 10, 9, 2), actual);
    }

    // =========================================================================
    // Q014 - Lowest Common Ancestor
    // =========================================================================

    @Test
    void q014_LowestCommonAncestor_LCA() {
        header(Q014_LowestCommonAncestor_LCA.class);
        TreeNode root2 = sampleRoot2();
        int n1 = 1, n2 = 9;
        int actual = new Q014_LowestCommonAncestor_LCA().getLCA(root2, n1, n2);
        System.out.println("getLCA(root2, " + n1 + ", " + n2 + ") -> " + actual);
        assertEquals(10, actual);
    }

    @Test
    void q015_RightViewOfBinaryTree() {
        header(Q015_RightViewOfBinaryTree.class);
        TreeNode root = sampleRoot();
        List<Integer> actual = new Q015_RightViewOfBinaryTree().getRightViewOfBinaryTree(root);
        System.out.println("getRightViewOfBinaryTree -> " + actual);
        assertEquals(List.of(15, 23, 28, 2), actual);
    }

    // =========================================================================
    // Q016-Q018 - iterative traversals (must match Q001/Q002/Q003)
    // =========================================================================

    @Test
    void q016_IterativeInorderTraversal() {
        header(Q016_IterativeInorderTraversal.class);
        TreeNode root = sampleRoot();
        List<Integer> actual = new Q016_IterativeInorderTraversal().inorderTraversalIterative(root);
        System.out.println("inorderTraversalIterative -> " + actual);
        assertEquals(List.of(10, 2, 9, 15, 18, 23, 28), actual);
    }

    @Test
    void q017_IterativePreorderTraversal() {
        header(Q017_IterativePreorderTraversal.class);
        TreeNode root = sampleRoot();
        List<Integer> actual = new Q017_IterativePreorderTraversal().preorderTraversalIterative(root);
        System.out.println("preorderTraversalIterative -> " + actual);
        assertEquals(List.of(15, 10, 9, 2, 23, 18, 28), actual);
    }

    @Test
    void q018_IterativePostorderTraversal() {
        header(Q018_IterativePostorderTraversal.class);
        TreeNode root = sampleRoot();
        List<Integer> actual = new Q018_IterativePostorderTraversal().postorderTraversalIterative(root);
        System.out.println("postorderTraversalIterative -> " + actual);
        assertEquals(List.of(2, 9, 10, 18, 28, 23, 15), actual);
    }

    // =========================================================================
    // Q019 - Zigzag level order
    // =========================================================================

    @Test
    void q019_ZigzagLevelOrderTraversal() {
        header(Q019_ZigzagLevelOrderTraversal.class);
        TreeNode zigzagTree = TreeUtil.buildTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        List<List<Integer>> actual = new Q019_ZigzagLevelOrderTraversal().zigzagLevelOrder(zigzagTree);
        System.out.println("zigzagLevelOrder -> " + actual);
        assertEquals(List.of(List.of(3), List.of(20, 9), List.of(15, 7)), actual);
    }

    // =========================================================================
    // Q020 - Boundary traversal
    // =========================================================================

    @Test
    void q020_BoundaryTraversal() {
        header(Q020_BoundaryTraversal.class);
        TreeNode root = sampleRoot();
        List<Integer> actual = new Q020_BoundaryTraversal().boundaryTraversal(root);
        System.out.println("boundaryTraversal -> " + actual);
        // root, left boundary (non-leaf) top-down, leaves left-to-right, right boundary (non-leaf) bottom-up.
        assertEquals(List.of(15, 10, 9, 2, 18, 28, 23), actual);
    }

    // =========================================================================
    // Q021 - Vertical order traversal
    // =========================================================================

    @Test
    void q021_VerticalOrderTraversal() {
        header(Q021_VerticalOrderTraversal.class);
        TreeNode verticalTree = TreeUtil.buildTree(new Integer[]{3, 9, 8, 4, 0, 1, 7});
        List<List<Integer>> actual = new Q021_VerticalOrderTraversal().verticalTraversal(verticalTree);
        System.out.println("verticalTraversal -> " + actual);
        assertEquals(List.of(List.of(4), List.of(9), List.of(3, 0, 1), List.of(8), List.of(7)), actual);
    }

    // =========================================================================
    // Q022-Q024 - top view, bottom view, diagonal traversal
    // =========================================================================

    @Test
    void q022_TopViewOfBinaryTree() {
        header(Q022_TopViewOfBinaryTree.class);
        TreeNode root = sampleRoot();
        List<Integer> actual = new Q022_TopViewOfBinaryTree().topView(root);
        System.out.println("topView -> " + actual);
        assertEquals(List.of(10, 15, 23, 28), actual);
    }

    @Test
    void q023_BottomViewOfBinaryTree() {
        header(Q023_BottomViewOfBinaryTree.class);
        TreeNode root = sampleRoot();
        List<Integer> actual = new Q023_BottomViewOfBinaryTree().bottomView(root);
        System.out.println("bottomView -> " + actual);
        assertEquals(List.of(2, 18, 23, 28), actual);
    }

    @Test
    void q024_DiagonalTraversal() {
        header(Q024_DiagonalTraversal.class);
        TreeNode root = sampleRoot();
        List<Integer> actual = new Q024_DiagonalTraversal().diagonalTraversal(root);
        System.out.println("diagonalTraversal -> " + actual);
        assertEquals(List.of(15, 23, 28, 10, 9, 18, 2), actual);
    }

    // =========================================================================
    // Q025 / Q026 - construct tree from two traversals
    // =========================================================================

    @Test
    void q025_ConstructTreeFromPreorderAndInorder() {
        header(Q025_ConstructTreeFromPreorderAndInorder.class);
        int[] preorderArr = {3, 9, 20, 15, 7};
        int[] inorderArr = {9, 3, 15, 20, 7};
        TreeNode actual = new Q025_ConstructTreeFromPreorderAndInorder().buildTreeFromPreIn(preorderArr, inorderArr);
        System.out.println("buildTreeFromPreIn -> " + TreeUtil.printLevelOrder(actual));
        assertSameShape(TreeUtil.buildTree(new Integer[]{3, 9, 20, null, null, 15, 7}), actual);
    }

    @Test
    void q026_ConstructTreeFromInorderAndPostorder() {
        header(Q026_ConstructTreeFromInorderAndPostorder.class);
        int[] inorderArr = {9, 3, 15, 20, 7};
        int[] postorderArr = {9, 15, 7, 20, 3};
        TreeNode actual = new Q026_ConstructTreeFromInorderAndPostorder().buildTreeFromInPost(inorderArr, postorderArr);
        System.out.println("buildTreeFromInPost -> " + TreeUtil.printLevelOrder(actual));
        assertSameShape(TreeUtil.buildTree(new Integer[]{3, 9, 20, null, null, 15, 7}), actual);
    }

    // =========================================================================
    // Q027 - Serialize / deserialize (round-trip property)
    // =========================================================================

    @Test
    void q027_SerializeAndDeserializeBinaryTree() {
        header(Q027_SerializeAndDeserializeBinaryTree.class);
        TreeNode root = sampleRoot();
        Q027_SerializeAndDeserializeBinaryTree q = new Q027_SerializeAndDeserializeBinaryTree();
        String serialized = q.serialize(root);
        System.out.println("serialize -> " + serialized);
        TreeNode deserialized = q.deserialize(serialized);
        System.out.println("deserialize -> " + TreeUtil.printLevelOrder(deserialized));
        assertSameShape(root, deserialized);
    }

    // =========================================================================
    // Q028 - Morris inorder traversal (must match Q001)
    // =========================================================================

    @Test
    void q028_MorrisInorderTraversal() {
        header(Q028_MorrisInorderTraversal.class);
        TreeNode root = sampleRoot();
        List<Integer> actual = new Q028_MorrisInorderTraversal().morrisInorderTraversal(root);
        System.out.println("morrisInorderTraversal -> " + actual);
        assertEquals(List.of(10, 2, 9, 15, 18, 23, 28), actual);
    }

    // =========================================================================
    // Q029 / Q030 - diameter, max path sum
    // =========================================================================

    @Test
    void q029_DiameterOfBinaryTree() {
        header(Q029_DiameterOfBinaryTree.class);
        TreeNode diameterTree = TreeUtil.buildTree(new Integer[]{1, 4, 3, null, 5});
        int actual = new Q029_DiameterOfBinaryTree().diameterOfBinaryTree(diameterTree);
        System.out.println("diameterOfBinaryTree -> " + actual);
        assertEquals(3, actual);
    }

    @Test
    void q030_BinaryTreeMaximumPathSum() {
        header(Q030_BinaryTreeMaximumPathSum.class);
        TreeNode maxPathSumTree = TreeUtil.buildTree(new Integer[]{-10, -20, -50});
        int actual = new Q030_BinaryTreeMaximumPathSum().maxPathSum(maxPathSumTree);
        System.out.println("maxPathSum -> " + actual);
        assertEquals(-10, actual);
    }

    // =========================================================================
    // Q031-Q033 - tree comparisons
    // =========================================================================

    @Test
    void q031_SameTree() {
        header(Q031_SameTree.class);
        TreeNode a = TreeUtil.buildTree(new Integer[]{1, null, 3});
        TreeNode b = TreeUtil.buildTree(new Integer[]{1, null, 2});
        boolean actual = new Q031_SameTree().isSameTree(a, b);
        System.out.println("isSameTree -> " + actual);
        assertFalse(actual);
    }

    @Test
    void q032_SymmetricTree() {
        header(Q032_SymmetricTree.class);
        TreeNode symmetricTree = TreeUtil.buildTree(new Integer[]{1, 2, 2, 3, 4, 4, 3});
        boolean actual = new Q032_SymmetricTree().isSymmetric(symmetricTree);
        System.out.println("isSymmetric -> " + actual);
        assertTrue(actual);
    }

    @Test
    void q033_SubtreeOfAnotherTree() {
        header(Q033_SubtreeOfAnotherTree.class);
        TreeNode bigTree = TreeUtil.buildTree(new Integer[]{3, 4, 5, 1, 2});
        TreeNode subTree = TreeUtil.buildTree(new Integer[]{4, 1});
        boolean actual = new Q033_SubtreeOfAnotherTree().isSubtree(bigTree, subTree);
        System.out.println("isSubtree -> " + actual);
        // bigTree's node-4 subtree has an extra right child (2) that subTree lacks, so no exact match.
        assertFalse(actual);
    }

    // =========================================================================
    // Q034 - Flatten binary tree to linked list (property: right-chain == preorder)
    // =========================================================================

    @Test
    void q034_FlattenBinaryTreeToLinkedList() {
        header(Q034_FlattenBinaryTreeToLinkedList.class);
        TreeNode flattenTree = TreeUtil.buildTree(new Integer[]{1, 2, 5, 3, 4, null, 6});
        new Q034_FlattenBinaryTreeToLinkedList().flatten(flattenTree);
        System.out.println("flatten -> " + TreeUtil.printLevelOrder(flattenTree));
        assertFlattenedRightChain(List.of(1, 2, 3, 4, 5, 6), flattenTree);
    }

    // =========================================================================
    // Q035 - Completeness check (2 examples)
    // =========================================================================

    @Test
    void q035_CheckCompletenessOfBinaryTree() {
        header(Q035_CheckCompletenessOfBinaryTree.class);
        Q035_CheckCompletenessOfBinaryTree q = new Q035_CheckCompletenessOfBinaryTree();
        assertAll("Q035_CheckCompletenessOfBinaryTree",
                () -> {
                    TreeNode completeTree = TreeUtil.buildTree(new Integer[]{1, 2, 3, 4, 5, 6});
                    boolean actual = q.isCompleteTree(completeTree);
                    System.out.println("Example A (complete): isCompleteTree -> " + actual);
                    assertTrue(actual, "Example A");
                },
                () -> {
                    TreeNode incompleteTree = TreeUtil.buildTree(new Integer[]{1, 2, 3, 4, 5, null, 7});
                    boolean actual = q.isCompleteTree(incompleteTree);
                    System.out.println("Example B (incomplete): isCompleteTree -> " + actual);
                    assertFalse(actual, "Example B");
                }
        );
    }

    // =========================================================================
    // Q036 / Q037 - distance K, min time to burn
    // =========================================================================

    @Test
    void q036_AllNodesDistanceKInBinaryTree() {
        header(Q036_AllNodesDistanceKInBinaryTree.class);
        TreeNode distanceKTree = TreeUtil.buildTree(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        TreeNode target = distanceKTree.left; // node with value 5
        List<Integer> actual = new Q036_AllNodesDistanceKInBinaryTree().distanceK(distanceKTree, target, 2);
        System.out.println("distanceK(target=5, K=2) -> " + actual);
        assertUnorderedInts(List.of(7, 4, 1), actual, "Q036");
    }

    @Test
    void q037_MinTimeToBurnBinaryTree() {
        header(Q037_MinTimeToBurnBinaryTree.class);
        TreeNode burningTree = TreeUtil.buildTree(new Integer[]{1, 2, 3, 4, 5, null, null, null, null, 6, 7});
        int actual = new Q037_MinTimeToBurnBinaryTree().minTimeToBurnTree(burningTree, 5);
        System.out.println("minTimeToBurnTree(target=5) -> " + actual);
        // Farthest node from 5 is 3, at distance 3 (5->2->1->3).
        assertEquals(3, actual);
    }

    // =========================================================================
    // Q038-Q040 - path sum, path sum III, leaf/single-child counts
    // =========================================================================

    @Test
    void q038_PathSum() {
        header(Q038_PathSum.class);
        TreeNode pathSumTree = TreeUtil.buildTree(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1});
        int targetSum = 20;
        boolean actual = new Q038_PathSum().hasPathSum(pathSumTree, targetSum);
        System.out.println("hasPathSum(" + targetSum + ") -> " + actual);
        // Root-to-leaf sums are 27, 22, 26, 18 - none equal 20.
        assertFalse(actual);
    }

    @Test
    void q039_PathSumIII() {
        header(Q039_PathSumIII.class);
        TreeNode pathSumIIITree = TreeUtil.buildTree(new Integer[]{10, 5, -3, 3, 2, null, 11, 3, -2, null, 1});
        int target = 8;
        int actual = new Q039_PathSumIII().pathSumIII(pathSumIIITree, target);
        System.out.println("pathSumIII(" + target + ") -> " + actual);
        // Matching downward paths: [5,3], [5,2,1], [-3,11].
        assertEquals(3, actual);
    }

    @Test
    void q040_CountLeafAndSingleChildNodes() {
        header(Q040_CountLeafAndSingleChildNodes.class);
        TreeNode root = sampleRoot();
        Q040_CountLeafAndSingleChildNodes q = new Q040_CountLeafAndSingleChildNodes();
        int leafCount = q.countLeafNodes(root);
        int singleChildCount = q.countSingleChildNodes(root);
        System.out.println("countLeafNodes -> " + leafCount + ", countSingleChildNodes -> " + singleChildCount);
        // Leaves: 2, 18, 28. Single-child: 10 (right only), 9 (left only).
        assertAll("Q040_CountLeafAndSingleChildNodes",
                () -> assertEquals(3, leafCount, "leaf count"),
                () -> assertEquals(2, singleChildCount, "single-child count")
        );
    }

    // =========================================================================
    // Q041-Q048 - BST questions
    // =========================================================================

    @Test
    void q041_ValidateBinarySearchTree() {
        header(Q041_ValidateBinarySearchTree.class);
        TreeNode invalidBST = TreeUtil.buildTree(new Integer[]{5, 1, 4, null, null, 3, 6});
        boolean actual = new Q041_ValidateBinarySearchTree().isValidBST(invalidBST);
        System.out.println("isValidBST -> " + actual);
        // Node 4 (right of root 5) has left child 3, which is < 5 - violates BST ordering globally.
        assertFalse(actual);
    }

    @Test
    void q042_KthSmallestElementInBST() {
        header(Q042_KthSmallestElementInBST.class);
        TreeNode kthSmallestTree = TreeUtil.buildTree(new Integer[]{3, 1, 4, null, 2});
        int actual = new Q042_KthSmallestElementInBST().kthSmallest(kthSmallestTree, 1);
        System.out.println("kthSmallest(k=1) -> " + actual);
        assertEquals(1, actual);
    }

    @Test
    void q043_InsertIntoBST() {
        header(Q043_InsertIntoBST.class);
        TreeNode insertBSTTree = TreeUtil.buildTree(new Integer[]{4, 2, 7, 1, 3});
        int val = 10;
        TreeNode actual = new Q043_InsertIntoBST().insertIntoBST(insertBSTTree, val);
        System.out.println("insertIntoBST(" + val + ") -> " + TreeUtil.printLevelOrder(actual));
        assertSameShape(TreeUtil.buildTree(new Integer[]{4, 2, 7, 1, 3, null, 10}), actual);
    }

    @Test
    void q044_DeleteNodeInBST() {
        header(Q044_DeleteNodeInBST.class);
        TreeNode deleteBSTTree = TreeUtil.buildTree(new Integer[]{5, 3, 6, 2, 4, null, 7});
        int key = 3;
        TreeNode actual = new Q044_DeleteNodeInBST().deleteNode(deleteBSTTree, key);
        System.out.println("deleteNode(" + key + ") -> " + TreeUtil.printLevelOrder(actual));
        // Node 3 has two children (2, 4) - replaced by its inorder successor, 4.
        assertSameShape(TreeUtil.buildTree(new Integer[]{5, 4, 6, 2, null, null, 7}), actual);
    }

    @Test
    void q045_SortedArrayToBST() {
        header(Q045_SortedArrayToBST.class);
        int[] sortedArray = {-10, -3, 0, 5, 9};
        TreeNode actual = new Q045_SortedArrayToBST().sortedArrayToBST(sortedArray);
        System.out.println("sortedArrayToBST -> " + TreeUtil.printLevelOrder(actual));
        // Multiple balanced BSTs are valid from the same sorted array - check the property
        // (inorder reproduces the sorted input, height is a balanced one) rather than one exact shape.
        assertValidBalancedBST(sortedArray, actual);
    }

    @Test
    void q046_InorderSuccessorInBST() {
        header(Q046_InorderSuccessorInBST.class);
        TreeNode successorTree = TreeUtil.buildTree(new Integer[]{2, 1, 3});
        TreeNode p = successorTree.left; // node with value 1
        TreeNode actual = new Q046_InorderSuccessorInBST().inorderSuccessor(successorTree, p);
        System.out.println("inorderSuccessor(1) -> " + (actual == null ? "null" : actual.val));
        assertNotNull(actual);
        assertEquals(2, actual.val);
    }

    @Test
    void q047_LowestCommonAncestorInBST() {
        header(Q047_LowestCommonAncestorInBST.class);
        TreeNode lcaBSTTree = TreeUtil.buildTree(new Integer[]{6, 2, 8, 0, 4, 7, 9, null, null, 3, 5});
        int p = 2, q = 0;
        int actual = new Q047_LowestCommonAncestorInBST().lowestCommonAncestorBST(lcaBSTTree, p, q);
        System.out.println("lowestCommonAncestorBST(" + p + ", " + q + ") -> " + actual);
        // 0 is a direct child of 2, so 2 is its own ancestor here.
        assertEquals(2, actual);
    }

    @Test
    void q048_TwoSumIV_InputIsBST() {
        header(Q048_TwoSumIV_InputIsBST.class);
        TreeNode twoSumBSTTree = TreeUtil.buildTree(new Integer[]{5, 3, 6, 2, 4, null, 7});
        int k = 9;
        boolean actual = new Q048_TwoSumIV_InputIsBST().findTarget(twoSumBSTTree, k);
        System.out.println("findTarget(" + k + ") -> " + actual);
        // 5 + 4 = 9 (two distinct nodes).
        assertTrue(actual);
    }

    // =========================================================================
    // Q049 / Q050 - BST to DLL, populate next pointers
    // =========================================================================

    @Test
    void q049_ConvertBSTToSortedDoublyLinkedList() {
        header(Q049_ConvertBSTToSortedDoublyLinkedList.class);
        TreeNode dllTree = TreeUtil.buildTree(new Integer[]{10, 5, 20, null, null, 15, 30});
        TreeNode head = new Q049_ConvertBSTToSortedDoublyLinkedList().bstToSortedDLL(dllTree);
        System.out.println("bstToSortedDLL head -> " + (head == null ? "null" : head.val));
        assertNotNull(head);
        List<Integer> values = new ArrayList<>();
        for (TreeNode cur = head; cur != null && values.size() <= 5; cur = cur.right) {
            values.add(cur.val);
        }
        assertEquals(List.of(5, 10, 15, 20, 30), values);
    }

    @Test
    void q050_PopulateNextRightPointers() {
        header(Q050_PopulateNextRightPointers.class);
        Q050_PopulateNextRightPointers.Node root = new Q050_PopulateNextRightPointers.Node(1);
        root.left = new Q050_PopulateNextRightPointers.Node(2);
        root.right = new Q050_PopulateNextRightPointers.Node(3);
        root.left.left = new Q050_PopulateNextRightPointers.Node(4);
        root.left.right = new Q050_PopulateNextRightPointers.Node(5);
        root.right.left = new Q050_PopulateNextRightPointers.Node(6);
        root.right.right = new Q050_PopulateNextRightPointers.Node(7);

        Q050_PopulateNextRightPointers.Node connected = new Q050_PopulateNextRightPointers().connect(root);
        System.out.println("connect -> root val=" + (connected == null ? "null" : connected.val));
        assertSame(root, connected);
        assertAll("Q050_PopulateNextRightPointers",
                () -> assertNull(root.next, "1.next"),
                () -> assertEquals(3, root.left.next.val, "2.next"),
                () -> assertNull(root.right.next, "3.next"),
                () -> assertEquals(5, root.left.left.next.val, "4.next"),
                () -> assertEquals(6, root.left.right.next.val, "5.next"),
                () -> assertEquals(7, root.right.left.next.val, "6.next"),
                () -> assertNull(root.right.right.next, "7.next")
        );
    }

    // =========================================================================
    // Q051 - Search in a BST
    // =========================================================================

    @Test
    void q051_SearchInBST() {
        header(Q051_SearchInBST.class);
        TreeNode searchBSTTree = TreeUtil.buildTree(new Integer[]{4, 2, 7, 1, 3});
        TreeNode actual = new Q051_SearchInBST().searchBST(searchBSTTree, 2);
        System.out.println("searchBST(2) -> " + TreeUtil.printLevelOrder(actual));
        assertSameShape(TreeUtil.buildTree(new Integer[]{2, 1, 3}), actual);
    }

    // =========================================================================
    // assertion helpers - domain-specific to trees, so kept local rather
    // than in the shared QuestionDriverSupport.
    // =========================================================================

    /** Structural equality via level-order representation - simpler than a node-by-node walk. */
    private static void assertSameShape(TreeNode expected, TreeNode actual) {
        assertEquals(TreeUtil.printLevelOrder(expected), TreeUtil.printLevelOrder(actual));
    }

    private static void assertFlattenedRightChain(List<Integer> expectedPreorder, TreeNode root) {
        List<Integer> actual = new ArrayList<>();
        for (TreeNode cur = root; cur != null; cur = cur.right) {
            assertNull(cur.left, "node " + cur.val + " should have no left child after flattening");
            actual.add(cur.val);
        }
        assertEquals(expectedPreorder, actual);
    }

    private static void assertValidBalancedBST(int[] sortedValues, TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        collectInorder(root, inorder);
        assertEquals(Arrays.stream(sortedValues).boxed().collect(Collectors.toList()), inorder,
                "inorder traversal should reproduce the sorted input");
        int n = sortedValues.length;
        int maxBalancedHeight = (int) Math.ceil(Math.log(n + 1) / Math.log(2)) + 1;
        assertTrue(height(root) <= maxBalancedHeight,
                "tree should be height-balanced (height " + height(root) + " > " + maxBalancedHeight + " for n=" + n + ")");
    }

    private static void collectInorder(TreeNode node, List<Integer> out) {
        if (node == null) return;
        collectInorder(node.left, out);
        out.add(node.val);
        collectInorder(node.right, out);
    }

    private static int height(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }
}
