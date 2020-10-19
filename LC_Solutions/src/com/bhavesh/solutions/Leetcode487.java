package com.bhavesh.solutions;

public class Leetcode487 {
	public int findMaxConsecutiveOnes(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		// The idea is to keep count of continuous ones until a new 0 is encountered
		// Current streak of ones
		int cur = 0;
		// Previous streaks of ones including a bit flip
		int prev = 0;
		// Keeps max counter
		int max = 0;
		// Iterate whole array
		for (int i = 0; i < nums.length; i++) {
			// Each bit is counted towards current run of ones
			cur++;
			/*
			 * If a zero is encountered, it means we have flipped this zero into one and
			 * thus we have to decommission the previously flipped zeroes and all the 1's
			 * before that
			 */
			if (nums[i] == 0) {
				cur -= prev;
				prev = cur;
			}
			// After each traversal find the max
			max = Math.max(max, cur);

		}
		// Return max
		return max;
	}
}