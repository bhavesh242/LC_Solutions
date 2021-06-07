package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.List;

public class Leetcode1268 {
	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		TrieNode root = new TrieNode();
		TrieNode cur = root;
		for (String word : products) {
			addWord(word, root);
		}

		List<List<String>> res = new ArrayList<List<String>>();
		StringBuffer prefix = new StringBuffer();

		int i = 0;
		for (i = 0; i < searchWord.length(); i++) {
			char x = searchWord.charAt(i);
			List<String> a = new ArrayList<String>();
			if (cur.child[x - 'a'] != null) {
				cur = cur.child[x - 'a'];
				prefix.append(x);
				dfs(cur, prefix, a);
				res.add(a);
			} else {
				break;
			}
		}

		for (; i < searchWord.length(); i++) {
			res.add(new ArrayList<String>());
		}
		return res;

	}

	public void dfs(TrieNode root, StringBuffer prefix, List<String> a) {
		if (root.isEnd) {
			a.add(new String(prefix));

		}
		for (char i = 'a'; i <= 'z'; i++) {
			if (root.child[i - 'a'] != null) {
				if (a.size() == 3) {
					return;
				}
				dfs(root.child[i - 'a'], prefix.append(i), a);
				prefix.setLength(prefix.length() - 1);

			}
		}

	}

	public void addWord(String word, TrieNode root) {
		TrieNode cur = root;
		for (char x : word.toCharArray()) {
			if (cur.child[x - 'a'] == null) {
				cur.child[x - 'a'] = new TrieNode();
			}
			cur = cur.child[x - 'a'];
		}
		cur.isEnd = true;
	}
}

class TrieNode {
	TrieNode[] child;
	boolean isEnd;

	TrieNode() {
		isEnd = false;
		child = new TrieNode[26];
	}
}