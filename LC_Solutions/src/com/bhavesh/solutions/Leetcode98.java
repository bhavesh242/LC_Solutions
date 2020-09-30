package com.bhavesh.solutions;

public class Leetcode98 {
	public boolean isValidBST(TreeNode root) {
        /*Applying standard rules of BST. All nodes on left of a node should be smaller than that node and 
        all nodes right than that node should be bigger in value */
        return validateBSTWithBounds(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    //We use long because there can be nodes with values equal to max and min integer values
    public boolean validateBSTWithBounds(TreeNode root, long lower, long upper){
        if(root == null){
            return true;
        }
        if(root.val <=lower || root.val >=upper){
            return false;
        }
        
        return validateBSTWithBounds(root.left, lower, root.val) && validateBSTWithBounds(root.right, root.val, upper); 
        
    }
}