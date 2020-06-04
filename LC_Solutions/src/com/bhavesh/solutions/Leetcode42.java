package com.bhavesh.solutions;

public class Leetcode42 {
	public int trap(int[] height) {
		if (height.length == 0) {
			return 0;
		}
		int left = 0;
		int right = height.length - 1;
		int lmax = height[left];
		int rmax = height[right];
		int ans = 0;
		while (left < right) {
			if (height[left] < height[right]) {
				if (lmax <= height[left]) {
					lmax = height[left];
				} else {
					ans += lmax - height[left];

				}
				left++;
			} else {
				if (rmax <= height[right]) {
					rmax = height[right];
				} else {
					ans += rmax - height[right];

				}
				right--;
			}

		}

		return ans;
	}
}
