package com.bhavesh.solutions;

public class Leetcode1347 {
	public int minSteps(String s, String t) {

		int[] freq = new int[26];
		for (char x : s.toCharArray()) {
			freq[x - 'a']++;
		}

		int count = 0;
		for (int i = 0; i < t.length(); i++) {
			if (freq[t.charAt(i) - 'a'] > 0) {
				freq[t.charAt(i) - 'a']--;
			} else {
				count++;
			}
		}

		return count;
	}
}
