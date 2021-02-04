package com.bhavesh.solutions;

public class Leetcode849 {
	public int maxDistToClosest(int[] seats) {

		int maxDist = Integer.MIN_VALUE;
		int prevZero = -1, numZeroes = 0;
		int i = 0;
		for (i = 0; i < seats.length; i++) {
			if (seats[i] == 1) {
				prevZero = i;
				numZeroes = 0;
			} else {
				numZeroes++;
				if (prevZero == -1) {
					maxDist = numZeroes;
				} else {
					maxDist = Math.max(maxDist, (numZeroes + 1) / 2);

				}
			}
		}
		if (seats[i - 1] == 0) {
			maxDist = Math.max(numZeroes, maxDist);
		}

		return maxDist;

	}
}