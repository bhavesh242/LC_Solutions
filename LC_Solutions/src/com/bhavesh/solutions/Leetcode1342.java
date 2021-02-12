package com.bhavesh.solutions;

public class Leetcode1342 {
	public int numberOfSteps(int num) {
		int steps = -1;
		while (num != 0) {
			if ((num & 1) == 1) {
				num--;
				steps++;
			}
			num = num >> 1;
			steps++;
		}

		return steps == -1 ? 0 : steps;
	}
}