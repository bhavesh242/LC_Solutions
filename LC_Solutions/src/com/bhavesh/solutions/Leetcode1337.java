package com.bhavesh.solutions;

import java.util.PriorityQueue;

public class Leetcode1337 {
	public int[] kWeakestRows(int[][] mat, int k) {

		int R = mat.length;
		int C = mat[0].length;

		// MaxHeap to keep min k values, remove rows with more soldiers, in case of tie, remove bigger numbered rows
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> {
			if (a[0] == b[0]) {
				return b[1] - a[1];
			}
			return b[0] - a[0];
		});

		//For each row, Use binary search to find number of 1's (strength) 
		for (int i = 0; i < R; i++) {
			int strength = binarySearch(mat[i], C);
			pq.add(new int[] { strength, i });
			//If size exceeds k, remove the strongest row, to always keep k minimum rows
			if (pq.size() > k) {
				pq.poll();
			}
		}

		//Convert priority queue to integer array answer
		int[] ans = new int[k];
		for (int i = k - 1; i >= 0; i--) {
			ans[i] = pq.poll()[1];
		}

		return ans;
	}

	
	public int binarySearch(int[] mat, int n) {
		int left = 0, right = n;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (mat[mid] == 1) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}

		return right;
	}
}
