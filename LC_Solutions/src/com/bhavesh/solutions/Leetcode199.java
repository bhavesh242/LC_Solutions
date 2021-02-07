package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode199 {
	public List<Integer> rightSideView(TreeNode root) {
		
		//Main idea : Perform BFS and insert last element from each level into answer list
		ArrayList<Integer> visible = new ArrayList<Integer>();
		if (root == null) {
			return visible;
		}

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int n = queue.size();
			for (int i = 0; i < n; i++) {
				TreeNode k = queue.remove();

				if (i == n - 1) {
					visible.add(k.val);
				}
				if (k.left != null)
					queue.add(k.left);
				if (k.right != null)
					queue.add(k.right);
			}
		}

		return visible;
	}
}