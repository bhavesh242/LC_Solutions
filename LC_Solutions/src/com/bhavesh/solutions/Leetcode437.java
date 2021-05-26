package com.bhavesh.solutions;

import java.util.HashMap;

public class Leetcode437 {

	//Sol 2 is more efficient in time
}

//o(n) using prefix sum
class Sol1437_2 {
	HashMap<Integer, Integer> map;
	int target = 0, count = 0;

	public int pathSum(TreeNode root, int targetSum) {
		// To store counts of prefix sum
		map = new HashMap<Integer, Integer>();
		target = targetSum;
		preorder(root, 0);
		return count;
	}

	public void preorder(TreeNode root, int curSum) {
		// If root is null, just go back
		if (root == null) {
			return;
		}
		// Add root val to get current prefix sum
		curSum += root.val;
		// If the curremt sum is the same as target, add 1 to count
		if (target == curSum) {
			count++;
		}

		/*
		 * Check if we encountered any of the current prexif sum's compliments until
		 * this point. If yes, add those to our current count
		 */
		count += map.getOrDefault(curSum - target, 0);
		// Add the current sum's count in the map
		map.put(curSum, map.getOrDefault(curSum, 0) + 1);
		// Continue with the preorder traversals
		preorder(root.left, curSum);
		preorder(root.right, curSum);

		/*
		 * Remove the prefix sum so that it does not interefere in the processing of the
		 * parellel subtrees emerging from other roots this root
		 */

		map.put(curSum, map.get(curSum) - 1);

	}
}

//(n^2) time complexity, but this can be improved using prefix sum
class Sol437_1 {
	int pathCount = 0;

	public int pathSum(TreeNode root, int targetSum) {
		if (root == null) {
			return 0;
		}
		helper(root, targetSum);
		pathSum(root.left, targetSum);
		pathSum(root.right, targetSum);
		return pathCount;
	}

	public void helper(TreeNode root, int sum) {
		if (root == null) {
			return;
		}
		sum = sum - root.val;
		if (sum == 0) {
			pathCount++;
		}
		helper(root.left, sum);
		helper(root.right, sum);
	}
}
