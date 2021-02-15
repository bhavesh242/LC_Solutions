package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode582 {
	public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {

		// HashMap + BFS

		HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < pid.size(); i++) {
			if (ppid.get(i) == 0) {
				continue;
			}
			graph.putIfAbsent(ppid.get(i), new ArrayList<Integer>());
			graph.get(ppid.get(i)).add(pid.get(i));
		}

		List<Integer> list = new ArrayList<Integer>();
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(kill);
		while (!queue.isEmpty()) {
			int process = queue.poll();
			list.add(process);
			for (int x : graph.getOrDefault(process, new ArrayList<Integer>())) {
				queue.add(x);
			}
		}

		return list;
	}
}