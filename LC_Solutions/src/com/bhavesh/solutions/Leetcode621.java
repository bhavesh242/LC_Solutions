package com.bhavesh.solutions;

import java.util.Arrays;

public class Leetcode621 {
	public int leastInterval(char[] tasks, int n) {

		// Simple Math, find maximum number of idel slots, decrement the slots as you
		// encounter frequencies
		int map[] = new int[26];
		for (char x : tasks) {
			map[x - 'A']++;
		}
		Arrays.sort(map);
		int maxFreq = map[25] - 1;
		int idle_time = maxFreq * n;
		for (int i = 24; i >= 0; i--) {
			idle_time -= Math.min(maxFreq, map[i]);
		}

		return idle_time > 0 ? idle_time + tasks.length : tasks.length;
	}
}