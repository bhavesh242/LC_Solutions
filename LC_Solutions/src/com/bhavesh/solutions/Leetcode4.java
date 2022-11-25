package com.bhavesh.solutions;

public class Leetcode4 {


    /*
      Take minimum size of two array. Possible number of partitions are from 0 to m in m size array.
      Try every cut in binary search way. When you cut first array at i then you cut second array at j = (m + n + 1)/2 - i
      Now try to find a position i such that nums1[i-1] <= nums2[j] and nums2[j-1] <= nums1[i].
      So this i is partition around which lies the median.
     */

    //Binary search Solution : o(log(m+n))
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        /*
          If second array is greater than first array, call the function by switching the array positions
          This is done for 2 reasons :
          1) if array1 is greater than array 2, then when we try to calculate the second cut, the formula (m+n+1)/2 - cut1
          can yield a negative number or a number that is out of bounds in array2
          2) We binary search over second array, so this will ensure lesser iterations as the array is smaller.
         */
        if (n < m) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int low = 0;
        int high = m;

        //Half is used to determine where the second cut will exist in num2 based on num1
        int half = (m + n + 1) / 2;

        while (low <= high) {
            //partition will be middle of low and high
            int cut1 = (high + low) / 2;
            int cut2 = half - cut1;

            //if cut1 is 0 it means nothing is there on left side. Use -INF for l1
            //if cut1 is length of input array then there is nothing on right side. Use +INF for r1
            //Do the same as above for cut2 and second array
            int l1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int r1 = cut1 == m ? Integer.MAX_VALUE : nums1[cut1];
            int l2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int r2 = cut2 == n ? Integer.MAX_VALUE : nums2[cut2];
            
            if (l1 > r2) {
                //This means we are on the left of the desired partition and need to shift leftwards
                high = cut1 - 1;
            } else if (l2 > r1) {
                //This means we are on the left of the desired partition and need to shift leftwards
                low = cut1 + 1;
            } else {
                /*This implies we have reached our ideal partition element.
                 Now get max of left elements and min of right elements to get the median in case of even length combined array size
                 or get max of left for odd length combined array size.
                */
                if ((m + n) % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                }
                return 1.0 * Math.max(l1, l2);
            }
        }

        //This will only happen if the arrays were not sorted.
        throw new IllegalArgumentException("Input is Invalid.");
    }
}

