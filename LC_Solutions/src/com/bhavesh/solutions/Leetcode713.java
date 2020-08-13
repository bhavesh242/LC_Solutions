package com.bhavesh.solutions;

public class Leetcode713 {
	public int numSubarrayProductLessThanK(int[] nums, int k) {
		// Since all elements in nuns are 1 or greater, no subarray can return a product
		// less than 1
		if (k <= 1) {
			return 0;
		}
		// left pointer
		int left = 0;
		// We use this to store our current product
		int product = 1;
		int count = 0;

		// Start right pointer from 0,
		for (int right = 0; right < nums.length; right++) {
			// multiply product to current element at right pointer
			product *= nums[right];

			// If the product exceeds the value of k, divide producr by element at left
			// pointer and increase left pointer by 1
			while (product >= k && left <= right) {
				product = product / nums[left++];
			}
			// Number of sub arrays between left and right added to sum (nC2)
			count += right - left + 1;
		}

		return count;

	}
}
