package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Leetcode1192 {

	int time = 0;
	int low[];
	int disc[];
	boolean visited[];
	HashMap<Integer, ArrayList<Integer>> graph;
	List<List<Integer>> ans;

	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

		init(n, connections);
		// Perform dfs on every node that is unvisited
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				dfs(i, -1);
			}
		}

		return ans;
	}

	// This method initializes discovery times array, visited array, low visit times
	// and builds the graph.
	public void init(int n, List<List<Integer>> connections) {
		visited = new boolean[n];
		low = new int[n];
		disc = new int[n];
		graph = new HashMap<Integer, ArrayList<Integer>>();
		ans = new ArrayList<List<Integer>>();
		for (List<Integer> edge : connections) {
			graph.putIfAbsent(edge.get(0), new ArrayList<Integer>());
			graph.putIfAbsent(edge.get(1), new ArrayList<Integer>());
			graph.get(edge.get(0)).add(edge.get(1));
			graph.get(edge.get(1)).add(edge.get(0));
		}
	}

	// Perform DFS
	public void dfs(int i, int parent) {
		// Mark node as visited
		visited[i] = true;

		// For first time visit, discovery and low time are same
		low[i] = time;
		disc[i] = time;
		time++;

		// For all neighbours of the current node
		for (int x : graph.get(i)) {
			// If the neighbour is the parent node from which we arrived at this node,
			// continue
			if (x == parent) {
				continue;
			}
			// If the neighbour is unvisited we perform DFs on it with passing current node
			// as parent
			if (!visited[x]) {
				dfs(x, i);
				// After we return from the dfs, we set this node's lowtime as the lowest time
				// amongst the node's neighbours
				low[i] = Math.min(low[i], low[x]);
				// If the discovery time of node i is still lower than the low time of it's
				// neighbour x, it means edge [x,i] is a critical connection
				if (low[x] > disc[i]) {
					ArrayList<Integer> a = new ArrayList<Integer>();
					a.add(x);
					a.add(i);
					ans.add(a);
				}
			}

			/*
			 * If the neighbour was already visited, we set the low time of the current node
			 * i as the lower among the low time of node i or discovery time of already
			 * visited neighbour x
			 */
			else {
				low[i] = Math.min(low[i], disc[x]);
			}

		}
	}
}