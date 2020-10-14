package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.List;

public class Leetcode131 {
	List<List<String>> op;

	// We use DFS with BackTracking to Solve this problem
	public List<List<String>> partition(String s) {
		op = new ArrayList();
		List<String> ans = new ArrayList();
		backTrack(s, 0, ans);
		return op;
	}

	// We Take a substrings starting from currentIndex and go to length of string
	/*
	 * If that substring is a palindrome, add to result and perfrom backtracking on
	 * remainder of string
	 */
	public void backTrack(String s, int curIndex, List<String> ans) {

		// If we have exceeded the last index of the string, it means we have found an
		// output
		if (curIndex == s.length()) {
			ArrayList<String> curAns = new ArrayList(ans);
			op.add(curAns);
		}

		for (int i = curIndex; i < s.length(); i++) {
			/*
			 * If Current Substring is palindrome, add it to current output and backTrack to
			 * remainder of the string
			 */
			if (isPalinDrome(s, curIndex, i)) {
				ans.add(s.substring(curIndex, i + 1));
				backTrack(s, i + 1, ans);
				ans.remove(ans.size() - 1);
			}
		}
	}

	// Utility function to check for a palindromic string
	public boolean isPalinDrome(String s, int l, int r) {
		while (l < r) {
			if (s.charAt(l++) != s.charAt(r--)) {
				return false;
			}
		}

		return true;
	}
}