package com.bhavesh.solutions;

public class Leetcode48 {
	public void rotate(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        //Convert the matrix into it's transpose
        for(int i=0;i<R;i++){
            for(int j=0;j<i;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp; 
            }
        }
        
        //Reverse each row
        for(int i=0;i<R;i++){
            for(int j=0;j<C/2;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][C-1-j];
                matrix[i][C-1-j] = temp;
            }
        }
    }
}
