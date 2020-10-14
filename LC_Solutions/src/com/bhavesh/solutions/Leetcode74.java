package com.bhavesh.solutions;

public class Leetcode74 {
	public boolean searchMatrix(int[][] matrix, int target) {
		// This is an array arranged as a 2D matrix, ranging from 0 to m*n-1
		int row = matrix.length;
		if (row == 0) {
			return false;
		}
		int col = matrix[0].length;
		if (col == 0) {
			return false;
		}
		int left = 0, right = row * col - 1;
		while (left < right) {
			int mid = (right + left) / 2;
			int ele = matrix[mid / col][mid % col];
			if (ele >= target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}

		return matrix[left / col][left % col] == target;
	}
}