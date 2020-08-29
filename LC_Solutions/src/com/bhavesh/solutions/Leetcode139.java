package com.bhavesh.solutions;

import java.util.HashSet;
import java.util.List;

public class Leetcode139 {
	public boolean wordBreak(String s, List<String> wordDict) {

		// Convert the list of string into set for faster lookup
		HashSet<String> set = new HashSet();
		set.addAll(wordDict);

		int n = s.length();

		boolean dp[] = new boolean[n + 1];
		// Dp of empty string "" is also a valid string, so dp[0] is true
		dp[0] = true;
		// Start traversing from index 1, i represents the index we are at on string s
		for (int i = 1; i <= n; i++) {
			// j checks for valid substring from 0 to i
			for (int j = 0; j < i; j++) {
				if (dp[j] && set.contains(s.substring(j, i))) {
					dp[i] = true;
					break;
				}
			}
		}

		// Result would be obtained at last index of dp array
		return dp[n];

	}
}