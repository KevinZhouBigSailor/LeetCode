package LinkedIn;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by zzhou on 4/2/2018.
 */
public class FindKPairswithSmallestSums {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> ret = new ArrayList<>();
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return ret;
        int len1 = nums1.length, len2 = nums2.length;
        PriorityQueue<Pair> pq = new PriorityQueue(k, new CompPair());
        for (int i = 0; i < nums1.length && i < k; i++) { // only need first k number in nums1 to start
            pq.offer(new Pair(0, nums1[i], nums2[0]));
        }
        for (int i = 1; i <= k && !pq.isEmpty(); i++) { // get the first k sums
            Pair p = pq.poll();
            ret.add(p.pair);
            if (p.idx + 1 < len2) { // get to next value in nums2
                int next = p.idx + 1;
                pq.offer(new Pair(next, p.pair[0], nums2[next]));
            }
        }
        return ret;
    }

    class Pair {
        int[] pair;
        int idx; // current index to nums2
        long sum;

        Pair(int idx, int n1, int n2) {
            this.idx = idx;
            pair = new int[]{n1, n2};
            sum = (long) n1 + (long) n2;
        }
    }

    class CompPair implements Comparator<Pair> {
        public int compare(Pair p1, Pair p2) {
            return Long.compare(p1.sum, p2.sum);
        }
    }

    public List<int[]> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        List<int[]> list = new ArrayList();
        int m = nums1.length;
        int n = nums2.length;
        if (m == 0 || n == 0 || k < 1) return list;

        PriorityQueue<Data> heap = new PriorityQueue<Data>((a, b) -> (a.val - b.val));

        heap.offer(new Data(0, 0, nums1[0] + nums2[0]));

        while (!heap.isEmpty() && k > 0) {
            Data d = heap.poll();

            int[] pair = {nums1[d.i], nums2[d.j]};
            list.add(pair);
            k--;

            //add the next column item
            if (d.j < n - 1) {
                heap.offer(new Data(d.i, d.j + 1, nums1[d.i] + nums2[d.j + 1]));
            }
            // always store the next row (smallest)
            if (d.j == 0 && d.i < m - 1) {
                heap.offer(new Data(d.i + 1, 0, nums1[d.i + 1] + nums2[0]));
            }
        }
        return list;
    }

    class Data {
        int i;
        int j;
        int val;

        public Data(int i, int j, int val) {
            this.i = i;
            this.j = j;
            this.val = val;
        }
    }
}
