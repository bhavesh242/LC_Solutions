package com.bhavesh.solutions;

public class Leetcode980 {
	// Path Counter that will be or output for this problem
	int[][] grid;
	int walkCount = 0;
	int pathCount = 0;

	public int uniquePathsIII(int[][] grid) {
		// Variables to Store start row and column
		int sRow = 0, sCol = 0;
		this.grid = grid;
		// Keep a global count of number of walkable tiles in the grid and also make not
		// of co-ordinates of start point
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 0) {
					walkCount++;
				} else if (grid[i][j] == 1) {
					sRow = i;
					sCol = j;
				}
			}
		}
		// Start DFS from Starting Position
		dfs(sRow, sCol, 0);
		// Once DFS is done, return walkcount
		return pathCount;
	}

	// Do a DFS, marking a wolked over tile with -1 to not traverse it again
	// Once the destination is reached, check if we walked over all walkapble paths,
	// including start
	// If yes, increase answer by one
	public void dfs(int row, int column, int startCt) {

		if (row < 0 || column < 0 || row >= grid.length || column >= grid[0].length || grid[row][column] == -1) {
			return;
		}
		if (grid[row][column] == 2) {
			if (startCt == walkCount + 1) {
				pathCount++;
			}
			return;
		} else {
			startCt++;
			int temp = grid[row][column];
			grid[row][column] = -1;
			dfs(row - 1, column, startCt);
			dfs(row + 1, column, startCt);
			dfs(row, column - 1, startCt);
			dfs(row, column + 1, startCt);
			grid[row][column] = temp;
		}

	}
}