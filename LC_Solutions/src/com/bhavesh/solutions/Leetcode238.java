package com.bhavesh.solutions;

public class Leetcode238 {
	public int[] productExceptSelf(int[] nums) {
		//Build a left array where left[0] = 1 and left[i] = product of elements nums[0] through nums[i-1]
		int left[] = new int[nums.length];
		left[0] = 1;
		for (int i = 1; i < nums.length; i++) {
			left[i] = left[i - 1] * nums[i - 1];
		}

		//now start doing the same process from right, left[i] = left[i] * (product nums[n] to nums[i+1]) exclusing nums[i]
		int R = 1;
		for (int j = nums.length - 1; j >= 0; j--) {
			left[j] = left[j] * R;
			R = R * nums[j];
		}
		//resultant array is solution
		return left;
	}
}
