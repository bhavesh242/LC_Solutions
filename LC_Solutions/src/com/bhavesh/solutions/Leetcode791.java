package com.bhavesh.solutions;


//Question was a little ambiguous
public class Leetcode791 {
	public String customSortString(String S, String T) {
        
		//Take Character count for characters in T 
		int [] arr = new int[26];
        for(char x : T.toCharArray()){
            arr[x-'a']++;
        }
        
        //Read S character by character and append as the T characters into a stringbuffer according to characters in S
        StringBuffer str = new StringBuffer();
        for(char x : S.toCharArray()){
            while(arr[x-'a']-- >0){
                str.append(x);
            }
        }
        
        //This snippet appends all the other characters in T not present in S
        
        for(char x = 'a'; x<='z';x++){
            while(arr[x-'a']-- >0){
                str.append(x);
            }
        }
        return str.toString();
    }
}
