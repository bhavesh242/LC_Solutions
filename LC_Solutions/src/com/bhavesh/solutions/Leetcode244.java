package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.HashMap;

public class Leetcode244 {

}

class WordDistance {

	HashMap<String, ArrayList<Integer>> map;

	public WordDistance(String[] words) {
		map = new HashMap<String, ArrayList<Integer>>();
		for (int i = 0; i < words.length; i++) {
			map.putIfAbsent(words[i], new ArrayList<Integer>());
			map.get(words[i]).add(i);
		}
	}

	public int shortest(String word1, String word2) {
		ArrayList<Integer> idx1 = map.get(word1);
		ArrayList<Integer> idx2 = map.get(word2);
		int i1 = 0, i2 = 0;
		int minDiff = Integer.MAX_VALUE;
		while (i1 < idx1.size() && i2 < idx2.size()) {
			int num1 = idx1.get(i1), num2 = idx2.get(i2);
			minDiff = Math.min(minDiff, Math.abs(num1 - num2));
			if (num1 <= num2) {
				i1++;
			} else {
				i2++;
			}
		}

		return minDiff;
	}
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words); int param_1 =
 * obj.shortest(word1,word2);
 */