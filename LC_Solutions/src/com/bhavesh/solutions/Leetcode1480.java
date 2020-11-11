package com.bhavesh.solutions;

public class Leetcode1480 {
	public int[] runningSum(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			nums[i] = sum + nums[i];
			sum = nums[i];
		}

		return nums;
	}
}