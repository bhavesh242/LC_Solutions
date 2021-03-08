package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.List;

public class Leetcode438 {

	// Sliding window approach

	public List<Integer> findAnagrams(String s, String p) {

		List<Integer> op = new ArrayList<Integer>();

		if (p.length() == 0 || s.length() < p.length()) {
			return op;
		}

		int map[] = new int[26];

		for (char x : p.toCharArray()) {
			map[x - 'a']++;
		}

		int left = 0;
		for (int right = 0; right < s.length(); right++) {
			map[s.charAt(right) - 'a']--;
			while (map[s.charAt(right) - 'a'] < 0) {
				map[s.charAt(left) - 'a']++;
				left++;
			}

			if (right - left + 1 == p.length()) {
				op.add(left);
			}
		}

		return op;
	}

	//Approach 2 : Count Arrays
	public List<Integer> findAnagrams_1(String s, String p) {

		List<Integer> op = new ArrayList<Integer>();

		if (p.length() == 0 || s.length() < p.length()) {
			return op;
		}

		int map[] = new int[26];

		for (char x : p.toCharArray()) {
			map[x - 'a']++;
		}

		int left = 0;
		int count = p.length();
		for (int right = 0; right < s.length(); right++) {
			if (right - left == p.length() && map[s.charAt(left++) - 'a']++ >= 0) {
				count++;
			}

			if (--map[s.charAt(right) - 'a'] >= 0) {
				count--;
			}

			if (count == 0) {
				op.add(left);
			}
		}

		return op;
	}
}
