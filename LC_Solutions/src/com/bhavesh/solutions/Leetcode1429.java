package com.bhavesh.solutions;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Leetcode1429 {

}

class FirstUnique {
	Map<Integer, Boolean> map;
	Set<Integer> set;

	public FirstUnique(int[] nums) {
		map = new HashMap<Integer, Boolean>();
		set = new LinkedHashSet<Integer>();
		for (int x : nums) {
			add(x);
		}
	}

	public int showFirstUnique() {
		if (set.iterator().hasNext()) {
			return set.iterator().next();
		}
		return -1;
	}

	public void add(int value) {
		if (!map.containsKey(value)) {
			map.put(value, true);
			set.add(value);
		} else {
			map.put(value, false);
			set.remove(value);
		}
	}
}
