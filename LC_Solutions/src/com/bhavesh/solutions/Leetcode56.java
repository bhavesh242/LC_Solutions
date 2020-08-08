package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Leetcode56 {
	public int[][] merge(int[][] intervals) {
        //If there are no intervals, return the same empty array 
        if(intervals.length == 0){
             return intervals;
         }
        //Sort the intervals array in ascending order of their start times
        Arrays.sort(intervals, new Comparator<int[]> (){
            public int compare(int []a, int[]b){
                return a[0] - b[0];
            }
        });
        //Use an arraylist to store output as we wont know size of the output yet
        List<int[]> op = new ArrayList<int[]>();
        //Take the earliest interval i.e. intervals[0] and add it to output arraylist
        //Also call this interval as early
        int []early = intervals[0];
        op.add(early);
        //start traversing from next index i.e 1 to all intervals
        for(int i=1;i<intervals.length;i++){
            //take current interval
            int cur[] = intervals[i];
            //if the early interval overlaps with current, merge them into one
            if(early[1]>=cur[0]){
                /*end of this merge interval will be the later end point of early                   and current*/
                early[1] = Math.max(early[1],cur[1]);
            }
            /*if the intervals dont overlap, set current interval as early and
            put it into output array*/
            else{
                early = cur;
                op.add(cur);
            }
        }
        
        //the following code converts arraylist to array
        
        return op.toArray(new int[op.size()][2]);
    }
}
