package com.bhavesh.solutions;

public class Leetcode974 {
	public int subarraysDivByK(int[] A, int K) {

		// Concept : If 2 numbers return same modulo, then all rhe indices between them
		// sum upto divisible of K
		// https://www.youtube.com/watch?v=u9m-hnlcydk
		int map[] = new int[K];
		int sum = 0;
		for (int i = 0; i < A.length; i++) {
			int cur = K + A[i] % K;
			sum += cur;
			map[sum % K]++;
		}
		/*
		 * Special Case for Prefix Sums that are divisible by K, i.e sum%K == 0 i.e
		 * map[0]. For these cases, we need to consider the sums as well as the indexes
		 * i.e if map[0] == n, then add n(n-1)/2 + n to the count
		 */
		int count = map[0];
		for (int c : map) {
			count += c * (c - 1) / 2;
		}

		return count;
	}
}
