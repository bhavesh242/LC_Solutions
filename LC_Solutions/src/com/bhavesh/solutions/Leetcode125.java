package com.bhavesh.solutions;

public class Leetcode125 {
	public boolean isPalindrome(String s) {
		int left = 0;
		int right = s.length() - 1;
		while (left < right) {

			while (left < s.length() && !(Character.isLetter(s.charAt(left)) || Character.isDigit(s.charAt(left)))) {
				left++;
			}
			if (left == s.length()) {
				break;
			}
			while (right >= 0 && !(Character.isLetter(s.charAt(right)) || Character.isDigit(s.charAt(right)))) {
				right--;
			}

			if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
				return false;
			}
			left++;
			right--;

		}

		return true;
	}
}