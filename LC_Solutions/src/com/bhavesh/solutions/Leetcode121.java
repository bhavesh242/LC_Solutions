package com.bhavesh.solutions;

public class Leetcode121 {
	public int maxProfit(int[] prices) {
		int bestBuy = prices[0];
		int bestSell = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] < bestBuy) {
				bestBuy = prices[i];
			} else {
				bestSell = Math.max(prices[i] - bestBuy, bestSell);
			}
		}

		return bestSell;
	}
}