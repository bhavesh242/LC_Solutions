package com.bhavesh.solutions;

import java.util.HashSet;

public class Leetcode1615 {

	// O(n^2) using 2-D matrix for graph and indegree array
	public int maximalNetworkRank(int n, int[][] roads) {

		boolean[][] graph = new boolean[n][n];
		int indegree[] = new int[n];
		/*
		 * For each pair of nodes in a road, update the graph[i][j] = true also update
		 * the indegrees for both nodes
		 */
		for (int[] road : roads) {
			graph[road[0]][road[1]] = true;
			indegree[road[0]]++;
			graph[road[1]][road[0]] = true;
			indegree[road[1]]++;
		}

		// For each possible pair of nodes, calculate rank
		// If nodes are connected, rank = indegree[i]+indegree[j]-1
		// Otherwise rank = indegree[i]+indegree[j]
		int result = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				result = Math.max(result, graph[i][j] ? indegree[i] + indegree[j] - 1 : indegree[i] + indegree[j]);
			}
		}
		return result;

	}

	/*
	 * Same approach as above, but using an array of hashset to record graph
	 * connectivity and use it's size of graph[i] to find node i's indegree
	 */
	// O(n^2) time complexity, but slower because of hashing overhead
	public int maximalNetworkRank1(int n, int[][] roads) {

		HashSet<Integer>[] graph = new HashSet[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new HashSet<Integer>();
		}
		for (int[] road : roads) {
			graph[road[0]].add(road[1]);
			graph[road[1]].add(road[0]);

		}

		int result = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {

				int connect = graph[i].size() + graph[j].size();
				result = Math.max(result, graph[i].contains(j) ? connect - 1 : connect);
			}
		}

		return result;

	}

	// O(m+n) solution
	public int maximalNetworkRank3(int n, int[][] roads) {
		// calculate all node degrees
		int[] degrees = new int[n];
		for (int[] road : roads) {
			degrees[road[0]]++;
			degrees[road[1]]++;
		}

		// find first max and second max degree
		int firstMax = 0, secondMax = 0;
		for (int degree : degrees) {
			if (degree > firstMax) {
				secondMax = firstMax;
				firstMax = degree;
			} else if (degree > secondMax && degree < firstMax) {
				secondMax = degree;
			}
		}

		// there can be multiple node with firstMax degree or secondMax degree
		// find the count of such nodes
		int fmCount = 0, smCount = 0;
		for (int degree : degrees) {
			if (degree == firstMax)
				fmCount++;
			if (degree == secondMax)
				smCount++;
		}

		// if there are more than one node with degree = 'firstMax'
		// then we can consider 'firstMax' twice and we don't need to use 'secondMax'
		if (fmCount > 1) {
			// here the answer in general will be 2*firstMax
			// but if the two nodes that we're considering are connected, then we'll have to
			// subtract 1
			// in other words, if there are atleast one such pair, whose degree = 'firstMax'
			// and
			// they're not connected then answer is 2*firstMax, else if all nodes with
			// degree = 'firstMax'
			// are connected to each other, then answer is 2*firstMax-1, as we'll have one
			// common edge
			int edgeCount = 0;
			for (int[] road : roads)
				if (degrees[road[0]] == firstMax && degrees[road[1]] == firstMax)
					edgeCount++;

			// maximum number of edges possible with all the nodes with degree = 'firstMax'
			int maxEdgeWithFirstMax = fmCount * (fmCount - 1) / 2;

			// if all nodes with degree = 'firstMax' are connected to each other, then
			// subtract 1
			return 2 * firstMax - (maxEdgeWithFirstMax == edgeCount ? 1 : 0);
		}

		// if there is only one node with degree = 'firstMax'
		// then we'll need to use node with degree = 'secondMax'
		else {
			// here the answer in general will be (firstMax + secondMax)
			// but if the two nodes that we're considering are connected, then we'll have to
			// subtract 1
			// in other words, if there are atleast one such pair, whose degree = 'firstMax'
			// and 'secondMax'
			// and they're not connected then answer is (firstMax + secondMax), else if all
			// pairs
			// are connected to each other, then answer is (firstMax + secondMax - 1), as
			// we'll have one common edge
			int edgeCount = 0;
			for (int[] road : roads) {
				if (degrees[road[0]] == firstMax && degrees[road[1]] == secondMax)
					edgeCount++;
				if (degrees[road[0]] == secondMax && degrees[road[1]] == firstMax)
					edgeCount++;
			}

			// if all pairs are connected to each other, then subtract 1
			return firstMax + secondMax - (smCount == edgeCount ? 1 : 0);
		}
	}
}
