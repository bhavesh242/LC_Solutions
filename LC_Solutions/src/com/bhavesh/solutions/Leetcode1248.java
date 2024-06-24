package com.bhavesh.solutions;

public class Leetcode1248 {

	public int numberOfSubArrays_1(int [] nums, int k) {
		/**
		 * From Lee215 :
		 * Intuition:
		 * First you may have feeling of using sliding window.
		 * Then this idea get stuck in the middle.
		 *
		 * This problem will be a very typical sliding window,
		 * if it asks the number of subarrays with at most K distinct elements.
		 *
		 * Just need one more step to reach the folloing equation:
		 * exactly(K) = atMost(K) - atMost(K-1)
		 */

		return atMost(k, nums) - atMost(k-1, nums);
	}

	private int atMost(int k, int []nums) {
		int left = 0;
		int res = 0;
		for(int right=0; right < nums.length; right++) {
			if(nums[right] %2 == 1) {
				k--;
			}
			while(k < 0) {
				if(nums[left++]%2 == 1) {
					k++;
				}
			}
			res+= right - left + 1;
		}
		return res;
	}

	/**
	 * Option 2 :
	 * oddCt: An integer variable initialized to 0 to keep track of the current count of odd numbers
	 * encountered while iterating through the array.
	 * map: An integer array of size nums.length + 1 to store the frequency (count) of encountering a specific number of
	 * odd elements (oddCt) so far.
	 */

	public int numberOfSubarrays_2(int[] nums, int k) {
		int ans = 0;
		int oddCt = 0;
		int[] map = new int[nums.length + 1];
		map[0] = 1;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] % 2 != 0) {
				oddCt++;
			}
			/**
			 * map[oddCt] is incremented by 1.
			 * This is done to record how many times we've encountered a specific number of odd elements (oddCt) so far.
			 */
			map[oddCt]++;
			if (oddCt >= k) {
				/**
				 * If the condition is true, it means we might have encountered a subarray with
				 * k odd numbers ending at the current element nums[i].
				 *
				 * we access the value stored in map[oddCt - k].
				 *  map[oddCt - k] tells us how many times we've previously encountered oddCt - k odd elements
				 *  (i.e., subarrays with k fewer odd numbers than the current one).
				 *
				 * For each such subarray (map[oddCt - k]),
				 * we increment the ans variable by 1, effectively counting the nice subarrays ending with k odd numbers.
				 */
				ans += map[oddCt - k];
			}
		}
		return ans;
	}
}