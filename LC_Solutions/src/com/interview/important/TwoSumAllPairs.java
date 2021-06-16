package com.interview.important;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TwoSumAllPairs {
	public static void main(String[] args) {
		int arr[] = new int[] { 1, 5, 7, -1, 1, 5 };
		int target = 6;
		List<int[]> allPairs = getAllPairs(arr, target);
		for (int[] pair : allPairs) {
			System.out.println(pair[0] + "," + pair[1]);
		}
	}

	public static List<int[]> getAllPairs(int[] arr, int target) {
		List<int[]> res = new ArrayList<int[]>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < arr.length; i++) {
			int rem = target - arr[i];
			if (map.getOrDefault(rem, 0) != 0) {
				res.add(new int[] { arr[i], rem });
				map.put(rem, map.get(rem) - 1);
			} else {
				map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
			}
		}
		return res;
	}
}
