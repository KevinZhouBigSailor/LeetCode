package leetCode;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by zzhou on 1/19/2018.
 */
public class SmallestRange_632 {
    public int[] smallestRange(List<List<Integer>> nums) {
        int minx = 0, miny = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int[] next = new int[nums.size()];
        PriorityQueue<Integer> min_queue = new PriorityQueue<Integer>((i, j) -> nums.get(i).get(next[i]) - nums.get(j).get(next[j]));
        for (int i = 0; i < nums.size(); i++) {
            min_queue.offer(i);
            max = Math.max(max, nums.get(i).get(0));
        }
        search:
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                int min_i = min_queue.poll();
                if (miny - minx > max - nums.get(min_i).get(next[min_i])) {
                    minx = nums.get(min_i).get(next[min_i]);
                    miny = max;
                }
                next[min_i]++;
                if (next[min_i] == nums.get(min_i).size()) {
                    break search;
                }
                min_queue.offer(min_i);
                max = Math.max(max, nums.get(min_i).get(next[min_i]));
            }
        }
        return new int[]{minx, miny};
    }
}
