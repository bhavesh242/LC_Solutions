package com.bhavesh.solutions;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode297 {

}

class Codec {

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		StringBuffer str = new StringBuffer();
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if (node == null) {
				str.append("null,");

			} else {
				str.append(node.val + ",");
				queue.add(node.left);
				queue.add(node.right);
			}
		}
		return str.toString();

	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		String nodes[] = data.split(",");
		if (nodes[0].equalsIgnoreCase("null")) {
			return null;
		}
		TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
		int i = 0;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		i++;
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();

			String left = nodes[i++];
			String right = nodes[i++];
			if (!left.equalsIgnoreCase("null")) {
				node.left = new TreeNode(Integer.parseInt(left));
				queue.add(node.left);
			}
			if (!right.equalsIgnoreCase("null")) {
				node.right = new TreeNode(Integer.parseInt(right));
				queue.add(node.right);
			}
		}

		return root;
	}
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));