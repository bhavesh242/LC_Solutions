package com.bhavesh.amznPrep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AmznTruck {
	public static void main(String[] args) {
		AmznTruck amzn = new AmznTruck();
		System.out.println(amzn.maxBoxes(4, new int[] {5,3,6,2},4, new int[]{1,7,3,1},11));
	}
	public int maxBoxes(int num, int[]boxes, int unitSize, int []unitsPerBox, int truckSize) {
		int unit_count = 0;
		if(truckSize <=0) {
			return unit_count;
		}
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int []>() {
			public int compare(int []a, int b[]) {
				return b[0]-a[0];
			}
		});
		for(int k=0; k<unitSize;k++){
			pq.add(new int[] {unitsPerBox[k],boxes[k]});
		}
		
		while(!pq.isEmpty()) {
			int []arr = pq.remove();
			for(int i=0;i<arr[1];i++) {
				unit_count = unit_count + arr[0];
				if(--truckSize == 0) {
					return unit_count;
				}
				
			}
		}
		return unit_count;
	}
}
