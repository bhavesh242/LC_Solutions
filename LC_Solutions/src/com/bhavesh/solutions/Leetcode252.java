package com.bhavesh.solutions;

import java.util.PriorityQueue;

public class Leetcode252 {
	public boolean canAttendMeetings(int[][] intervals) {
		// Build a min heap that sorts by stating time
		if (intervals.length == 0) {
			return true;
		}
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> (a[0] - b[0]));
		// Add all intervals to priorityQueue
		for (int a[] : intervals) {
			pq.add(a);
		}

		// Take the smallest interval as the current interval
		int[] cur = pq.remove();
		// Remove intervals and keep checking if they overlap with current interval
		// If yes, return false
		while (!pq.isEmpty()) {
			int[] next = pq.remove();
			if (next[0] < cur[1]) {
				return false;
			}
			cur = next;
		}

		return true;

	}
}

