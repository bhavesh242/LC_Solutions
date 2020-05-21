package com.bhavesh.solutions;

public class Leetcode1287 {
	public int findSpecialInteger(int[] arr) {
		int quart = arr.length / 4;
		for (int i = 0; i < arr.length - quart; i++) {
			if (arr[i] == arr[i + quart]) {
				return arr[i];
			}
		}
		return 0;
	}
}