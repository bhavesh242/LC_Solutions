package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.List;

public class Leetcode438 {
	// Sliding Window Solution
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> op = new ArrayList();
		if (s.isEmpty() || p.isEmpty())
			return op;
		int map[] = new int[26];
		int count = 0;
		for (char c : p.toCharArray()) {
			map[c - 'a']++;
		}
		int plen = p.length();

		int left = 0, right = 0;
		/*
		 * Move right pointer always and keep a counter variable to check for charecters
		 * present in string p
		 */

		/*
		 * Move left pointer until left and right pointer are placed at p.length()-1
		 * distance
		 */
		/*
		 * If all characters between these 2 pointers are present in string p, all left
		 * pointer to output
		 */
		for (right = 0; right < s.length(); right++) {
			if (--map[s.charAt(right) - 'a'] >= 0) {
				count++;
			}
			while (right - left + 1 > plen) {
				if (++map[s.charAt(left++) - 'a'] > 0) {
					count--;
				}
			}
			if (count == plen) {
				op.add(left);
			}
		}

		return op;
	}
}