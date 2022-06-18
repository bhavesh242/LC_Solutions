package com.bhavesh.solutions;

import java.util.HashSet;

public class Leetcode1615 {

	//O(n^2) using 2-D matrix for graph and indegree array
	public int maximalNetworkRank(int n, int[][] roads) {

		boolean[][] graph = new boolean[n][n];
		int indegree[] = new int[n];
		/*For each pair of nodes in a road, update the graph[i][j] = true
		 also update the indegrees for both nodes*/
		for (int[] road : roads) {
			graph[road[0]][road[1]] = true;
			indegree[road[0]]++;
			graph[road[1]][road[0]] = true;
			indegree[road[1]]++;
		}
		
		//For each possible pair of nodes, calculate rank
		//If nodes are connected, rank = indegree[i]+indegree[j]-1
		//Otherwise rank = indegree[i]+indegree[j]
		int result = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				result = Math.max(result, graph[i][j] ? indegree[i] + indegree[j] - 1 : indegree[i] + indegree[j]);
			}
		}
		return result;

	}

	/*Same approach as above, but using an array of hashset to record graph connectivity and use 
	 it's size of graph[i] to find node i's indegree*/
	//O(n^2) time complexity, but slower because of hashing overhead
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

}