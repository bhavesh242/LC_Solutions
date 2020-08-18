package com.bhavesh.amznPrep;

import java.util.ArrayList;
import java.util.List;

public class AmznVideoScenes {
	public List<Integer> partitionLabel(String s){
		List<Integer> op = new ArrayList<Integer>();
		int []lastIdx = new int[26];
		for(int i=0;i<s.length(); i++) {
			lastIdx[s.charAt(i)-'a'] = i; 
		}
		int i = 0;
		while(i<s.length()) {
			int end = lastIdx[s.charAt(i)-'a'];
			int j= i+1;
			while(j<end) {
				end = Math.max(end, lastIdx[s.charAt(j)-'a']);
				j++;
			}
			
			op.add(end - i+1);
			i = end+1;
		}
		return op;
	}
}
