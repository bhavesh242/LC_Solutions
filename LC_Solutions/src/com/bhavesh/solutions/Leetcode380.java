package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Leetcode380 {

}
class RandomizedSet {

    /** Initialize your data structure here. */
    HashMap<Integer, Integer> dict;
    List<Integer> list; 
    Random rand;
    public RandomizedSet() {
        dict = new HashMap<Integer, Integer>();
        list = new ArrayList<Integer>();
        rand = new Random();
        
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(dict.containsKey(val)){
            return false;
        }
        dict.put(val,list.size());
        list.add(list.size(),val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!dict.containsKey(val)){
            return false;
        }
        int last_elem = list.get(list.size()-1);
        int element_index = dict.get(val);
        list.set(element_index,last_elem);
        dict.put(last_elem,element_index);
        list.remove(list.size()-1);
        dict.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */