package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Leetcode49 {
	 public List<List<String>> groupAnagrams(String[] strs) {
	        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
	        List<List<String>> op = new ArrayList<List<String>>();
	        for(String x : strs)
	        {
	            char c [] = x.toCharArray();
	            Arrays.sort(c);
	            String  ch = new String(c);
	            if(!map.containsKey(ch))
	            {
	                List<String> l = new ArrayList<String>();
	                l.add(x);
	                map.put(ch,l);
	            }
	            else
	            {
	                map.get(ch).add(x);
	            }
	        }
	        
	        
	        
	        return new ArrayList<List<String>>(map.values());
	        
	    }
}
