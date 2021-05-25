package com.bhavesh.solutions;

public class Leetcode211 {

}

class WordDictionary {

	/** Initialize your data structure here. */
	TrieNode211 root;

	public WordDictionary() {
		root = new TrieNode211();
	}

	public void addWord(String word) {
		root.addWord(word);
	}

	public boolean search(String word) {
		return root.search(word, 0);
	}
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary(); obj.addWord(word); boolean param_2
 * = obj.search(word);
 */

class TrieNode211 {
	TrieNode211 child[];
	boolean word;

	TrieNode211() {
		child = new TrieNode211[26];
		word = false;
	}

	public void addWord(String word) {
		TrieNode211 root = this;
		for (char x : word.toCharArray()) {
			if (root.child[x - 'a'] == null) {
				root.child[x - 'a'] = new TrieNode211();
			}
			root = root.child[x - 'a'];
		}
		root.word = true;
	}

	public boolean search(String word, int index) {
		TrieNode211 root = this;
		if (index == word.length()) {
			return root.word;
		}
		char c = word.charAt(index);
		if (c == '.') {
			for (TrieNode211 child : root.child) {
				if (child != null && child.search(word, index + 1)) {
					return true;
				}
			}
			return false;
		}

		return root.child[c - 'a'] != null && root.child[c - 'a'].search(word, index + 1);
	}

}