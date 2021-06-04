package com.bhavesh.solutions;

public class Leetcode45 {
	public int jump(int[] nums) {

		/* We follow a greedy approach to get the minimum number of steps */
		int jump = 0;
		/*
		 * This is to store what is the farthest we can go based based on what ladder we
		 * climb based on all the the indices we have covered
		 */
		int farthest = 0;

		/* This is to store how far we can go based on the current ladder we are on */

		int currentJump = 0;

		/*
		 * Current jump and farthest is 0 because we start at 0th index and that is
		 * farthest we can go initially
		 */

		/*
		 * We iterate till the second last index only because we dont need to jump if we
		 * reach the last index
		 */
		for (int i = 0; i < nums.length - 1; i++) {
			// Look what is the farthest you can jump
			farthest = Math.max(farthest, i + nums[i]);

			/*
			 * If we have reached to the end of our current jump we need to switch to
			 * another far reaching ladder
			 */
			// Greedily, we jump to the ladder that reaches farthest
			if (i == currentJump) {
				jump++;
				currentJump = farthest;
			}
		}

		return jump;
	}
}