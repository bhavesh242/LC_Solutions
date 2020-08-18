package com.bhavesh.amznPrep;

import java.util.Collections;
import java.util.PriorityQueue;

public class AmznMaxProfitSupplier {
	public static void main(String[] args) {
		AmznMaxProfitSupplier maxPro = new AmznMaxProfitSupplier();
		int [] supp = new int[] {9,2,6,1};
		System.out.println(maxPro.maxProfit(5, supp, 7));
	}
	public int maxProfit(int nsuppliers, int []stockList, int orders) {
		int profit = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		for(int x : stockList) {
			pq.offer(x);
		}
		int i=0;
		while(i<orders) {
			int cur= pq.remove();
			profit = profit+cur;
			if(cur>1) {
				pq.offer(cur-1);
			}
			i++;
		}
		
		return profit;
	}
}
