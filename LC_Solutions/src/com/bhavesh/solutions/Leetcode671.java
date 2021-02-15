package com.bhavesh.solutions;

public class Leetcode671 {
	int secondMin = Integer.MAX_VALUE;
	boolean foundOne = false;
	public int findSecondMinimumValue(TreeNode root) {
		if (root == null) {
			return -1;
		}
		int minVal = root.val;
		helper(root, minVal);
		return !foundOne ? -1 : secondMin;
	}

	public void helper(TreeNode root, int val) {
		if (root == null) {
			return;
		}
		if (root.val != val) {
			secondMin = Math.min(secondMin, root.val);
			foundOne = true;
		}
		helper(root.left, val);
		helper(root.right, val);
	}
}