package com.bhavesh.solutions;

public class Leetcode1306 {
	// Simple DFS with in place memoization
	public boolean canReach(int[] arr, int start) {
		if (start < 0 || start >= arr.length) {
			return false;
		}
		if (arr[start] == 0) {
			return true;
		}
		// Mark the index as visited by setting the element as negative
		if (arr[start] < 0) {
			return false;
		}
		int jump = arr[start];
		arr[start] = -arr[start];
		return canReach(arr, start + jump) || canReach(arr, start - jump);
	}
}