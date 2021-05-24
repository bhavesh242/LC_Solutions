package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Leetcode323 {
	// Approach 1 : Union Find (path compression)
	public int countComponents(int n, int[][] edges) {
		int parents[] = new int[n];

		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}

		for (int[] e : edges) {
			int A = find(e[0], parents);
			int B = find(e[1], parents);
			if (A == B) {
				continue;
			}
			parents[A] = B;

		}

		int count = 0;
		for (int i = 0; i < n; i++) {
			if (parents[i] == i) {
				count++;
			}
		}

		return count;
	}

	public int find(int x, int parents[]) {
		if (x == parents[x]) {
			return x;
		}
		int k = find(parents[x], parents);
		parents[x] = k;
		return parents[x];
		
		/*
		 Without path compression
		 if(x == parents[x])
		 {
		 	return x;
		 }
		 return find(parents[x], parents);
		 */
	}

	// Approach 2 : DFS
	public int countComponents_1(int n, int[][] edges) {
		HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			map.put(i, new ArrayList<Integer>());
		}

		for (int[] edge : edges) {
			map.get(edge[0]).add(edge[1]);
			map.get(edge[1]).add(edge[0]);
		}

		int count = 0;
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				count++;
				dfs(i, map, visited);
			}
		}

		return count;
	}

	public void dfs(int i, HashMap<Integer, List<Integer>> graph, boolean[] visited) {
		if (visited[i]) {
			return;
		}
		visited[i] = true;
		for (int x : graph.get(i)) {
			dfs(x, graph, visited);
		}
	}
}