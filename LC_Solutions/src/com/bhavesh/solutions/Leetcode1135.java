package com.bhavesh.solutions;

import java.util.PriorityQueue;

public class Leetcode1135 {
	int parents[];
	int rank[];

	public int minimumCost(int n, int[][] connections) {

		// You need atleast n-1 connections to build a Minimum Spanning Tree
		if (connections.length < n - 1) {
			return -1;
		}

		// Initiailize parents and rank
		parents = new int[n + 1];
		rank = new int[n + 1];
		// These will track total sum and number of edges
		int edges = 0;
		int sum = 0;

		for (int i = 1; i <= n; i++) {
			rank[i] = 1;
			parents[i] = i;
		}

		// PriortiyQueue to sort connections in ascending order of edge cost
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[2] - b[2]);
		for (int[] conn : connections) {
			pq.offer(conn);
		}

		// While connections exist in Min heap and number of edges is still less than
		// n-1
		while (!pq.isEmpty() && edges < n - 1) {

			int conn[] = pq.remove();
			// Union find
			int a = conn[0];
			int b = conn[1];

			if (union(a, b)) {
				edges++;
				sum += conn[2];
			}
		}

		return sum;

	}

	public int find(int x) {
		if (x != parents[x]) {
			parents[x] = find(parents[x]);
		}
		return parents[x];
	}

	public boolean union(int a, int b) {
		int A = find(a);
		int B = find(b);

		if (A == B) {
			return false;
		}

		if (rank[A] > rank[B]) {
			parents[B] = A;
			rank[A] += rank[B];
		} else {
			parents[A] = B;
			rank[B] += rank[A];
		}

		return true;
	}
}