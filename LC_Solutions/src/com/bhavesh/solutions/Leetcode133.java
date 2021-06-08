package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode133 {

	class Node {
		public int val;
		public List<Node> neighbors;

		public Node() {
			val = 0;
			neighbors = new ArrayList<Node>();
		}

		public Node(int _val) {
			val = _val;
			neighbors = new ArrayList<Node>();
		}

		public Node(int _val, ArrayList<Node> _neighbors) {
			val = _val;
			neighbors = _neighbors;
		}
	}

	Node map[] = new Node[101];

	// Method 1 : DFS
	public Node cloneGraph_1(Node node) {
		if (node == null) {
			return null;
		}
		if (map[node.val] != null) {
			return map[node.val];
		}

		Node newNode = new Node(node.val, new ArrayList<Node>());
		map[node.val] = newNode;
		for (Node n : node.neighbors) {
			newNode.neighbors.add(cloneGraph_1(n));
		}

		return newNode;
	}

	// Method2 : BFS
	public Node cloneGraph_2(Node node) {
		if (node == null) {
			return null;
		}
		Node map[] = new Node[101];
		Queue<Node> queue = new LinkedList<Node>();
		Node newNode = new Node(node.val, new ArrayList<Node>());
		queue.add(node);
		map[node.val] = newNode;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			for (Node n : cur.neighbors) {
				if (map[n.val] == null) {
					map[n.val] = new Node(n.val, new ArrayList<Node>());
					queue.add(n);
				}
				map[cur.val].neighbors.add(map[n.val]);

			}
		}

		return newNode;

	}

}
