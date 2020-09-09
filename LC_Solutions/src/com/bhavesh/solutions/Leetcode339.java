package com.bhavesh.solutions;

import java.util.List;

public class Leetcode339 {
	 public int depthSum(List<NestedInteger> nestedList) {
	        return dfs(nestedList, 1);
	        
	    }
	    public int dfs(List<NestedInteger> nestedList, int depth){
	        int sum = 0;
	        for(NestedInteger n : nestedList){
	            if(n.isInteger()){
	                sum+= n.getInteger()*depth;
	            }
	            else{
	                sum+= dfs(n.getList(), depth+1);
	            }
	        }
	        return sum;
	    }
	    
	}