package com.bhavesh.solutions;

public class Leetcode61 {
	public ListNode rotateRight(ListNode head, int k) {
		ListNode cur = head;
		int n = 0;
		// Find out the size of the Linked List
		while (cur != null) {
			n++;
			cur = cur.next;
		}
		// If the size of the list is 0, return the empty list
		if (n == 0) {
			return head;
		}
		// mod the value of K with the size of the list, to get rid of cycles
		k = k % n;
		if (k == 0) {
			return head;
		}
		// Go to the node just before the kth node from last
		cur = head;
		for (int i = n; i > k + 1; i--) {
			cur = cur.next;
		}

		// The new head is next node of current
		ListNode newHead = cur.next;

		// Use the newHead as the head of the rotated list and close the list at n-kth
		// node
		cur.next = null;

		cur = newHead;
		while (cur.next != null) {
			cur = cur.next;
		}
		cur.next = head;
		return newHead;
	}
}