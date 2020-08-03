package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.List;

public class Leetcode442 {
	public List<Integer> findDuplicates(int[] nums) {
        
        List<Integer> op = new ArrayList<Integer>();
        //treat each number like an index, use the array like a map
        //for each num between 1 and nums.length, negating nums[abs(num)-1] implies num exists and was visited
        for(int num : nums){
            nums[Math.abs(num) -1] *= -1;
        }
        
        //check for all num that are still positive, means they appeared twice and thus double negation caused them to go positive and add results
        //negate these again so that they dont get added twice
        for(int num : nums){
            if(nums[Math.abs(num) -1 ] > 0 ){
                op.add(Math.abs(num));
                nums[Math.abs(num) -1] *= -1;
            }
        }
        
        return op;
    }
}
