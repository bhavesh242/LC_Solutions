package com.bhavesh.solutions;

public class Leetcode647 {
	char[] S;

	// Check Palindromes from Middle Algorithm
	public int countSubstrings(String s) {
		int count = 0;
		S = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			count += checkPali(i, i) + checkPali(i, i + 1);
		}

		return count;
	}

	public int checkPali(int l, int r) {
		int count = 0;
		while (l >= 0 && r < S.length) {
			if (S[l--] == S[r++]) {
				count++;
			} else {
				break;
			}
		}
		return count;
	}
}	