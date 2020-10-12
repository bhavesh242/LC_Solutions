package com.bhavesh.solutions;

public class Leetcode209 {
	public int minSubArrayLen(int s, int[] nums) {

		// Sliding Window
		int minLen = Integer.MAX_VALUE;
		int left = 0;
		int curSum = 0;
		// This variable tracks if a solutin was found or not
		boolean solExist = false;
		// Increment right pointer, add the value to current sum
		for (int right = 0; right < nums.length; right++) {
			curSum += nums[right];
			/*
			 * Move Left pointer and keep subtracting from current sum and keep comparing
			 * with current length for minimum
			 */
			while (curSum >= s) {
				solExist = true;
				minLen = Math.min(minLen, right - left + 1);
				curSum -= nums[left++];
			}
		}
		// If solution was found, return it else return 0
		return solExist ? minLen : 0;
	}
}