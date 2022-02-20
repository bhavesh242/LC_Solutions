package com.bhavesh.solutions;

import java.util.Arrays;

public class Leetcode1288 {
	public int removeCoveredIntervals(int[][] intervals) {

		Arrays.sort(intervals, (a, b) -> {
			if (a[0] == b[0]) {
				return b[1] - a[1];
			} else {
				return a[0] - b[0];
			}
		});

		int earliest = intervals[0][1];
		int count = 1;

		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i][1] <= earliest) {
				continue;
			}
			count++;
			earliest = intervals[i][1];
		}

		return count;
	}
}