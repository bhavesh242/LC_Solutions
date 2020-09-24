package com.bhavesh.solutions;

public class Leetcode138 {
	public RandomNode copyRandomList(RandomNode head) {

		// Inter weaving, throough which, we would not need extra space to do this
		/*
		 * The idea is to create new pointers right next to old pointers in one pass and
		 * then getting rid of old pointers in another pass
		 */
		if (head == null) {
			return null;
		}
		// Insert Alternate New Nodes, that is make A->B->C to A->A1->B->B1....
		RandomNode ptr = head;
		while (ptr != null) {
			RandomNode newPtr = new RandomNode(ptr.val);
			newPtr.next = ptr.next;
			ptr.next = newPtr;
			ptr = newPtr.next;
		}
		ptr = head;
		// Copy the random pointer structures
		while (ptr != null) {
			ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
			ptr = ptr.next.next;
		}

		// Sperate out the two lists;
		// OldList is our original list and newList is our new list
		// After sepration, return head node of new list
		ptr = head.next;
		RandomNode oldList = head;
		RandomNode newList = head.next;
		while (oldList != null) {
			oldList.next = oldList.next.next;
			newList.next = newList.next != null ? newList.next.next : null;
			oldList = oldList.next;
			newList = newList.next;
		}
		return ptr;
	}
}

class RandomNode {
    int val;
    RandomNode next;
    RandomNode random;

    public RandomNode(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}