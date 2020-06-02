package com.bhavesh.solutions;

public class Leetcode236 {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode anc = helper(root, p, q);
		return anc;
	}

	public TreeNode helper(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}
		if (root == p) {
			return p;
		}
		if (root == q) {
			return q;
		}

		TreeNode findLeft = helper(root.left, p, q);
		TreeNode findRight = helper(root.right, p, q);

		if (findLeft != null && findRight != null) {
			return root;
		}
		if (findLeft != null) {
			return findLeft;
		}
		if (findRight != null) {
			return findRight;
		}
		return null;

	}
}
