package com.bhavesh.solutions;

public class Leetcode559 {

	//find max depth of an N-ary tree node
	public int maxDepth(Node root) {
		
		//If node does not exist, depth of that node is 0;
		if (root == null) {
			return 0;
		}
		//For maxDepth out of all children for given node 
		int max = 0;
		for (Node child : root.children) {
			int depth = maxDepth(child);
			if (depth > max) {
				max = depth;
			}
		}
		//for a given node, maxDepth will be depth of that node (1) + maximum depth out of all it's children
		return 1 + max;
	}
}
