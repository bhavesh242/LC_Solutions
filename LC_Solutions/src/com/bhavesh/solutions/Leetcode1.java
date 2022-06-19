package com.bhavesh.solutions;

import java.util.HashMap;

public class Leetcode1 {
	public int[] twoSum(int[] nums, int target) {

		HashMap<Integer, Integer> table = new HashMap<>();

		// for(int i=0; i<nums.length; i++){
		// table.put(nums[i], i);
		// }

		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];

			if (table.containsKey(complement) && table.get(complement) != i) {
				return new int[] { i, table.get(complement) };
			}

			table.put(nums[i], i);
		}

		throw new IllegalArgumentException("No solution");
	}
}