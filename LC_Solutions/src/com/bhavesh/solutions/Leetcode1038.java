package com.bhavesh.solutions;

public class Leetcode1038 {
	int sum = 0;

	public TreeNode bstToGst(TreeNode root) {
		/*
		 * On careful observation, we see that the sum follows a right, root, left order
		 */
		if (root == null) {
			return root;
		}

		bstToGst(root.right);
		sum += root.val;
		root.val = sum;
		bstToGst(root.left);
		return root;
	}
}