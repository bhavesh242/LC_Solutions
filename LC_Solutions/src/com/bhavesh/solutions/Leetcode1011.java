package com.bhavesh.solutions;

public class Leetcode1011 {
	public int shipWithinDays(int[] weights, int days) {
		int binMin = 0, binMax = 0;
		for (int w : weights) {
			binMax = binMax + w;
			binMin = Math.max(binMin, w);
		}

		return binarySearch(weights, days, binMin, binMax);
	}

	public int binarySearch(int[] weights, int days, int l, int r) {
		while (l < r) {
			int mid = l + (r - l) / 2;
			boolean feaseLow = isFeasible(weights, days, mid);
			if (feaseLow) {
				r = mid;
			} else {
				l = mid + 1;
			}
		}
		return l;
	}

	public boolean isFeasible(int[] weights, int days, int capacity) {
		int remCap = capacity;
		int remDays = days;
		for (int i = 0; i < weights.length - 1; i++) {
			if (remDays == 0 || weights[i] > capacity) {
				return false;
			}
			remCap = remCap - weights[i];
			if (remCap < weights[i + 1]) {
				remDays--;
				remCap = capacity;

			}
		}

		return remCap >= weights[weights.length - 1] && remDays >= 1;
	}
}