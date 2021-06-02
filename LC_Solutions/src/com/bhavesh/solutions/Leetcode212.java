package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.List;

public class Leetcode212 {

	//Trie with DFS
	public List<String> findWords(char[][] board, String[] words) {
		Trie root = new Trie();
		for (String word : words) {
			addWord(word, root);
		}
		List<String> res = new ArrayList<String>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				dfs(root, i, j, board, new StringBuffer(), res);

			}
		}

		return res;

	}

	public void addWord(String word, Trie root) {
		Trie cur = root;
		for (char x : word.toCharArray()) {
			if (cur.child[x - 'a'] == null) {
				cur.child[x - 'a'] = new Trie();
			}
			cur = cur.child[x - 'a'];
		}
		cur.isEnd = true;
	}

	public void dfs(Trie root, int i, int j, char[][] board, StringBuffer str, List<String> res) {
		if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == '#'
				|| root.child[board[i][j] - 'a'] == null) {
			return;
		}
		char ch = board[i][j];
		root = root.child[ch - 'a'];
		str.append(ch);
		if (root.isEnd) {
			root.isEnd = false;
			res.add(new String(str));
		}

		board[i][j] = '#';
		dfs(root, i + 1, j, board, str, res);
		dfs(root, i - 1, j, board, str, res);
		dfs(root, i, j + 1, board, str, res);
		dfs(root, i, j - 1, board, str, res);
		board[i][j] = ch;
		str.setLength(str.length() - 1);

	}

}

class Trie {
	Trie[] child;
	boolean isEnd;

	Trie() {
		child = new Trie[26];
		isEnd = false;
	}
}
