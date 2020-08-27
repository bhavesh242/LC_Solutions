package com.bhavesh.solutions;

import java.util.Stack;

public class Leetcode682 {
	public int calPoints(String[] ops) {
		// Create a stack of valid points points
		Stack<Integer> stack = new Stack<Integer>();
		// Create a sum integer
		int sum = 0;
		// iterate over the string array
		for (String s : ops) {
			// If the operation is C, remove last set of valid points
			if (s.equals("C")) {
				sum -= stack.pop();
			}
			// Operation D means add double of last valid score
			else if (s.equals("D")) {
				int newTop = 2 * stack.peek();
				sum = sum + newTop;
				stack.push(newTop);
			}
			// Add two last valid scores if operation +
			// to get last 2 pushes to the stack, pop once and peek once.
			else if (s.equals("+")) {
				int oldTop = stack.pop();
				int newTop = oldTop + stack.peek();
				sum = sum + newTop;
				stack.push(oldTop);
				stack.push(newTop);
			} else {
				int newScore = Integer.parseInt(s);
				stack.push(newScore);
				sum += newScore;
			}
		}

		return sum;

	}

}
