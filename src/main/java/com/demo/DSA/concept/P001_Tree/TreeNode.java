package com.demo.DSA.concept.P001_Tree;

import java.util.List;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtil.buildTree(new Integer[]{15, 10, 23, null, 9, 18, 28, 2});
        TreeUtil.printTree(root);

        List<Integer> inorderTraversalList = new Q001_InorderTraversal().inorderTraversal(root);
        System.out.println(inorderTraversalList); // expected: [7, 10, 9, 15, 18, 23, 28]

        List<Integer> preOrderList  = new Q002_PreOrderTraversal().preorderTraversal(root);
        System.out.println(preOrderList);

        List<Integer> postOrderList = new Q003_PostOrderTraversal().postOrderTraversal(root);
        System.out.println(postOrderList);

        int heightOfTree = new Q004_HeightOfBinaryTree().findHeightOfBinaryTree(root);
        System.out.println("Height: "+heightOfTree);

        List<Integer> kthLevelNode = new Q005_PrintNodeAtKthLevelFromTop().getNodeAtKLevel(root,2);
        System.out.println("Nodes at 2nd level are : "+ kthLevelNode);

        List<List<Integer>> listOfNodesAtEachLevel = new Q006_LevelOrderTraversalLineByLine().getLevelOrderTraversalLineByLine(root);
        System.out.println("list Of Nodes At Each Level: "+ listOfNodesAtEachLevel);

        System.out.println("total nodes : "+ new Q007_SizeOfBinaryTree().findTotalNodesOfBinaryTree(root));
        System.out.println("Largest node by value : "+ new Q008_MaximumInBinaryTree().getLargetNodeByValue(root));

        List<Integer> leftView = new Q009_LeftViewOfBinaryTree().getLeftViewOfBinaryTree(root);
        System.out.println("Left view of binary tree is "+ leftView);

        System.out.println("Is it satisfies the Children Sum Property : "+ new Q010_ChildrenSumInABinaryTree().isSumProperty(root));

        TreeNode root2 = TreeUtil.buildTree(new Integer[]{33, 10, 23, 7, 9, 18, 5, 1});
        TreeUtil.printTree(root2);

        System.out.println("Is it satisfies the Children Sum Property : "+ new Q010_ChildrenSumInABinaryTree().isSumProperty(root2));

        System.out.println("IS Binary tree balanced: "+new Q011_CheckForBalancedBinaryTree().isBalancedTree(root));
        System.out.println("Maximum width of binary tree1 is : "+ new Q012_MaxWidthInBinaryTree().getMaxWidth(root));
        System.out.println("find path from root to any node(target) in tree : "+ new Q013_FindPathFromRootToAnyNode().getPath(root, 2));

        int n1=1, n2=9;
        int lca = new Q014_LowestCommonAncestor_LCA().getLCA(root2, n1, n2);
        if(lca==-1)
            System.out.println("Lowest common Ancestor for "+n1+" & "+n2+" is not available");
        else
            System.out.println("Lowest common Ancestor for "+n1+" & "+n2+" is "+ lca);

        List<Integer> rightView = new Q015_RightViewOfBinaryTree().getRightViewOfBinaryTree(root);
        System.out.println("Right view of binary tree is "+ rightView);

        // ---- Q016-Q018: Iterative traversals (on shared `root`) ----
        System.out.println("Inorder (iterative): " + new Q016_IterativeInorderTraversal().inorderTraversalIterative(root));
        System.out.println("Preorder (iterative): " + new Q017_IterativePreorderTraversal().preorderTraversalIterative(root));
        System.out.println("Postorder (iterative): " + new Q018_IterativePostorderTraversal().postorderTraversalIterative(root));

        // ---- Q019: Zigzag level order traversal ----
        TreeNode zigzagTree = TreeUtil.buildTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println("Zigzag level order: " + new Q019_ZigzagLevelOrderTraversal().zigzagLevelOrder(zigzagTree));

        // ---- Q020: Boundary traversal (on shared `root`) ----
        System.out.println("Boundary traversal: " + new Q020_BoundaryTraversal().boundaryTraversal(root));

        // ---- Q021: Vertical order traversal ----
        TreeNode verticalTree = TreeUtil.buildTree(new Integer[]{3, 9, 8, 4, 0, 1, 7});
        System.out.println("\nVertical order traversal: " + new Q021_VerticalOrderTraversal().verticalTraversal(verticalTree));
        TreeUtil.printTree(verticalTree);


        // ---- Q022-Q024: Top view, bottom view, diagonal traversal (on shared `root`) ----
        System.out.println("\nQ022 Top view: " + new Q022_TopViewOfBinaryTree().topView(root));
        TreeUtil.printTree(root);
        System.out.println("Bottom view: " + new Q023_BottomViewOfBinaryTree().bottomView(root));
        System.out.println("Diagonal traversal: " + new Q024_DiagonalTraversal().diagonalTraversal(root));

        // ---- Q025: Construct tree from preorder + inorder ----
        int[] preorderArr = {3, 9, 20, 15, 7};
        int[] inorderArr = {9, 3, 15, 20, 7};
        TreeNode builtFromPreIn = new Q025_ConstructTreeFromPreorderAndInorder().buildTreeFromPreIn(preorderArr, inorderArr);
        System.out.println("Constructed tree from preorder+inorder (level order): " + TreeUtil.printLevelOrder(builtFromPreIn));

        // ---- Q026: Construct tree from inorder + postorder ----
        int[] inorderArr2 = {9, 3, 15, 20, 7};
        int[] postorderArr = {9, 15, 7, 20, 3};
        TreeNode builtFromInPost = new Q026_ConstructTreeFromInorderAndPostorder().buildTreeFromInPost(inorderArr2, postorderArr);
        System.out.println("Constructed tree from inorder+postorder (level order): " + TreeUtil.printLevelOrder(builtFromInPost));

        // ---- Q027: Serialize and deserialize (on shared `root`) ----
        Q027_SerializeAndDeserializeBinaryTree serDeser = new Q027_SerializeAndDeserializeBinaryTree();
        String serialized = serDeser.serialize(root);
        System.out.println("\nQ027A. Serialized tree: " + serialized);
        TreeUtil.printTree(root);
        TreeNode deserialized = serDeser.deserialize(serialized);
        System.out.println("\nQ027B. Deserialized tree (level order): " + TreeUtil.printLevelOrder(deserialized));
        TreeUtil.printTree(deserialized);


        // ---- Q028: Morris inorder traversal (on shared `root`) ----
        System.out.println("Morris inorder traversal: " + new Q028_MorrisInorderTraversal().morrisInorderTraversal(root));

        // ---- Q029: Diameter of binary tree ----
        TreeNode diameterTree = TreeUtil.buildTree(new Integer[]{1, 4, 3, null, 5});
        System.out.println("\nDiameter of binary tree: " + new Q029_DiameterOfBinaryTree().diameterOfBinaryTree(diameterTree));
        TreeUtil.printTree(diameterTree);

        // ---- Q030: Binary tree maximum path sum ----
        TreeNode maxPathSumTree = TreeUtil.buildTree(new Integer[]{-10, -20, -50});
        System.out.println("\nMax path sum: " + new Q030_BinaryTreeMaximumPathSum().maxPathSum(maxPathSumTree));
        TreeUtil.printTree(maxPathSumTree);
        // ---- Q031: Same tree ----
        TreeNode sameTreeA = TreeUtil.buildTree(new Integer[]{1, null, 3});
        TreeNode sameTreeB = TreeUtil.buildTree(new Integer[]{1, null, 2});
        System.out.println("Are trees same: " + new Q031_SameTree().isSameTree(sameTreeA, sameTreeB));

        // ---- Q032: Symmetric tree ----
        TreeNode symmetricTree = TreeUtil.buildTree(new Integer[]{1, 2, 2, 3, 4, 4, 3});
        System.out.println("\nQ032 Is symmetric tree: " + new Q032_SymmetricTree().isSymmetric(symmetricTree));
        TreeUtil.printTree(symmetricTree);


        // ---- Q033: Subtree of another tree ----
        TreeNode bigTree = TreeUtil.buildTree(new Integer[]{3, 4, 5, 1, 2});
        TreeNode subTree = TreeUtil.buildTree(new Integer[]{4, 1});
        System.out.println("\n Q033. Is subtree: " + new Q033_SubtreeOfAnotherTree().isSubtree(bigTree, subTree));
        TreeUtil.printTree(bigTree);
        TreeUtil.printTree(subTree);

        // ---- Q034: Flatten binary tree to linked list ----
        TreeNode flattenTree = TreeUtil.buildTree(new Integer[]{1, 2, 5, 3, 4, null, 6});
        new Q034_FlattenBinaryTreeToLinkedList().flatten(flattenTree);
        System.out.println("Flattened tree (level order, right-only chain expected): " + TreeUtil.printLevelOrder(flattenTree));

        // ---- Q035: Check completeness of a binary tree ----
        TreeNode completeTree = TreeUtil.buildTree(new Integer[]{1, 2, 3, 4, 5, 6});
        TreeNode incompleteTree = TreeUtil.buildTree(new Integer[]{1, 2, 3, 4, 5, null, 7});

        System.out.println("\nIs complete tree (example 1): " + new Q035_CheckCompletenessOfBinaryTree().isCompleteTree(completeTree));
        TreeUtil.printTree(completeTree);
        System.out.println("\nIs complete tree (example 2): " + new Q035_CheckCompletenessOfBinaryTree().isCompleteTree(incompleteTree));
        TreeUtil.printTree(incompleteTree);

        // ---- Q036: All nodes distance K in binary tree ----
        TreeNode distanceKTree = TreeUtil.buildTree(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        TreeNode targetNodeForK = distanceKTree.left; // node with value 5
        System.out.println("Nodes at distance K=2 from node 5: " + new Q036_AllNodesDistanceKInBinaryTree().distanceK(distanceKTree, targetNodeForK, 2));

        // ---- Q037: Minimum time to burn binary tree from target leaf ----
        TreeNode burningTree = TreeUtil.buildTree(new Integer[]{1, 2, 3, 4, 5, null, null, null, null, 6, 7});
        System.out.println("Min time to burn tree from target 5: " + new Q037_MinTimeToBurnBinaryTree().minTimeToBurnTree(burningTree, 5));

        // ---- Q038: Path sum ----
        TreeNode pathSumTree = TreeUtil.buildTree(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1});
        int tsum=20;
        System.out.println("\nQ0038. Has path sum "+tsum+" : " + new Q038_PathSum().hasPathSum(pathSumTree, tsum));
        TreeUtil.printTree(pathSumTree);

        // ---- Q039: Path sum III ----
        TreeNode pathSumIIITree = TreeUtil.buildTree(new Integer[]{10, 5, -3, 3, 2, null, 11, 3, -2, null, 1});
        System.out.println("Path sum III count for target 8: " + new Q039_PathSumIII().pathSumIII(pathSumIIITree, 8));

        // ---- Q040: Count leaf nodes and single-child nodes (on shared `root`) ----
        Q040_CountLeafAndSingleChildNodes q040 = new Q040_CountLeafAndSingleChildNodes();
        System.out.println("Leaf node count: " + q040.countLeafNodes(root));
        System.out.println("Single child node count: " + q040.countSingleChildNodes(root));

        // ---- Q041: Validate binary search tree ----
        TreeNode invalidBST = TreeUtil.buildTree(new Integer[]{5, 1, 4, null, null, 3, 6});
        TreeUtil.printTree(invalidBST);
        System.out.println("Is valid BST: " + new Q041_ValidateBinarySearchTree().isValidBST(invalidBST));

        // ---- Q042: Kth smallest element in a BST ----
        TreeNode kthSmallestTree = TreeUtil.buildTree(new Integer[]{3, 1, 4, null, 2});
        System.out.println("\n >> Q042 Kth(1) smallest in BST: " + new Q042_KthSmallestElementInBST().kthSmallest(kthSmallestTree, 1));
        TreeUtil.printTree(kthSmallestTree);


        // ---- Q043: Insert into a BST ----
        TreeNode insertBSTTree = TreeUtil.buildTree(new Integer[]{4, 2, 7, 1, 3});
        int val1=10;
        TreeNode afterInsert = new Q043_InsertIntoBST().insertIntoBST(insertBSTTree, val1);
        System.out.println("BST before inserting \n");
        TreeUtil.printTree(insertBSTTree);
        System.out.println("\nBST after inserting "+val1+" (level order): " + TreeUtil.printLevelOrder(afterInsert));
        TreeUtil.printTree(afterInsert);

        // ---- Q044: Delete node in a BST ----
        TreeNode deleteBSTTree = TreeUtil.buildTree(new Integer[]{5, 3, 6, 2, 4, null, 7});
        int deleteKey=3;
        System.out.println("\nBST before deleting "+deleteKey+" node: ");
        TreeUtil.printTree(deleteBSTTree);
        TreeNode afterDelete = new Q044_DeleteNodeInBST().deleteNode(deleteBSTTree, deleteKey);
        System.out.println("\nBST after deleting 3 (level order): " + TreeUtil.printLevelOrder(afterDelete));
        TreeUtil.printTree(afterDelete);


        // ---- Q045: Convert sorted array to BST ----
        int[] sortedArray = {-10, -3, 0, 5, 9};
        TreeNode balancedBST = new Q045_SortedArrayToBST().sortedArrayToBST(sortedArray);
        System.out.println("\nQ045Balanced BST from sorted array (level order): " + TreeUtil.printLevelOrder(balancedBST));
        TreeUtil.printTree(balancedBST);
        // ---- Q046: Inorder successor in BST ----
        TreeNode successorTree = TreeUtil.buildTree(new Integer[]{2, 1, 3});
        TreeNode pNode = successorTree.left; // node with value 1
        TreeNode successor = new Q046_InorderSuccessorInBST().inorderSuccessor(successorTree, pNode);
        System.out.println("Q046. Inorde˳r successor of 1: " + (successor == null ? "null" : successor.val));
        TreeUtil.printTree(successorTree);

        // ---- Q047: Lowest common ancestor of a BST ----
        TreeNode lcaBSTTree = TreeUtil.buildTree(new Integer[]{6, 2, 8, 0, 4, 7, 9, null, null, 3, 5});
        int p=2;
        int q=0;
        System.out.println("\nQ047 LCA in BST for "+p+" & "+q+": " + new Q047_LowestCommonAncestorInBST().lowestCommonAncestorBST(lcaBSTTree, p, q));
        TreeUtil.printTree(lcaBSTTree);
        // ---- Q048: Two Sum IV - Input is a BST ----
        TreeNode twoSumBSTTree = TreeUtil.buildTree(new Integer[]{5, 3, 6, 2, 4, null, 7});
        System.out.println("Two Sum IV (k=9): " + new Q048_TwoSumIV_InputIsBST().findTarget(twoSumBSTTree, 9));

        // ---- Q049: Convert BST to sorted doubly linked list ----
        TreeNode dllTree = TreeUtil.buildTree(new Integer[]{10, 5, 20, null, null, 15, 30});
        TreeNode dllHead = new Q049_ConvertBSTToSortedDoublyLinkedList().bstToSortedDLL(dllTree);
        System.out.println("BST to sorted DLL head: " + (dllHead == null ? "null" : dllHead.val));

        // ---- Q050: Populate next right pointers in each node ----
        Q050_PopulateNextRightPointers.Node perfectRoot = new Q050_PopulateNextRightPointers.Node(1);
        perfectRoot.left = new Q050_PopulateNextRightPointers.Node(2);
        perfectRoot.right = new Q050_PopulateNextRightPointers.Node(3);
        perfectRoot.left.left = new Q050_PopulateNextRightPointers.Node(4);
        perfectRoot.left.right = new Q050_PopulateNextRightPointers.Node(5);
        perfectRoot.right.left = new Q050_PopulateNextRightPointers.Node(6);
        perfectRoot.right.right = new Q050_PopulateNextRightPointers.Node(7);
        Q050_PopulateNextRightPointers.Node connectedRoot = new Q050_PopulateNextRightPointers().connect(perfectRoot);
        System.out.println("Populate next right pointers - root after connect: " + (connectedRoot == null ? "null" : connectedRoot.val));

        // ---- Q051: Search in a binary search tree ----
        TreeNode searchBSTTree = TreeUtil.buildTree(new Integer[]{4, 2, 7, 1, 3});
        TreeUtil.printTree(searchBSTTree);
        TreeNode found = new Q051_SearchInBST().searchBST(searchBSTTree, 2);

        System.out.println("Search BST for 2 (level order): " + TreeUtil.printLevelOrder(found));
        TreeUtil.printTree(found);
    }
}