package com.bhavesh.solutions;

public class Leetcode1163 {
	public String lastSubstring(String s) {
		// i is index for first substring
		// j is index for second substring
		// k is the substring length
		int i = 0, j = 1, k = 0;
		// convert string to char array for faster access
		char[] c = s.toCharArray();
		// while second substring doesnt exceed string end
		while (j + k < c.length) {
			// c1 is character in first substring
			// c2 is character in second substring
			char c1 = c[i + k], c2 = c[j + k];

			/*
			 * if both characters are equal, move to the next character to check for the
			 * bigger string
			 */
			if (c1 == c2) {
				k++;
			}
			/*
			 * if c1 is bigger than c2, it implies that substring 1 is bigger so we need not
			 * move i as it always represents the bigger substring, we move j to next
			 * substring, beyond (i+k) to start checking again from length 0
			 */
			else if (c1 < c2) {
				i = j;
				j++;
				k = 0;
			}
			/*
			 * if c2 is bigger than c1, it implies that substring 2 is bigger so move i j's
			 * position, move j to the next character of i and again start checking from
			 * string length 0
			 */

			else if (c1 > c2) {
				j = j + k + 1;
				k = 0;
			}
		}
		// return substring with begin index at i
		return s.substring(i);
	}
}
