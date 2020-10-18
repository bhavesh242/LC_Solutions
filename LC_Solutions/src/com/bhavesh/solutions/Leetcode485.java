package com.bhavesh.solutions;

public class Leetcode485 {
	public int findMaxConsecutiveOnes(int[] nums) {
		int maxCtr = 0;
		int curCount = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 1) {
				curCount++;

			} else {
				maxCtr = Math.max(maxCtr, curCount);
				curCount = 0;
			}
		}
		return Math.max(maxCtr, curCount);
	}
}