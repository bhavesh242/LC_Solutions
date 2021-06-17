package com.bhavesh.solutions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Leetcode352 {
	class SnakeGame {

		/**
		 * Initialize your data structure here.
		 * 
		 * @param width  - screen width
		 * @param height - screen height
		 * @param food   - A list of food positions E.g food = [[1,1], [1,0]] means the
		 *               first food is positioned at [1,1], the second is at [1,0].
		 */
		LinkedList<Integer> snake;
		int W;
		int H;
		Set<Integer> exists;
		int foodPos;
		int[][] food;
		int score;

		public SnakeGame(int width, int height, int[][] food) {
			this.food = food;
			this.W = width;
			this.H = height;
			snake = new LinkedList<Integer>();
			exists = new HashSet<Integer>();
			this.foodPos = 0;
			this.score = 0;
			// Add head to the snake
			// Since 0 is the hash of our initial position
			snake.add(0);
		}

		/**
		 * Moves the snake.
		 * 
		 * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
		 * @return The game's score after the move. Return -1 if game over. Game over
		 *         when snake crosses the screen boundary or bites its body.
		 */
		public int move(String direction) {
			/// Get the head of the snake
			int head = snake.peekFirst();
			int row = head / W;
			int col = head % W;
			// Change the head according to the movement direction
			if (direction.equals("U")) {
				row -= 1;
			} else if (direction.equals("D")) {
				row += 1;
			} else if (direction.equals("L")) {
				col -= 1;
			} else if (direction.equals("R")) {
				col += 1;
			}
			// If new head position is out of bounds, return -1;
			if (row < 0 || row >= H || col < 0 || col >= W) {
				return score = -1;
			}

			// Otherwise we check if we need to remove tail or add score according to the
			// availibility of score.
			// Check for food
			if (foodPos < food.length && row == food[foodPos][0] && col == food[foodPos][1]) {
				score++;
				foodPos++;
			} else {
				int tail = snake.pollLast();
				exists.remove(tail);
			}

			int newhead = row * W + col;
			if (exists.contains(newhead)) {
				return score = -1;
			}
			exists.add(newhead);
			snake.addFirst(newhead);
			return score;
		}
	}

	/**
	 * Your SnakeGame object will be instantiated and called as such: SnakeGame obj
	 * = new SnakeGame(width, height, food); int param_1 = obj.move(direction);
	 */
}
