package com.bhavesh.solutions;

public class Leetcode1120 {
	double maxAvg = 0;

	public double maximumAverageSubtree(TreeNode root) {
		helper(root);
		return maxAvg;
	}

	// Bottom Up recursuve approach where you pass up the sum of Values and Count of
	// nodes up
	public Pairs helper(TreeNode root) {
		if (root == null) {
			return new Pairs(0, 0);
		}
		Pairs leftA = helper(root.left);
		Pairs rightA = helper(root.right);
		double curAvg = (leftA.sum + rightA.sum + root.val) / (leftA.count + rightA.count + 1);
		maxAvg = Math.max(maxAvg, curAvg);
		return new Pairs(leftA.count + rightA.count + 1, leftA.sum + rightA.sum + root.val);
	}
}

// Custom Class to bundle Count of Nodes and their sum together
class Pairs {
	int count;
	double sum;

	Pairs(int ct, double s) {
		count = ct;
		sum = s;
	}
}