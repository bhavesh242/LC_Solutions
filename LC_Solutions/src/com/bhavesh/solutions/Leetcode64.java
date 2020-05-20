package com.bhavesh.solutions;

public class Leetcode64 {
	public int minPathSum(int[][] grid) {

		int[][] dp = new int[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				dp[i][j] = calcDp(i, j, dp) + grid[i][j];
			}
		}
		return dp[grid.length - 1][grid[0].length - 1];
	}

	public int calcDp(int i, int j, int dp[][]) {
		if (i == 0 && j == 0) {
			return 0;
		}
		if (i == 0) {
			return dp[i][j - 1];
		}
		if (j == 0) {
			return dp[i - 1][j];
		}
		return Math.min(dp[i][j - 1], dp[i - 1][j]);
	}
}
