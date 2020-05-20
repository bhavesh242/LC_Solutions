package com.bhavesh.solutions;

public class Leetcode221 {
	public int maximalSquare(char[][] matrix) {
		// Use the dp formula dp[i][j] = min(dp[i-1][j-1],dp[i-1][j],dp[i][j-1] + 1)
		int row = matrix.length;
		int col = row > 0 ? matrix[0].length : 0;
		int dp[][] = new int[row + 1][col + 1];
		int maxSq = 0;
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				if (matrix[i - 1][j - 1] == '1') {
					dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
					maxSq = Math.max(maxSq, dp[i][j]);
				}
			}
		}
		return maxSq * maxSq;
	}
}
