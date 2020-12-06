package com.bhavesh.solutions;

public class Leetcode34 {
	int a[];
	int target;

	public int[] searchRange(int[] nums, int target) {
		a = nums;
		this.target = target;
		return new int[] { binSearch(true), binSearch(false) };
	}

	public int binSearch(boolean first) {
		int l = 0, r = a.length - 1;
		int ans = -1;
		while (l <= r) {
			int mid = l + (r - l) / 2;
			if (a[mid] == target) {
				ans = mid;
				if (first) {
					r = mid - 1;
				} else {
					l = mid + 1;
				}
			} else if (a[mid] > target) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}

		return ans;
	}
}