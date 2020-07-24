package com.bhavesh.solutions;

public class Leetcode19 {
	public ListNode removeNthFromEnd(ListNode head, int n) {
		
		//Create A dummy head and point a left and right pointer to it
		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode left = dummy, right = dummy;

		//Move right pointer by n positions
		for (int i = 0; i < n; i++) {
			right = right.next;
		}
		
		//Move both left and right pointers until the right pointer reaches last node 
		while (right.next != null) {
			right = right.next;
			left = left.next;
		}
		
		//delete the node left pointer is pointing to
		left.next = left.next.next;
		return dummy.next;
	}
}
