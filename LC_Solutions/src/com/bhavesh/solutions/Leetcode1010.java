package com.bhavesh.solutions;

public class Leetcode1010 {
	public int numPairsDivisibleBy60(int[] time) {
		int[] map = new int[60];
		int sum = 0;
		for (int i = 0; i < time.length; i++) {
			int rem = time[i] % 60;
			if (rem != 0) {
				sum += map[60 - rem];
			} else {
				sum += map[0];
			}
			map[rem]++;
		}

		return sum;
	}
}