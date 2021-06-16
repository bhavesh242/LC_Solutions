package com.bhavesh.solutions;

public class Leetcode53 {
	public int maxSubArray(int[] nums) {
		int curmax = nums[0];
		int globalMax = nums[0];
		for (int i = 1; i < nums.length; i++) {
			curmax = Math.max(curmax + nums[i], nums[i]);
			globalMax = Math.max(globalMax, curmax);
		}

		return globalMax;
	}
}