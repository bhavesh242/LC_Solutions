package com.bhavesh.solutions;

public class Leetcode779 {
	public int kthGrammar(int N, int K) {
		// map k all the way back to level 1
		if (N == 1) {
			return 0;
		}
		// Parent would be on previous level
//         int parent = kthGrammar(N-1, (K+1)/2) ; 

//         //If parent 1 is then digit at K is one if K is odd else 0
//         if(parent == 1)
//         {
//             return K%2;
//         }
//         //If parent is 0 then digit at K is zero if K is odd else 1
//         else
//         {
//             return (K+1)%2;
//         }
		return (K % 2) == kthGrammar(N - 1, (K + 1) / 2) ? 1 : 0;
		// Can also use negation of xor
	}
}