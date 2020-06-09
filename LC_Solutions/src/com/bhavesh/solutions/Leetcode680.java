package com.bhavesh.solutions;

public class Leetcode680 {
	public boolean validPalindrome(String s) {
        int i=0;
        int j = s.length()-1;
        while(i<j){
            if(s.charAt(i)!=s.charAt(j)){
                return isValidPalindrome(s,i+1,j)|| isValidPalindrome(s,i,j-1);
            }
            i++;
            j--;
        }
        return true;
    }
    
    public boolean isValidPalindrome(String s, int i, int j){
        while(i<j){
            if(s.charAt(i)!=s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
