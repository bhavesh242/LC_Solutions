package com.bhavesh.solutions;

public class Leetcode289 {
	public void gameOfLife(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				int count = countLive(i, j, board);
				if (board[i][j] == 1) {
					if (count < 2 || count > 3) {
						board[i][j] = -1;
					}
				}
				if (board[i][j] == 0 && count == 3) {
					board[i][j] = 2;
				}
			}
		}

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == -1) {
					board[i][j] = 0;
				}
				if (board[i][j] == 2) {
					board[i][j] = 1;
				}
			}
		}
	}

	public int countLive(int i, int j, int[][] board) {
		int row[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int col[] = { -1, 0, 1, -1, 1, -1, 0, 1 };
		int count = 0;
		for (int k = 0; k < 8; k++) {
			if (isValid(i + row[k], j + col[k], board)) {
				if (board[i + row[k]][j + col[k]] == 1 || board[i + row[k]][j + col[k]] == -1) {
					count++;
				}
			}
		}

		return count;
	}

	public boolean isValid(int i, int j, int[][] board) {
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
			return false;
		}
		return true;
	}
}
