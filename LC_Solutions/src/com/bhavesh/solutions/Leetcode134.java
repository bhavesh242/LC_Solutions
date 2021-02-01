package com.bhavesh.solutions;

public class Leetcode134 {
	public int canCompleteCircuit(int[] gas, int[] cost) {
		// Find INdex such tha sum from 0 to idx-1 = idx to right
		int total_sum = 0;
		int curSum = 0;
		int start = 0;
		/*
		 * For all gas stations that have a defecit in gas i.e. gas[i]-cost[i] < 0 we
		 * can directly ignore and assume next index is starting point
		 */
		for (int i = 0; i < gas.length; i++) {
			curSum += gas[i] - cost[i];
			total_sum += gas[i] - cost[i];
			if (curSum < 0) {
				start = i + 1;
				curSum = 0;
			}

		}

		// If there is a deficit in total gos of a round trip then no solution exists
		if (total_sum < 0) {
			return -1;
		}
		// else return starting index
		// https://www.youtube.com/watch?v=wDgKaNrSOEI
		return start;
	}
}
