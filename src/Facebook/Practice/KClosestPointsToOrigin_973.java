package Facebook.Practice;

import java.util.*;

public class KClosestPointsToOrigin_973 {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        for (int i = 0; i < points.length; i++) {
            int[] entry = {squaredDistance(points[i]), 1};
            if (maxHeap.size() < K) {
                maxHeap.offer(entry);
            } else if (entry[0] < maxHeap.peek()[0]) {
                maxHeap.poll();
                maxHeap.offer(entry);
            }
        }

        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            int entryIndex = maxHeap.poll()[1];
            res[i] = points[entryIndex];
        }
        return res;
    }

    private int squaredDistance(int[] point) {
        // Calculate and return the squared Euclidean distance
        return point[0] * point[0] + point[1] * point[1];
    }
}
