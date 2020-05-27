package com.bhavesh.solutions;

public class Leetcode543 {
	int max = 0;

	public int diameterOfBinaryTree(TreeNode root) {
		findDepths(root);
		return max;
	}

	public int findDepths(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = findDepths(root.left);
		int right = findDepths(root.right);
		max = Math.max(left + right, max);
		return 1 + Math.max(left, right);
	}
}
