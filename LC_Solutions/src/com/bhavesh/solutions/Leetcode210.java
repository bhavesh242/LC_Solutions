package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Leetcode210 {
	public int[] findOrder(int numCourses, int[][] prerequisites) {

		int[] incoming = new int[numCourses];

		HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();

		for (int edge[] : prerequisites) {
			graph.putIfAbsent(edge[1], new ArrayList<Integer>());
			graph.get(edge[1]).add(edge[0]);
			incoming[edge[0]]++;
		}

		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < numCourses; i++) {
			if (incoming[i] == 0) {
				queue.add(i);
			}
		}

		int i = 0;
		int ans[] = new int[numCourses];
		while (!queue.isEmpty()) {
			int node = queue.poll();
			ans[i++] = node;
			for (int x : graph.getOrDefault(node, new ArrayList<Integer>())) {
				if (--incoming[x] == 0) {
					queue.add(x);
				}
			}
		}

		if (i == numCourses) {
			return ans;
		}

		return new int[0];

	}
}