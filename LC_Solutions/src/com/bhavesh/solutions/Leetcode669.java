package com.bhavesh.solutions;

public class Leetcode669 {
	public TreeNode trimBST(TreeNode root, int low, int high) {
		if (root == null) {
			return null;
		}

		/*
		 * Main Idea is that in a BST, if the root val < low it implies, all the childer
		 * on left will also be less than low, therefore we can invalidate entire left
		 * side. Similarly if the root val > high, we can invalidate the entire right
		 * side
		 */

		if (root.val > high) {
			return trimBST(root.left, low, high);
		}
		if (root.val < low) {
			return trimBST(root.right, low, high);
		}
		root.left = trimBST(root.left, low, high);
		root.right = trimBST(root.right, low, high);
		return root;
	}
}