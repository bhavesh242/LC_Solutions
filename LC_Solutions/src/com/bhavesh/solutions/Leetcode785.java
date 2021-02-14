package com.bhavesh.solutions;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Leetcode785 {
	public boolean isBipartite(int[][] graph) {

		// Approach 1 : DFS
		return isBipartiteDFS(graph);
		// Approach 2 : BFS
		// return isBipartiteBFS(graph);
	}

	public boolean isBipartiteDFS(int[][] graph) {

		// 1 - Red
		// 2 - Blue

		int n = graph.length;
		int[] color = new int[graph.length];

		for (int i = 0; i < n; i++) {
			if (color[i] == 0) {
				Stack<Integer> st = new Stack<Integer>();
				st.push(i);
				color[i] = 1;
				while (!st.isEmpty()) {
					int pos = st.pop();
					for (int neigh : graph[pos]) {
						if (color[neigh] == 0) {
							st.push(neigh);
							color[neigh] = color[pos] == 1 ? 2 : 1;
						} else {
							if (color[neigh] == color[pos]) {
								return false;
							}

						}
					}
				}
			}
		}

		return true;
	}

	public boolean isBipartiteBFS(int[][] graph) {

		// 1 - Red
		// 2 - Blue
		int n = graph.length;
		int[] color = new int[graph.length];
		for (int t = 0; t < n; t++) {
			if (color[t] == 0) {
				Queue<Integer> q = new LinkedList<Integer>();
				q.add(t);
				color[t] = 1;
				while (!q.isEmpty()) {
					int size = q.size();
					for (int i = 0; i < size; i++) {
						int pos = q.poll();
						for (int x : graph[pos]) {
							if (color[x] == 0) {
								color[x] = color[pos] == 1 ? 2 : 1;
								q.add(x);
							} else {
								if (color[pos] == color[x]) {
									return false;
								}
							}
						}
					}
				}
			}
		}

		return true;
	}
}
