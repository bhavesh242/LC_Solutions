package com.bhavesh.solutions;

public class Leetcode821 {
	public int[] shortestToChar(String S, char C) {
		int n = S.length();
		int arr[] = new int[n];
		int prevInd = -n;

		  /*
        						number line
        |---------c---------------0-------------n-------------c-------|
                 -n               |--our array--|             2n     
		   */
		
		// Calculating from left, assuming first Character C is at index -n
		for (int i = 0; i < n; i++) {
			if (S.charAt(i) == C) {
				prevInd = i;
			}
			arr[i] = i - prevInd;
		}

		// Coming from Right assuming first character c is at index 2n

		prevInd = 2 * n;
		for (int i = n - 1; i >= 0; i--) {
			if (S.charAt(i) == C) {
				prevInd = i;
			}
			arr[i] = Math.min(arr[i], prevInd - i);
		}

		return arr;
	}
}