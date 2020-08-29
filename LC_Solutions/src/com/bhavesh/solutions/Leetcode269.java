package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Leetcode269 {
	public String alienOrder(String[] words) {

		// create a graph using a hashmap
		Map<Character, ArrayList<Character>> graph = new HashMap();

		// This map keeps track of the indegrees of characters, for topological sort
		Map<Character, Integer> indegree = new HashMap();

		// Start Building a graph
		// Firstly create a node for each character in the graph and assign it with
		// indegree 0
		for (String word : words) {
			for (char x : word.toCharArray()) {
				graph.putIfAbsent(x, new ArrayList<Character>());
				indegree.putIfAbsent(x, 0);
			}
		}

		/*
		 * Start travering consecutive words to build out a directed topological graph
		 * the lexicographically smaller node serves as the parent and bigger serves as
		 * a child
		 */
		for (int i = 0; i < words.length - 1; i++) {
			String str1 = words[i];
			String str2 = words[i + 1];
			/*
			 * If the lexicographically smaller string is bigger in length and starts with
			 * the second string, then the given arrangement is invalid and thus return an
			 * empty string
			 */
			if (str1.length() > str2.length() && str1.startsWith(str2)) {
				return "";
			}
			// else start constructing the graph
			for (int j = 0; j < Math.min(str1.length(), str2.length()); j++) {
				// Parent and Child character
				char parent = str1.charAt(j);
				char child = str2.charAt(j);
				/*
				 * Add edge from parent to child character and increase indegree of child
				 * character by one
				 */
				if (parent != child) {
					if (!graph.get(parent).contains(child)) {
						graph.get(parent).add(child);
						indegree.put(child, indegree.get(child) + 1);
					}
					break;
				}
			}
		}
		/*
		 * To start off our Topoligical sort bfs, start by adding all nodes with
		 * indegree 0 to a queue
		 */
		Queue<Character> queue = new LinkedList();
		for (char x : indegree.keySet()) {
			if (indegree.get(x) == 0) {
				queue.add(x);
			}
		}

		// This stringbuffer wil store our result
		StringBuffer op = new StringBuffer();
		// Start off the BFS / Topological Sort
		while (!queue.isEmpty()) {
			char x = queue.remove();
			op.append(x);
			for (char child : graph.get(x)) {
				indegree.put(child, indegree.get(child) - 1);
				if (indegree.get(child) == 0) {
					queue.offer(child);
				}
			}
		}
		/*
		 * At the end of the sort we check if all the characters in the dictionary are
		 * covered in the output string, if we have covered all then the alien
		 * dictionary is valid else return an empty string
		 */
		return op.length() == graph.size() ? op.toString() : "";
	}
}