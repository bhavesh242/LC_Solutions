package com.bhavesh.solutions;

public class Leetcode152 {
	public int maxProduct(int[] nums) {

		int curMax = nums[0];
		int curMin = nums[0];
		int final_max = nums[0];
		/*
		 * For each element maintain current max and min till that point. We do this as
		 * minimum numbers that are negative can instantly turn maximums if they
		 * encounter negative numbers and similarly poitive maximum numbers can turn
		 * minimums if they encounter negative numbers
		 */
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
