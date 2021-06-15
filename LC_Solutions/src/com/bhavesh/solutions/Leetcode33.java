package com.bhavesh.solutions;

public class Leetcode33 {

	class Sol_33_1 {
		// Approach 1 : find the index of the lowest element and begin binary search
		// from that element.
		public int search1(int[] nums, int target) {
			int n = nums.length;
			int l = 0, r = n - 1;
			while (l < r) {
				int mid = l + (r - l) / 2;
				if (nums[mid] > nums[r]) {
					l = mid + 1;
				} else {
					r = mid;
				}
			}

			int start = l;
			l = 0;
			r = nums.length - 1;
			while (l <= r) {
				int mid = l + (r - l) / 2;

				int rotmid = (mid + start) % n;
				if (nums[rotmid] == target) {
					return rotmid;
				} else if (nums[rotmid] > target) {
					r = mid - 1;
				} else {
					l = mid + 1;
				}
			}

			return -1;
		}
	}

	// Approach 2
	class Sol_33_2 {
		public int search(int[] nums, int target) {
			int n = nums.length;
			int l = 0, r = n - 1;
			while (l <= r) {
				int mid = l + (r - l) / 2;
				// If target is found, return index
				if (nums[mid] == target) {
					return mid;

					// If left side of the mid is monotonic, Search if target lies in this monotonic
					// range
				} else if (nums[l] <= nums[mid]) {
					// If it does, your search space becomes left to mid, otherwise mid+1 to right
					if (nums[l] <= target && nums[mid] >= target) {
						r = mid;
					} else {
						l = mid + 1;
					}

					// If left right of the mid is monotonic, Search if target lies in this
					// monotonic range
				} else {
					if (nums[mid] <= target && nums[r] >= target) {
						// If it does, your search space becomes mid to right, otherwise left to mid-1
						l = mid;
					} else {
						r = mid - 1;
					}
				}
			}

			return -1;
		}
	}

}