package com.bhavesh.solutions;

public class Leetcode55 {

}

//Greedy Method
class Sol55_1 {
	public boolean canJump(int[] nums) {
		if (nums.length <= 1)
			return true;
		int maxWindow = nums[0];
		int currentMax;
		int i;
		for (i = 0; i < nums.length - 1; i++) {
			currentMax = i + nums[i];
			if (maxWindow < currentMax) {
				maxWindow = currentMax;
			}
			if (maxWindow >= nums.length - 1) {
				return true;
			}
			if (i == maxWindow) {
				return false;
			}
		}
		return true;
	}
}

//DFS with memoization
enum Index {
	GOOD, BAD, UNKNOWN
}

class Sol_55_2 {
	Index[] memo;

	public boolean canJumpFromPosition(int position, int[] nums) {
		if (memo[position] != Index.UNKNOWN) {
			return memo[position] == Index.GOOD ? true : false;
		}

		int furthestJump = Math.min(position + nums[position], nums.length - 1);
		for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
			if (canJumpFromPosition(nextPosition, nums)) {
				memo[position] = Index.GOOD;
				return true;
			}
		}

		memo[position] = Index.BAD;
		return false;
	}

	public boolean canJump(int[] nums) {
		memo = new Index[nums.length];
		for (int i = 0; i < memo.length; i++) {
			memo[i] = Index.UNKNOWN;
		}
		memo[memo.length - 1] = Index.GOOD;
		return canJumpFromPosition(0, nums);
	}
}