package com.bhavesh.solutions;

import java.util.Arrays;

public class Leetcode1648 {
	public int maxProfit(int[] inventory, int orders) {
		Arrays.sort(inventory);
		int n = inventory.length - 1;
		int count = 1;
		long ans = 0;
		while (orders > 0) {
			if (n > 0 && orders >= count * (inventory[n] - inventory[n - 1])) {
				ans += count * getSum(inventory[n], inventory[n - 1]);
				orders -= count * (inventory[n] - inventory[n - 1]);
			} else {
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