package com.bhavesh.solutions;

public class Leetcode45 {
	public int jump(int[] nums) {
		int maxSteps = nums[0];
		int maxPos = nums[0];
		int numJumps = 1;
		for (int i = 0; i < nums.length; i++) {
			if (i > maxSteps) {
				numJumps++;
				maxSteps = Math.max(maxSteps, maxPos);
			}
			maxPos = Math.max(maxPos, i + nums[i]);
		}
		return numJumps;

	}
}
