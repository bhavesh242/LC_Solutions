package com.bhavesh.solutions;

import java.util.Stack;

public class Leetcode739 {
	public int[] dailyTemperatures(int[] T) {
        int sol[] = new int[T.length];
        Stack<Integer> st = new Stack<Integer>();
        for(int i=0;i<T.length;i++){
            if(st.isEmpty() || T[i] <= T[st.peek()]){
                st.push(i);
            }
            else{
                while(!st.empty() && T[st.peek()] < T[i])
                {
                    int index = st.pop();
                    sol[index] = i - index;
                }
                st.push(i);
            }   
        }
        
        return sol;
    }
}
