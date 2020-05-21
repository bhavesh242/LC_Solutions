package com.bhavesh.solutions;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode994 {
	public int orangesRotting(int[][] grid) {
		Queue<Orange> oq = new LinkedList<Orange>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 2) {
					oq.add(new Orange(i, j, 0));
				}
			}
		}

		int maxDist = 0;
		while (!oq.isEmpty()) {
			Orange o = oq.poll();
			maxDist = Math.max(maxDist, o.dist);
			if (isValid((o.i + 1), o.j, grid) && grid[o.i + 1][o.j] == 1) {
				oq.add(new Orange(o.i + 1, o.j, o.dist + 1));
				grid[o.i + 1][o.j] = 2;
			}
			if (isValid((o.i - 1), o.j, grid) && grid[o.i - 1][o.j] == 1) {
				oq.add(new Orange(o.i - 1, o.j, o.dist + 1));
				grid[o.i - 1][o.j] = 2;
			}
			if (isValid(o.i, (o.j + 1), grid) && grid[o.i][o.j + 1] == 1) {
				oq.add(new Orange(o.i, o.j + 1, o.dist + 1));
				grid[o.i][o.j + 1] = 2;
			}
			if (isValid(o.i, (o.j - 1), grid) && grid[o.i][o.j - 1] == 1) {
				oq.add(new Orange(o.i, o.j - 1, o.dist + 1));
				grid[o.i][o.j - 1] = 2;
			}
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					return -1;
				}
			}
		}
		return maxDist;
	}

	public boolean isValid(int i, int j, int[][] grid) {
		if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length) {
			return true;
		}
		return false;
	}

}

class Orange {
	int i;
	int j;
	int dist;

	Orange(int i, int j, int dist) {
		this.i = i;
		this.j = j;
		this.dist = dist;
	}
}
