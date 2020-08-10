package com.bhavesh.solutions;

public class Leetcode11 {
	public int maxArea(int[] height) {
		/*
		 * We follow the 2 pointer approach, we start from the widest container and
		 * decrease width in search for bigger container heights
		 */
		// Area = (min of the 2 wall heights) * (distance between the 2 walls)
		// Extreme left end
		int l = 0;
		// Extreme right end
		int r = height.length - 1;
		// Max Area will be stored in this variable (maxArea >=0)
		int maxArea = 0;
		while (l < r) {
			// Calculate Current Area and compare with max Area
			maxArea = Math.max(maxArea, (r - l) * Math.min(height[l], height[r]));
			// If left wall is shorter than right wall move towards right in search for
			// longer wall
			if (height[l] < height[r]) {
				l++;
			} else {
				r--;
			}
		}
		return maxArea;
	}
}
