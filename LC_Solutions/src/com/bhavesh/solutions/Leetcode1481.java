package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Leetcode1481 {
	public int findLeastNumOfUniqueInts(int[] arr, int k) {
		if (arr.length == 0 || k >= arr.length) {
			return 0;
		}

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int x : arr) {
			map.put(x, map.getOrDefault(x, 0) + 1);
		}
		ArrayList<Integer> list = new ArrayList<Integer>(map.values());
		Collections.sort(list);
		int size = list.size();
		for (int i = 0; i < list.size(); i++) {
			int num = list.get(i);
			if (num <= k) {
				k -= num;
				size--;
			} else {
				k = 0;
			}

			if (k == 0) {
				break;
			}

		}
		return size;
	}
}