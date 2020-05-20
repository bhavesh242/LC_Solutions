package com.bhavesh.solutions;

public class Leetcode67 {
	public String addBinary(String a, String b) {
		int ind1 = a.length() - 1;
		int ind2 = b.length() - 1;
		int carry = 0;
		int pos1;
		int pos2;
		int sum = 0;
		StringBuilder sb = new StringBuilder();
		while (ind1 >= 0 || ind2 >= 0 || sum != 0) {
			if (ind1 >= 0) {
				sum = sum + getDig(ind1, a);
			}
			if (ind2 >= 0) {
				sum = sum + getDig(ind2, b);
			}
			sum = sum + carry;
			carry = buildString(sum, sb);
			ind1--;
			ind2--;
			sum = 0;
		}
		if (carry > 0) {
			sb.append(carry);
		}
		return sb.reverse().toString();
	}

	public int getDig(int ind, String s) {
		return s.charAt(ind) == '0' ? 0 : 1;
	}

	public int buildString(int sum, StringBuilder sb) {
		if (sum == 0) {
			sb.append(0);
			return 0;
		} else if (sum == 1) {
			sb.append(1);
			return 0;
		} else if (sum == 2) {
			sb.append(0);
			return 1;
		} else {
			sb.append(1);
			return 1;
		}
	}
}
