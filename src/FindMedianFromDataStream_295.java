import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedianFromDataStream_295 {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public void MedianFinder() {
        minHeap = new PriorityQueue<Integer>();
        maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
    }

    // Adds a number into the data structure.
    public void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }
        if (minHeap.size() >= maxHeap.size() + 2) {
            maxHeap.add(minHeap.remove());
        } else if (maxHeap.size() >= minHeap.size() + 2) {
            minHeap.add(maxHeap.remove());
        }
    }

    // Returns the median of current data stream
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
