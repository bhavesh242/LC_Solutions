package com.bhavesh.solutions;

public class Leetcode122 {
	public int maxProfit(int[] prices) {

		// Intuition : Just add the sum of consecutive price differences everytime the
		// price is more that previous.
		int prev = prices[0];
		int sum = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > prev) {
				sum += prices[i] - prev;
			}
			prev = prices[i];
		}

		return sum;
	}
}