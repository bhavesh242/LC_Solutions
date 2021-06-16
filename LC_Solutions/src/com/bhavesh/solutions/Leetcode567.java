package com.bhavesh.solutions;

public class Leetcode567 {
	class Sol_567_1 {
		public boolean checkInclusion(String s1, String s2) {

			// Basic Sliding window, same as 438
			int[] map = new int[26];
			for (char x : s1.toCharArray()) {
				map[x - 'a']++;
			}

			int left = 0;
			for (int right = 0; right < s2.length(); right++) {
				map[s2.charAt(right) - 'a']--;
				while (map[s2.charAt(right) - 'a'] < 0) {
					map[s2.charAt(left++) - 'a']++;
				}

				if (right - left + 1 == s1.length()) {
					return true;
				}
			}

			return false;
		}
	}

	class Sol_567_2 {
		public boolean checkInclusion(String s1, String s2) {
			int map[] = new int[26];
			for (int x : s1.toCharArray()) {
				map[x - 'a']++;
			}
			int len = s1.length();
			int left = 0;
			int count = 0;
			for (int right = 0; right < s2.length(); right++) {
				if (map[s2.charAt(right) - 'a']-- > 0) {
					count++;
				}
				while (right - left + 1 > len) {
					if (map[s2.charAt(left++) - 'a']++ >= 0) {
						count--;
					}
				}

				if (count == len) {
					return true;
				}
			}

			return false;
		}
	}
}
