package com.bhavesh.solutions;

public class Leetcode24 {
	public ListNode swapPairs(ListNode head) {

		return swapNodes(head);
	}

	public ListNode swapNodes(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode next = head.next;
		ListNode temp = next.next;
		next.next = head;
		head.next = swapNodes(temp);
		return next;
	}
}
