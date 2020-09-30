package com.bhavesh.solutions;

public class Leetcode695 {
	int max = 0;
	int current = 0;

	public int maxAreaOfIsland(int[][] grid) {
		/*
		 * Idea is to perform a simple DFS and keep track of number of Ones for finding
		 * area
		 */
		/* Island with maximum number of ones will be the island with maximum Area */
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					current = 0;
					dfs(grid, i, j);
					max = Math.max(max, current);
				}
			}
		}

		return max;
	}

	public void dfs(int[][] grid, int i, int j) {
		if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {
			current++;
			grid[i][j] = 0;
			dfs(grid, i + 1, j);
			dfs(grid, i - 1, j);
			dfs(grid, i, j + 1);
			dfs(grid, i, j - 1);
		}
	}

}