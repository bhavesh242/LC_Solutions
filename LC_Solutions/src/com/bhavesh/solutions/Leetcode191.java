package com.bhavesh.solutions;

public class Leetcode191 {
	
	public int hammingWeight(int n) {
		int ct = 0;
		while (n != 0) {
			ct++;
			n = n & (n - 1);
		}
		return ct;
	}
}