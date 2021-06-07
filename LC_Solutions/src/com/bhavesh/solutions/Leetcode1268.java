package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Leetcode1268 {
}

class Sol_1268_1 {
	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		TrieNode root = new TrieNode();
		TrieNode cur = root;

		// Build a trie and add all words to the trie
		for (String word : products) {
			addWord(word, root);
		}

		List<List<String>> res = new ArrayList<List<String>>();
		StringBuffer prefix = new StringBuffer();

		/*
		 * Start iterating over the alphabets of the word and add them to the prefix
		 * stringBuffer
		 */

		int i = 0;
		for (i = 0; i < searchWord.length(); i++) {
			char x = searchWord.charAt(i);
			List<String> a = new ArrayList<String>();
			/*
			 * If there is a child from current node for current letter, start dfs from that
			 * node and pass the prefix that needs to be appended at the beginning of the
			 * results
			 */
			if (cur.child[x - 'a'] != null) {
				cur = cur.child[x - 'a'];
				prefix.append(x);

				/*
				 * Once the dfs completes, we will have added at a list with at max 3 matching
				 * strings to our result
				 */
				dfs(cur, prefix, a);
				res.add(a);
				// Otherwise break, as we will get empty results from that character to end
			} else {
				break;
			}
		}

		// Add the empty results for the remainder of the string and return final result
		for (; i < searchWord.length(); i++) {
			res.add(new ArrayList<String>());
		}
		return res;

	}

	// DFS method to traverse the tree
	public void dfs(TrieNode root, StringBuffer prefix, List<String> a) {

		//If we already have a full result of size 3, no need to proceed further 
		if (a.size() == 3) {
			return;
		}
		//Otherwise add newly encountered word to the results
		if (root.isEnd) {
			a.add(new String(prefix));
		}
		//Continue dfs and perform it recursively on all non null children
		for (char i = 'a'; i <= 'z'; i++) {
			if (root.child[i - 'a'] != null) {
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

	class TrieNode {
		TrieNode[] child;
		boolean isEnd;

		TrieNode() {
			isEnd = false;
			child = new TrieNode[26];
		}
	}
}

class Sol_1268_2 {
	public List<List<String>> suggestedProducts(String[] products, String searchWord) {

		//sort input as output needs lexicogrpahical sorting
		Arrays.sort(products);
		List<String> list = Arrays.asList(products);
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		//Add all words into treemap with their respective indices
		for (int i = 0; i < products.length; i++) {
			map.put(products[i], i);
		}

		
		List<List<String>> ans = new ArrayList<List<String>>();
		StringBuffer prefix = new StringBuffer();
		//Iterate over the searchword and add character to prefix
		for (char x : searchWord.toCharArray()) {
			prefix.append(x);
			String ceil = map.ceilingKey(prefix.toString());
			String floor = map.floorKey(prefix.toString() + "{");
			if (ceil == null || floor == null) {
				break;
			}
			ans.add(list.subList(map.get(ceil), Math.min(map.get(ceil) + 3, map.get(floor) + 1)));
		}

		while (ans.size() < searchWord.length()) {
			ans.add(new ArrayList<String>());
		}
		return ans;

	}
}
