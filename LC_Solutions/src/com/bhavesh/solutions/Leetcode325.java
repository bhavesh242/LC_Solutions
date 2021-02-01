package com.bhavesh.solutions;

import java.util.HashMap;

public class Leetcode325 {
	public int maxSubArrayLen(int[] nums, int k) {
		// Using Hashmap + prefix sum
		// We store the indexes of the prefix sum until that point
		int sum = 0;
		int maxLen = 0;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums.length; i++) {
			/*
			 * For each number, add it to prefix sum variable "sum" in order to get sum
			 * uptil that point
			 */
			sum += nums[i];
			/*
			 * In order to find if there is a subarray that sums up to k, we need to search
			 * for it's complement in the hashmap. Complement = sum - k
			 */
			int target = sum - k;
			/*
			 * If this complement is 0, it implies, the sum until 0 to this index i is a
			 * maximal subarray summing upto k until this index i
			 */
			if (target == 0) {
				maxLen = i + 1;
			}
			// If the complement is not 0, search for a complementary suffix sum in the
			// hashmap
			// If one is found, evaluate it's length against current maxLen
			else if (map.containsKey(target)) {
				maxLen = Math.max(maxLen, i - map.get(target));
			}

			/*
			 * We always ensure we insert the farthest index into hashmap, therefore only
			 * insert this sum-index pair if it already does not exist in the map
			 */
			map.putIfAbsent(sum, i);

		}

		return maxLen;

	}
}