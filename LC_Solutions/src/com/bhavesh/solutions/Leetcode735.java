package com.bhavesh.solutions;

import java.util.Stack;

public class Leetcode735 {
	public int[] asteroidCollision(int[] asteroids) {

		Stack<Integer> st = new Stack<Integer>();

		for (int x : asteroids) {
			if (x > 0) {
				st.push(x);
				continue;
			}

			while (!st.isEmpty() && st.peek() > 0 && st.peek() < -x) {
				st.pop();
			}

			if (!st.isEmpty() && st.peek() == -x) {
				st.pop();
			} else if (st.isEmpty() || st.peek() < 0) {
				st.push(x);
			}
		}
		int[] ans = new int[st.size()];
		for (int i = st.size() - 1; i >= 0; i--) {
			ans[i] = st.pop();
		}

		return ans;

	}
}
