package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Leetcode207 {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
        /*Create a Array of ArrayLists which acts as graph, storing edges from a course to all                     courses for which it is needed as a prerequisite*/
        ArrayList<Integer> []  graph = new ArrayList[numCourses];
        //Create a graph to store indegrees for each node, we are doing a topological sort with BFS
        int indegree[] = new int[numCourses];
        int count = 0;
        for(int p[] : prerequisites){
            if(graph[p[1]] == null){
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(p[0]);
                graph[p[1]] = list;
            }
            else{
                graph[p[1]].add(p[0]);
            }
            indegree[p[0]]++;
        }
        //Take a queue of Nodes for BFS
        Queue<Integer> nodes = new LinkedList();
        //Initially, Add all nodes that have an indegree of 0 to the queue
        for(int i=0;i<numCourses;i++){
            if(indegree[i] == 0){
                nodes.add(i);
            }
        }
        //Start BFS
        while(!nodes.isEmpty()){
            //Take an element off the queue
            int course = nodes.remove();
            //If indegree of removed course is 0, add count by 1
            if(indegree[course] == 0){
                count++;
            }
            /* "Remove" that nodes from the graph and decrease the indegree of each of 
            it's connected nodes by 1*/
            if(graph[course] != null){
                for(int x : graph[course]){
                    if(--indegree[x] == 0){
                    nodes.add(x);   
                    }
                }
            }
        }
        /*if at the end of the bfs, all courses have indegree of 0, it implies no presence
        of a cycle, thus the couse schedule is valid, else invalid*/
        return count == numCourses;
    }
}
