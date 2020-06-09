package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.List;

public class Leetcode986 {
	public int[][] intervalIntersection(int[][] A, int[][] B) {
		int i = 0, j = 0;
		List<int[]> op = new ArrayList<int[]>();
		while (i < A.length && j < B.length) {
			int low = Math.max(A[i][0], B[j][0]);
			int high = Math.min(A[i][1], B[j][1]);
			if (low <= high) {
				op.add(new int[] { low, high });
			}
			if (A[i][1] < B[j][1]) {
				i++;
			} else {
				j++;
			}
		}
		return op.toArray(new int[op.size()][2]);
	}
}
