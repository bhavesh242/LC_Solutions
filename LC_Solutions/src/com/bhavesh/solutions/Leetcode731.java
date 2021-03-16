package com.bhavesh.solutions;

import java.util.Map;
import java.util.TreeMap;

public class Leetcode731 {

}

class MyCalendarTwo {
	Map<Integer, Integer> timeLine;

	public MyCalendarTwo() {
		timeLine = new TreeMap<Integer, Integer>();
	}

	public boolean book(int start, int end) {
		timeLine.put(start, timeLine.getOrDefault(start, 0) + 1);
		timeLine.put(end, timeLine.getOrDefault(end, 0) - 1);
		int count = 0;
		for (int x : timeLine.values()) {

			count += x;
			if (count >= 3) {
				timeLine.put(start, timeLine.get(start) - 1);
				timeLine.put(end, timeLine.get(end) + 1);
				if (timeLine.get(start) == 0) {
					timeLine.remove(start);
				}
				if (timeLine.get(end) == 0) {
					timeLine.remove(end);
				}
				return false;
			}
		}

		return true;
	}
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo(); boolean param_1 =
 * obj.book(start,end);
 */