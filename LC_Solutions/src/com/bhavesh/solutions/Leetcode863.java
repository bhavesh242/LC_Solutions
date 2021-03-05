package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode863 {

	/*
	 * Approach 1 : USE DFS traversal to annotate each node's parents and then start
	 * a bidirectional bfs from the target node towards it's children and parent to
	 * find nodes at distance K
	 */
	HashMap<TreeNode, TreeNode> parents;

	public List<Integer> distanceK_1(TreeNode root, TreeNode target, int K) {
		parents = new HashMap<TreeNode, TreeNode>();
		dfs(root);
		HashSet<TreeNode> seen = new HashSet<TreeNode>();
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(target);
		seen.add(target);
		int dist = 0;
		ArrayList<Integer> op = new ArrayList<Integer>();
		while (!q.isEmpty()) {
			if (dist == K) {
				for (TreeNode x : q) {
					op.add(x.val);
				}
				return op;
			}

			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = q.poll();
				if (node.left != null && !seen.contains(node.left)) {
					q.add(node.left);
					seen.add(node.left);
				}
				if (node.right != null && !seen.contains(node.right)) {
					q.add(node.right);
					seen.add(node.right);
				}
				if (parents.containsKey(node) && !seen.contains(parents.get(node))) {
					q.add(parents.get(node));
					seen.add(parents.get(node));
				}
			}
			dist++;
		}

		return op;

	}

	public void dfs(TreeNode root) {
		if (root == null) {
			return;
		}
		if (root.left != null) {
			parents.put(root.left, root);
			dfs(root.left);
		}
		if (root.right != null) {
			parents.put(root.right, root);
			dfs(root.right);
		}
	}

	
	//Approach 2
	int k;
	ArrayList<Integer> ans;
	TreeNode target;

	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
		ans = new ArrayList<Integer>();
		k = K;
		this.target = target;
		findTarget(root);
		return ans;
	}

	public int findTarget(TreeNode root) {
		if (root == null) {
			return -1;
		}
		if (root == target) {
			addsubtree(0, root);
			return 1;
		}

		int left = findTarget(root.left);
		int right = findTarget(root.right);

		if (left != -1) {
			if (left == k) {
				ans.add(root.val);
			} else if (left < k) {
				addsubtree(left + 1, root.right);
			}
			return left + 1;
		} else if (right != -1) {
			if (right == k) {
				ans.add(root.val);
			} else if (right < k) {
				addsubtree(right + 1, root.left);
			}
			return right + 1;
		} else {
			return -1;
		}
	}

	public void addsubtree(int dist, TreeNode root) {
		if (root == null) {
			return;
		}
		if (dist == k) {
			ans.add(root.val);

		} else if (dist < k) {
			addsubtree(dist + 1, root.left);
			addsubtree(dist + 1, root.right);
		}
	}

}