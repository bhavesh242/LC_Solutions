package com.bhavesh.solutions;

public class Leetcode287 {
	public int findDuplicate(int[] nums) {
		// Both tortoise and hare point to first index
		int tort = nums[0], hare = nums[0];

		// Make Hare move twice the speed of tortoise and return first point of
		// intersection
		do {
			tort = nums[tort];
			hare = nums[nums[hare]];

		} while (tort != hare);

		// set tortoise to first index
		tort = nums[0];
		// Make both tortoise and hare move one position until they collide at one point
		// this point is the point of cycle (duplicate in this case)
		while (tort != hare) {
			tort = nums[tort];
			hare = nums[hare];
		}

		return tort;
	}
}