package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Leetcode140 {
	HashSet<String> wordSet;
	HashMap<String, List<List<String>>> memo;

	public List<String> wordBreak(String s, List<String> wordDict) {

		// Recusrive BackTracking with memoization

		wordSet = new HashSet<String>();
		for (String str : wordDict) {
			wordSet.add(str);
		}

		memo = new HashMap<String, List<List<String>>>();
		List<List<String>> ans = backTrack(s);

		List<String> op = new ArrayList<String>();
		for (List<String> list : ans) {
			StringBuffer str = new StringBuffer(list.get(0));
			for (int i = 1; i < list.size(); i++) {
				str.append(" " + list.get(i));
			}
			op.add(new String(str));
		}

		return op;
	}

	public List<List<String>> backTrack(String s) {
		if (s.length() == 0) {
			List<List<String>> ans = new ArrayList<List<String>>();
			ans.add(new ArrayList<String>());
			return ans;
		}
		if (memo.containsKey(s)) {
			return memo.get(s);
		}

		List<List<String>> sMap = new ArrayList<List<String>>();
		memo.put(s, sMap);

		for (int i = 1; i <= s.length(); i++) {
			String word = s.substring(0, i);
			if (wordSet.contains(word)) {
				List<List<String>> nextWords = backTrack(s.substring(i));
				for (List<String> words : nextWords) {
					List<String> a = new ArrayList<String>();
					a.add(word);
					a.addAll(words);
					memo.get(s).add(a);
				}
			}
		}

		return memo.get(s);

	}
}
