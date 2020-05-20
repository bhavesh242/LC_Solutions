package com.bhavesh.solutions;

public class Leetcode1249 {
	public String minRemoveToMakeValid(String s) {
		StringBuffer s1 = new StringBuffer();
		int open = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				open++;
			} else if (s.charAt(i) == ')') {
				if (open == 0) {
					continue;
				}
				open--;
			}
			s1.append(s.charAt(i));
		}

		StringBuilder s2 = new StringBuilder();
		for (int i = s1.length() - 1; i >= 0; i--) {
			if (s1.charAt(i) == '(' && open-- > 0) {
				continue;
			}
			s2.append(s1.charAt(i));
		}

		return s2.reverse().toString();
	}

}
