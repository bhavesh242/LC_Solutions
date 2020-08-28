package com.bhavesh.solutions;

import java.util.PriorityQueue;

public class Leetcode1167 {
	public int connectSticks(int[] sticks) {
		int sum = 0;
		//Use a priority queeu to always select 2 smallest sticks
		PriorityQueue<Integer> pq = new PriorityQueue();
		for (int i = 0; i < sticks.length; i++) {
			pq.add(sticks[i]);
		}
		//Until one stick remains, keep combinig smallest 2 sticks 
		while (pq.size() > 1) {
			int small1 = pq.remove();
			int small2 = pq.remove();
			int newStick = small1 + small2;
			sum += newStick;
			pq.add(newStick);
		}

		return sum;
	}
}
