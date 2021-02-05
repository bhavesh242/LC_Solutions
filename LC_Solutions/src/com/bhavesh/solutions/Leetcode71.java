package com.bhavesh.solutions;

import java.util.ArrayDeque;
import java.util.Deque;

public class Leetcode71 {
	public String simplifyPath(String path) {

		// Using a stack or a Deque
		String pathArr[] = path.split("/");
		Deque<String> dq = new ArrayDeque<String>();
		for (int i = 0; i < pathArr.length; i++) {
			String curDir = pathArr[i].trim();
			if (curDir.length() == 0 || curDir.equalsIgnoreCase(".")) {
				continue;
			}
			if (curDir.equalsIgnoreCase("..")) {
				if (!dq.isEmpty()) {
					dq.removeLast();
				}
			} else {
				dq.addLast(curDir);
			}
		}

		if (dq.isEmpty()) {
			return "/";
		}
		StringBuffer str = new StringBuffer();
		while (!dq.isEmpty()) {
			str.append("/" + dq.pollFirst());
		}

		return str.toString();
	}
}