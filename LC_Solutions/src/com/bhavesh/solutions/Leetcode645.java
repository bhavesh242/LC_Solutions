package com.bhavesh.solutions;

public class Leetcode645 {
	public int[] findErrorNums(int[] nums) {
		int arr[] = new int[2];

		for (int i = 0; i < nums.length; i++) {
			if (nums[Math.abs(nums[i]) - 1] < 0) {

				arr[0] = Math.abs(nums[i]);
			} else {
				nums[Math.abs(nums[i]) - 1] = -nums[Math.abs(nums[i]) - 1];
			}
		}

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) {
				arr[1] = i + 1;
				break;
			}
		}
		return arr;
	}
}