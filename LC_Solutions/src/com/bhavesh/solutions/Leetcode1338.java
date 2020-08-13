package com.bhavesh.solutions;

import java.util.Collections;
import java.util.PriorityQueue;

public class Leetcode1338 {
	public int minSetSize(int[] arr) {

		// For Faster Performance use array instead of hashmap
		int max = 0;
		for (int x : arr) {
			max = Math.max(x, max);
		}
		// Create an array to take as a hashmap for faster access
		int[] map = new int[max + 1];
		for (int x : arr) {
			map[x]++;
		}
		// Create a reverse order priority queue
		PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
		// Add all non zero elements into the Priority Queue
		for (int x : map) {
			if (x != 0) {
				pq.offer(x);
			}
		}
		// Target is half of the array size
		int target = arr.length / 2;
		// Ct is where we store our output
		int ct = 0;
		// Start pulling elements one by one, and subtract from target until target is
		// reduces to 0 or less
		while (!pq.isEmpty()) {
			ct++;
			target = target - pq.remove();
			if (target <= 0) {
				break;
			}
		}
		return ct;

	}
}
