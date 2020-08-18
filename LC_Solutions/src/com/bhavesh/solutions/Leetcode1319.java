package com.bhavesh.solutions;

import java.util.ArrayList;

public class Leetcode1319 {
	public int makeConnected(int n, int[][] connections) {

		// to connect V vertices, we need atleast E = V-1
		if (n > connections.length + 1) {
			return -1;
		}
		// Create an undirected graph using an arraylist of arrays
		ArrayList<Integer> graph[] = new ArrayList[n];
		// Create a visited array to perform DFS
		boolean visited[] = new boolean[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		// Create an undirected graph
		for (int i = 0; i < connections.length; i++) {
			graph[connections[i][0]].add(connections[i][1]);
			graph[connections[i][1]].add(connections[i][0]);
		}
		// Count number of disconnected components
		int conComp = 0;
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				ArrayList<Integer> com = new ArrayList();
				conComp++;
				dfs(graph, visited, i);
			}
		}
		/*
		 * If we have sufficient number of edges, the minimum number of edges required
		 * to connect the entire system would be number of different connected
		 * components -1
		 */
		return conComp - 1;

	}

	// This method performs DFS
	public void dfs(ArrayList<Integer>[] graph, boolean[] visited, int node) {
		visited[node] = true;
		for (int x : graph[node]) {
			if (!visited[x]) {
				dfs(graph, visited, x);
			}
		}
	}
}
