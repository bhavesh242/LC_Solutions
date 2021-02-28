package com.bhavesh.solutions;

import java.util.HashMap;

public class Leetcode992 {
	public int subarraysWithKDistinct(int[] A, int K) {
		// https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/235235/C%2B%2BJava-with-picture-prefixed-sliding-window

		/*
		 * If the subarray [j, i] contains K unique numbers, and first prefix numbers
		 * also appear in [j + prefix, i] subarray, we have total 1 + prefix good
		 * subarrays. For example, there are 3 unique numers in [1, 2, 1, 2, 3]. First
		 * two numbers also appear in the remaining subarray [1, 2, 3], so we have 1 + 2
		 * good subarrays: [1, 2, 1, 2, 3], [2, 1, 2, 3] and [1, 2, 3].
		 */
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int left = 0, prefix = 0, distinct = 0;
		int total = 0;
		for (int right = 0; right < A.length; right++) {

			if (!map.containsKey(A[right]) || map.get(A[right]) == 0) {
				// encountered a Integer new to the section
				distinct++;
			}
			map.put(A[right], map.getOrDefault(A[right], 0) + 1);

			/*
			 * We always maintain j in a way that the integer at that position is the the
			 * only one in the section, so when we move j forward the count will drop by one
			 */
			if (distinct > K) {
				map.put(A[left], map.get(A[left]) - 1);
				left++;
				distinct--;

				// Reset Prefix also
				prefix = 0;
			}

			while (map.get(A[left]) > 1) //// means the integer at position j has a duplicate in the later part of the
											//// section
			{
				map.put(A[left], map.get(A[left]) - 1);
				left++;
				prefix++;
			}

			if (distinct == K) {
				/*
				 * starting from every position of the first prefix length of the section to the
				 * current i position (which is the end of the section) will have K unique
				 * Integer, need to add 1 more which is the current j position
				 */
				total += 1 + prefix;
			}
		}

		return total;
	}
}