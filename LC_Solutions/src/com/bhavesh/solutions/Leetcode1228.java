package com.bhavesh.solutions;

public class Leetcode1228 {
	public int missingNumber(int[] arr) {
		//Binary Search
		int n = arr.length;
		int diff = (arr[n - 1] - arr[0]) / n;
		int left = 0, right = n - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;
			/*
			 * If Middle element matches with Arithmetic progression's calculations, it
			 * means that left-mid indexes are correct and we need to search in indexes
			 * starting from mid+1
			 */
			if (arr[mid] == arr[0] + mid * diff) {
				left = mid + 1;
			}
			// OtherWise there is a problem in indexes left-mid including mid itself, so
			// narrow the search accordingly
			else {
				right = mid;
			}
		}

		// Eventually we would land on that index for which the element was missing
		// The missing element can then be calculated using A.P. formula
		return arr[0] + left * diff;
	}
}
