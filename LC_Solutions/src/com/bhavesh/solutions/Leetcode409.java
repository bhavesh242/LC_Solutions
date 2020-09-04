package com.bhavesh.solutions;

public class Leetcode409 {
	public int longestPalindrome(String s) {
		int[] map = new int[128];
		for (char x : s.toCharArray()) {
			map[x]++;
		}
		int ans = 0;
		int oddFlag = 0;
		for (int count : map) {
			if (count % 2 == 1) {
				ans += (count - 1);
				oddFlag = 1;
			} else {
				ans += count;
			}
		}
		return ans + oddFlag;
	}
}