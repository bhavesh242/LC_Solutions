package com.bhavesh.solutions;

public class Leetcode1629 {
	public char slowestKey(int[] releaseTimes, String keysPressed) {
		int prevTime = releaseTimes[0];
		int maxTime = releaseTimes[0];
		char maxChar = keysPressed.charAt(0);
		for (int i = 1; i < releaseTimes.length; i++) {
			int diff = releaseTimes[i] - prevTime;
			char c = keysPressed.charAt(i);
			if (diff > maxTime) {
				maxTime = diff;
				maxChar = c;
			} else if (maxTime == diff) {
				maxChar = maxChar > c ? maxChar : c;
			}

			prevTime = releaseTimes[i];
		}

		return maxChar;
	}
}