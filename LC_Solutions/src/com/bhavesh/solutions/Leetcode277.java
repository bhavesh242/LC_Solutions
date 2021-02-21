package com.bhavesh.solutions;

public class Leetcode277 {
	public int findCelebrity(int n) {

		// in a full o(n) loop, we can eliminate n-1 nodes

		/*
		 * For any nodes x and y, if x knows y it implies x is not a celebrity else if x
		 * does not know y, it implies y is not a celebrity
		 */
		/*
		 * We start by assuming candidate 0 is the celebrity and keep updating our
		 * potential celebrity by eliminating based on above choices
		 */
		int candidate = 0;
		for (int i = 1; i < n; i++) {
			/*
			 * If candidate knows i, then make i candidate else keep candidate same and
			 * eliminate i by simple increment
			 */
			if (knows(candidate, i)) {
				candidate = i;
			}
		}

		/*
		 * Now that we have a potential candidate for celebrity check it's validity by
		 * verifying it is known to everyone and it knows no-one
		 */
		for (int i = 0; i < n; i++) {
			if (i == candidate)
				continue;
			if (!knows(i, candidate) || knows(candidate, i)) {
				return -1;
			}
		}

		return candidate;
	}

	/* The knows API is defined in the parent class Relation.
    boolean knows(int a, int b); */

	//Some method knows
	public boolean knows(int a, int b)
	{
		return true;
	}
}