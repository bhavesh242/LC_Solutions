package com.bhavesh.solutions;

public class Leetcode171 {

	public int titleToNumber(String columnTitle) {
		int col = 0;
		int aVal = 'A' - 1;
		for (char c : columnTitle.toCharArray()) {
			col = col * 26 + c - aVal;
		}
		return col;
	}
}