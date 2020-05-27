package com.bhavesh.solutions;

import java.util.Deque;
import java.util.LinkedList;

public class Leetcode239 {
	public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if(n*k==0){
            return new int[0];
        }
        Deque<Integer> dq = new LinkedList<Integer>();
        int op[] = new int [n-k+1];
        for(int i=0;i<n;i++){
            if(!dq.isEmpty() && dq.peekFirst() < i- k + 1){
                dq.pollFirst();
            }
            while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i])
            {
                dq.pollLast();
            }
            dq.offerLast(i);
            if(i - k + 1 >= 0){
                op[i-k+1] = nums[dq.peekFirst()]; 
            }
        }
        
        return op;
    }
}
