package com.bhavesh.solutions;

import java.util.Stack;

public class Leetcode1990 {
	public String reverseParentheses(String s) {
		//convert string into char array for faster access
		char ans[] = s.toCharArray();
		Stack<Integer> st = new Stack<Integer>();
		for (int i = 0; i < s.length(); i++) {
			//push position of opening bracket onto stack
			if (ans[i] == '(') {
				st.push(i);
			}
			/* whenever a closing bracket is encountered , pop topmost element of stack to get 
			 * respective opening bracket postion, reverse the array between the two index positions */
			if (ans[i] == ')') {
				int idx = st.pop();
				reverseCharArray(ans, idx, i);
			}
		}
		
		//From the char array, create a stringbuffer excluding all brackets
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < ans.length; i++) {
			if (ans[i] != '(' && ans[i] != ')') {
				str.append(ans[i]);
			}
		}
		//return resultant string
		return str.toString();
	}

	private void reverseCharArray(char[] ans, int idx, int i) {
		int start = idx + 1;
		int end = i - 1;
		while (start < end) {
			char temp = ans[start];
			ans[start] = ans[end];
			ans[end] = temp;
			start++;
			end--;
		}
	}
}
