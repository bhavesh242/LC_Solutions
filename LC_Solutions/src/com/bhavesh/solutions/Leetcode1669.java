package com.bhavesh.solutions;

public class Leetcode1669 {
	public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {

		ListNode l2tail = list2;
		while (l2tail.next != null) {
			l2tail = l2tail.next;
		}

		ListNode A = list1, B = list1;

		for (int i = 0; i < a - 1; i++) {
			A = A.next;
		}

		for (int i = 0; i < b + 1; i++) {
			B = B.next;
		}

		A.next = list2;
		l2tail.next = B;

		return list1;
	}
}