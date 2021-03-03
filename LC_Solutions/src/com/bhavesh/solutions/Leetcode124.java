package com.bhavesh.solutions;

public class Leetcode124 {
	int maxSum = Integer.MIN_VALUE;

	public int maxPathSum(TreeNode root) {
		maxPathSumHelper(root);
		return maxSum;
	}

	public int maxPathSumHelper(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = maxPathSumHelper(root.left);
		int right = maxPathSumHelper(root.right);
		int val = root.val;
		if (left > 0) {
			val = val + left;
		}
		if (right > 0) {
			val = val + right;
		}
		
		//Check if the best path through this node is greater than current maxSum
		maxSum = Math.max(maxSum, val);
		
		/*	If we are checking for other paths that pass through the parents of current node, then
`			we can only consider one of the 2 subtrees as current node is the Lowest common ancestor for the 2 
			subtrees so paths passing through ancestors of current node can include only one of the 2 subtrees
		 	We naturally pass the maximal subtree to the ancestor, or just the node value if both subtrees are 
		 	negative
		 */
		return Math.max(root.val, root.val + Math.max(left, right));
	}
}
