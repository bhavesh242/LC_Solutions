package com.bhavesh.solutions;

public class Leetcode159 {
	// Use Sliding window method
	// Increment right pointer for every turn and calculate len of SubString agains
	// max length
	// Once you encounter more than 2 distinct characters, increase left pointer
	// until you reach 2 distinct characters again
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		int[] map = new int[128];
		int left = 0;
		int charct = 0;
		int len = 0;
		for (int right = 0; right < s.length(); right++) {
			if (++map[s.charAt(right)] == 1) {
				charct++;
			}
			while (charct > 2) {
				if (--map[s.charAt(left++)] == 0) {
					charct--;
				}
			}
			len = Math.max(len, right - left + 1);
		}
		return len;
	}
}