package com.bhavesh.solutions;

import java.util.HashMap;

public class Leetcode106 {
	int postIdx;
	HashMap<Integer, Integer> map;

	public TreeNode buildTree(int[] inorder, int[] postorder) {
		// PreIdx keeps track of current index of root on the preorder traversal map
		postIdx = postorder.length - 1;

		// Build a map of inoder values and their indexes on the inorder array to get
		// indexex of values in o(1) time
		map = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}

		return helper(postorder, inorder, 0, inorder.length - 1);
	}

	public TreeNode helper(int postorder[], int inorder[], int stIn, int endIn) {
		if (stIn > endIn) {
			return null;
		}

		int root_val = postorder[postIdx--];
		TreeNode root = new TreeNode(root_val);
		int ind = map.get(root_val);
		root.right = helper(postorder, inorder, ind + 1, endIn);
		root.left = helper(postorder, inorder, stIn, ind - 1);

		return root;

	}

}