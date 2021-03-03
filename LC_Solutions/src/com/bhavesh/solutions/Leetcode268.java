package com.bhavesh.solutions;

public class Leetcode268 {
	public int missingNumber(int[] nums) {

		int sum = 0;
		int n = nums.length;
		for (int x : nums) {
			sum += x;
		}

		return (n + 1) * n / 2 - sum;
	}
}