package com.bhavesh.solutions;

import java.util.Arrays;

public class Leetcode205 {
	public boolean isIsomorphic(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		if (s.equals(t)) {
			return true;
		}

		char[] mappings = new char[128];

		// Fill Array with non-ascii character
		Arrays.fill(mappings, (char) (129));
		boolean[] seen = new boolean[128];

		for (int i = 0; i < s.length(); i++) {
			int sChar = s.charAt(i);
			char tChar = t.charAt(i);

			if (mappings[sChar] == (char) (129)) {
				if (seen[tChar]) {
					return false;
				}
				mappings[sChar] = tChar;
				seen[tChar] = true;
			} else {
				if (mappings[sChar] != tChar) {
					return false;
				}
			}
		}

		return true;
	}

	// Approach 2
	public boolean isIsomorphic1(String s, String t) {
		// If either strings are null or unequal in length, return false
		if (s == null || t == null || s.length() != t.length()) {
			return false;
		}

		// Just use maps to store last index match
		int[] smap = new int[128];
		int[] tmap = new int[128];

		for (int i = 0; i < s.length(); i++) {
			int schar = s.charAt(i);
			int tchar = t.charAt(i);

			// If either are mapped to different values
			if (smap[schar] != tmap[tchar]) {
				return false;
			}

			smap[schar] = i + 1;
			tmap[tchar] = i + 1;
		}

		return true;
	}
}
