package com.bhavesh.solutions;

public class Leetcode74 {
	public boolean searchMatrix(int[][] matrix, int target) {

		// Treat 2d array as a single long array

		int R = matrix.length;
		int C = matrix[0].length;

		int left = 0;
		int right = R * C - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;
			int pRow = mid / C;
			int pCol = mid % C;
			if (matrix[pRow][pCol] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}

		return matrix[left / C][left % C] == target;
	}
}