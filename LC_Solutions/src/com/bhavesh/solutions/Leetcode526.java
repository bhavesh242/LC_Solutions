package com.bhavesh.solutions;

public class Leetcode526 {
	// Count variable will store our result
	int count;

	public int countArrangement(int N) {
		/*
		 * Visited array will make sure we dont input the same element twice in our
		 * arrangement
		 */
		boolean visited[] = new boolean[N + 1];
		// Start backtracking from index 1
		backTrack(N, 1, visited);
		return count;
	}

	public void backTrack(int N, int index, boolean[] visited) {
		/*
		 * If the current index has exceeded the N value, increase count by 1 and return
		 */
		if (index > N) {
			count++;
			return;
		}
		/*
		 * For current index find a value between 1 and M that has not already been used
		 * and satisfies the 2 rules of divisibility. If such a number is found, mark it
		 * as visited and proceed to the next position
		 */
		for (int i = 1; i <= N; i++) {
			if (!visited[i] && (i % index == 0 || index % i == 0)) {
				visited[i] = true;
				backTrack(N, index + 1, visited);
				visited[i] = false;
			}
		}
	}
}
