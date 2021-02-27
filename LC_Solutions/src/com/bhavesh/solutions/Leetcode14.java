package com.bhavesh.solutions;

public class Leetcode14 {
	public String longestCommonPrefix(String[] strs) {

		// Vertical Scanning

		StringBuffer lcp = new StringBuffer();
		if (strs == null || strs.length == 0) {
			return lcp.toString();
		}

		int index = 0;

		for (char c : strs[0].toCharArray()) {
			for (int i = 1; i < strs.length; i++) {
				if (index >= strs[i].length() || c != strs[i].charAt(index)) {
					return lcp.toString();
				}
			}

			lcp.append(c);
			index++;
		}

		return lcp.toString();
	}

}