package com.bhavesh.solutions;

public class Leetcode1472 {

}

class BrowserHistory {

	BrowserLinkedList root;
	BrowserLinkedList cur;

	public BrowserHistory(String homepage) {
		root = new BrowserLinkedList(homepage);
		cur = root;
	}

	public void visit(String url) {
		BrowserLinkedList visited = new BrowserLinkedList(url);
		cur.front = visited;
		visited.back = cur;
		cur = cur.front;
	}

	public String back(int steps) {
		for (int i = 0; i < steps; i++) {
			if (cur.back == null) {
				return cur.url;
			}
			cur = cur.back;
		}
		return cur.url;
	}

	public String forward(int steps) {
		for (int i = 0; i < steps; i++) {
			if (cur.front == null) {
				return cur.url;
			}
			cur = cur.front;
		}
		return cur.url;
	}
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage); obj.visit(url); String
 * param_2 = obj.back(steps); String param_3 = obj.forward(steps);
 */

class BrowserLinkedList {
	String url;
	BrowserLinkedList front;
	BrowserLinkedList back;

	BrowserLinkedList(String url) {
		this.url = url;
	}

}