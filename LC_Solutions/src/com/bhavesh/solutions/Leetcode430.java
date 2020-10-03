package com.bhavesh.solutions;

public class Leetcode430 {
	public NodeDLL flatten(NodeDLL head) {
		if (head == null) {
			return null;
		}
		NodeDLL flattendedChild = flatten(head.child);
		NodeDLL sortedNext = flatten(head.next);
		if (flattendedChild != null) {
			NodeDLL child = flattendedChild;
			while (child.next != null) {
				child = child.next;
			}
			head.next = flattendedChild;
			flattendedChild.prev = head;
			child.next = sortedNext;
			if (sortedNext != null)
				sortedNext.prev = child;
		}

		head.child = null;
		return head;
	}
}

class NodeDLL {
	public int val;
	public NodeDLL prev;
	public NodeDLL next;
	public NodeDLL child;
}