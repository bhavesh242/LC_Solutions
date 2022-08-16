package com.bhavesh.solutions;

import java.util.Arrays;
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

		return st.stream().mapToInt(i -> i).toArray();
	}

	// Faster solution that uses Array instead of stacks
	public int[] asteroidCollision2(int[] asteroids) {

		int[] stack = new int[asteroids.length];
		int index = -1;
		for (int obj : asteroids) {
			if (obj > 0) {
				stack[++index] = obj;
				continue;
			}
			while (index >= 0 && stack[index] > 0) {
				if (stack[index] > -obj) {
					obj = 0;
					break;
				} else if (stack[index] < -obj) {
					index--;
				} else {
					index--;
					obj = 0;
					break;
				}
			}
			if (obj != 0) {
				stack[++index] = obj;
			}

		}
		return Arrays.copyOfRange(stack, 0, index + 1);
	}
}
