package com.bhavesh.solutions;

public class Leetcode108 {

	// There are several solutions that can be valid for this particular problem
	/*
	 * It mainly depends on choice of root, we can perform preorder traversal with
	 * selecting left mid element as root or right mid selement as root or selecting
	 * a random middle element as root using random function
	 */
	public TreeNode sortedArrayToBST(int[] nums) {
		return helper(0, nums.length - 1, nums);
	}

	public TreeNode helper(int left, int right, int[] nums) {
		if (left > right) {
			return null;
		}
		int mid = (left + right) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = helper(left, mid - 1, nums);
		root.right = helper(mid + 1, right, nums);
		return root;

	}
}