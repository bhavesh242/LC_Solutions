package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Leetcode1086 {

	public int[][] highFive(int[][] items) {

		Arrays.sort(items, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				if (a[0] != b[0]) {
					return a[0] - b[0];
				} else {
					return b[1] - a[1];
				}
			}
		});
		ArrayList<int[]> op = new ArrayList<int[]>();
		int id = items[0][0];
		int count = 0;
		int mean = 0;
		for (int i = 0; i < items.length; i++) {
			if (id != items[i][0]) {
				count = 0;
				id = items[i][0];
			}
			if (count < 5) {
				mean = mean + items[i][1];
				count++;
			}
			if (count == 5) {
				mean = mean / 5;
				op.add(new int[] { items[i][0], mean });
				mean = 0;
			}
			if (count >= 5) {
				count++;
			}
		}

		int[][] ans = new int[op.size()][2];
		for (int i = 0; i < op.size(); i++) {
			ans[i] = op.get(i);
		}

		return ans;

	}
}
