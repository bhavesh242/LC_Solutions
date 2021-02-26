package com.bhavesh.solutions;

import java.util.HashMap;

public class Leetcode105 {
	int preIdx = 0;
	HashMap<Integer, Integer> map;

	public TreeNode buildTree(int[] preorder, int[] inorder) {

		// PreIdx keeps track of current index of root on the preorder traversal map
		preIdx = 0;

		// Build a map of inoder values and their indexes on the inorder array to get
		// indexex of values in o(1) time
		map = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}

		return helper(preorder, inorder, 0, inorder.length - 1);
	}

	public TreeNode helper(int[] preorder, int[] inorder, int startIn, int endIn) {

		if (preIdx >= preorder.length || startIn > endIn) {
			return null;
		}
		// Find out the root value from preorder array and preIndex
		int root_val = preorder[preIdx++];

		// Build the root node
		TreeNode root = new TreeNode(root_val);

		// Get the index of this root in the inorder array using the index map
		int idx = map.get(root_val);
		// Everything to the right of this index comprises the left subtree and
		// everything to the right comprises the right subtree
		// We need not worry about pre Index as we are building the tree in an inorder
		// fashion, so the order of getting root vals is maintained
		root.left = helper(preorder, inorder, startIn, idx - 1);
		root.right = helper(preorder, inorder, idx + 1, endIn);

		// Once left and right are set, return root;
		return root;

	}

	// Another method instead of using the preIdx gloabal variable is this :

	// We know when preIdx is the index of the root, the left node's value will be
	// at index will be on the preIdx+1 in the preorder array
	// The right side will be at the index after all the left subtree indexes,
	// preIdx + idx-startIn

	// root.left = helper(preorder, inorder, startIn, idx-1, preIdx+1);
	// root.right = helper(preorder, inorder, idx+1, endIn, preIdx + idx-startIn+1);

}