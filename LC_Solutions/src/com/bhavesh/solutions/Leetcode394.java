package com.bhavesh.solutions;

import java.util.Stack;

public class Leetcode394 {
	public String decodeString(String s) {
		// index points to starting index of the stack
		int index = 0;
		// Keep a stack for keeping number of counts for numbers that appear in string
		Stack<Integer> counts = new Stack<Integer>();
		// keep a stack to store resultant strings
		Stack<String> strs = new Stack<String>();
		// We use res to store our final output
		StringBuffer res = new StringBuffer();
		// travese whole string
		while (index < s.length()) {
			// If encountered character is digit, read the entire number anpush it to counts
			// stack
			if (Character.isDigit(s.charAt(index))) {
				int count = 0;
				while (Character.isDigit(s.charAt(index))) {
					count = count * 10 + (s.charAt(index) - '0');
					index++;
				}
				counts.push(count);
			}
			/*
			 * If an opening bracket is encountered, push current string to strs stack and
			 * make current string to empty
			 */
			else if (s.charAt(index) == '[') {
				strs.push(res.toString());
				res.setLength(0);
				index++;
			}
			/*
			 * if closing bracket is encountered, pop off the top of the count stack and
			 * multiply current string those many times, then append to the string popped
			 * off from top of the strs stack
			 */
			else if (s.charAt(index) == ']') {
				StringBuffer temp = new StringBuffer(strs.pop());
				int ct = counts.pop();
				for (int i = 0; i < ct; i++) {
					temp.append(res);
				}
				res = temp;
				index++;
			}
			// If the character at index is alphabet, simply append it to string
			else {
				res.append(s.charAt(index));
				index++;
			}
		}

		return res.toString();
	}
}
