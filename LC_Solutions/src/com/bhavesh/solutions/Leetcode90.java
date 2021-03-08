package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode90 {
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		ArrayList<List<Integer>> op = new ArrayList<List<Integer>>();
		ArrayList<Integer> curList = new ArrayList<Integer>();
		Arrays.sort(nums);
		helper(0, nums, op, curList);
		return op;
	}

	public void helper(int index, int[] nums, ArrayList<List<Integer>> op, ArrayList<Integer> curList) {
		op.add(new ArrayList<Integer>(curList));
		for (int i = index; i < nums.length; i++) {
			if (i != index && nums[i] == nums[i - 1]) {
				continue;
			}

			curList.add(nums[i]);
			helper(i + 1, nums, op, curList);
			curList.remove(curList.size() - 1);
		}
	}
}