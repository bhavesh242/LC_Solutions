package com.bhavesh.solutions;

public class Leetcode738 {
	public int monotoneIncreasingDigits(int N) {
		//convert the string into a character array
		char[] s = String.valueOf(N).toCharArray();
		int i = 1;
		
		//scroll to the digit where the digits stop being monotonically increasing
		while (i < s.length && s[i - 1] <= s[i]) {
			i++;
		}
		//if all digits are monotonically increasing, you can directly return N as your answer
		if (i < s.length) {
			//from the previous digit of the digit where monotonicity stops, start decreasing that digit by one
			//as you decrease, keep checking if the new decreased digit is now less than it's previous digit
			//if that is the case, decrease the previous digit as well, do this in a while loop
			while (i > 0 && i < s.length && s[i - 1] > s[i]) {
				s[--i]--;

			}
			//for your i+1th position, make all other digits to 9
			for (int j = i + 1; j < s.length; j++) {
				s[j] = '9';
			}
		}
		//convert char array to int and return result.
		return Integer.parseInt(String.valueOf(s));
	}
}
