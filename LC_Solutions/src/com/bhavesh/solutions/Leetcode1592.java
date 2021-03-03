package com.bhavesh.solutions;

import java.util.ArrayList;

public class Leetcode1592 {
	public String reorderSpaces(String text) {
		ArrayList<String> words = new ArrayList<String>();
		int t = 0;
		int spaceCt = 0;
		StringBuffer str = new StringBuffer();
		while (t < text.length()) {
			if (text.charAt(t) == ' ') {
				spaceCt++;
				if (str.length() != 0) {
					words.add(new String(str));
					str.setLength(0);
				}

			} else {
				str.append(text.charAt(t));
			}

			t++;
		}

		if (str.length() != 0) {
			words.add(new String(str));
		}
		str.setLength(0);

		int remainder;
		str.append(words.get(0));
		if (words.size() == 1) {
			remainder = spaceCt;
		} else {
			int equalSpaces = (spaceCt) / (words.size() - 1);
			StringBuffer spaces = new StringBuffer("");
			for (int i = 0; i < equalSpaces; i++) {
				spaces.append(' ');
			}

			for (int i = 1; i < words.size(); i++) {
				str.append(spaces + words.get(i));
			}

			remainder = spaceCt % (words.size() - 1);
		}

		for (int i = 0; i < remainder; i++) {
			str.append(' ');
		}

		return str.toString();
	}
}