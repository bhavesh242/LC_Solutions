package com.bhavesh.solutions;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode662 {
	public int widthOfBinaryTree(TreeNode root) {
		int maxWidth = 0;

		Queue<PairTreeInteger<TreeNode, Integer>> q = new LinkedList<PairTreeInteger<TreeNode, Integer>>();
		if (root == null) {
			return maxWidth;
		}
		maxWidth = 0;
		q.add(new PairTreeInteger<>(root, 1));
		while (!q.isEmpty()) {
			PairTreeInteger<TreeNode, Integer> head = q.peek();
			TreeNode heaNode = head.getKey();
			int headIdx = head.getValue();
			int size = q.size();
			for (int i = 0; i < size; i++) {
				PairTreeInteger<TreeNode, Integer> ele = q.poll();
				TreeNode node = ele.getKey();
				int curIdx = ele.getValue();
				maxWidth = Math.max(maxWidth, curIdx - headIdx + 1);
				if (node.left != null) {
					q.add(new PairTreeInteger(node.left, 2 * curIdx));
				}
				if (node.right != null) {
					q.add(new PairTreeInteger(node.right, 2 * curIdx + 1));
				}
			}
		}

		return maxWidth;

	}
}

//Imitation of Pair class in java
class PairTreeInteger<K, V> {

	private final K key;
	private final V value;

	PairTreeInteger(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

}