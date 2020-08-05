package com.bhavesh.solutions;

public class Leetcode142 {
	public ListNode detectCycle(ListNode head) {

		// If head is null return null
		if (head == null) {
			return null;
		}

		// initialize tortoise and hare to head;
		ListNode tor = head, hare = head;
		// Find an intersection point between them
		while (hare != null && hare.next != null) {
			tor = tor.next;
			hare = hare.next.next;
			if (tor == hare) {
				break;
			}
		}
		// If there is no cycle, return null
		if (hare == null || hare.next == null) {
			return null;
		}

		// Ptr1 is pointing to head
		ListNode ptr1 = head;
		// Ptr2 is pointing to first intersection point
		ListNode ptr2 = tor;
		// Move ptr1 and ptr2 one step at a time until the meet, that meeting point is
		// where cycle began
		while (ptr1 != ptr2) {
			ptr1 = ptr1.next;
			ptr2 = ptr2.next;
		}

		return ptr1;

	}
}
