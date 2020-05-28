package com.bhavesh.solutions;

public class Leetcode152 {
	public int maxProduct(int[] nums) {
		int curMax = nums[0];
		int curMin = nums[0];
		int final_max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int curMaxTemp = Math.max(nums[i], Math.max(curMax * nums[i], curMin * nums[i]));
			int curMinTemp = Math.min(nums[i], Math.min(curMax * nums[i], curMin * nums[i]));
			curMax = curMaxTemp;
			curMin = curMinTemp;
			final_max = Math.max(curMax, final_max);

		}
		return final_max;

	}
}
