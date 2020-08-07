package com.bhavesh.solutions;

public class Leetcode547 {
	public int findCircleNum(int[][] M) {
		int count = 0;
		// we solve this using depth first search
		// Create a visited array
		boolean visited[] = new boolean[M.length];
		// start dfs for all number (1 to N) represented by indexes (0 to N-1)
		for (int i = 0; i < M.length; i++) {
			/*
			 * If an index has not been traversed, it belongs to a friend circle consisting
			 * of one or more people and needs to be explored
			 */
			if (!visited[i]) {
				// mark that index as visited and initiate dfs
				visited[i] = true;
				dfs(M, visited, i);
				// increase count
				count++;
			}
		}
		return count;
	}

	// Method for DFS
	public void dfs(int[][] M, boolean[] visited, int i) {

		for (int j = 0; j < M.length; j++) {
			/*
			 * check if an index is unvisited and a friend of i if it is, it belongs to i's
			 * friend circle and needs to be explored further
			 */
			if (!visited[j] && M[i][j] == 1) {
				// mark it as visited and continue the dfs
				visited[j] = true;
				dfs(M, visited, j);
			}
		}
	}
}