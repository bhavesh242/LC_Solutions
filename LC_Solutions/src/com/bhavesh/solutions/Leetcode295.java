package com.bhavesh.solutions;

import java.util.Collections;
import java.util.PriorityQueue;

public class Leetcode295 {

    /**
     * Solution : We use 2 heaps, a min-heap and max-heap.
     * The idea is that we will always keep these 2 heaps balanced by exchanging top elements when
     * new numbers are added from the data stream, so that min-heap and max-heap will always have
     * the middle elements on top. If (min-heap size + max-heap size) is even, answer is mean of top elements of both
     * heaps, otherwise top of minHeap
     */
    class MedianFinder {

        PriorityQueue<Integer> minHeap;
        PriorityQueue<Integer> maxHeap;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {

            minHeap = new PriorityQueue<Integer>();
            maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        }

        public void addNum(int num) {

            minHeap.add(num);
            maxHeap.add(minHeap.poll());

            if (minHeap.size() < maxHeap.size()) {
                minHeap.add(maxHeap.poll());
            }
        }

        public double findMedian() {

            if (minHeap.size() > maxHeap.size()) {
                return (double) minHeap.peek();
            } else {
                return (minHeap.peek() + maxHeap.peek()) * 0.5;
            }
        }
    }
}
