package com.bhavesh.solutions;

public class Leetcode124 {
	int maxSum = Integer.MIN_VALUE;

	public int maxPathSum(TreeNode root) {
		maxPathSumHelper(root);
		return maxSum;
	}

	public int maxPathSumHelper(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = maxPathSumHelper(root.left);
		int right = maxPathSumHelper(root.right);
		int val = root.val;
		if (left > 0) {
			val = val + left;
		}
		if (right > 0) {
			val = val + right;
		}
		maxSum = Math.max(maxSum, val);

		return Math.max(root.val, root.val + Math.max(left, right));
	}
}
