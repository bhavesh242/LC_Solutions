package com.bhavesh.solutions;

public class Leetcode742 {
	
	int distance= Integer.MAX_VALUE;
    int node = -1;
    public int findClosestLeaf(TreeNode root, int k) {
        findK(root, k);
        return node;
    }
    
    public int findK(TreeNode root, int k)
    {
        if(root == null)
        {
            return -1;
        }
        if(root.val == k)
        {
            findClosest(root, 0);
            return 1;
        }
        int left = findK(root.left,k);
        if(left > 0)
        {
            findClosest(root.right,++left);
        }
        int right = findK(root.right,k);
        if(right > 0)
        {
            findClosest(root.left, ++right);
        }
        
        return Math.max(left, right);
    }
    
    public void findClosest(TreeNode root, int depth)
    {
        if(root == null)
        {
            return;
        }
        if(root.left ==null && root.right==null)
        {
            if(depth < distance)
            {
                distance = depth;
                node = root.val;
            }
        }
        findClosest(root.left,depth+1);
        findClosest(root.right,depth+1);
    }
}

