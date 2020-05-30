package com.bhavesh.solutions;

public class Leetcode55 {
	public boolean canJump(int[] nums) {
		if (nums.length == 1)
			return true;
		int maxWindow = nums[0];
		int currentMax;
		int i;
		for (i = 0; i < nums.length - 1; i++) {
			currentMax = i + nums[i];
			if (maxWindow < currentMax) {
				maxWindow = currentMax;
			}
			if (maxWindow >= nums.length - 1) {
				return true;
			}
			if (i == maxWindow) {
				return false;
			}
		}
		return true;
	}
}
