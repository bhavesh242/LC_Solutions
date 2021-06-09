package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode417 {

}

//BFS
class Sol_417_1 {
	int R;
	int C;
	int[][] heights;

	public List<List<Integer>> pacificAtlantic(int[][] heights) {
		this.heights = heights;
		R = heights.length;
		C = heights[0].length;
		Queue<int[]> pacificQueue = new LinkedList<int[]>();
		Queue<int[]> atlanticQueue = new LinkedList<int[]>();

		for (int i = 0; i < R; i++) {
			pacificQueue.add(new int[] { i, 0 });
			atlanticQueue.add(new int[] { i, C - 1 });
		}

		for (int i = 0; i < C; i++) {
			pacificQueue.add(new int[] { 0, i });
			atlanticQueue.add(new int[] { R - 1, i });
		}

		boolean[][] pacificReachable = bfs(pacificQueue);
		boolean[][] atlanticReachable = bfs(atlanticQueue);

		List<List<Integer>> commons = new ArrayList<List<Integer>>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (pacificReachable[i][j] && atlanticReachable[i][j]) {
					ArrayList<Integer> a = new ArrayList<Integer>();
					a.add(i);
					a.add(j);
					commons.add(a);
				}
			}
		}

		return commons;
	}

	public boolean[][] bfs(Queue<int[]> queue) {
		boolean[][] arr = new boolean[R][C];
		int[][] directions = new int[][] { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
		while (!queue.isEmpty()) {
			int[] pos = queue.poll();
			arr[pos[0]][pos[1]] = true;
			for (int[] dir : directions) {
				int newRow = pos[0] + dir[0];
				int newCol = pos[1] + dir[1];
				if (isInValid(newRow, newCol) || arr[newRow][newCol]
						|| heights[newRow][newCol] < heights[pos[0]][pos[1]]) {
					continue;
				}
				queue.add(new int[] { newRow, newCol });
			}
		}

		return arr;

	}

	public boolean isInValid(int x, int y) {
		return x < 0 || x >= R || y < 0 || y >= C;
	}
}

//Solution 2 : DFS (Faster)
class Sol_417_2 {
	int R, C;
	int[][] heights;
	int[][] directions = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public List<List<Integer>> pacificAtlantic(int[][] heights) {
		R = heights.length;
		C = heights[0].length;
		this.heights = heights;

		boolean[][] pacific = new boolean[R][C];
		boolean[][] atlantic = new boolean[R][C];

		for (int i = 0; i < C; i++) {
			dfs(pacific, 0, i);
			dfs(atlantic, R - 1, i);
		}

		for (int i = 0; i < R; i++) {
			dfs(pacific, i, 0);
			dfs(atlantic, i, C - 1);
		}
		List<List<Integer>> commons = new ArrayList<List<Integer>>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (pacific[i][j] && atlantic[i][j]) {
					ArrayList<Integer> a = new ArrayList<Integer>();
					a.add(i);
					a.add(j);
					commons.add(a);
				}
			}
		}

		return commons;
	}

	public void dfs(boolean[][] arr, int x, int y) {
		arr[x][y] = true;
		for (int[] dir : directions) {
			int newX = x + dir[0];
			int newY = y + dir[1];
			if (isInValid(newX, newY) || arr[newX][newY] || heights[x][y] > heights[newX][newY]) {
				continue;
			}
			dfs(arr, newX, newY);
		}
	}

	public boolean isInValid(int x, int y) {
		return x < 0 || x >= R || y < 0 || y >= C;
	}
}