package com.bhavesh.solutions;

import java.util.HashMap;
import java.util.Map;

public class Leetcode2615 {

    public static void main(String[] args) {
        distance2(new int[]{1,2,1,1, 1});
    }
    public static long[] distance(int[] nums) {
        long result[] = new long[nums.length];
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new int[]{1, i});
            } else {
                int arr[] = map.get(nums[i]);
                result[i] = arr[0] * (i - arr[1]) + result[arr[1]];
                ++arr[0];
                arr[1] = i;
            }
        }
        map = new HashMap<>();
        long temp[] = new long[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new int[]{1, i});
            } else {
                int arr[] = map.get(nums[i]);
                temp[i] = arr[0] * (arr[1] - i) + temp[arr[1]];
                ++arr[0];
                arr[1] = i;
                result[i] = result[i] + temp[i];
            }
        }
        return result;
    }

    //Alternate Solution
    public static long[] distance2(int[] arr) {
        Map<Long, long[]> map = new HashMap<>();
        // [0] -> sum of indices at left of i
        // [1] -> sum of indices at right of i
        // [2] -> left freq
        // [3] -> right freq
        int i = 0;
        for (int e : arr) {
            long x = e;
            if (map.get(x) == null) {
                map.put(x, new long[4]);
            }
            map.get(x)[1] += i++; // total sum of indices with value x
            map.get(x)[3]++;    // no. of occurences of x in arr
        }

        long[] res = new long[arr.length];
        i = 0;
        for (int e : arr) {
            long x = e;
            long[] temp = map.get(x);
            temp[1] -= i;  // sum of indices at right
            temp[3]--;   // right freq
            long leftSide = Math.abs(temp[0] - i * temp[2]);
            long rightSide = Math.abs(temp[1] - i * temp[3]);
            res[i] = leftSide + rightSide;
            temp[0] += i++;  // sum of indices at left
            temp[2]++;   // left freq
        }
        return res;
    }
}
