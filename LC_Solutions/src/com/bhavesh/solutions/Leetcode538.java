package com.bhavesh.solutions;

public class Leetcode538 {
	int sum = 0;

	public TreeNode convertBST(TreeNode root) {
		/*
		 * On careful observation, we see that the sum follows a right, root, left order
		 */
		if (root == null) {
			return root;
		}

		convertBST(root.right);
		sum += root.val;
		root.val = sum;
		convertBST(root.left);
		return root;
	}
}