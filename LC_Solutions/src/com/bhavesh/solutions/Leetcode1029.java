package com.bhavesh.solutions;

import java.util.Arrays;
import java.util.Comparator;

public class Leetcode1029 {
	public int twoCitySchedCost(int[][] costs) {

		/*
		 * We need to optimize the cost difference we incur when we decide to send a
		 * candidate to city A instead of City B
		 */
		/*
		 * That way we can optimize the first candidates cost and select first N costs
		 * as cost of flying candidate A and the next N as cost of flying candidate B
		 */
		Arrays.sort(costs, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return a[0] - a[1] - (b[0] - b[1]);
			}
		});
		int sum = 0;
		int n = costs.length / 2;
		for (int i = 0; i < n; i++) {
			sum += costs[i][0] + costs[i + n][1];
		}

		return sum;
	}
}