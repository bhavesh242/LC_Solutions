package com.bhavesh.solutions;

public class Leetcode974 {
	public int subarraysDivByK(int[] A, int K) {
		// Concept 1: If 2 prefix sums have same modulo, then all the indices between
		// them sum upto divisible of K
		// https://www.youtube.com/watch?v=u9m-hnlcydk
		int map[] = new int[K];
		int sum = 0;
		for (int i = 0; i < A.length; i++) {
			int cur = K + A[i] % K;
			sum += cur;
			map[sum % K]++;
		}
		int count = map[0];
		for (int c : map) {
			count += c * (c - 1) / 2;
		}

		return count;

	}
}