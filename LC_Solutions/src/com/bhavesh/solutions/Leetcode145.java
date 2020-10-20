package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.List;

public class Leetcode145 {
	List<Integer> op;

	public List<Integer> postorderTraversal(TreeNode root) {
		op = new ArrayList();
		helper(root);
		return op;
	}

	public void helper(TreeNode root) {
		if (root == null) {
			return;
		}
		helper(root.left);
		helper(root.right);
		op.add(root.val);

	}
}