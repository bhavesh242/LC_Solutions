package com.bhavesh.solutions;

import java.util.HashSet;

public class Leetcode575 {
	public int distributeCandies(int[] candyType) {

		HashSet<Integer> set = new HashSet<Integer>();
		for (int x : candyType) {
			set.add(x);
		}

		return Math.min(set.size(), candyType.length / 2);
	}
}