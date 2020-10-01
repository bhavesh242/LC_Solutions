package com.bhavesh.solutions;

import java.util.HashSet;

public class Leetcode3 {
	public int lengthOfLongestSubstring(String s) {

		// Declare maxLen to find the maximum string length
		int maxLen = 0;
		// set keeps track of unique characters, in case we encounter duplicates
		HashSet<Character> set = new HashSet<Character>();
		// Two pointers, left and right, right moves every turn, left moves on spotting
		// of a duplicate
		int left = 0;

		for (int right = 0; right < s.length(); right++) {
			char cur = s.charAt(right);
			if (set.contains(cur)) {
				while (s.charAt(left) != cur) {
					set.remove(s.charAt(left));
					left++;
				}
				set.remove(s.charAt(left));
				left++;
			}
			set.add(s.charAt(right));
			maxLen = Math.max(right - left + 1, maxLen);
		}

		return maxLen;
	}
}
