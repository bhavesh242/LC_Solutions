package com.bhavesh.solutions;

import java.util.List;

public class Leetcode524 {
	
	//Simple Approach : Just check with every string if it is a subsequence.
	//If it is, just compare it with current maximal string and set it if it is bigger or lexicographically smaller if of the same length
	
	public String findLongestWord(String s, List<String> d) {

		String maxString = "";
		int maxLen = 0;
		for (String t : d) {
			if (isSubSqnce(s, t)) {
				if (t.length() > maxLen) {
					maxLen = t.length();
					maxString = t;
				} else if (t.length() == maxLen) {
					maxString = maxString.compareTo(t) > 0 ? t : maxString;
				}
			}
		}

		return maxString;

	}

	public boolean isSubSqnce(String s, String t) {
		int k = 0;
		for (int i = 0; i < s.length(); i++) {
			if (t.charAt(k) == s.charAt(i)) {
				if (++k == t.length()) {
					return true;
				}
			}
		}
		return false;
	}
}