package com.bhavesh.solutions;

import java.util.List;

public class Leetcode120 {
	public int minimumTotal(List<List<Integer>> triangle) {
		int size = triangle.size();
		int row[] = new int[size];
		for (int i = 0; i < size; i++) {
			row[i] = triangle.get(size - 1).get(i);
		}

		for (int i = size - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				row[j] = triangle.get(i).get(j) + Math.min(row[j], row[j + 1]);
			}
		}

		return row[0];
	}
}