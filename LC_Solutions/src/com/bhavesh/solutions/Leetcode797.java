package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.List;

public class Leetcode797 {
	List<List<Integer>> list = new ArrayList();
	int graph[][];

	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
		this.graph = graph;
		ArrayList<Integer> path = new ArrayList();
		path.add(0);
		dfsBackTrack(0, path);
		return list;
	}

	public void dfsBackTrack(int curr, List<Integer> path) {
		if (curr == graph.length - 1) {
			list.add(new ArrayList<Integer>(path));
			return;
		}
		for (int x : graph[curr]) {
			path.add(x);
			dfsBackTrack(x, path);
			path.remove(path.size() - 1);
		}
	}
}