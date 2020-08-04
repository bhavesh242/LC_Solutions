package com.bhavesh.solutions;

import java.util.Arrays;

public class Leetcode62 {
	public int uniquePaths(int m, int n) {
        
		//use dp[row][column] = dp[row-1][column] + dp[row][column-1] formula;
		int dp[][] = new int[m][n];
        //fill entire array with 1, because no. of path along edges will always be 1
		for( int arr[] : dp){
            Arrays.fill(arr,1);
        }
        
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        
        return dp[m-1][n-1];
    }
}
