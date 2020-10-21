package com.bhavesh.solutions;

public class Leetcode453 {
	public int minMoves(int[] nums) {
		// Incrementing n-1 elements by one is equivalent to decreasing 1 element by one
		// In our case, to make all numbers equal we must decrement all elements to the
		// standard of min element
		// Record min
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			min = Math.min(min, nums[i]);
		}
		int sum = 0;
		// Difference between min and each number is number of steps needed by that
		// number to make array equal
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i] - min;
		}

		return sum;
	}
}