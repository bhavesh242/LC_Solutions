package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Leetcode721 {
	
	
	//Solution 1  : DFS
	public List<List<String>> accountsMerge(List<List<String>> accounts) {

		HashMap<String, String> emailToName = new HashMap<String, String>();
		HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();

		for (List<String> acc : accounts) {
			String name = acc.get(0);
			for (int i = 1; i < acc.size(); i++) {
				emailToName.put(acc.get(i), name);
				graph.putIfAbsent(acc.get(i), new ArrayList<String>());
				if (i == 1) {
					continue;
				}
				graph.get(acc.get(i)).add(acc.get(1));
				graph.get(acc.get(1)).add(acc.get(i));
			}
		}

		HashSet<String> seen = new HashSet<String>();
		List<List<String>> ans = new ArrayList<List<String>>();
		for (String node : graph.keySet()) {
			if (!seen.contains(node)) {
				ArrayList<String> curNodes = new ArrayList<String>();
				dfs(node, graph, seen, curNodes);
				Collections.sort(curNodes);
				curNodes.add(0, emailToName.get(node));
				ans.add(curNodes);
			}
		}

		return ans;
	}

	public void dfs(String node, HashMap<String, ArrayList<String>> graph, HashSet<String> seen,
			ArrayList<String> curNodes) {
		curNodes.add(node);
		seen.add(node);
		for (String neigh : graph.get(node)) {
			if (!seen.contains(neigh)) {
				dfs(neigh, graph, seen, curNodes);
			}
		}
		return;
	}
}