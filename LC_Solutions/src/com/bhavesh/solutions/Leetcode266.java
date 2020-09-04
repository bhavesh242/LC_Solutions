package com.bhavesh.solutions;

public class Leetcode266 {
	public boolean canPermutePalindrome(String s) {

		// Create a character map of 128 length
		int[] map = new int[128];
		// Add counts to map
		for (char x : s.toCharArray()) {
			map[x]++;
		}
		int count = 0;
		// For palindrome, there should be at most one character that has an odd count
		for (int i = 0; i < 128; i++) {
			count = count + map[i] % 2;
		}
		return count <= 1;
	}
}