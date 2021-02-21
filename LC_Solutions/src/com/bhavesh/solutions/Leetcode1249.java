package com.bhavesh.solutions;

import java.util.Stack;

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

	// Approach 2 : Stacks
	public String minRemoveToMakeValid1(String s) {

		char ans[] = s.toCharArray();
		int i = 0;
		Stack<Integer> st = new Stack<Integer>();
		while (i < ans.length) {
			if (ans[i] == '(') {
				st.push(i);
				ans[i] = '#';
			} else if (ans[i] == ')') {
				if (st.isEmpty()) {
					ans[i] = '#';
				} else {
					ans[st.pop()] = '(';
				}
			}
			i++;
		}
		StringBuffer str = new StringBuffer();
		for (int j = 0; j < ans.length; j++) {
			if (ans[j] == '#')
				continue;
			str.append(ans[j]);
		}

		return str.toString();
	}
}
