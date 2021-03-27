package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Leetcode472 {

	class Solution1 {
		// Approach 2 : DFS and Memo
		public List<String> findAllConcatenatedWordsInADict_DFS_MEMO(String[] words) {
			List<String> ans = new ArrayList<>();
			HashSet<String> wordSet = new HashSet<>(Arrays.asList(words));
			HashMap<String, Boolean> cache = new HashMap<>();
			for (String word : words)
				if (dfs(word, wordSet, cache))
					ans.add(word);
			return ans;
		}

		boolean dfs(String word, HashSet<String> wordSet, HashMap<String, Boolean> cache) {
			if (cache.containsKey(word))
				return cache.get(word);
			for (int i = 1; i < word.length(); i++) {
				if (wordSet.contains(word.substring(0, i))) {
					String suffix = word.substring(i);
					if (wordSet.contains(suffix) || dfs(suffix, wordSet, cache)) {
						cache.put(word, true);
						return true;
					}
				}
			}
			cache.put(word, false);
			return false;
		}
	}

	class Solution2 {
		public List<String> findAllConcatenatedWordsInADict(String[] words) {
			List<String> ans = new ArrayList<String>();
			HashSet<String> prevWords = new HashSet<String>();
			Arrays.sort(words, new Comparator<String>() {
				public int compare(String a, String b) {
					return a.length() - b.length();
				}
			});

			for (String word : words) {
				if (wordBreak(word, prevWords)) {
					ans.add(word);
				}
				prevWords.add(word);

			}
			return ans;
		}

		public boolean wordBreak(String word, HashSet<String> prevWords) {
			if (prevWords.isEmpty()) {
				return false;
			}

			boolean dp[] = new boolean[word.length() + 1];
			dp[0] = true;
			for (int i = 1; i <= word.length(); i++) {
				for (int j = 0; j < i; j++) {
					if (dp[j] && prevWords.contains(word.substring(j, i))) {
						dp[i] = true;
						break;
					}
				}
			}

			return dp[word.length()];
		}
	}
	
	class Solution3
	{
		
	}
}
