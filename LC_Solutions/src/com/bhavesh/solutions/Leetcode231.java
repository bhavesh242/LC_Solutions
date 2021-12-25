package com.bhavesh.solutions;

public class Leetcode231 {
	public boolean isPowerOfTwo(int n) {
		if (n == 0) {
			return false;
		}

		// Need to convert input to long to avoid overflow for testcases like
		// -2147483648
		long N = (long) n;
		return (N & (-N)) == N;
	}
}