package com.bhavesh.solutions;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode958 {

	class Solution {
	     public boolean isCompleteTree(TreeNode root) {
	     
	         Queue<TreeNode> queue = new LinkedList<TreeNode>();
	         boolean childReached = false;
	         queue.add(root);
	         while(!queue.isEmpty()){
	             int size = queue.size();
	             for(int i=0;i<size;i++)
	             {
	                 TreeNode cur = queue.poll();
	                 if(cur == null){
	                     childReached =true;
	                 }
	                 else
	                 {
	                     if(childReached){
	                         return false;
	                     }
	                     queue.add(cur.left);
	                     queue.add(cur.right);
	                 }
	             }
	         }
	         return true;
	     }
	}
}
