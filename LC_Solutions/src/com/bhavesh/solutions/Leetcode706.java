package com.bhavesh.solutions;

public class Leetcode706 {
	class MyHashMap {
		int SIZE;
		LinkList[] bucket;

		/** Initialize your data structure here. */
		public MyHashMap() {
			SIZE = 10000;
			bucket = new LinkList[SIZE];
		}

		/** value will always be non-negative. */
		public void put(int key, int value) {
			int index = getHash(key);
			if (bucket[index] == null) {
				bucket[index] = new LinkList(-1, -1);
			}
			LinkList prev = findPrev(bucket[index], key);
			if (prev.next == null) {
				prev.next = new LinkList(key, value);
			} else {
				prev.next.val = value;
			}
		}

		/**
		 * Returns the value to which the specified key is mapped, or -1 if this map
		 * contains no mapping for the key
		 */
		public int get(int key) {
			int index = getHash(key);
			LinkList prev = findPrev(bucket[index], key);
			if (prev == null || prev.next == null) {
				return -1;
			}
			return prev.next.val;
		}

		/**
		 * Removes the mapping of the specified value key if this map contains a mapping
		 * for the key
		 */
		public void remove(int key) {
			int index = getHash(key);
			LinkList prev = findPrev(bucket[index], key);
			if (prev == null || prev.next == null) {
				return;
			}
			prev.next = prev.next.next;
		}

		public int getHash(int key) {
			return Integer.hashCode(key) % SIZE;
		}

		public LinkList findPrev(LinkList bucket, int key) {
			LinkList cur = bucket, prev = null;
			while (cur != null && cur.key != key) {
				prev = cur;
				cur = cur.next;

			}
			return prev;
		}

		class LinkList {
			int key;
			int val;
			LinkList next;

			LinkList(int key, int val) {
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
