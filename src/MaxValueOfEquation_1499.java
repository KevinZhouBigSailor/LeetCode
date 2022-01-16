import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/max-value-of-equation/discuss/709264/Java-Max-Heap-O(nlogn)-and-Monotone-Queue-O(n)
 */

public class MaxValueOfEquation_1499 {
    public int findMaxValueOfEquation(int[][] points, int k) {
        int result = Integer.MIN_VALUE;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[1] - b[0] - (a[1] - a[0])));
        for (int[] point : points) {
            while (!pq.isEmpty() && point[0] - pq.peek()[0] > k) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                int[] head = pq.peek();
                result = Math.max(result, point[1] + head[1] + point[0] - head[0]);
            }
            pq.offer(point);
        }

        return result;
    }

    public int findMaxValueOfEquation2(int[][] points, int k) {
        int result = Integer.MIN_VALUE;
        Deque<int[]> list = new LinkedList<>();
        for (int[] point : points) {
            while (list.size() > 0 && point[0] - list.getFirst()[0] > k) {
                list.pollFirst();
            }
            if (list.size() > 0) {
                int curVal = point[1] - point[0];
                result = Math.max(result, point[0] + point[1] + list.getFirst()[1] - list.getFirst()[0]);
                while (list.size() > 0 && (list.getLast()[1] - list.getLast()[0]) <= curVal) {
                    list.pollLast();
                }
            }
            list.offer(point);
        }

        return result;
    }
}