package com.bhavesh.solutions;

import java.util.Arrays;

public class Leetcode252 {
	//Sort the arrays and cjeck consecutive overlapping intervals
	public boolean canAttendMeetings(int[][] intervals) {
		if (intervals == null || intervals.length == 0) {
			return true;
		}
		Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
		int[] cur = intervals[0];

		for (int i = 1; i < intervals.length; i++) {
			int[] next = intervals[i];
			if (cur[1] > next[0]) {
				return false;
			}
			cur = next;
		}
		return true;
	}
}