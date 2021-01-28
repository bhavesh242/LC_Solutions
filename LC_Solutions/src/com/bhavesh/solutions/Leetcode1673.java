package com.bhavesh.solutions;

import java.util.ArrayDeque;
import java.util.Deque;

public class Leetcode1673 {
	public int[] mostCompetitive(int[] nums, int k) {
		//Concept used in monotone stack
		if (nums.length == k)
			return nums;
		//Deque is used as a stack alternative, stack can also be used
		Deque<Integer> dq = new ArrayDeque<Integer>();
		
		int[] res = new int[k];

		//For each element
		for (int i = 0, n = nums.length; i < n; i++) {
			/*While deque is not empty, keep removing larger elements from stack/dequeue end such that there are 
			sufficient elements remaining for size k. This is handled by condition (n - (i+1) + dq.size() >=k)*/
			while (!dq.isEmpty() && nums[i] < nums[dq.peekLast()] && (n - i - 1) + dq.size() >= k)
				dq.removeLast();
			dq.addLast(i);
		}

		for (int i = 0; i < k; i++)
			res[i] = nums[dq.pollFirst()];

		return res;

	}
}

/*
 * class Solution { public int[] mostCompetitive(int[] nums, int k) {
 * Deque<Integer> queue = new ArrayDeque<Integer>(); int additionalCount =
 * nums.length - k; for (int i = 0; i < nums.length; i++) { while
 * (!queue.isEmpty() && queue.peekLast() > nums[i] && additionalCount > 0) {
 * queue.pollLast(); additionalCount--; } queue.addLast(nums[i]); } int[] result
 * = new int[k]; for (int i = 0; i < k; i++) { result[i] = queue.pollFirst(); }
 * return result; } }
 * 
 * 
 */