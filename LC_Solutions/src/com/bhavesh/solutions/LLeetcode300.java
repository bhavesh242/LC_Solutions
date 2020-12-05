package com.bhavesh.solutions;

import java.util.Arrays;

public class LLeetcode300 {
	// Refer solutions

	/*
	 * Brute Force
	 * 
	 * public int lengthOfLIS(int[] nums) { return lengthofLIS(nums,
	 * Integer.MIN_VALUE, 0); }
	 * 
	 * public int lengthofLIS(int[] nums, int prev, int curpos) { if (curpos ==
	 * nums.length) { return 0; } int taken = 0; if (nums[curpos] > prev) { taken =
	 * 1 + lengthofLIS(nums, nums[curpos], curpos + 1); } int nottaken =
	 * lengthofLIS(nums, prev, curpos + 1); return Math.max(taken, nottaken); }
	 * 
	 */

	/*
	 * Recursion with memoization public int lengthOfLIS(int[] nums) { int memo[][]
	 * = new int[nums.length][nums.length]; for (int[] l : memo) { Arrays.fill(l,
	 * -1); } return lengthofLIS(nums, -1, 0, memo); } public int lengthofLIS(int[]
	 * nums, int previndex, int curpos, int[][] memo) { if (curpos == nums.length) {
	 * return 0; } if (memo[previndex + 1][curpos] >= 0) { return memo[previndex +
	 * 1][curpos]; } int taken = 0; if (previndex < 0 || nums[curpos] >
	 * nums[previndex]) { taken = 1 + lengthofLIS(nums, curpos, curpos + 1, memo); }
	 * 
	 * int nottaken = lengthofLIS(nums, previndex, curpos + 1, memo); memo[previndex
	 * + 1][curpos] = Math.max(taken, nottaken); return memo[previndex + 1][curpos];
	 * }
	 */

	/*
	 * Dynamic Programming public int lengthOfLIS(int[] nums) { if (nums.length ==
	 * 0) { return 0; } int[] dp = new int[nums.length]; dp[0] = 1; int maxans = 1;
	 * for (int i = 1; i < dp.length; i++) { int maxval = 0; for (int j = 0; j < i;
	 * j++) { if (nums[i] > nums[j]) { maxval = Math.max(maxval, dp[j]); } } dp[i] =
	 * maxval + 1; maxans = Math.max(maxans, dp[i]); } return maxans; }
	 */

	// DP with Binary Search
	public int lengthOfLIS(int[] nums) {
		int[] dp = new int[nums.length];
		int len = 0;
		for (int num : nums) {
			int i = Arrays.binarySearch(dp, 0, len, num);
			if (i < 0) {
				i = -(i + 1);
			}
			dp[i] = num;
			if (i == len) {
				len++;
			}
		}
		return len;
	}
}