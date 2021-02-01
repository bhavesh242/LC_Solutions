package com.bhavesh.solutions;

public class Leetcode573 {
	public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {

		/*
		 * Based on the simple principle that if if there are n nuts squirrel has to
		 * travel total 2(distances to n-1 nuts from tree) + (squirrel to optimal nut) +
		 * (optimal nut to tree) distance
		 */

		/*
		 * This can also be rewritten as: 2(distances to all n nuts from tree) +
		 * (squirrel to optimal nut) - (optimal nut to tree distance ) We need to
		 * minimize the (squirrel to optimal nut) - (optimal nut to tree distance ) part
		 * 
		 */
		int sum = 0;
		int minDist = Integer.MAX_VALUE;
		for (int nut[] : nuts) {
			int curDist = calcDist(nut, tree);
			sum += curDist;
			int dist = calcDist(squirrel, nut) - curDist;
			if (dist < minDist) {
				minDist = dist;
			}
		}

		return 2 * sum + minDist;
	}

	public int calcDist(int[] a, int[] b) {
		return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
	}
}