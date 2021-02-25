package com.bhavesh.solutions;

import java.util.Stack;

public class Leetcode581 {
	public int findUnsortedSubarray1(int[] nums) {

		// Find first and last misplaced elements using stack

		Stack<Integer> st = new Stack<Integer>();
		int minLeft = nums.length;
		for (int i = 0; i < nums.length; i++) {
			while (!st.isEmpty() && nums[st.peek()] > nums[i]) {
				minLeft = Math.min(minLeft, st.pop());
			}
			st.push(i);
		}

		st.clear();
		int maxRight = 0;
		for (int i = nums.length - 1; i >= 0; i--) {
			while (!st.isEmpty() && nums[st.peek()] < nums[i]) {
				maxRight = Math.max(maxRight, st.pop());
			}
			st.push(i);
		}

		return maxRight - minLeft + 1;
	}

	public int findUnsortedSubarray(int[] nums) {
		int minVal = Integer.MAX_VALUE;
		int maxVal = Integer.MIN_VALUE;

		// Find the min and Maximum misplaced values and find their respective positions

		for (int i = 1; i < nums.length; ++i)
			if (nums[i] < nums[i - 1])
				minVal = Math.min(nums[i], minVal);

		for (int i = nums.length - 2; i >= 0; --i)
			if (nums[i] > nums[i + 1])
				maxVal = Math.max(nums[i], maxVal);

		int i = 0;
		for (; i < nums.length; ++i)
			if (nums[i] > minVal)
				break;

		int j = nums.length - 1;
		for (; j >= 0; --j)
			if (nums[j] < maxVal)
				break;

		return i > j ? 0 : j - i + 1;
	}
}