package com.bhavesh.solutions;

public class Leetcode190 {
	// you need treat n as an unsigned value
	public int reverseBits(int n) {
		int ans = 0;

		for (int i = 0; i < 32; i++) {
			ans = ans << 1;
			if ((n & 1) == 1) {
				ans++;
			}

			n = n >> 1;
		}

		return ans;
	}

	// Same solution as above but with a different representation
	public int reverseBits_2(int n) {
		int res = 0;
		for (int i = 0; i < 32; i++) {
			res += n & 1;// get the most right bit each time
			n >>>= 1;// do UN-signed right shift by 1 each time
			if (i < 31) {
				res <<= 1;// shift this number to the left by 1 each time, so that eventually, this number
							// is reversed
			}
		}
		return res;
	}

}