package com.bhavesh.solutions;

public class Leetcode6 {
	public String convert(String s, int numRows) {
		if (numRows == 1) {
			return s;
		}
		StringBuffer op = new StringBuffer();
		int cycle = 2 * numRows - 2;
		int n = s.length();
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j + i < n; j = j + cycle) {
				op.append(s.charAt(i + j));
				if (i != 0 && i != numRows - 1 && j + cycle - i < n) {
					op.append(s.charAt(j + cycle - i));
				}
			}
		}

		return op.toString();
	}
}
