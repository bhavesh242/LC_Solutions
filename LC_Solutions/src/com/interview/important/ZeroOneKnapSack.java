package com.interview.important;

public class ZeroOneKnapSack {
	public static void main(String[] args) {
		int val[] = new int[] {60,100,120};
		int wt[] = new int[] {10,20,30};
		int W = 50;
		int n = val.length;
		System.out.println(ks(val,wt,W,n));
	}
	
	public static int ks(int val[], int[] wt, int W, int n)
	{
		int []dp = new int[W+1];
		for(int i=0;i<n;i++)
		{
			for(int w = W; w>0;w--)
			{
				if(wt[i]<=w)
				{
					dp[w] = Math.max(dp[w], dp[w - wt[i]]+ val[i]);
				}
			}
		}
		
		return dp[W];
	}
}