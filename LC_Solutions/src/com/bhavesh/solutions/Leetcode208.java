package com.bhavesh.solutions;

public class Leetcode208 {
	class Trie {
		class TrieNode {
			TrieNode[] children;
			boolean isEnd;

			TrieNode() {
				children = new TrieNode[26];
				isEnd = false;
			}
		}

		TrieNode root;

		/** Initialize your data structure here. */
		public Trie() {
			System.out.print("Called Trie Constructor");
			root = new TrieNode();
		}

		/** Inserts a word into the trie. */
		public void insert(String word) {
			TrieNode current = root;
			for (char s : word.toCharArray()) {
				TrieNode childNode = current.children[s - 'a'];
				if (childNode == null) {
					childNode = new TrieNode();
					current.children[s - 'a'] = childNode;
				}
				current = childNode;
			}
			current.isEnd = true;
		}

		/** Returns if the word is in the trie. */
		public boolean search(String word) {
			TrieNode current = root;
			for (char s : word.toCharArray()) {
				TrieNode childNode = current.children[s - 'a'];
				if (childNode == null) {
					return false;
				}
				current = childNode;
			}
			return current.isEnd;
		}

		/**
		 * Returns if there is any word in the trie that starts with the given prefix.
		 */
		public boolean startsWith(String prefix) {
			TrieNode current = root;
			for (char s : prefix.toCharArray()) {
				TrieNode childNode = current.children[s - 'a'];
				if (childNode == null) {
					return false;
				}
				current = childNode;
			}
			return true;
		}
	}

	/**
	 * Your Trie object will be instantiated and called as such: Trie obj = new
	 * Trie(); obj.insert(word); boolean param_2 = obj.search(word); boolean param_3
	 * = obj.startsWith(prefix);
	 */
}
