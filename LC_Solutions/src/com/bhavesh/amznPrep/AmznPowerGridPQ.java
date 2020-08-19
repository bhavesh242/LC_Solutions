package com.bhavesh.amznPrep;

import java.util.*;

class AmznPowerGridPQ {
	public static int findParent(int x, int[] parent) {
		if (parent[x] == -1) {
			return x;
		}
		return findParent(parent[x], parent);
	}

	public static ArrayList<Connection> findMinimumCostToConnectServers(ArrayList<Connection> connections) {
		ArrayList<Connection> res = new ArrayList<>();
		PriorityQueue<Connection> minHeap = new PriorityQueue<Connection>(
				(Connection a, Connection b) -> (a.cost - b.cost));
		minHeap.addAll(connections);
		int parent[] = new int[26];

		for (int i = 0; i < 26; i++) {
			parent[i] = -1;
		}

		while (minHeap.size() > 0) {
			Connection ele = minHeap.remove();

			int parentFrom = findParent(ele.firstTown - 'A', parent);
			int parentTo = findParent(ele.secondTown - 'A', parent);

			if (parentFrom != parentTo) {
				parent[parentTo] = parentFrom;
				res.add(new Connection(ele.firstTown, ele.secondTown, ele.cost));
			}
		}
		return res;
	}

	public static void main(String[] args) throws java.lang.Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		ArrayList<Connection> a = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			a.add(new Connection(sc.next().charAt(0), sc.next().charAt(0), sc.nextInt()));
		}
		ArrayList<Connection> ans = findMinimumCostToConnectServers(a);
		for (int i = 0; i < ans.size(); i++) {
			System.out.println(ans.get(i).firstTown + " " + ans.get(i).secondTown + " " + ans.get(i).cost);
		}
	}
}

class Connection {
	char firstTown, secondTown;
	int cost;

	Connection(char from, char to, int cost) {
		this.firstTown = from;
		this.secondTown = to;
		this.cost = cost;
	}
}
