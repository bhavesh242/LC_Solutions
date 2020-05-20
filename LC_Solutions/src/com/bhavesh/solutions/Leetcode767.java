package com.bhavesh.solutions;

import java.util.Arrays;
import java.util.Comparator;

public class Leetcode767 {
	public String reorganizeString(String S) {
		int[][] pairs = new int[26][2];
		int max = Integer.MIN_VALUE;
		for (char x : S.toCharArray()) {
			pairs[x - 'a'][0]++;
			pairs[x - 'a'][1] = x;
			max = Math.max(max, pairs[x - 'a'][0]);
		}
		int n = S.length();
		if (max > (n + 1) / 2) {
			return "";
		}

		Comparator<int[]> cmp = new Comparator<int[]>() {

			public int compare(int[] a, int[] b) {
				return b[0] - a[0];
			}
		};
		Arrays.sort(pairs, cmp);
		int ind = 0;
		char arr[] = new char[n];
		for (int[] pair : pairs) {
			while (pair[0]-- > 0) {
				arr[ind] = (char) pair[1];
				ind = ind + 2;
				if (ind >= n) {
					ind = 1;
				}
			}
		}
		return new String(arr);
	}
}
