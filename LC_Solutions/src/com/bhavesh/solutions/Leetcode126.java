package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Leetcode126 {
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> ladders = new ArrayList<>();
		Set<String> visited = new HashSet<>();
		Set<String> dict = new HashSet<>(wordList);
		if (!dict.contains(endWord)) {
			return new ArrayList<>();
		}
		// bfs with list of current paths
		Queue<List<String>> queue = new LinkedList<>();
		List<String> temp = new ArrayList<>();
		temp.add(beginWord);
		queue.offer(temp);
		visited.add(beginWord);
		boolean found = false;
		while (!queue.isEmpty()) {
			int size = queue.size();
			// keeping track of visited words on current level; we need to do this because
			// same word can be used by multiple paths in same level
			Set<String> tempVisited = new HashSet<>();
			for (int i = 0; i < size; i++) {
				List<String> curLadder = queue.poll();
				// extract the last word in the current path
				char cur[] = curLadder.get(curLadder.size() - 1).toCharArray();
				for (int j = 0; j < cur.length; j++) {
					char curLetter = cur[j];
					for (char c = 'a'; c <= 'z'; c++) {
						if (c == cur[j]) {
							continue;
						}
						cur[j] = c;
						String transformed = String.valueOf(cur);
						// found
						if (transformed.equals(endWord)) {
							found = true;
							ladders.add(addNewStep(curLadder, transformed));
						} else if (dict.contains(transformed) && !visited.contains(transformed)) {
							tempVisited.add(transformed);
							queue.offer(addNewStep(curLadder, transformed));
						}
					}
					cur[j] = curLetter;
				}
			}
			if (found) {
				return ladders;
			}
			// add all visited words in this level to master set
			visited.addAll(tempVisited);
		}
		return new ArrayList<>();
	}

	// copy current ladder and add new valid step
	private List<String> addNewStep(List<String> current, String step) {
		List<String> ladder = new ArrayList<>(current);
		ladder.add(step);
		return ladder;
	}
}