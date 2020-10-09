package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode987 {
	int maxCol = Integer.MIN_VALUE;
	int minCol = Integer.MAX_VALUE;

	public List<List<Integer>> verticalTraversal(TreeNode root) {
		Queue<NodeLevels> queue = new LinkedList();
		queue.add(new NodeLevels(root, 0));
		HashMap<Integer, List<Integer>> map = new HashMap();
		while (!queue.isEmpty()) {
			int size = queue.size();
			HashMap<Integer, ArrayList<Integer>> levelMap = new HashMap();
			for (int i = 0; i < size; i++) {
				NodeLevels node = queue.remove();
				maxCol = Math.max(maxCol, node.level);
				minCol = Math.min(minCol, node.level);
				levelMap.putIfAbsent(node.level, new ArrayList());
				levelMap.get(node.level).add(node.node.val);
				if (node.node.left != null) {
					queue.add(new NodeLevels(node.node.left, node.level - 1));
				}
				if (node.node.right != null) {
					queue.add(new NodeLevels(node.node.right, node.level + 1));
				}
			}
			for (int x : levelMap.keySet()) {
				ArrayList l = levelMap.get(x);
				Collections.sort(l);
				map.putIfAbsent(x, new ArrayList());
				map.get(x).addAll(l);
			}
		}
		List<List<Integer>> op = new ArrayList();
		for (int x = minCol; x <= maxCol; x++) {
			if (map.containsKey(x)) {
				op.add(map.get(x));
			}
		}

		return op;

	}

}

class NodeLevels {
	TreeNode node;
	int level;

	NodeLevels(TreeNode n, int x) {
		node = n;
		level = x;
	}
}