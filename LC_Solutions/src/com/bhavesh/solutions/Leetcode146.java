package com.bhavesh.solutions;

import java.util.HashMap;

public class Leetcode146 {

}

class LRUCache {

	DLL head;
	DLL tail;
	int capacity;
	HashMap<Integer, DLL> map;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		map = new HashMap<Integer, DLL>();
		head = new DLL(0, 0);
		tail = new DLL(0, 0);
		head.next = tail;
		tail.prev = head;
	}

	public int get(int key) {
		if (!map.containsKey(key)) {
			return -1;
		}
		DLL node = map.get(key);
		removeNode(node);
		addNode(node);
		return node.val;

	}

	public void put(int key, int value) {
		if (map.containsKey(key)) {
			DLL node = map.get(key);
			node.val = value;
			removeNode(node);
			addNode(node);
		} else {

			if (map.size() == capacity) {
				map.remove(tail.prev.key);
				removeNode(tail.prev);
			}
			DLL node = new DLL(key, value);
			map.put(key, node);
			addNode(node);

		}
	}

	public void removeNode(DLL node) {
		DLL next = node.next;
		DLL prev = node.prev;
		prev.next = next;
		next.prev = prev;
	}

	public void addNode(DLL node) {
		DLL next = head.next;
		head.next = node;
		node.prev = head;
		node.next = next;
		next.prev = node;
	}
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */

class DLL {
	int key;
	int val;
	DLL prev;
	DLL next;

	DLL(int key, int val) {
		this.key = key;
		this.val = val;
	}
}