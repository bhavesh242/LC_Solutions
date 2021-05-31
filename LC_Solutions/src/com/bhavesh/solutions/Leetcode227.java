package com.bhavesh.solutions;

import java.util.Stack;

public class Leetcode227 {

}

class Sol_227_1 {

	public int calculate(String s) {

		// The idea is to immediately process all multiplications and divisions and
		// store the additions and subtractions into a stack
		Stack<Integer> st = new Stack<Integer>();
		int res = 0;
		int num = 0;
		char sign = '+';

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c)) {
				num = num * 10 + (c - '0');
			}

			if ((!Character.isDigit(c) && c != ' ') || (i == s.length() - 1)) {
				if (sign == '+') {
					st.push(num);
				} else if (sign == '-') {
					st.push(-num);
				} else if (sign == '*') {
					st.push(st.pop() * num);
				} else if (sign == '/') {
					st.push(st.pop() / num);
				}

				sign = c;
				num = 0;
			}
		}

		for (int x : st) {
			res += x;
		}

		return res;
	}
}