package com.bhavesh.solutions;

public class Leetcode572 {
	public boolean isSubtree(TreeNode s, TreeNode t) {

		return (s != null) && (checker(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t));
	}

	public boolean checker(TreeNode s, TreeNode t) {
		if (s == null && t == null) {
			return true;
		}
		if (s == null || t == null) {
			return false;
		}

		return (s.val == t.val) && checker(s.left, t.left) && checker(s.right, t.right);

	}
}
