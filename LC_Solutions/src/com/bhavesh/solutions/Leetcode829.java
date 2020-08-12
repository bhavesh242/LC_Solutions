package com.bhavesh.solutions;

public class Leetcode829 {
	public int consecutiveNumbersSum(int N) {
        //from formula N = (x+1) + (x+2) + (x+2) ... + (x+k)
        
        // k<= root(2N - 1/4) - 1/2
        int upper_lim_k = (int)(Math.sqrt(2*N + 0.25) - 0.5);
        //Used to store the result
        int count = 0;
        for(int k = 1;k<= upper_lim_k;k++){
            //Also x is an interger meaning N/K - (k+1)/2 should also be an integer
            //If that constraint is satisfied, increase count by 1
            if((N - k*(k+1)/2)%k == 0){
                count++;
            }
        }
        
        return count;
    }
    
    //The Following is an optimization of the above method
    public int consecutiveNumbersSum_Optimized(int N) {
        //from formula N = (x+1) + (x+2) + (x+2) ... + (x+k)
        
        // k<= root(2N - 1/4) - 1/2
        int upper_lim_k = (int)(Math.sqrt(2*N + 0.25) - 0.5);
        //Used to store the result
        int count = 0;
        for(int k = 1;k<= upper_lim_k;k++){
            
            //Decrease N by K and check if N%K == 0
            N = N - k;
            if(N %k == 0){
                count++;
            }
        }
        
        return count;
    }
    
    
}