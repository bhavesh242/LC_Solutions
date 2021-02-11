package com.bhavesh.solutions;

public class Leetcode242 {
	public boolean isAnagram(String s, String t) {

		int[] map = new int[26];
		int ct = 0;
		for (char x : s.toCharArray()) {
			if (map[x - 'a'] == 0) {
				ct++;
			}
			map[x - 'a']++;
		}

		for (char x : t.toCharArray()) {
			if (map[x - 'a'] == 0) {
				return false;
			}
			if (map[x - 'a'] == 1) {
				ct--;
			}
			map[x - 'a']--;
		}

		return ct == 0;
	}
}