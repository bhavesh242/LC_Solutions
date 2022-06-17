package com.bhavesh.solutions;

public class Leetcode1937 {

	/*
	 * Time O(m*n)? As a dp concept, consider (i,j), we can record the max from j to
	 * left, and j to right with two containers. Then do not forget to calculate the
	 * shift by subtracting 1. After counting the left, right container. We can
	 * easily to count the sum(i+1,j) as the points(i+1,j) + max(left, right); Time
	 * O(m*n) Space O(n)
	 */

	public long maxPoints1(int[][] points) {

		int m = points.length;
		int n = points[0].length;
		long dp[] = new long[n];
		long[] left = new long[n];
		long right[] = new long[n];

		for (int j = 0; j < n; j++) {
			dp[j] = points[0][j];
		}

		for (int i = 1; i < m; i++) {

			left[0] = dp[0];
			for (int j = 1; j < n; j++) {
				left[j] = Math.max(dp[j], left[j - 1] - 1);
			}

			right[n - 1] = dp[n - 1];
			for (int j = n - 2; j >= 0; j--) {
				right[j] = Math.max(dp[j], right[j + 1] - 1);
			}

			for (int j = 0; j < n; j++) {
				dp[j] = points[i][j] + Math.max(left[j], right[j]);
			}
		}

		long result = 0;
		for (int i = 0; i < n; i++) {
			result = Math.max(result, dp[i]);
		}
		return result;
	}

	// Same approach as above without using the left and right arrays
	public long maxPoints(int[][] points) {

		int m = points.length;
		int n = points[0].length;
		long dp[] = new long[n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++)
				dp[j] += points[i][j];

			for (int j = 1; j < n; j++) {
				dp[j] = Math.max(dp[j], dp[j - 1] - 1);
			}

			for (int j = n - 2; j >= 0; j--) {
				dp[j] = Math.max(dp[j], dp[j + 1] - 1);
			}
		}

		long result = 0;
		for (int j = 0; j < n; j++) {
			result = Math.max(result, dp[j]);
		}
		return result;
	}

}