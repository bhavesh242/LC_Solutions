package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode811 {
	  public List<String> subdomainVisits(String[] cpdomains) {
	        HashMap<String,Integer> map = new HashMap<String, Integer>();
	        String website="";
	        int frequency=0;
	        for(String domain: cpdomains)
	        {
	            for(int i=0;i<domain.length();i++)
	            {
	                if(domain.charAt(i) == ' ')
	                {
	                    frequency = Integer.parseInt(domain.substring(0,i));
	                    website = domain.substring(i+1);
	                    map.put(website,map.getOrDefault(website,0)+frequency);
	                }
	                if(domain.charAt(i) == '.')
	                {
	                    String subD = domain.substring(i+1);
	                    map.put(subD, map.getOrDefault(subD,0)+frequency);
	                }
	            }
	            
	        }
	        ArrayList<String> op = new ArrayList();
	        for(Map.Entry<String,Integer> e : map.entrySet())
	        {
	            op.add(e.getValue() + " " + e.getKey());
	        }
	        return op;
	    }
}
