package com.bhavesh.solutions;

import java.util.ArrayList;

public class Leetcode653 {
	public boolean findTarget(TreeNode root, int k) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		TreeNode cur = root;
		inOrder(cur, numbers);
		int left = 0;
		int right = numbers.size() - 1;
		while (left < right) {
			int sum = numbers.get(left) + numbers.get(right);
			if (sum == k) {
				return true;
			} else if (sum > k) {
				right--;
			} else {
				left++;
			}
		}

		return false;
	}

	public void inOrder(TreeNode root, ArrayList<Integer> numbers) {
		if (root == null) {
			return;
		}
		inOrder(root.left, numbers);
		numbers.add(root.val);
		inOrder(root.right, numbers);
	}
}