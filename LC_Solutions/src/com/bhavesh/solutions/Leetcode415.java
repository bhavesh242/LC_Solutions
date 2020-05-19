package com.bhavesh.solutions;

public class Leetcode415 {
	public String addStrings(String num1, String num2) {

		int ind1 = num1.length() - 1;
		int ind2 = num2.length() - 1;

		StringBuffer sb = new StringBuffer();
		int carry = 0;
		while (ind1 >= 0 || ind2 >= 0) {
			int n1 = ind1 < 0 ? 0 : num1.charAt(ind1) - '0';
			int n2 = ind2 < 0 ? 0 : num2.charAt(ind2) - '0';

			int sum = carry + n1 + n2;
			if (sum >= 10) {
				sum = sum % 10;
				carry = 1;
			} else {
				carry = 0;
			}
			sb.append(sum);
			ind1--;
			ind2--;
		}
		if (carry > 0) {
			sb.append(1);
		}
		sb = sb.reverse();
		return sb.toString();
	}
}
