package com.bhavesh.solutions;

public class Leetcode148 {
	public ListNode sortList(ListNode head) {
		// Find out the Middle element of the List, divide the list into those 2 parts
		// and merge those two presumably sorted lists
		if (head == null || head.next == null) {
			return head;
		}
		ListNode middle = getMiddleNode(head);
		ListNode left = sortList(head);
		ListNode right = sortList(middle);
		return merge(left, right);
	}

	// Recursive or Iterative method to Merge two sorted linked lists
	public ListNode merge(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode();
		ListNode cur = dummy;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				cur.next = l1;
				l1 = l1.next;
				cur = cur.next;
			} else {
				cur.next = l2;
				l2 = l2.next;
				cur = cur.next;
			}
		}
		cur.next = (l1 != null) ? l1 : l2;
		return dummy.next;
	}

	public ListNode getMiddleNode(ListNode head) {
		ListNode slow = head, fast = head.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode mid = slow.next;
		slow.next = null;
		return mid;
	}

}