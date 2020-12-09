package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.List;

public class Leetcode257 {
	List<String> paths;

	public List<String> binaryTreePaths(TreeNode root) {
		paths = new ArrayList();
		dfsWithBackTrack(root, new ArrayList<Integer>());
		return paths;
	}

	public void dfsWithBackTrack(TreeNode root, List<Integer> curPath) {
		if (root == null) {
			return;
		}

		curPath.add(root.val);
		if (root.left == null && root.right == null) {
			paths.add(pathAsString(curPath));
		} else {
			dfsWithBackTrack(root.left, curPath);
			dfsWithBackTrack(root.right, curPath);
		}
		curPath.remove(curPath.size() - 1);

	}

	private String pathAsString(List<Integer> path) {
		StringBuilder sb = new StringBuilder();
		sb.append(path.get(0));
		for (int i = 1; i < path.size(); i++)
			sb.append("->").append(path.get(i));
		return sb.toString();
	}
}