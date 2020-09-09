package com.bhavesh.solutions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Leetcode1429 {

}

class FirstUnique {
    Queue<Integer> queue = new LinkedList();
    HashMap<Integer, Boolean> map = new HashMap();
    public FirstUnique(int[] nums) {
        //Add each element of the array to Queue
        for(int num : nums){
            add(num);
        }
    }
    
    public int showFirstUnique() {
        //Remove all the elements from queue that are not unique
        while(!queue.isEmpty() && !map.get(queue.peek())){
            queue.remove();    
        }
        //If queue is empty, return -1 else return the first remaining element in queue
        return queue.isEmpty()? -1 : queue.peek();
    }
    
    
    public void add(int value) {
        /*If the map does not contain the value as key, add it to the queue 
        and set unique to true*/
        if(!map.containsKey(value)){
            queue.offer(value);
            map.put(value, true);
        }
        //Else the element is a duplicate, and set unique to false
        else{
            map.put(value, false);
        }
    }
}

/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique obj = new FirstUnique(nums);
 * int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */
