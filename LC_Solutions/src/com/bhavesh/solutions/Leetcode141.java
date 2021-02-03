package com.bhavesh.solutions;

public class Leetcode141 {
	public boolean hasCycle(ListNode head) {
		//Check for Singular Nodes
		if (head == null || head.next == null) {
			return false;
		}
		//Use slow pointer and fast pointer
		ListNode slow = head, fast = head.next;
		//Check if fast pointer can move 2 places
		while (fast != null && fast.next != null) {
			//If the slow = fast, there is a cycle
			if (slow == fast) {
				return true;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		
		//If the fast reached end point, it means, there is no cycle, return false
		return false;
	}
}