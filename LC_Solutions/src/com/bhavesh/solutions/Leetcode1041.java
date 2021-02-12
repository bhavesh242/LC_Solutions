package com.bhavesh.solutions;

public class Leetcode1041 {
	public boolean isRobotBounded(String instructions) {

		// Based on the mathematic principle that after at most 4 cycles, the trajectory
		// converges to the origin
		// But we need not traverse all 4 cycles
		// If the robot returns to origin at the end of first cycle itself, then it
		// bound by a circle

		/* or */
		/*
		 * At the end of the first cycle, if the face of the robot is any other
		 * direction than the starting facing direction (north in our case) , then the
		 * trajectory is bounded by a circle
		 */

		// Initial Coordinates
		int x = 0;
		int y = 0;

		int dir[][] = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

		// Curfacing stores the curerent facing direction of our robot
		// 0 is north, 1 is east, 2 is left and 3 is south
		int curFacing = 0;
		for (char c : instructions.toCharArray()) {
			if (c == 'G') {
				x = x + dir[curFacing][0];
				y = y + dir[curFacing][1];
			} else if (c == 'L') {
				// This code is to turn left and also avoid negative indexes
				curFacing = ((curFacing - 1) % 4 + 4) % 4;

			} else {
				// This code is to turn right and also avoid negative indexes
				curFacing = ((curFacing + 1) % 4 + 4) % 4;
			}

		}

		// Check if robot reached origin or isnt facing north
		return (x == 0 && y == 0) || curFacing != 0;

	}
}