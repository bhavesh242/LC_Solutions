package com.bhavesh.solutions;

public class Leetcode953 {
}

//Sol 1 : Storing Postions in a map
class Sol_953_1 {
	class Solution {
		public boolean isAlienSorted(String[] words, String order) {
			int map[] = new int[26];
			for (int i = 0; i < order.length(); i++) {
				map[order.charAt(i) - 'a'] = i;
			}

			for (int i = 0; i < words.length - 1; i++) {
				if (!verifyOrder(words[i], words[i + 1], map)) {
					return false;
				}
			}

			return true;
		}

		public boolean verifyOrder(String a, String b, int[] map) {
			for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
				if (a.charAt(i) != b.charAt(i)) {
					return map[a.charAt(i) - 'a'] < map[b.charAt(i) - 'a'];

				}
			}
			return b.length() >= a.length();
		}
	}
}

//Sol 2 : Using indexOf function
class Sol_953_2 {
	public boolean isAlienSorted(String[] words, String order) {

		for (int i = 0; i < words.length - 1; i++) {
			if (!compare(words[i], words[i + 1], order)) {
				return false;
			}
		}
		return true;
	}

	public boolean compare(String a, String b, String order) {
		for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				return order.indexOf(a.charAt(i)) < order.indexOf(b.charAt(i));
			}
		}

		if (a.length() <= b.length()) {
			return true;
		}
		return false;
	}
}