package com.bhavesh.solutions;

import java.util.HashMap;

public class Leetcode957 {

	public int[] prisonAfterNDays(int[] cells, int n) {

		// Main Idea : Fast forwarding
		// Convert cells into a bit map and store the day on which that state was seen
		/*
		 * If a state repeats after every x days, we can fast forward N/x days and will
		 * be left with very limited days to process
		 */

		// First we convert the cells into an integer state
		int state = 0;
		for (int i = 0; i < 8; i++) {
			state = state << 1;
			state = (state | cells[i]);
		}
		// This map is used to store the seens states along with days remaining
		HashMap<Integer, Integer> seen = new HashMap<Integer, Integer>();

		// used to know if a state was seen and thus have we encountered cycles
		boolean wasStateSeen = false;

		while (n > 0) {
			if (!wasStateSeen) {
				// If this state was seen
				if (seen.containsKey(state)) {
					wasStateSeen = true;
					/*
					 * We can skip all the stages that were already seen between the recurring
					 * stages and arrive at the final days using Fast Forwarding
					 */
					n %= seen.get(state) - n;
				} else {
					// Put this previously unseen state into the seen map along with days remaining
					seen.put(state, n);
				}
			}
			if (n > 0) {
				n--;
				state = getNextDay(state);
			}
		}

		// Need to convert state back into an array
		int res[] = new int[8];
		for (int i = 7; i >= 0; i--) {
			res[i] = (state & 1);
			state = state >> 1;
		}

		return res;
	}

	public int getNextDay(int state) {
		int day = 0;
		// Since the 0th and last cell do not have 2 neighours, they will anyways be 0
		for (int i = 1; i <= 6; i++) {
			// Check for the remaining positions if their neighbours are same
			if ((state >> (i - 1) & 1) == (state >> (i + 1) & 1)) {
				int k = 1 << i;
				day = day | k;
			}
		}
		return day;
	}
}
