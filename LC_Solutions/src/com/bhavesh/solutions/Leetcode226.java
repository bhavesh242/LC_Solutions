package com.bhavesh.solutions;

public class Leetcode226 {
public TreeNode invertTree(TreeNode root) {
        
        root = helper(root);
        return root;
        }
        public TreeNode helper(TreeNode root){
            if(root == null)
            {
                return null;
            }
            TreeNode left = helper(root.right);
            TreeNode right = helper(root.left);
            root.left = left;
            root.right = right;
            return root;
        } 
}
