package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * The main idean here is that you can not decide the nodes to half the cost without knowing the number of times a node is visited.
 * So first run a simple DFS and count the number of times a node will be visited.
 * Once you have this number, you can find the total cost a node will contribute towards final cost after taking all the trips.
 * Now we need to half the price of few of the nodes
 * The key here is that they are not suppose to be neighbours but that doesn't mean you can alternate between the nodes.
 * eg: a -> b -> c -> d
 * in this case it might be possible that best value will come if you half the price of a and d instead of a and c; or it can also happen that you must choose b
 *
 * So you need to find the minimum from all these options.
 * You can again use DFS here and use memoisation to improve the runtime
 */
public class Leetcode2646 {
    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        List<Integer>[] graph = buildTree(n, edges);

        int visitedCount[] = new int[n];
        for (int trip[] : trips) {
            makeTrip(-1, trip[0], trip[1], visitedCount, graph);
        }

        int contribution[] = new int[n];
        for (int i = 0; i < n; i++) {
            contribution[i] = visitedCount[i] * price[i];
        }

        int dp[][] = new int[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int seen[] = new int[n];
        return findMinPrice(0, -1, 1, dp, graph, contribution);
    }

    private List<Integer>[] buildTree(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        return graph;
    }

    private boolean makeTrip(int parent, int cur, int dst,
                             int visitedCount[], List<Integer>[] graph) {
        if (cur == dst) {
            visitedCount[cur]++;
            return true;
        }

        for (int next : graph[cur]) {
            if (next == parent) {
                continue;
            }
            boolean found = makeTrip(cur, next, dst, visitedCount, graph);
            if (found) {
                visitedCount[cur]++;
                return true;
            }
        }
        return false;
    }
    private int findMinPrice(int node, int parent, int canHalf,
                             int[][] dp, List<Integer>[] graph, int[] cost) {
        if (dp[node][canHalf] != -1) {
            return dp[node][canHalf];
        }
        int nonHalvedVal = cost[node];
        for (int next : graph[node]) {
            if (next == parent) {
                continue;
            }
            nonHalvedVal += findMinPrice(next, node, 1, dp, graph, cost);
        }
        int halvedVal = Integer.MAX_VALUE;
        if (canHalf == 1) {
            halvedVal = cost[node] / 2;
            for (int next : graph[node]) {
                if (next == parent) {
                    continue;
                }
                halvedVal += findMinPrice(next, node, 0, dp, graph, cost);
            }
        }
        return dp[node][canHalf] = Math.min(halvedVal, nonHalvedVal);
    }
}