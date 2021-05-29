package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Leetcode721 {
}

class Sol721_1 {
	// Solution 1 : DFS
	public List<List<String>> accountsMerge(List<List<String>> accounts) {

		HashMap<String, String> emailToName = new HashMap<String, String>();
		HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();

		for (List<String> acc : accounts) {
			String name = acc.get(0);
			for (int i = 1; i < acc.size(); i++) {
				emailToName.put(acc.get(i), name);
				graph.putIfAbsent(acc.get(i), new ArrayList<String>());
				if (i == 1) {
					continue;
				}
				graph.get(acc.get(i)).add(acc.get(1));
				graph.get(acc.get(1)).add(acc.get(i));
			}
		}

		HashSet<String> seen = new HashSet<String>();
		List<List<String>> ans = new ArrayList<List<String>>();
		for (String node : graph.keySet()) {
			if (!seen.contains(node)) {
				ArrayList<String> curNodes = new ArrayList<String>();
				dfs(node, graph, seen, curNodes);
				Collections.sort(curNodes);
				curNodes.add(0, emailToName.get(node));
				ans.add(curNodes);
			}
		}

		return ans;
	}

	public void dfs(String node, HashMap<String, ArrayList<String>> graph, HashSet<String> seen,
			ArrayList<String> curNodes) {
		curNodes.add(node);
		seen.add(node);
		for (String neigh : graph.get(node)) {
			if (!seen.contains(neigh)) {
				dfs(neigh, graph, seen, curNodes);
			}
		}
		return;
	}
}

class Sol721_2 {
	// Approach 2 : Union find with Maps
	public List<List<String>> accountsMerge(List<List<String>> accounts) {

		Map<String, String> emailToName = new HashMap<String, String>();
		Map<String, String> parents = new HashMap<String, String>();

		for (List<String> account : accounts) {
			String name = account.get(0);
			for (int i = 1; i < account.size(); i++) {
				parents.putIfAbsent(account.get(i), account.get(i));
				emailToName.putIfAbsent(account.get(i), name);
			}
		}

		for (List<String> account : accounts) {

			if (account.size() <= 2) {
				continue;
			}
			String parent = find(account.get(1), parents);
			for (int i = 2; i < account.size(); i++) {
				String email = account.get(i);
				union(parent, email, parents);
			}
		}

		Map<String, TreeSet<String>> unions = new HashMap<>();

		for (List<String> a : accounts) {
			String p = find(a.get(1), parents);
			if (!unions.containsKey(p))
				unions.put(p, new TreeSet<>());
			for (int i = 1; i < a.size(); i++)
				unions.get(p).add(a.get(i));
		}
		List<List<String>> res = new ArrayList<>();
		for (String p : unions.keySet()) {
			List<String> emails = new ArrayList<String>(unions.get(p));
			emails.add(0, emailToName.get(p));
			res.add(emails);
		}
		return res;
	}

	public void union(String parent, String email, Map<String, String> parents) {
		String P = find(parent, parents);
		String E = find(email, parents);
		if (P.equals(E)) {
			return;
		}
		parents.put(E, P);

	}

	public String find(String email, Map<String, String> parents) {
		if (parents.get(email).equals(email)) {
			return email;
		}

		return find(parents.get(email), parents);
	}
}

class Sol721_3 {

	// Approach 3 : Union Find with arrays and email groupings (fastest)
	public List<List<String>> accountsMerge(List<List<String>> accounts) {

		// This is to assign unique id's to each email groups
		HashMap<String, Integer> mailIdx = new HashMap<String, Integer>();

		int size = accounts.size();
		// Initialize our Disjoint Set union objecy
		DSU dsu = new DSU(size);

		// Iterate over the accounts list
		for (int i = 0; i < size; i++) {
			List<String> acc = accounts.get(i);

			/*
			 * If an email was not seen in the mail-id map, assign current account's id to
			 * it If you encounter an email that was already present in the mail-id map, it
			 * means that this group is a child of another group, find that encountered
			 * email's id and make it a parent iof current group using DSU union find
			 */
			for (int j = 1; j < acc.size(); j++) {
				String email = acc.get(j);
				if (mailIdx.containsKey(email)) {
					dsu.union(mailIdx.get(email), i);
				} else {
					mailIdx.put(email, i);
				}
			}

		}

		// Once all parents have been found, create a Map that stores parent ids with
		// all emails under those parentId
		// Use a treeset to get a sorting of the emails, as per the question's
		// requirements
		Map<Integer, TreeSet<String>> map = new HashMap();
		for (String mail : mailIdx.keySet()) {
			int parentID = dsu.find(mailIdx.get(mail));
			map.putIfAbsent(parentID, new TreeSet<String>());
			map.get(parentID).add(mail);
		}

		/*
		 * Convert this map into desired output format. To get account name, use the
		 * id's from the mail-id mapping as the accountId's are same as their indexex in
		 * the accounts input list
		 */
		List<List<String>> ans = new ArrayList();
		for (int x : map.keySet()) {
			ArrayList<String> a = new ArrayList<String>();
			a.add(accounts.get(x).get(0));
			a.addAll(map.get(x));
			ans.add(a);
		}

		return ans;

	}
}

class DSU {
	int parents[];

	DSU(int size) {
		parents = new int[size];
		for (int i = 0; i < size; i++) {
			parents[i] = i;
		}

	}

	public void union(int a, int b) {
		int A = find(a);
		int B = find(b);
		if (A != B) {
			parents[A] = B;
		}
	}

	public int find(int x) {
		if (parents[x] == x) {
			return x;
		}

		parents[x] = find(parents[x]);
		return parents[x];
	}
}