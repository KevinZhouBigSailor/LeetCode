package Facebook;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class KClosestPointsToOrigin_973 {
    public int[][] kClosest(int[][] points, int K) {
        int len = points.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = helper(points, l, r);
            if (mid == K) break;
            if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private int helper(int[][] A, int l, int r) {
        int[] pivot = A[l];
        while (l < r) {
            while (l < r && compare(A[r], pivot) >= 0) r--;
            A[l] = A[r];
            while (l < r && compare(A[l], pivot) <= 0) l++;
            A[r] = A[l];
        }
        A[l] = pivot;
        return l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }

    // Alternative
    class solution {
        int[][] points;

        public int[][] kClosest(int[][] points, int K) {
            this.points = points;
            sort(0, points.length - 1, K);
            return Arrays.copyOfRange(points, 0, K);
        }

        public void sort(int i, int j, int K) {
            if (i >= j) return;
            int k = ThreadLocalRandom.current().nextInt(i, j);
            swap(i, k);

            int mid = partition(i, j);
            int leftLength = mid - i + 1;
            if (K < leftLength)
                sort(i, mid - 1, K);
            else if (K > leftLength)
                sort(mid + 1, j, K - leftLength);
        }

        public int partition(int i, int j) {
            int oi = i;
            int pivot = dist(i);
            i++;

            while (true) {
                while (i < j && dist(i) < pivot)
                    i++;
                while (i <= j && dist(j) > pivot)
                    j--;
                if (i >= j) break;
                swap(i, j);
            }
            swap(oi, j);
            return j;
        }

        public int dist(int i) {
            return points[i][0] * points[i][0] + points[i][1] * points[i][1];
        }

        public void swap(int i, int j) {
            int t0 = points[i][0], t1 = points[i][1];
            points[i][0] = points[j][0];
            points[i][1] = points[j][1];
            points[j][0] = t0;
            points[j][1] = t1;
        }
    }

    class Solution2 {
        public int[][] kClosest(int[][] points, int k) {
            // Use a lambda comparator to sort the PQ by farthest distance
            Queue<int[]> maxPQ = new PriorityQueue<>((a, b) -> b[0] - a[0]);
            for (int i = 0; i < points.length; i++) {
                int[] entry = {squaredDistance(points[i]), i};
                if (maxPQ.size() < k) {
                    // Fill the max PQ up to k points
                    maxPQ.add(entry);
                } else if (entry[0] < maxPQ.peek()[0]) {
                    // If the max PQ is full and a closer point is found,
                    // discard the farthest point and add this one
                    maxPQ.poll();
                    maxPQ.add(entry);
                }
            }

            // Return all points stored in the max PQ
            int[][] answer = new int[k][2];
            for (int i = 0; i < k; i++) {
                int entryIndex = maxPQ.poll()[1];
                answer[i] = points[entryIndex];
            }
            return answer;
        }

        private int squaredDistance(int[] point) {
            // Calculate and return the squared Euclidean distance
            return point[0] * point[0] + point[1] * point[1];
        }
    };
}
