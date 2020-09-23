package com.bhavesh.solutions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode127 {
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		/* Main Idea : BFS */
		int level = 0;
		/*
		 * Create a set of words from wordlist, to avoid duplicates and also to keep
		 * track of unvisited words
		 */
		HashSet<String> wordSet = new HashSet();
		// Add all words from wordlist into wordset
		wordSet.addAll(wordList);
		// If the set does not contain our endWord, then return 0 as sequence would not
		// be possible
		if (!wordSet.contains(endWord))
			return level;
		// Or if End word is same as beginword, then return 0 as no transformation is
		// needed
		if (beginWord.equals(endWord))
			return level;
		// We are going to perform a BFS beginning from our startWord, for which we need
		// a Queue
		Queue<String> queue = new LinkedList();
		queue.add(beginWord);
		level++;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				char[] curWord = queue.remove().toCharArray();
				// Explore over each letter
				for (int j = 0; j < curWord.length; j++) {
					// Replace current letter with every other letter and check if that word exists
					// in wordSet
					char curLetter = curWord[j];
					// change current letter from a to z
					for (char c = 'a'; c <= 'z'; c++) {
						if (c == curLetter)
							continue;
						curWord[j] = c;
						/*
						 * Check if the new word is qual to end word, if yes, then we have reached our
						 * transformation and return level+1
						 */
						String newWord = String.valueOf(curWord);
						if (newWord.equals(endWord)) {
							return level + 1;
						}
						/*
						 * else if the word is present in the wordSet add it to queue and remove it from
						 * the wordSet
						 */
						if (wordSet.contains(newWord)) {
							queue.add(newWord);
							wordSet.remove(newWord);
						}
					}
					// Replace back the letter to original letter
					curWord[j] = curLetter;
				}
			}
			// Increase the level by one after done processing over current level
			level++;
		}
		/*
		 * If no value is returned during the BFS, it implies, a transformation from
		 * beginWord to endWord was not possible and return 0
		 */
		return 0;

	}
}