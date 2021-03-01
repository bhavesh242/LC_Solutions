package com.bhavesh.solutions;

import java.util.Arrays;

public class Leetcode1465 {
	public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {

		Arrays.sort(horizontalCuts);
		Arrays.sort(verticalCuts);

		int prevH = 0;
		int maxH = 0;
		for (int x : horizontalCuts) {
			maxH = Math.max(maxH, x - prevH);
			prevH = x;
		}

		maxH = Math.max(maxH, h - prevH);

		int prevV = 0;
		int maxV = 0;
		for (int x : verticalCuts) {
			maxV = Math.max(maxV, x - prevV);
			prevV = x;
		}

		maxV = Math.max(maxV, w - prevV);

		long l = 1L * maxH * maxV;
		return (int) (l % 1000000007);

	}
}