package com.bhavesh.solutions;

public class Leetcode42 {
	// Approach 1 : keep pushing leftmax and rightmax
	public int trap(int[] height) {
		int left = 0, right = height.length - 1;
		int sum = 0;
		int leftMax = 0, rightMax = 0;
		while (left < right) {
			if (height[left] < height[right]) {
				if (height[left] > leftMax) {
					leftMax = height[left++];
				} else {
					sum += leftMax - height[left++];
				}
			} else {
				if (height[right] > rightMax) {
					rightMax = height[right--];
				} else {
					sum += rightMax - height[right--];
				}
			}

		}
		return sum;
	}

	// Approach 2 : Finding min of 2 ends and moving another till you find a bigger
	// wall
	public int trap2(int[] height) {

		int left = 0, right = height.length - 1;
		int sum = 0;
		while (left < right) {
			int min = Math.min(height[left], height[right]);
			while (left < right && height[left] <= min) {
				sum += min - height[left++];
			}
			while (left < right && height[right] <= min) {
				sum += min - height[right--];
			}

		}

		return sum;
	}

	// Apprach 3 :Re-write of Apprach 2, move pointers until you find a new lower
	public int trap3(int[] height) {

		int left = 0, right = height.length - 1;
		int sum = 0;
		int level = 0;
		while (left < right) {
			int lower = height[height[left] < height[right] ? left++ : right--];
			level = Math.max(lower, level);
			sum += level - lower;
		}

		return sum;
	}
}