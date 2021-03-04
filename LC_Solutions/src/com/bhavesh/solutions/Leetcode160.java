package com.bhavesh.solutions;

public class Leetcode160 {
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

		/*
		 * Start 2 pointers and set the pointers to opposite heads when they reach end
		 * of their lists to make up for the difference in their lengths
		 */

		if (headA == null || headB == null) {
			return null;
		}

		ListNode aptr = headA, bptr = headB;
		while (aptr != null || bptr != null) {
			if (aptr == bptr) {
				return aptr;
			}
			aptr = aptr == null ? headB : aptr.next;
			bptr = bptr == null ? headA : bptr.next;
		}

		return null;
	}
}