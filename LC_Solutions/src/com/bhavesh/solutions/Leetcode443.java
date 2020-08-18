package com.bhavesh.solutions;

public class Leetcode443 {
	public int compress(char[] chars) {
		// set read pointer to 0
		int read = 0;
		// set write pointer to 0
		int write = 0;
		int n = chars.length;
		// Start reading characters off the array
		while (read < n) {
			// Current character is read
			char curChar = chars[read];
			/*
			 * Write current character at position of write pointer and move write to the
			 * right by one
			 */
			chars[write++] = curChar;
			/*
			 * count variable is to read number of consecutive characters equal to curChar
			 */
			int count = 0;
			/*
			 * move read pointer to the right until you find consecutive characters equal to
			 * curChar
			 */
			while (read < n && chars[read] == curChar) {
				read++;
				count++;
			}
			/*
			 * if more than one consecutive character are found to be grouped together,
			 * write the count beside that character in form of a char array, use
			 * System.arraycopy
			 */
			if (count > 1) {
				char[] temp = Integer.toString(count).toCharArray();
				System.arraycopy(temp, 0, chars, write, temp.length);
				// Move write pointer one position right to the newly written digits
				write = write + temp.length;
			}
		}

		// The position of your write pointer denotes size of the new array
		return write;

	}
}
