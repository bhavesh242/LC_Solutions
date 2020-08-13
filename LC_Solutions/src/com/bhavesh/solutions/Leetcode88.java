package com.bhavesh.solutions;

public class Leetcode88 {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		if (nums2 == null || nums2.length == 0) {
			return;
		}
		// Points to last non-zero element of nums1;
		int ind1 = m - 1;
		// Points to last element of nums2
		int ind2 = n - 1;
		// Point to last element in nums1 which is naturall a 0, from here we start
		// merging the arrays
		int zero = m + n - 1;
		while (ind1 >= 0 && ind2 >= 0) {
			/*
			 * Append the bigger element off nums1[ind1] and nums2[ind2] to the left and
			 * move that pointer and the zero pointer one position to the left
			 */
			nums1[zero--] = nums1[ind1] >= nums2[ind2] ? nums1[ind1--] : nums2[ind2--];
		}

		// Copy any leftover values in nums2 to beginning of nums1
		System.arraycopy(nums2, 0, nums1, 0, ind2 + 1);
	}
}
