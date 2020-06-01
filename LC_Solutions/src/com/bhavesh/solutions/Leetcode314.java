package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Leetcode314 {
	 public List<List<Integer>> verticalOrder(TreeNode root) {
	        List<List<Integer>> res = new ArrayList<List<Integer>>();
	        if(root == null){
	          return res;   
	        }
	        Map<Integer, ArrayList<Integer>> map = new HashMap();
	        Queue<Node> queue = new LinkedList<Node>();
	        queue.offer(new Node(root,0));
	        int min = 0;
	        int max = 0;
	        while(!queue.isEmpty()){
	            Node N = queue.poll();
	            TreeNode node = N.node;
	            int level = N.horiz;
	            map.putIfAbsent(level, new ArrayList<Integer>());
	            map.get(level).add(node.val);
	            if(node.left != null){
	                queue.add(new Node(node.left, level-1));
	                min = Math.min(min,level-1);
	            }
	            if(node.right != null){
	                queue.add(new Node(node.right, level+1));
	                max = Math.max(max,level+1);
	            }
	        }
	        
	        for(int i=min;i<=max;i++){
	            res.add(map.get(i));
	        }
	            
	        return res;            
	            
	        }
	 class Node{
		    TreeNode node;
		    int horiz;
		    Node(TreeNode root, int h)
		    {
		        node = root;
		        horiz = h;      
		    }
		}

}

	

