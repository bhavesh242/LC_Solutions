package com.bhavesh.solutions;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode934 {
	public int shortestBridge(int[][] A) {

		int[][] dirs = new int[][] { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } };
		Queue<int[]> queue = new LinkedList<int[]>();
		for (int i = 0; i < A.length; i++) {
			boolean found = false;
			for (int j = 0; j < A[0].length; j++) {
				if (A[i][j] == 1) {
					dfs(A, i, j, dirs, queue);
					found = true;
					break;
				}
			}
			if (found) {
				break;
			}
		}

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				int coords[] = queue.poll();
				int i = coords[0];
				int j = coords[1];
				int curLevel = A[i][j];
				for (int d[] : dirs) {
					int ix = i + d[0];
					int jx = j + d[1];
					if (isValid(A, ix, jx)) {
						if (A[ix][jx] == 1) {
							return curLevel - 2;
						} else if (A[ix][jx] == 0) {
							A[ix][jx] = curLevel + 1;
							queue.add(new int[] { ix, jx });
						}
					}
				}

			}

		}

		return -1;
	}

	public void dfs(int A[][], int i, int j, int[][] dirs, Queue<int[]> queue) {
		if (isValid(A, i, j) && A[i][j] == 1) {
			A[i][j] = 2;
			queue.add(new int[] { i, j });
			for (int[] d : dirs) {
				dfs(A, i + d[0], j + d[1], dirs, queue);
			}
		}
	}

	public boolean isValid(int A[][], int i, int j) {
		return i >= 0 && i < A.length && j >= 0 && j < A[0].length;
	}
}
