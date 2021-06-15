package com.bhavesh.solutions;

public class Leetcode706 {
	class MyHashMap {

		/** Initialize your data structure here. */

		ListNode[] bucket;
		int SIZE = 10000;

		// The Main idea is to use buckets of linkedlist to tackle colision
		public MyHashMap() {
			bucket = new ListNode[SIZE];
		}

		/** value will always be non-negative. */
		public void put(int key, int value) {
			// This returns the index in the bucket array.
			int index = getIndex(key);
			// If the bucket is null, initialize it
			if (bucket[index] == null) {
				bucket[index] = new ListNode(-1, -1);
			}

			// Find one node previous to the key we applying put on
			ListNode prev = findPrev(bucket[index], key);
			// If the key does not exist, create it
			if (prev.next == null) {
				prev.next = new ListNode(key, value);
			}
			// Otherwise just update the value
			else {
				prev.next.val = value;
			}
		}

		/**
		 * Returns the value to which the specified key is mapped, or -1 if this map
		 * contains no mapping for the key
		 */
		public int get(int key) {
			// Calculate index and find the previous of this key
			int index = getIndex(key);
			ListNode prev = findPrev(bucket[index], key);
			// If bucket is empty or key does not exist return -1
			if (prev == null || prev.next == null) {
				return -1;
			}
			// otherwise return the existing value
			else {
				return prev.next.val;
			}
		}

		/**
		 * Removes the mapping of the specified value key if this map contains a mapping
		 * for the key
		 */
		public void remove(int key) {
			int index = getIndex(key);
			ListNode prev = findPrev(bucket[index], key);
			// If bucket is empty or the key does not exist in the map, just return
			if (prev == null || prev.next == null) {
				return;
			}
			// Otherwise just set prev.next = prev.next.next;
			prev.next = prev.next.next;
		}

		// return the mod of the hashcode of the key
		private int getIndex(int key) {
			return Integer.hashCode(key) % SIZE;
		}

		// This method helps find one node previous to the key we are looking for
		private ListNode findPrev(ListNode bucket, int key) {
			ListNode cur = bucket, prev = null;
			while (cur != null && cur.key != key) {
				prev = cur;
				cur = cur.next;
			}
			return prev;
		}

		// Linked List structure
		class ListNode {
			int key, val;
			ListNode next;

			ListNode(int key, int val) {
				this.key = key;
				this.val = val;
			}
		}

	}

}
/**
 * Your MyHashMap object will be instantiated and called as such: MyHashMap obj
 * = new MyHashMap(); obj.put(key,value); int param_2 = obj.get(key);
 * obj.remove(key);
 */