package com.bhavesh.solutions;

public class Leetcode114 {
	public void flatten(TreeNode root) {
		if (root == null) {
			return;
		}
		flatten(root.left);
		flatten(root.right);
		TreeNode node = root.left;
		if (node == null) {
			return;
		}
		while (node.right != null) {
			node = node.right;
		}
		node.right = root.right;
		root.right = root.left;
		root.left = null;
	}
}
