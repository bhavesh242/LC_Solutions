package com.bhavesh.amznPrep;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AmznTwoSumUniquePairs {
	public static void main(String[] args) {
		System.out.println(new AmznTwoSumUniquePairs().getUniquePairsOpti(new int[]{1,5,2,4,5,1,4,2}, 6)); 
	}
	public int twoSum6(int[] nums, int target) {
		// Write your code here

		if (nums == null || nums.length < 2) {
			return 0;
		}

		Arrays.sort(nums);

		int count = 0;
		int left = 0;
		int right = nums.length - 1;

		while (left < right) {
			if (nums[left] + nums[right] < target) {
				left++;
			} else if (nums[left] + nums[right] > target) {
				right--;
			} else {
				count++;
				left++;
				right--;
				while (left < right && nums[left] == nums[left - 1]) {
					left++;
				}
				while (left < right && nums[right] == nums[right + 1]) {
					right--;
				}
			}
		}

		return count;
	}
	public  int getUniquePairsOpti(int[] nums, int target){
	    Set<Integer> seen =  new HashSet<>();
	    HashSet<Integer> map = new HashSet<>();
	    int ans = 0;
	    for (int num : nums){
	        if (map.contains(num)){
	            int key = num;
	            if (! seen.contains(key)){
	                ans++;
	                seen.add(key);
	            }
	        } else {
	            map.add(target-num);
	        }
	    }
	    return ans;

	}
}
