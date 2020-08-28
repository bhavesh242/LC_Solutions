package com.bhavesh.solutions;

import java.util.HashSet;

public class Leetcode694 {
	public int numDistinctIslands(int[][] grid) {
		// We store the shapes as set of strings to maintain uniqueness
		HashSet<String> shapes = new HashSet<String>();
		// Start dfs at places you find a 1 to find islands
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					StringBuffer sb = new StringBuffer();
					dfs(grid, i, j, sb.append("."));
					// Store the shape after returning from the DFS
					shapes.add(sb.toString());
				}
			}

		// Number of distinct shapes in the set represents number of distinct islands
		// found
		return shapes.size();
	}

	public void dfs(int[][] grid, int r, int c, StringBuffer sb) {
		// If the co-ordinates are out of bounds or grid element is 0, return
		if (r >= grid.length || r < 0 || c >= grid[0].length || c < 0 || grid[r][c] == 0) {
			return;
		}
		// Set the visited grid element to 0
		grid[r][c] = 0;
		/*
		 * Perform dfs in all 4 directions and append to the string the direction in
		 * which you're moving
		 */
		dfs(grid, r - 1, c, sb.append("s"));
		dfs(grid, r + 1, c, sb.append("n"));
		dfs(grid, r, c - 1, sb.append("w"));
		dfs(grid, r, c + 1, sb.append("e"));
	}
}
