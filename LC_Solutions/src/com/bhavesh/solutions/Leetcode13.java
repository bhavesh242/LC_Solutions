package com.bhavesh.solutions;

public class Leetcode13 {

	//Approach 1
	public int romanToInt(String s) {

		char[] romanArray = s.toCharArray();
		int sum = 0;
		for (int i = 0; i < romanArray.length; i++) {
			if (i < romanArray.length - 1 && getNum(romanArray[i]) < getNum(romanArray[i + 1])) {
				sum = sum - getNum(romanArray[i]);
			} else {
				sum = sum + getNum(romanArray[i]);
			}
		}

		return sum;

	}
	
	//Approach 2
	public int romanToInt2(String s) {

		int i = 0;
		int sum = 0;
		while (i < s.length() - 1) {
			if (getNum(s.charAt(i)) < getNum(s.charAt(i + 1))) {
				sum += getNum(s.charAt(i + 1)) - getNum(s.charAt(i));
				i += 2;
			} else {
				sum += getNum(s.charAt(i));
				i++;
			}
		}

		int retr = i == s.length() - 1 ? getNum(s.charAt(i)) : 0;

		return sum + retr;
	}

	public int getNum(char c) {
		switch (c) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;

		}

		return 0;
	}
}
