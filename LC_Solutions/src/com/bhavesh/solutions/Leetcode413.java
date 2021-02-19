package com.bhavesh.solutions;

public class Leetcode413 {
	public int numberOfArithmeticSlices(int[] A) {

		// Approach 1 Sliding window
		int left = 0, right = 0;
		int prevDiff = 0;
		int ct = 0;
		for (right = 1; right < A.length; right++) {
			int curDiff = A[right] - A[right - 1];
			if (curDiff == prevDiff) {
				ct += Math.max(0, right - left - 1);
			} else {
				left = right - 1;
			}

			prevDiff = curDiff;
		}

		return ct;
	}

	// Apprach2 DP
	public int numberOfArithmeticSlices1(int[] A) {

		int dp = 0, sum = 0;
		for (int i = 2; i < A.length; i++) {
			if (A[i - 2] - A[i - 1] == A[i - 1] - A[i]) {
				sum += ++dp;
			} else {
				dp = 0;
			}
		}
		return sum;
	}

}