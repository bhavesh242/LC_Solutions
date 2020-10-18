package com.bhavesh.solutions;

public class Leetcode448 {
	public List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> op = new ArrayList();
		if (nums == null || nums.length == 0) {
			return op;
		}
		// We negate the absolute of the integers to mark them as seen
		for (int i = 0; i < nums.length; i++) {
			int index = Math.abs(nums[i]) - 1;
			nums[index] = 0 - Math.abs(nums[index]);
		}
		/*
		 * All the indexes whose numbers are still positive implies those numbers we not
		 * present in array, so add them to output
		 */
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) {
				op.add(i + 1);
			}
		}
		return op;
	}
}