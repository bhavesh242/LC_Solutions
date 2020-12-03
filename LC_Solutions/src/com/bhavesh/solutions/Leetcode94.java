package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.List;

public class Leetcode94 {
	List<Integer> op;

	public List<Integer> inorderTraversal(TreeNode root) {
		op = new ArrayList();
		inorder(root);
		return op;
	}

	public void inorder(TreeNode root) {
		if (root == null) {
			return;
		}
		inorder(root.left);
		op.add(root.val);
		inorder(root.right);

	}
}