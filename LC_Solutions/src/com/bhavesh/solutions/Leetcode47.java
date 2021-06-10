package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leetcode47 {
	class Solution {
		public List<List<Integer>> permuteUnique(int[] nums) {
			List<List<Integer>> res = new ArrayList<>();
			helper(nums, 0, nums.length, res);
			return res;
		}

		private void helper(int[] nums, int index, int len, List<List<Integer>> res) {
			if (index == len) {
				if (len == 0)
					return;
				List<Integer> list = new ArrayList<>();
				for (int i : nums)
					list.add(i);
				res.add(list);
				return;
			}
			Set<Integer> set = new HashSet<>();
			for (int i = index; i < len; i++) {
				if (set.contains(nums[i]))
					continue;
				set.add(nums[i]);
				swap(nums, index, i);
				helper(nums, index + 1, len, res);
				swap(nums, index, i);
			}
		}

		private void swap(int[] nums, int l, int r) {
			int temp = nums[l];
			nums[l] = nums[r];
			nums[r] = temp;
		}
	}
}