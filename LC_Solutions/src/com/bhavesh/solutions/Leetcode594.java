package com.bhavesh.solutions;

import java.util.HashMap;

public class Leetcode594 {
	public int findLHS(int[] nums) {

		// O(n) solution
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int ans = 0;
		for (int n : nums) {
			map.put(n, map.getOrDefault(n, 0) + 1);
			if (map.containsKey(n + 1)) {
				ans = Math.max(ans, map.get(n) + map.get(n + 1));
			}
			if (map.containsKey(n - 1)) {
				ans = Math.max(ans, map.get(n) + map.get(n - 1));
			}
		}
		return ans;
	}

	// O(nlogn) solution
//    public int findLHS(int[] nums) {
//        Arrays.sort(nums);
//        int left = 0;
//        int right = 0;
//        int max=0;
//        while(left<=right && right<nums.length){
//            int diff = nums[right]-nums[left];
//            if(diff == 1){
//                max = Math.max(max, right-left+1);
//            }
//            if(diff>1){
//                left++;
//            }
//            else
//            {
//                right++;    
//            }
//        }
//        
//        return max;
//    }
}