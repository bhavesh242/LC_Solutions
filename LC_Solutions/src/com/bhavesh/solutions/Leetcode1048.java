package com.bhavesh.solutions;

import java.util.Arrays;
import java.util.Comparator;

public class Leetcode1048 {
	public int longestStrChain(String[] words) {
		Arrays.sort(words, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return a.length() - b.length();
			}
		});
		int maxChain = 1;
		int dp[] = new int[words.length];
		Arrays.fill(dp, 1);
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < i; j++) {
				if (words[i].length() != (words[j].length() + 1)) {
					continue;
				}
				if (isChain(words[j], words[i])) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);
					maxChain = Math.max(dp[i], maxChain);
				}
			}

		}

		return maxChain;

	}

	public boolean isChain(String x, String y) {
		int i = 0, j = 0;
		boolean diff = false;
		while (i < x.length() && j < y.length()) {
			if (x.charAt(i) != y.charAt(j)) {
				if (diff) {
					return false;
				}
				diff = true;
				j++;
				continue;
			}
			i++;
			j++;
		}

		return true;
	}
}
