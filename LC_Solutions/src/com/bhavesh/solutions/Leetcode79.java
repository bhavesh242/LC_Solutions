package com.bhavesh.solutions;

public class Leetcode79 {
	public boolean exist(char[][] board, String word) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (backTracker(i, j, board, word, 0)) {
					return true;
				}
			}
		}
		return false;

	}

	public boolean backTracker(int row, int col, char[][] board, String word, int index) {

		if (index == word.length()) {
			return true;
		}
		if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
				|| board[row][col] != word.charAt(index)) {
			return false;
		}
		board[row][col] = '#';
		boolean res = backTracker(row + 1, col, board, word, index + 1)
				|| backTracker(row - 1, col, board, word, index + 1)
				|| backTracker(row, col + 1, board, word, index + 1)
				|| backTracker(row, col - 1, board, word, index + 1);
		board[row][col] = word.charAt(index);
		return res;

	}
}
