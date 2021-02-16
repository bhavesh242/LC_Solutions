package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.List;

public class Leetcode784 {

	public List<String> letterCasePermutation(String S) {

		// Recursive
		List<String> ans = new ArrayList<String>();
		dfs(0, S.toCharArray(), ans);
		return ans;
	}

	public void dfs(int i, char[] sArr, List<String> ans) {
		if (i == sArr.length) {
			ans.add(new String(sArr));
			return;
		}
		if (Character.isLetter(sArr[i])) {
			char temp = sArr[i];
			sArr[i] = Character.toLowerCase(sArr[i]);
			dfs(i + 1, sArr, ans);
			sArr[i] = Character.toUpperCase(sArr[i]);
			dfs(i + 1, sArr, ans);
			sArr[i] = temp;
		} else {
			dfs(i + 1, sArr, ans);
		}

	}

	// Iterative Approach
	public List<String> letterCasePermutation2(String S) {
		List<StringBuilder> ans = new ArrayList();
		ans.add(new StringBuilder());

		for (char c : S.toCharArray()) {
			int n = ans.size();
			if (Character.isLetter(c)) {
				for (int i = 0; i < n; ++i) {
					ans.add(new StringBuilder(ans.get(i)));
					ans.get(i).append(Character.toLowerCase(c));
					ans.get(n + i).append(Character.toUpperCase(c));
				}
			} else {
				for (int i = 0; i < n; ++i)
					ans.get(i).append(c);
			}
		}

		List<String> finalans = new ArrayList();
		for (StringBuilder sb : ans)
			finalans.add(sb.toString());
		return finalans;
	}

}
