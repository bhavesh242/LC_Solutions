package com.bhavesh.amznPrep;

import java.util.ArrayList;
import java.util.Collections;

public class AmazonMusicID {
	public static void main(String[] args) {
		ArrayList<Integer> songDurations = new ArrayList<Integer>();
		songDurations.add(1);
		songDurations.add(10);
		songDurations.add(25);
		songDurations.add(35);
		songDurations.add(60);
		System.out.println(new AmazonMusicID().twoSumUnique(90,5, songDurations));
	}
	public int[] twoSumUnique(int rideDuration, int numSongs, ArrayList<Integer> songDurations) {
		Collections.sort(songDurations);
		int low = 0, high = numSongs - 1;
		while (low < high) {
			int sum = songDurations.get(low) + songDurations.get(high);
			if (sum == rideDuration - 30) {
				return new int[] { songDurations.get(low), songDurations.get(high) };
			} else if (sum < rideDuration - 30) {
				low++;
			} else {
				high--;
			}
		}
		return new int[] { -1, -1 };
	}

}
