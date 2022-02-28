package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.List;

public class Leetcode228 {
	public List<String> summaryRanges1(int[] nums) {
		List<String> op = new ArrayList<String>();
		if (nums.length == 0) {
			return op;
		}

		StringBuffer rStart = new StringBuffer();
		rStart.append(nums[0]);
		int count = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i - 1] + 1) {
				count++;
			} else {
				if (count > 1) {
					rStart.append("->" + nums[i - 1]);
				}
				op.add(new String(rStart));
				rStart.setLength(0);
				rStart = new StringBuffer();
				rStart.append(nums[i]);
				count = 1;
			}
		}

		if (count > 1) {
			rStart.append("->" + nums[nums.length - 1]);
		}
		op.add(new String(rStart));

		return op;

	}

	public List<String> summaryRanges2(int[] nums) {

		List<String> op = new ArrayList<String>();
		int i = 0;
		for (int j = 0; j < nums.length; j++) {
			i = j;
			while (j < nums.length - 1 && nums[j + 1] == nums[j] + 1) {
				j++;
			}
			if (i == j) {
				op.add(new String(nums[i] + ""));
			} else {
				op.add(new String(nums[i] + "->" + nums[j]));
			}
		}
		return op;
	}
}
