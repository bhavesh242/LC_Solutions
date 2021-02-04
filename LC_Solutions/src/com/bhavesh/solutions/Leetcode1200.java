package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode1200 {
	public List<List<Integer>> minimumAbsDifference(int[] arr) {
		Arrays.sort(arr);
		ArrayList<List<Integer>> ans = new ArrayList<List<Integer>>();
		int minDiff = Integer.MAX_VALUE;
		for (int i = 1; i < arr.length; i++) {
			minDiff = Math.min(minDiff, arr[i] - arr[i - 1]);
		}

		for (int i = 1; i < arr.length; i++) {
			if (minDiff == arr[i] - arr[i - 1]) {
				ArrayList<Integer> pair = new ArrayList<Integer>();
				pair.add(arr[i - 1]);
				pair.add(arr[i]);
				ans.add(pair);
			}
		}

		return ans;

	}
}