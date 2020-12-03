package com.bhavesh.solutions;

public class Leetcode26 {
	public int removeDuplicates(int[] nums) {
		// We use 2 pointers
		// Current Pointer to store index of cuurent element to be stored
		// Current pointer will also help us calculate resulting array length
		int cur = 0;
		// Another pointer will keep sweep the array for duplicates
		for (int dup = 1; dup < nums.length; dup++) {
			if (nums[dup] != nums[dup - 1]) {
				nums[++cur] = nums[dup];
			}
		}

		// If the last element was stored at index n, it means there are n+1 elements
		// unique elements in the array
		return cur + 1;
	}
}