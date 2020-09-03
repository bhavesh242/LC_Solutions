package com.bhavesh.solutions;

public class Leetcode1290 {
	public int getDecimalValue(ListNode head) {
		// Output variable
		int sum = 0;

		// Traverese Nodes till last element, which is a special case
		while (head.next != null) {
			// double the current sum and add 2 if the current bit is 1
			sum = sum * 2 + 2 * head.val;
			head = head.next;
		}
		// Add 1 if last bit is a 1
		return sum + head.val;
	}
}
