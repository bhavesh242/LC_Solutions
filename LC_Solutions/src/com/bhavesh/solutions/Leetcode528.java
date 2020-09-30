package com.bhavesh.solutions;

import java.util.Random;

public class Leetcode528 {
	 int []prefixWeights;
	    int sum;
	    //The idea is to generate an random number between 1 to sum of weights
	    //Based on what number that we obtain, we allocate appropriate index of weight
	    Random random;
	    public Leetcode528(int[] w) {
	        prefixWeights = new int[w.length];
	        for(int i=0;i<w.length;i++){
	            sum = sum + w[i];
	            prefixWeights[i] =sum;
	        }
	        random = new Random();
	    }
	    
	    public int pickIndex() {
	    	//Perform binary search and return index
	         int num = random.nextInt(sum)+1;
	          int left = 0;
	          int right = prefixWeights.length-1;
	          while(left<right){
	            int mid = (left+right)/2;
	            if(prefixWeights[mid] < num){
	                left = mid+1;
	            }  
	             else{
	                 right = mid;
	             }
	        }
	        
	        return left;
	    }
	}

	/**
	 * Your Solution object will be instantiated and called as such:
	 * Solution obj = new Solution(w);
	 * int param_1 = obj.pickIndex();
	 */

