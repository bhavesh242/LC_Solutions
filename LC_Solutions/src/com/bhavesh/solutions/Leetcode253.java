package com.bhavesh.solutions;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Leetcode253 {
	public int minMeetingRooms(int[][] intervals) {

		if (intervals.length == 0) {
			return 0;
		}
		// Sort the intervals by their start times
		Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

		// Create a min heap that sorts by ending time ascending order
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

		// Add first meeting to heap
		minHeap.offer(intervals[0][1]);
		// Start traversing meeting rooms
		for (int i = 1; i < intervals.length; i++) {
			// Remove from heap the earliest finishing meeeting
			int early = minHeap.remove();
			int[] cur = intervals[i];
			// Check if the early meeting overlaps with current meeting
			// If it does not, set meeting rooms ending time to current meeting ending time
			if (early <= cur[0]) {
				early = cur[1];
			}
			// Else add current interval times as a new meeting room to heap
			else {
				minHeap.add(cur[1]);
			}
			// Add early to heap
			minHeap.add(early);
		}
		// Final heap size is the no. of meeting rooms required
		return minHeap.size();
	}
}