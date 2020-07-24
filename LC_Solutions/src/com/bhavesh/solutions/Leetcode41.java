package com.bhavesh.solutions;

class Leetcode41 {
	public int firstMissingPositive(int[] nums) {
		int n = nums.length;
		boolean oneFlag = false;
		// Check For Presence of 1
		for (int i = 0; i < n; i++) {
			if (nums[i] == 1) {
				oneFlag = true;
				break;
			}
		}
		// If 1 is not present return 1 as the answer
		if (!oneFlag) {
			return 1;
		}
		if (n == 1) {
			return 2;
		}
		// Change all elements that are 0, nrgative or greater than n to value of 1
		for (int i = 0; i < n; i++) {
			if (nums[i] <= 0 || nums[i] > n) {
				nums[i] = 1;
			}
		}
		// Use the indexes as a Key in a map
		for (int i = 0; i < n; i++) {
			// Take absolute of the number
			int a = Math.abs(nums[i]);
			// Invalidate number at that index my converting it to negative
			nums[a - 1] = -1 * Math.abs(nums[a - 1]);
		}
		// Check the array for first positive value, if found return
		for (int i = 0; i < n; i++) {
			if (nums[i] > 0) {
				return i + 1;
			}
		}
		return n + 1;
	}
}