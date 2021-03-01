package com.bhavesh.solutions;

public class Leetcode10 {
	public boolean isMatch_Recursive(String s, String p) {
		if (p.isEmpty()) {
			return s.isEmpty();
		}

		boolean fMatch = !s.isEmpty() && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0));
		if (p.length() >= 2 && p.charAt(1) == '*') {
			return isMatch(s, p.substring(2)) || (fMatch && isMatch(s.substring(1), p));
		}

		return fMatch && isMatch(s.substring(1), p.substring(1));
	}

	// Dynamic Programming
	TVAL memo[][];

	public boolean isMatch(String s, String p) {
		memo = new TVAL[s.length() + 1][p.length() + 1];
		return regCheck(0, 0, s, p);
	}

	public boolean regCheck(int i, int j, String s, String p) {
		if (memo[i][j] != null) {
			return memo[i][j] == TVAL.TRUE;
		}

		boolean ans;
		if (j == p.length()) {
			ans = i == s.length();
		} else {
			boolean fMatch = (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));
			if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
				ans = (fMatch && regCheck(i + 1, j, s, p)) || regCheck(i, j + 2, s, p);
			} else {
				ans = fMatch && regCheck(i + 1, j + 1, s, p);
			}
		}

		memo[i][j] = ans ? TVAL.TRUE : TVAL.FALSE;
		return ans;
	}

}

enum TVAL {
	TRUE, FALSE
}