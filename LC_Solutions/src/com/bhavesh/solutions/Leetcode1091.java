package com.bhavesh.solutions;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode1091 {
	public int shortestPathBinaryMatrix(int[][] grid) {

		// BFS is the best option

		// Note : BFS with marking node as visited while insertion itself is also a
		// great idea
		int N = grid.length;
		if (N == 0) {
			return 0;
		}
		if (grid[0][0] == 1) {
			return -1;
		}
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] { 0, 0 });
		grid[0][0] = 1;
		int moves = 1;
		int[][] dir = new int[][] { { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 },
				{ 0, -1 } };
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] pos = q.poll();
				if (pos[0] == N - 1 && pos[1] == N - 1) {
					return moves;
				}
				// grid[pos[0]][pos[1]]=1;
				for (int[] go : dir) {
					if (isValid(pos[0] + go[0], pos[1] + go[1], grid, N)) {
						q.add(new int[] { pos[0] + go[0], pos[1] + go[1] });
						grid[pos[0] + go[0]][pos[1] + go[1]] = 1;
					}
				}

			}

			moves++;
		}

		return -1;
	}

	public boolean isValid(int x, int y, int[][] grid, int N) {
		if (x < 0 || x >= N || y < 0 || y >= N || grid[x][y] != 0) {
			return false;
		}
		return true;
	}
}