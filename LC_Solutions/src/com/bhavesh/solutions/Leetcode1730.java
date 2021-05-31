package com.bhavesh.solutions;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode1730 {
	public int getFood(char[][] grid) {

		int r = 0, c = 0;
		boolean flag = false;
		for (r = 0; r < grid.length; r++) {
			for (c = 0; c < grid[0].length; c++) {
				if (grid[r][c] == '*') {
					flag = true;
					break;
				}
			}
			if (flag) {
				break;
			}
		}

		int directions[][] = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { r, c });
		grid[r][c] = 'X';
		int len = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] pos = queue.poll();
				int R = pos[0];
				int C = pos[1];
				for (int[] dir : directions) {
					int r1 = R + dir[0];
					int c1 = C + dir[1];
					if (isValid(r1, c1, grid)) {
						if (grid[r1][c1] == '#') {
							return len + 1;
						}
						grid[r1][c1] = 'X';
						queue.add(new int[] { r1, c1 });
					}
				}
			}
			len++;
		}

		return -1;
	}

	public boolean isValid(int r, int c, char[][] grid) {
		if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 'X') {
			return false;
		}
		return true;
	}
}