package com.bhavesh.solutions;

public class Leetcode143 {

	// Simple Algorithm consisting of 3 steps
	// Step 1 : Find Middle Node
	// Step 2 : Reverse the second half of the list
	// Step 3 : Merge the 2 lists

	public void reorderList(ListNode head) {

		if (head == null) {
			return;
		}
		// Step 1 find the middle element using the classic slow and fast pointer
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}

		// Step 2:
		// Slow is now the head of our second list
		// Reverse this list
		ListNode prev = null;
		ListNode temp = null;
		ListNode cur = slow;
		while (cur != null) {
			temp = cur.next;
			cur.next = prev;
			prev = cur;
			cur = temp;
		}

		// Step 3 : Merge the 2 lists
		ListNode first = head;
		ListNode second = prev;
		while (second.next != null) {
			temp = first.next;
			first.next = second;
			second = second.next;
			first.next.next = temp;
			first = temp;
		}

	}
}
