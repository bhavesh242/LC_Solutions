package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.List;

public class Leetcode1557 {
	public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {

		/*
		 * Simple Solution : Nodes with no indegrees will not be reached from any other
		 * nodes so they must definetly be included in the solution set
		 */

		/*
		 * Since all other nodes with indegree > 0 have an edge coming into them, they
		 * will reached from other nodes, therefore our solution set wll only include
		 * nodes that have 0 indegree
		 */

		int[] indegree = new int[n];
		for (List<Integer> edge : edges) {
			indegree[edge.get(1)]++;
		}
		List<Integer> op = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			if (indegree[i] == 0) {
				op.add(i);
			}
		}
		return op;
	}
}