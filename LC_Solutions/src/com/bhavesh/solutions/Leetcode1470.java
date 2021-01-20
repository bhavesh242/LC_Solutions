package com.bhavesh.solutions;

public class Leetcode1470 {
	public int[] shuffle(int[] nums, int n) {
		int left = 0;
		int right = n;
		int newArr[] = new int[nums.length];
		for (int i = 0; i < newArr.length; i++) {
			if (i % 2 == 0) {
				newArr[i] = nums[left++];
			} else {
				newArr[i] = nums[right++];
			}
		}

		return newArr;
	}
}