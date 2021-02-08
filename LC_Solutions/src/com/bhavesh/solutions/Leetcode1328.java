package com.bhavesh.solutions;

public class Leetcode1328 {
	public String breakPalindrome(String palindrome) {

		char res[] = palindrome.toCharArray();

		// Special Case
		if (res.length == 1) {
			return "";
		}
		for (int i = 0; i < res.length / 2; i++) {
			if (res[i] != 'a') {
				res[i] = 'a';
				return new String(res);
			}
		}

		// This happens when we have number of a's >= palindrome.length-1
		// In this case, lexicographically smallest string would be one with b in last
		res[res.length - 1] = 'b';
		return new String(res);
	}
}