package com.bhavesh.solutions;

public class Leetcode1658 {
	public int minOperations(int[] nums, int x) {
		int sum = 0;
		// Calculate the total of the nums
		for (int i : nums) {
			sum += i;
		}

		/*
		 * The theme behind the solution for this type of problem is to transform it
		 * into another problem In this case, finding minimum operations to reduce x to
		 * 0 is eqvuvalent to finding a maximum subarray such that it sums to (total -
		 * x)
		 */
		/*
		 * Since we are provided with the constraint that all elements in the array are
		 * positive, we dont have to use a hashmap, instead, we could be using 2 pointer
		 * method
		 */
		// As discussed, our target is total - x;
		int target = sum - x;
		int maxLen = -1;
		int left = 0;
		int total = 0;
		for (int right = 0; right < nums.length; right++) {
			total += nums[right];
			// Left<=right as total = 0 is also a possibility -> target = 0 -> sum of all
			// nums = x
			// Example case : nums : [1,1,1,1,1], x = 5
			/*
			 * If for above case, we dont write left<=right, maxlen will always be -1 as we
			 * would not be apple to total = target = 0 in this case
			 */
			while (total > target && left <= right) {
				total -= nums[left++];
			}
			if (total == target) {
				maxLen = Math.max(maxLen, right - left + 1);
			}
		}

		return maxLen == -1 ? -1 : nums.length - maxLen;
	}
}