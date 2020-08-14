package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.List;

public class Leetcode39 {
	// Output arraylist
	List<List<Integer>> op = new ArrayList();

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		// We try backtracking from zeroth index and starting sum 0
		backTrack(0, 0, candidates, target, new ArrayList<Integer>());
		// Once backtracking return, return output
		return op;
	}

	public void backTrack(int index, int sum, int[] candidates, int target, ArrayList<Integer> arr) {
		// If sum equals target, add current list of elements to output list
		if (sum == target) {
			op.add(new ArrayList<Integer>(arr));
		}
		// if sum crosses target, return
		if (sum > target) {
			return;
		}

		// for each index, add element at current index to arraylist and backtrack
		for (int i = index; i < candidates.length; i++) {
			arr.add(candidates[i]);
			backTrack(i, sum + candidates[i], candidates, target, arr);
			// remove element from current list once we reurn from above backtracking
			arr.remove(arr.size() - 1);
		}
	}
}
