package com.bhavesh.solutions;

import java.util.Stack;

public class Leetcode150 {
	public int evalRPN(String[] tokens) {
		// Use stack to perform operations
		Stack<Integer> stack = new Stack();
		int a, b, result = 0;
		for (int i = 0; i < tokens.length; i++) {
			switch (tokens[i]) {
			case "*":
				a = stack.pop();
				b = stack.pop();
				result = a * b;
				break;
			case "/":
				a = stack.pop();
				b = stack.pop();
				result = b / a;
				break;
			case "+":
				a = stack.pop();
				b = stack.pop();
				result = a + b;
				break;
			case "-":
				a = stack.pop();
				b = stack.pop();
				result = b - a;
				break;
			default:
				result = Integer.parseInt(tokens[i]);
				break;

			}
			stack.push(result);

		}

		return result;
	}
}