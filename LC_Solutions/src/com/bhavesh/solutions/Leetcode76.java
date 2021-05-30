package com.bhavesh.solutions;

public class Leetcode76 {
	public String minWindow(String s, String t) {

		// Build A Character counter for t, since it can contain any printable
		// character, make it of size 128
		int[] counter = new int[128];
		for (char c : t.toCharArray()) {
			counter[c]++;
		}
		// The minimum substring length cannot be more than s.length, you can use
		// Integer.max_val as well
		int minLen = s.length() + 1;
		// To return our result
		String result = "";
		// This variable is used to check if we have covered all elements of t in the
		// substring
		int cnt = 0;

		// We are going to use a sliding window method, for this we need 2 pointers,
		// left and right both initially at index 0
		int left = 0;

		// Start increasing right pointer until a point when you cover all characters in
		// t
		for (int right = 0; right < s.length(); right++) {
			// If the character in s string is present in t string, increase count

			if (--counter[s.charAt(right)] >= 0) {
				cnt++;
			}
			// When you reach a substring that covers all the chars in t, check it's length
			// and compared with min length
			while (cnt == t.length()) {
				// Take this new SubString as result if it has smaller length than current
				// substring
				if (minLen > right - left + 1) {
					result = s.substring(left, right + 1);
					minLen = right - left + 1;
				}
				// Start moving left pointer untill you reach a substring until you reach a
				// point when the substring does not cover all chars in t
				if (++counter[s.charAt(left)] > 0) {
					cnt--;
				}
				left++;
			}
		}

		// Return result
		return result;

	}
}
