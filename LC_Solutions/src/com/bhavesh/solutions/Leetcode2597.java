package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Leetcode2597 {

    //O(NlogN) solution
    public int beautifulSubsets(int[] nums, int k) {
        /*
        Create an Array of TreeMap
        Each Index in this array represents the remainder we'll get when dividing
        the numbers in nums with k
        The idea is to group numbers with same remainder together
        Each TreeMap will store the numbers, along with their counts. We use TreeMap
        to keep the numbers sorted.
        */

        TreeMap<Integer, Integer> remainderGrps[] = new TreeMap[k];
        for (int i : nums) {
            int rem = i % k;
            if (remainderGrps[rem] == null) {
                remainderGrps[rem] = new TreeMap<>();
            }
            remainderGrps[rem].put(i, remainderGrps[rem].getOrDefault(i, 0) + 1);
        }

        /*
        As only elements within same remainders will affect each other and not from other groups
        we can calculate subsets in each group and multiply them to get final result
        */

        int result = 1;
        for (int i = 0; i < k; i++) {
            TreeMap<Integer, Integer> vals = remainderGrps[i];
            if (vals == null) {
                continue;
            }
            //notTaken counts the number of subsets if current element is not taken
            //taken counts the number of subsets if current element is taken
            int prev = 0, notTaken = 1, taken = 0;

            for (int j : vals.keySet()) {
                int tempNotTaken, tempTaken;
                //This multiplier accounts for duplicate values. (2^n) for n duplicate values
                int multiplier = 1 << vals.get(j);
                if (prev + k == j) {
                    tempNotTaken = taken + notTaken;
                    tempTaken = notTaken * (multiplier - 1);
                } else {
                    tempNotTaken = taken + notTaken;
                    tempTaken = (taken + notTaken) * (multiplier - 1);
                }
                notTaken = tempNotTaken;
                taken = tempTaken;
                prev = j;
            }

            result *= notTaken + taken;
        }

        //Returning -1 for discounting the empty subset
        return result - 1;
    }

    //O(N) solution :
    // https://leetcode.com/problems/the-number-of-beautiful-subsets/solutions/3314361/python-house-robber-o-n/?orderBy=most_votes
    //Look for all chains that have numbers placed a distance of K and calculate subsets for the
    //Multiply resulting numbers from all such chains
    public int beautifulSubsets2(int[] nums, int k) {
        //maps number to frequency
        Map< Integer, Integer > map = new HashMap < > ();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = 1;
        for (int key: map.keySet()) {

            //checks if value starts chain of k difference values
            if (!map.containsKey(key - k)) {
                int subsetWithout = 1, subsetWith = 0;
                int currentKey = key;
                while (map.containsKey(currentKey)) {
                    subsetWithout = subsetWithout + subsetWith;
                    subsetWith = ((1 << map.get(currentKey)) - 1) * (subsetWithout - subsetWith);
                    currentKey += k;
                }
                res *= (subsetWith + subsetWithout);
            }
        }
        return res - 1;
    }
}