package com.bhavesh.solutions;

public class Leetcode1428 {
	public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
		// Get rows and Columns
		int rows = binaryMatrix.dimensions().get(0);
		int columns = binaryMatrix.dimensions().get(1);

		// Set pointers
		int curRow = 0;
		int curCol = columns - 1;

		// If you encounter a zero, move down, else move left
		while (curRow < rows && curCol >= 0) {
			if (binaryMatrix.get(curRow, curCol) == 0) {
				curRow++;
			} else {
				curCol--;
			}
		}

		if (curCol == columns - 1) {
			return -1;
		}
		return curCol + 1;
	}
}