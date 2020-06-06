package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Leetcode17 {
	List<String> op = new ArrayList<String>();
    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length()==0){
            return op;
        }
        HashMap <Character, String> map = new HashMap<Character, String>();
        map.put('2', "abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        backTrack(map,0,digits, new StringBuffer());
        return op;
    }
    
    public void backTrack(HashMap<Character, String> map, int index, String digits, StringBuffer cur)
    {
        if(index == digits.length()){
            op.add(new String(cur));
            return;
        }
        for(char x : map.get(digits.charAt(index)).toCharArray()){
            cur.append(x);
            backTrack(map,index+1, digits, cur);
            cur.deleteCharAt(cur.length()-1);
        }
    }
}
