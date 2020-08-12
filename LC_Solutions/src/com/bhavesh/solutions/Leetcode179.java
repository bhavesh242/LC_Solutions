package com.bhavesh.solutions;

import java.util.Arrays;
import java.util.Comparator;

public class Leetcode179 {

	public String largestNumber(int[] nums) {

		// Convert the integer array to String array
		String[] sorted = new String[nums.length];

		// For each int, use Integer.toString() method for conversion
		for (int x = 0; x < nums.length; x++) {
			sorted[x] = Integer.toString(nums[x]);
		}

		/*
		 * Custom Comparator, checks for any two string, which combination of Strings
		 * will account for a higher value
		 */
		Comparator<String> cmp = new Comparator<String>() {
			public int compare(String A, String B) {
				String od1 = A + B;
				String od2 = B + A;
				return od2.compareTo(od1);
			}
		};
		// Sort the String array using our custome comparator
		Arrays.sort(sorted, cmp);
		/*
		 * if the sorted array starts with a zero, it means the largest element in that
		 * array is zero and thus entire array has zeroes only
		 */
		if (sorted[0].equals("0")) {
			return "0";
		}
		// Append the sorted array into a string, use StringBuffer for memory efficiency
		StringBuffer str = new StringBuffer();
		for (String x : sorted) {
			str.append(x);
		}

		// Return output string
		return str.toString();
	}
}