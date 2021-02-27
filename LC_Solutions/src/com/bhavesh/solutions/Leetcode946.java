package com.bhavesh.solutions;

import java.util.Stack;

public class Leetcode946 {
	public boolean validateStackSequences(int[] pushed, int[] popped) {
		Stack<Integer> st = new Stack<Integer>();
		int i = 0;

		// Push each element and greedily remove elements from the stack that match the
		// popped order

		for (int x : pushed) {
			st.push(x);
			while (!st.isEmpty() && st.peek() == popped[i]) {
				st.pop();
				i++;
			}
		}

		// If we are left with an empty stack, then this order is possible
		return st.isEmpty();
	}
}