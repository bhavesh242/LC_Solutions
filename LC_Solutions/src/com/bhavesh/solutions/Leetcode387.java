package com.bhavesh.solutions;

public class Leetcode387 {
	public int firstUniqChar(String s) {

		int[] map = new int[26];
		// HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		for (char x : s.toCharArray()) {
			map[x - 'a']++;
		}

		for (int i = 0; i < s.length(); i++) {
			char x = s.charAt(i);
			if (map[x - 'a'] == 1) {
				return i;
			}
		}

		return -1;
	}
}