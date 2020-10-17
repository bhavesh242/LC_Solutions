package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Leetode406 {
	public int[][] reconstructQueue(int[][] people) {
		// Greedy Approach
		/*
		 * When considering number of Guys in front of a person, the people with smaller
		 * heights are deemed invisible to taller people, i.e. they are not counted
		 */
		/*
		 * We arrange the people in decreasing order of heights and if 2 people have the
		 * same height, then they are arranged in increasing order of people ahead of
		 * them
		 */
		Arrays.sort(people, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				if (a[0] == b[0]) {
					return a[1] - b[1];
				}
				return b[0] - a[0];
			}
		});

		/*
		 * Once we have the arrangement, we iterate over the array of (h,k) values and
		 * add people by using the k values as index
		 */
		int n = people.length;
		List<int[]> l = new ArrayList();
		for (int p[] : people) {
			l.add(p[1], p);
		}
		return l.toArray(new int[n][2]);

	}
}