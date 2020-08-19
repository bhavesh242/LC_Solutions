package com.bhavesh.amznPrep;

import java.util.Arrays;
import java.util.List;

public class AmznMaxSubtreeTenure {

	NTreeNode maxNode = null;
	double maxTenure = Double.MIN_VALUE;

	public NTreeNode findHighestTenure(NTreeNode root) {
		if (root == null) {
			return maxNode;
		}
		helper(root);
		return maxNode;
	}

	public double[] helper(NTreeNode root) {
		if (root == null) {
			return new double[] { 0, 0 };
		}
		int count = 1;
		double sum = root.val;
		if (root.children != null) {
			for (NTreeNode child : root.children) {
				double cur[] = helper(child);
				sum += cur[0];
				count += cur[1];
			}
		}
		double avg = sum / count;
		if (count > 1 && avg > maxTenure) {
			maxNode = root;
			maxTenure = avg;
		}

		return new double[] { sum, count };
	}

	public static void main(String[] args) {
		// Input:
		// 20
		// / \
		// 12 18
		// / | \ / \
		// 11 2 3 15 8
		NTreeNode left = new NTreeNode(12);
		left.children = Arrays.asList(new NTreeNode(11), new NTreeNode(2), new NTreeNode(3));

		NTreeNode right = new NTreeNode(18);
		right.children = Arrays.asList(new NTreeNode(15), new NTreeNode(8));

		NTreeNode root = new NTreeNode(20);
		root.children = Arrays.asList(left, right);

		test(root); // output: 18
	}

	private static void test(NTreeNode root) {
		NTreeNode maxNode = new AmznMaxSubtreeTenure().findHighestTenure(root);
		System.out.println("Max Average: " + maxNode.val);
	}

}

class NTreeNode {
	public int val;
	public List<NTreeNode> children;

	public NTreeNode() {
	}

	public NTreeNode(int _val) {
		val = _val;
	}

	public NTreeNode(int _val, List<NTreeNode> _children) {
		val = _val;
		children = _children;
	}

}
