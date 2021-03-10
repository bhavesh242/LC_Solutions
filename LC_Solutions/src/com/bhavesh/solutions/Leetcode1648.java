package com.bhavesh.solutions;

import java.util.Arrays;

public class Leetcode1648 {
	public int maxProfit(int[] inventory, int orders) {

		// We will greedily add most expensive balls to our profit

		Arrays.sort(inventory);
		int n = inventory.length - 1;

		// Count is to maintain how many different colored balls at once we add to our
		// order
		int count = 1;

		long ans = 0;
		while (orders > 0) {
			if (n > 0 && orders >= count * (inventory[n] - inventory[n - 1])) {
				ans += count * getSum(inventory[n], inventory[n - 1]);
				orders -= count * (inventory[n] - inventory[n - 1]);
			}

			// This is a special case where we need to complete remianer of order but need
			// not add all the preceding balls to order as it is limited
			else {
				long a = orders / count;
				ans += count * getSum(inventory[n], inventory[n] - a);
				long b = orders % count;
				ans += b * (inventory[n] - a);
				orders = 0;
			}
			ans = ans % 1000000007;
			n--;
			count++;
		}

		return (int) ans % 1000000007;
	}

	public long getSum(long a, long b) {
		return a * (a + 1) / 2 - (b + 1) * b / 2;
	}
}