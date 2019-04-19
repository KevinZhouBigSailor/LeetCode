package Twitter;

import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedianFromDataStream {
    /**
     * initialize your data structure here.
     */
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;

    public void MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        if (maxHeap.size() >= minHeap.size() + 2) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() >= maxHeap.size() + 2) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            return (double) (minHeap.peek() + maxHeap.peek()) / 2;
        } else if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else {
            return maxHeap.peek();
        }
    }
}
