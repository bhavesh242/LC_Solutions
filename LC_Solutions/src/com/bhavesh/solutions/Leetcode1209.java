package com.bhavesh.solutions;

import java.util.Stack;

public class Leetcode1209 {
	public String removeDuplicates(String s, int k) {
		// Approach 1
		/*
		 * StringBuffer sb = new StringBuffer(s); Stack<Integer> st = new
		 * Stack<Integer>(); for(int i=0;i<sb.length();i++) { if(i==0 ||
		 * sb.charAt(i)!=sb.charAt(i-1)) { st.push(1); } else { int ct = st.pop()+1;
		 * if(ct == k) { sb.delete(i-k+1,i+1); i=i-k; } else { st.push(ct); } } }
		 * 
		 * return sb.toString();
		 */

		// Approach 3 using single stack
		Stack<CharCt> st = new Stack<CharCt>();
		for (int i = 0; i < s.length(); i++) {
			if (st.isEmpty() || s.charAt(i) != st.peek().ch) {
				st.push(new CharCt(s.charAt(i), 1));
			} else {
				if ((++st.peek().ct) == k) {
					st.pop();
				}
			}
		}

		StringBuffer sb = new StringBuffer();
		for (CharCt x : st) {
			for (int i = 0; i < x.ct; i++) {
				sb.append(x.ch);
			}
		}

		return sb.toString();
	}

}

class CharCt {
	char ch;
	int ct;

	CharCt(char ch, int ct) {
		this.ch = ch;
		this.ct = ct;
	}

	@Override
	public String toString() {
		return ch + " " + ct;
	}
}