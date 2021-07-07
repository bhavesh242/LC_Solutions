package com.bhavesh.solutions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Leetcode269 {
	public String alienOrder(String[] words) {
		// create a graph using an arrays of HashSet of size 26 as we only deal with
		// lowercase letters
		HashSet<Character>[] graph = new HashSet[26];
		// This array keeps track of the indegrees of characters, for topological sort
		int[] indegree = new int[26];
		// Fill indegree as -1 for all letters to keep track of letters we will never
		// encounter
		Arrays.fill(indegree, -1);

		// Start Building the graph
		// Firstly create a new hashset node for each character in the graph and assign
		// it with
		// indegree 0

		// This variable keeps track of number of nodes in the graph
		int graphSize = 0;
		for (String word : words) {
			for (char x : word.toCharArray()) {
				if (graph[x - 'a'] == null) {
					graphSize++;
					graph[x - 'a'] = new HashSet<Character>();
					indegree[x - 'a'] = 0;
				}
			}
		}
		/*
		 * Start travering consecutive words to build out a directed topological graph
		 * the lexicographically smaller node serves as the parent and bigger serves as
		 * a child
		 */
		for (int i = 0; i < words.length - 1; i++) {
			String a = words[i];
			String b = words[i + 1];
			/*
			 * If you encounter 2 words such that first word is larger and starts with the
			 * second word, it implies the ordering is flawed and therefore retunr empty
			 * string
			 */
			if (a.length() > b.length() && a.startsWith(b)) {
				return "";
			}

			for (int j = 0; j < Math.min(a.length(), b.length()); j++) {
				if (a.charAt(j) == b.charAt(j)) {
					continue;
				}
				char child = b.charAt(j);
				char parent = a.charAt(j);

				// This commented section is a little check to see if the child already has and
				// edge to parent
				// It it does, they clearly form a cycle and return ""
				// if(graph[child-'a'].contains(parent))
				// {
				// return "";
				// }

				/*
				 * Add edge from parent to child character and increase indegree of child
				 * character by one
				 */
				if (!graph[parent - 'a'].contains(child)) {
					graph[parent - 'a'].add(child);
					indegree[child - 'a']++;
				}
				break;
			}
		}

		// Result string
		StringBuffer res = new StringBuffer();
		Queue<Character> queue = new LinkedList<Character>();
		/*
		 * To start off our Topoligical sort bfs, start by adding all nodes with
		 * in-degree 0 to a queue
		 */
		for (char i = 'a'; i <= 'z'; i++) {
			if (indegree[i - 'a'] == 0) {
				queue.add(i);
			}
		}
		// Start off the BFS / Topological Sort
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				char c = queue.poll();
				res.append(c);
				for (char x : graph[c - 'a']) {
					indegree[x - 'a']--;
					if (indegree[x - 'a'] == 0) {
						queue.add(x);
					}
				}
			}
		}

		/*
		 * At the end of the sort we check if all the characters in the graph are
		 * covered in the output string, if we have covered all then the alien
		 * dictionary is valid else return an empty string
		 */
		return res.length() == graphSize ? res.toString() : "";
	}
}