package com.bhavesh.solutions;

public class Leetcode1283 {
	public int smallestDivisor(int[] nums, int threshold) {
		/*
		 * we simply solve this by perfoming binary search on ranges of the threshold
		 * which are [1, 10^-6]
		 */
		int left = 1;
		int right = (int) 1e6;
		while (left < right) {
			int m = (left + right) / 2, sum = 0;
			for (int i : nums)
				// This is the formula to caclulate ceil, faster than Math.ceil
				sum += (i + m - 1) / m;
			if (sum > threshold)
				left = m + 1;
			else
				right = m;
		}
		return left;
	}
}
