package com.bhavesh.solutions;

public class Leetcode91 {
	public int numDecodings(String s) {
		/*
		 * We are using the optimal method of dynamic programming equation dp[i] +=
		 * dp[i-1] +dp[i-2] if (dp[i-1] & dp[i-2] both form valid single letter and
		 * double letter encodings
		 */
		/*
		 * Here c2 starts from dp[0] =1 representing there is only one way to interpret
		 * an empty string
		 */
		// int dp[] = new int[s.length()+1];
		// dp[0] = 1;
		int c2 = 1;
		/*
		 * c1 is dp[1], if character at first index is 0, means the string is invalid,
		 * no valid string encoding would start from 0. If first character is not zero,
		 * it can also be interpreted in 1 way only
		 */
		// dp[1] = s.charAt(0) == '0'? 0 : 1;
		int c1;
		if (s.charAt(0) == '0') {
			return 0;
		}
		// character at index 0 is not 0, thus assign it as 1
		c1 = 1;
		// Start from dp[2], i.e second character s.charAt(1)
		for (int i = 1; i < s.length(); i++) {
			// Calculate value of one single digit at that index i
			int oneDigit = (s.charAt(i) - '0');
			// Calculate value of 2 digits i and (i-1) in tens position
			int twoDigit = oneDigit + (s.charAt(i - 1) - '0') * 10;

			/*
			 * If oneDigit is 0 and 2 digits make a number more than 26, it means this part
			 * of the string is invalid, rendering the entire string invalid and thus there
			 * are 0 valid ways to decode it
			 */
			if (oneDigit == 0 && twoDigit > 26) {
				return 0;
			}
			// We now proceed to calculate dp[i], represented by count here
			int count = 0;
			// If oneDigit is not 0, it implies, it is valid and can add to an encoding
			if (oneDigit != 0) {
				// dp[i] += dp[i-1];
				count += c1;
			}
			/*
			 * If twoDigits are between 10 and 26 inclusive, they too add to a valid encoding
			 */

			if (twoDigit >= 10 && twoDigit <= 26) {
				// dp[i]+=dp[i-2];
				count += c2;
			}
			/*
			 * Since dp[i] is only dependent on dp[i-1] (c1) and dp[i-2], we can just move
			 * them one step ahead in a memory optimization
			 */
			c2 = c1;
			c1 = count;
		}
		// At the end return dp[s.length()], as portrayed by c1
		return c1;
	}
}
