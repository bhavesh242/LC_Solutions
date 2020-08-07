package com.bhavesh.solutions;

public class Leetcode198 {
	public int rob(int[] nums) {
		/*
		 * follow DP dormula f(k) = max(f(k-2) + nums[k], f(k-1)) f(0) = nums[0], f(1) =
		 * Math.max(nums[0],nums[1])
		 */

		// As each f(k) depends on f(k-1) and f(k-2), we dont need an entire array
		int prevMax = 0;
		int curMax = 0;
		for (int i : nums) {
			int nextMax = curMax;
			curMax = Math.max(prevMax + i, nextMax);
			prevMax = nextMax;
		}

		return curMax;
	}
}