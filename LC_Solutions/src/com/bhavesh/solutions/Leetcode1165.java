package com.bhavesh.solutions;

public class Leetcode1165 {
	public int calculateTime(String keyboard, String word) {

		int map[] = new int[26];
		int prev = 0;
		for (int i = 0; i < 26; i++) {
			map[keyboard.charAt(i) - 'a'] = i;
		}

		int sum = 0;
		for (char x : word.toCharArray()) {
			sum += Math.abs(map[x - 'a'] - prev);
			prev = map[x - 'a'];
		}

		return sum;
	}
}