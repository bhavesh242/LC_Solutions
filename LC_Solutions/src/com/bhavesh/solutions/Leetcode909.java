package com.bhavesh.solutions;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode909 {
	public int snakesAndLadders(int[][] board) {

		int N = board.length;
		boolean[] visited = new boolean[N * N + 1];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(1);
		visited[1] = true;
		int moves = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				int pos = queue.poll();
				for (int j = pos + 1; j <= Math.min(pos + 6, N * N); j++) {
					int[] idxs = getIdxs(j, N);
					int newPos = j;
					if (board[idxs[0]][idxs[1]] > 0) {
						newPos = board[idxs[0]][idxs[1]];
					}
					if (newPos == N * N) {
						return moves + 1;
					}
					if (!visited[newPos]) {
						visited[newPos] = true;
						queue.add(newPos);
					}

				}

			}
			moves++;
		}

		return -1;
	}

	public int[] getIdxs(int j, int N) {
		int row = N - 1 - (j - 1) / N;
		int rem = (j - 1) % N;
		int col = N % 2 != row % 2 ? rem : N - 1 - rem;
		return new int[] { row, col };
	}
}