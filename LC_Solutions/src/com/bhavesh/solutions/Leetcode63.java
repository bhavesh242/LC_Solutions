package com.bhavesh.solutions;

public class Leetcode63 {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int row = obstacleGrid.length;
		int column = obstacleGrid[0].length;
		int dp[][] = new int[row][column];
		// If there is an obstacle at the start posiion itself, return 0 as there would
		// not be any paths
		if (obstacleGrid[0][0] == 1) {
			return 0;
		}
		// There is always 1 path at start position
		dp[0][0] = 1;

		// Filling out the edge cases, where dp is assigned, keeping obstacles in mind,
		// if there is obstacle present, we set that dp to 0
		// Filling out first column
		for (int i = 1; i < row; i++) {
			dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : dp[i - 1][0];
		}
		// Filling out first row
		for (int i = 1; i < column; i++) {
			dp[0][i] = obstacleGrid[0][i] == 1 ? 0 : dp[0][i - 1];
		}

		// Do the same as above for rest of the grid
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < column; j++) {
				dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : dp[i - 1][j] + dp[i][j - 1];
			}
		}

		return dp[row - 1][column - 1];
	}
}
