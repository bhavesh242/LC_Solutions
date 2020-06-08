package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Leetcode609 {
	public List<List<String>> findDuplicate(String[] paths) {
        HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
        for(String path : paths){
            String[]filenames = path.split("\\s+");
            for(int i=1;i<filenames.length;i++){
                String content[] = filenames[i].split("\\(");
                content[1]= content[1].replace(")", "");
                map.putIfAbsent(content[1],new ArrayList<String>());
                ArrayList<String> a = map.get(content[1]);
                a.add(filenames[0]+"/"+content[0]);
            }            
        }
        
        List<List<String>> op = new ArrayList<List<String>>();
        for(String x : map.keySet()){
            if(map.get(x).size() > 1){
                op.add(map.get(x));
            }
        }
        
        return op;
    }
}
