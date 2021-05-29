package com.bhavesh.solutions;

import java.util.Arrays;

public class Leetcode1648 {
	public int maxProfit(int[] inventory, int orders) {

		/*
		 * Greedy approach, we reverse sort the inventory table and we will pick the
		 * most expensive balls first
		 */

		/*
		 * We compare the value of this ball and value of the enxt ball and keep a count
		 * of all the balls we have covered and add these balls to our cost untill all
		 * the orders have been completed
		 */
		Arrays.sort(inventory);
		int n = inventory.length - 1;
		int count = 1;
		long total = 0;

		while (orders > 0) {

			/*
			 * If we bring down all the balls to the value of next ball and stii order would
			 * be not completed or exactly completed, just bring down all balls to the next
			 * ball's value and all to total
			 */
			if (n > 0 && orders >= count * (inventory[n] - inventory[n - 1])) {
				total += count * getSum(inventory[n], inventory[n - 1]);
				orders -= count * (inventory[n] - inventory[n - 1]);
			}

			/*
			 * If the remaining orders are little such that you dont have to bring down
			 * every ball to the next ball's value
			 */
			else {
				// Check what value you need to bring the current covered balls to
				long a = orders / count;
				// Add these decremented values to the total
				total += count * getSum(inventory[n], inventory[n] - a);
				// Check for remainder orders
				long b = orders % count;
				total += b * (inventory[n] - a);
				orders = 0;

			}
			total %= 1000000007;
			count++;
			n--;
		}
		return (int) total % 1000000007;
	}

	public long getSum(long a, long b) {
		return a * (a + 1) / 2 - b * (b + 1) / 2;
	}
}