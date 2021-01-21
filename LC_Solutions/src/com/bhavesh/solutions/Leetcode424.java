package com.bhavesh.solutions;

public class Leetcode424 {
	public int characterReplacement(String s, int k) {
		int left = 0;
		int maxCharCt = 0;
		int map[] = new int[26];
		int maxLen = 0;
		for (int right = 0; right < s.length(); right++) {
			map[s.charAt(right) - 'A']++;
			maxCharCt = Math.max(maxCharCt, map[s.charAt(right) - 'A']);
			while (right - left + 1 - maxCharCt > k) {
				map[s.charAt(left) - 'A']--;
				left++;
			}
			/*
			 * Trick - No need to calculate max freq again, since operation in this block
			 * will decrease the frequency and we will now be interested in a window with
			 * higher frequency than currently known i.e. why we are not changing the max
			 * value.
			 */
			maxLen = Math.max(right - left + 1, maxLen);
		}

		return maxLen;
	}
}