package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

public class Leetcode261 {
	
	
	//Approach : union find with path compression
	public boolean validTree(int n, int[][] edges) {
		//Check is the no. of edges = n-1
		if (edges.length != n - 1) {
			return false;
		}
		
		//Parents to note parents
		int parents[] = new int[n];
		//To note sizes of disjoint sets
		int sizes[] = new int[n];
		
		for (int i = 0; i < n; i++) {
			parents[i] = i;
			sizes[i] = 1;
		}

		//Find method also does path compression
		for (int edge[] : edges) {
			int x = find(edge[0], parents);
			int y = find(edge[1], parents);
			
			//If these nodes already belong to the same set, there is a cycle
			if (x == y) {
				return false;
			}
			
			//Always make smaller set child of bigger set
			if (sizes[x] > sizes[y]) {
				parents[y] = x;
				sizes[x] += sizes[y];
			} else {
				parents[x] = y;
				sizes[y] += sizes[x];
			}
		}

		return true;
	}

	// Find With path compression
	public int find(int A, int parents[]) {
		
		int root = A;
		//1st loop to find root
		while (parents[root] != root) {
			root = parents[root];
		}

		//Second loop to make each node in the path to root as direct child of root
		while (A != root) {
			int oldRoot = parents[A];
			parents[A] = root;
			A = oldRoot;
		}

		return root;
	}

	// Approach : UnionFind without path compression
	public boolean validTree3(int n, int[][] edges) {
		if (edges.length != n - 1) {
			return false;
		}
		int parents[] = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}

		for (int edge[] : edges) {
			int parent = find3(edge[0], parents);
			int child = find3(edge[1], parents);
			if (parent == child) {
				return false;
			}
			parents[child] = parent;
		}

		return true;
	}

	// Find Without path compression
	public int find3(int A, int parents[]) {
		if (parents[A] == A) {
			return A;
		}

		return find(parents[A], parents);

	}

	public boolean validTree1(int n, int[][] edges) {
		if (edges.length != n - 1) {
			return false;
		}

		Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();

		// Create an adjacency list
		for (int[] edge : edges) {
			map.putIfAbsent(edge[0], new ArrayList<Integer>());
			map.putIfAbsent(edge[1], new ArrayList<Integer>());
			map.get(edge[0]).add(edge[1]);
			map.get(edge[1]).add(edge[0]);
		}

		HashSet<Integer> seen = new HashSet<Integer>();
		Stack<Integer> st = new Stack<Integer>();
		st.add(0);
		seen.add(0);
		while (!st.isEmpty()) {
			int node = st.pop();
			for (int neigh : map.getOrDefault(node, new ArrayList<Integer>())) {
				// If neighbour is already seen continue
				if (seen.contains(neigh)) {
					continue;
				}
				// Else add this neighbour to stack to explore and set it as seen
				st.push(neigh);
				seen.add(neigh);
			}
		}

		// Check if we were able to see all nodes
		return seen.size() == n;

	}

	public boolean validTree2(int n, int[][] edges) {

		Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();

		// Create an adjacency list
		for (int[] edge : edges) {
			map.putIfAbsent(edge[0], new ArrayList<Integer>());
			map.putIfAbsent(edge[1], new ArrayList<Integer>());
			map.get(edge[0]).add(edge[1]);
			map.get(edge[1]).add(edge[0]);
		}

		/*
		 * We use the parent map to keep track of seen nodes as well as to keep track of
		 * the parent node from which we got to this current node
		 */
		HashMap<Integer, Integer> parent = new HashMap<Integer, Integer>();
		Stack<Integer> st = new Stack<Integer>();
		st.add(0);
		parent.put(0, -1);
		while (!st.isEmpty()) {
			int node = st.pop();
			for (int neigh : map.getOrDefault(node, new ArrayList<Integer>())) {
				// If neighbour is current node's parent, move to next parent
				if (parent.get(node) == neigh) {
					continue;
				}

				// If neighbour is in parent table, it means it was already seen and therefore
				// cycle exists
				if (parent.containsKey(neigh)) {
					return false;
				}

				// Else add this neighbour to stack to explore and set node as it's parent
				st.push(neigh);
				parent.put(neigh, node);
			}
		}

		// Check is all nodes were seen
		return parent.size() == n;
	}

}