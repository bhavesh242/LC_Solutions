package com.bhavesh.solutions;

public class Leetcode1248 {
	public int numberOfSubarrays(int[] nums, int k) {

		int[] prefix = new int[nums.length];
		int ans = 0;
		int oddCt = 0;
		int[] map = new int[nums.length + 1];
		map[0] = 1;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] % 2 != 0) {
				oddCt++;
			}
			map[oddCt]++;

			if (oddCt >= k) {
				ans += map[oddCt - k];
			}
		}

		return ans;
	}
}