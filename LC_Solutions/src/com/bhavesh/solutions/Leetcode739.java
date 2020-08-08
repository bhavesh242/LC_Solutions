package com.bhavesh.solutions;

import java.util.Stack;

public class Leetcode739 {
	public int[] dailyTemperatures(int[] T) {
		// Create an answer array
		int ans[] = new int[T.length];
		// Create a stack where we would store indexes
		Stack<Integer> st = new Stack<Integer>();

		for (int i = 0; i < T.length; i++) {
			/*
			 * If stack is not empty and the temperature at index at top of stack is smaller
			 * than temperature at current index, it implies we are at a hotter day and pop
			 * the stack index and put (index - current) at that index answer
			 */
			while (!st.isEmpty() && T[st.peek()] < T[i]) {
				int index = st.pop();
				ans[index] = i - index;
			}
			// Keep pushing current indexes on top of stack in search for hotter days
			st.push(i);
		}
		return ans;
	}
}