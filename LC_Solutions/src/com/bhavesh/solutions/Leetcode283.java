package com.bhavesh.solutions;

public class Leetcode283 {
	public void moveZeroes(int[] nums) {
        int numz=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){
                int temp = nums[i];
                nums[i] = nums[numz];
                nums[numz] = temp;
                numz++;
            }
        }
        
    }
}
