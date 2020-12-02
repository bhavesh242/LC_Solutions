package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode40 {
	// This problem can be done wtih BFS with Backtracking
	List<List<Integer>> op;

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		op = new ArrayList();
		// Array is sorted to avoid duplicates in the output sets
		Arrays.sort(candidates);
		bfsBackTrack(target, 0, candidates, new ArrayList());
		return op;
	}

	public void bfsBackTrack(int target, int curIndex, int[] candidates, ArrayList curList) {
		if (target < 0) {
			return;
		}
		if (0 == target) {
			op.add(new ArrayList<Integer>(curList));
			return;
		}
		/*
		 * The candidates[i]<=target is called as an optimization, since we cannot move
		 * ahead once we have achieved our target
		 */
		for (int i = curIndex; i < candidates.length && candidates[i] <= target; i++) {
			/*
			 * Since the array is sorted, equal elements will be placed side by side, so
			 * once we begin dfs with the first element, we need not perform same bfs on
			 * subsequent equal elements as they would add duplicate sets to the output
			 */
			if (i > curIndex && candidates[i] == candidates[i - 1]) {
				continue;
			}
			curList.add(candidates[i]);
			bfsBackTrack(target - candidates[i], i + 1, candidates, curList);
			curList.remove(curList.size() - 1);
		}
	}
}

//[1,1,2,5,6,7,10]