package com.bhavesh.solutions;

import java.util.Arrays;

public class Leetcode881 {
	public int numRescueBoats(int[] people, int limit) {
		Arrays.sort(people);
		int count = 0;
		int left = 0;
		int right = people.length - 1;
		while (left <= right) {
			count++;
			if (people[left] + people[right] <= limit) {
				left++;
			}
			right--;
		}

		return count;
	}
}