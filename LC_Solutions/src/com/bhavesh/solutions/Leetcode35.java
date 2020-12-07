package com.bhavesh.solutions;

public class Leetcode35 {
	public int searchInsert(int[] nums, int target) {
		int mid, left = 0, right = nums.length - 1;
		while (left <= right) {
			mid = left + (right - left) / 2;
			if (nums[mid] == target) {
				return mid;
			}
			if (target < nums[mid]) {
				right = mid - 1;
			} else
				left = mid + 1;
		}
		return left;
	}
}

/*
 * 
 * public static void main(String[] args) { int nums[] = new int[]
 * {5,5,5,5,5,5,5,7,7,8,8,100,100}; int target =12; int firstpos = -1; int
 * pivot, left = 0, right = nums.length - 1; while (left <= right) { pivot =
 * left + (right - left) / 2; if (nums[pivot] == target) { firstpos = pivot;
 * right = pivot-1; } else if (target < nums[pivot]) { right = pivot - 1; } else
 * { left = pivot + 1; } } System.out.println(firstpos + " " + left); } }
 * 
 */
// General Binary Search Template, left stores the point of insertion/first
// presence