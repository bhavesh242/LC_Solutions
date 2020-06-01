package com.bhavesh.solutions;

public class Leetcode75 {
	public void sortColors(int[] nums) {
		int p0 = 0;
		int p2 = nums.length - 1;
		int cur = 0;

		while (cur <= p2) {
			if (nums[cur] == 0) {
				int temp = nums[p0];
				nums[p0] = 0;
				nums[cur] = temp;
				cur++;
				p0++;
			}

			else if (nums[cur] == 2) {
				int temp = nums[p2];
				nums[p2] = 2;
				nums[cur] = temp;
				p2--;
			} else {
				cur++;
			}
		}

	}
}
