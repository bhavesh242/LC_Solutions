package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.List;

public class Leetcode57 {
	public int[][] insert(int[][] intervals, int[] newInterval) {
		// Edge case: If no intervals are present, just return new interval

		List<int[]> op = new ArrayList();

		// Insert all intervals that end before the newInterval into output
		int i = 0;
		while (i < intervals.length && intervals[i][1] < newInterval[0]) {
			op.add(intervals[i]);
			i++;
		}
		// While intserting the new Interval, we merge it with all overlapping intervals

		// Merge current interval with newInterval if they are overlapping, else take
		// newInterval's start as start index
		int start = i == intervals.length ? newInterval[0] : Math.min(intervals[i][0], newInterval[0]);

		/*
		 * Jump over all the intervals that are completely covered by the new interval
		 * until you reach an interval that does not finish before the newInterval
		 */
		while (i < intervals.length && intervals[i][1] < newInterval[1]) {
			i++;
		}

		/*
		 * or the end interval, check if the newinterval's finished before
		 * currentInterval's start, if yes, assign newIntervals's end as end index,
		 * otherwise, take currentIntervals finish index as end
		 */
		int end = -1;

		if (i == intervals.length || newInterval[1] < intervals[i][0]) {
			end = newInterval[1];
		}

		else {
			end = intervals[i][1];
			i++;
		}
		// Add this new overlapped interval to results
		op.add(new int[] { start, end });

		// Add all following intervals after current to the result too
		while (i < intervals.length) {
			op.add(intervals[i]);
			i++;
		}

		return op.toArray(new int[op.size()][2]);
	}
}
