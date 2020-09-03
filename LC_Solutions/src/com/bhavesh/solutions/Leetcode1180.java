package com.bhavesh.solutions;

public class Leetcode1180 {
	public int countLetters(String S) {

		/*
		 * Logic : If a character repeats n times continuously in the string, it becomes
		 * a part of n(n+1)/2 substrings which have that character as 1 distinct element
		 */

		if (S.length() == 0) {
			return 0;
		}
		int sum = 1;
		int count = 1;
		char prev = S.charAt(0);
		for (int i = 1; i < S.length(); i++) {
			char cur = S.charAt(i);
			if (cur == prev) {
				count++;
			} else {

				count = 1;
				prev = cur;
			}
			sum += count;
		}
		return sum;
	}
}