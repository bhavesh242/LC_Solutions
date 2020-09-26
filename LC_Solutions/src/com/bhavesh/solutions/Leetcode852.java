package com.bhavesh.solutions;

public class Leetcode852 {
	public int peakIndexInMountainArray(int[] arr) {
        //Binary Search, check if the mid is on an increasing slope
        int left = 0, right = arr.length-1;
        while(left<right){
            int mid = left + (right-left)/2 ;
            if(arr[mid]<arr[mid+1])
            {
                left = mid+1;
            } 
            else
            {
                right = mid;
            }
        }
        return left;
    }
}