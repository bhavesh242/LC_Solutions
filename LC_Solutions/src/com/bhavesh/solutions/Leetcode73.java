package com.bhavesh.solutions;

public class Leetcode73 {
	public void setZeroes(int[][] matrix) {

		int R = matrix.length;
		int C = matrix[0].length;
		// The idea is if matrix[i][j]==0, set matrix[0][j]=0 and matrix[i][0]=0
		/*
		 * This is a way of marking those rows and columns to be set to zero without
		 * using any extra space
		 */
		/*
		 * We just need one extra variable to store if the first columns also has any
		 * zeroes or not
		 */
		boolean fCol = false;
		for (int r = 0; r < R; r++) {
			/*
			 * If any of the first elements in the row is a 0, it implies, we need to make
			 * the first column as Zero as well, for which we store it in flag that we will
			 * use later
			 */
			if (matrix[r][0] == 0) {
				fCol = true;
			}
			for (int c = 1; c < C; c++) {
				if (matrix[r][c] == 0) {
					matrix[r][0] = 0;
					matrix[0][c] = 0;
				}
			}
		}

		for (int r = 1; r < R; r++) {
			for (int c = 1; c < C; c++) {
				if (matrix[r][0] == 0 || matrix[0][c] == 0) {
					matrix[r][c] = 0;
				}
			}
		}

		/*
		 * If the first element is 0, it implies, that first row needs to be cleared as
		 * originally either itself was a zero or one of the elements in the first row
		 * was
		 */
		if (matrix[0][0] == 0) {
			for (int c = 1; c < C; c++) {
				matrix[0][c] = 0;
			}
		}
		//Set first column to 0 based on flag
		if (fCol) {
			for (int r = 0; r < R; r++) {
				matrix[r][0] = 0;
			}
		}
	}
}
