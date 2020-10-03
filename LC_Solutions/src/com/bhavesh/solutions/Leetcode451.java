package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Leetcode451 {
	public String frequencySort(String s) {

		// Take Character count and maxCount
		Map<Character, Integer> map = new HashMap();
		int maxCount = 0;
		for (char c : s.toCharArray()) {
			int curCount = map.getOrDefault(c, 0) + 1;
			map.put(c, curCount);
			if (maxCount < curCount) {
				maxCount = curCount;
			}
		}

		// Create a bucket, with indexes ranging from indexes 0 to maxCount(size =
		// maxCount+1)
		ArrayList<Character>[] bucket = new ArrayList[maxCount + 1];
		for (char c : map.keySet()) {
			int index = map.get(c);
			bucket[index] = (bucket[index] == null) ? new ArrayList() : bucket[index];
			bucket[index].add(c);
		}

		// Reverse traverse the buckets and create the output string using a stringbuffer
		StringBuffer sb = new StringBuffer();
		for (int i = bucket.length - 1; i >= 1; i--) {
			if (bucket[i] != null) {
				for (Character c : bucket[i]) {
					for (int j = 0; j < i; j++) {
						sb.append(c);
					}
				}
			}
		}
		return sb.toString();
	}
}
