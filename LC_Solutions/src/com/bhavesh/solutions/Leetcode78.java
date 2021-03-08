package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.List;

public class Leetcode78 {
	public List<List<Integer>> subsets(int[] nums) {
        ArrayList<List<Integer>> op = new ArrayList<List<Integer>>();
        ArrayList<Integer> curList = new ArrayList<Integer>();
        helper(0,nums,op, curList);
        return op;
    }
    
    public void helper(int index, int[]nums, ArrayList<List<Integer>> op, ArrayList<Integer> curList)
    {
        op.add(new ArrayList<Integer>(curList));
        for(int i=index;i<nums.length;i++)
        {
            curList.add(nums[i]);
            helper(i+1,nums,op,curList);
            curList.remove(curList.size()-1);
        }
    }
}