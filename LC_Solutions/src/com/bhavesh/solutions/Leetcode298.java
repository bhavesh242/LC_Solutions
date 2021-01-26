package com.bhavesh.solutions;

public class Leetcode298 {
	int maxLen = 0;

	public int longestConsecutive(TreeNode root) {
		if (root == null) {
			return 0;
		}
		helper(root, 1);
		return maxLen;
	}

	public void helper(TreeNode root, int current) {

		maxLen = Math.max(maxLen, current);

		if (root.left == null && root.right == null) {
			return;
		}

		if (root.left != null) {
			helper(root.left, root.left.val == root.val + 1 ? current + 1 : 1);
		}

		if (root.right != null) {
			helper(root.right, root.right.val == root.val + 1 ? current + 1 : 1);
		}

	}

}