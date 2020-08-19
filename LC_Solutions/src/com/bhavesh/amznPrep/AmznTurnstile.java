package com.bhavesh.amznPrep;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class AmznTurnstile {
	public int compare(int enter, int exit, int time, int status) {
		enter -= time;
		exit -= time;
		if (enter < 0)
			enter = 0;
		if (exit < 0)
			exit = 0;
		if (enter < exit)
			return -1;
		if (enter == exit) {
			if (status == 1)
				return 1;
			else
				return -1;
		}
		return 1;
	}

	public int[] amazonTurnstile(int numCustomers, int[] arrTime, int[] direction) {
		// 1: exit, 0: enter
		int[] res = new int[numCustomers];
		Arrays.fill(res, -1);
		Queue<int[]> exit = new LinkedList<>();
		Queue<int[]> enter = new LinkedList<>();

		for (int i = 0; i < numCustomers; i++) {
			if (direction[i] == 0) {
				enter.offer(new int[] { i, arrTime[i] });
			} else {
				exit.offer(new int[] { i, arrTime[i] });
			}
		}

		int status = 1;// default exit
		int time = 0;

		while (!exit.isEmpty() && !enter.isEmpty()) {
			int exitTime = exit.peek()[1];
			int enterTime = enter.peek()[1];
			int comp = compare(enterTime, exitTime, time, status);
			if (comp == 1) {// exit pass
				int index = exit.peek()[0];
				int val = exit.poll()[1];
				res[index] = Math.max(time, val);
				time = Math.max(time, val) + 1;
				status = 1;
			} else {// enter pass
				int index = enter.peek()[0];
				int val = enter.poll()[1];
				res[index] = Math.max(time, val);
				time = Math.max(time, val) + 1;
				status = 0;
			}
		}

		while (!exit.isEmpty()) {
			int index = exit.peek()[0];
			int val = exit.poll()[1];
			res[index] = Math.max(time, val);
			time = Math.max(time, val) + 1;
			status = 1;
		}

		while (!enter.isEmpty()) {
			int index = enter.peek()[0];
			int val = enter.poll()[1];
			res[index] = Math.max(time, val);
			time = Math.max(time, val) + 1;
			status = 0;
		}

		return res;
	}

	public static void main(String[] args) {
		System.out.println(new AmznTurnstile().amazonTurnstile(5, new int[] { 0, 0, 0, 0,0 }, new int[] { 1,1,0,0,1 }));
	}
}