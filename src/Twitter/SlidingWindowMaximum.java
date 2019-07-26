package Twitter;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int len = nums.length;
        int[] r = new int[len - k + 1];
        int rIdx = 0;
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            while (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }

            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            q.offer(i);
            if (i >= k - 1) {
                r[rIdx++] = nums[q.peekFirst()];
            }
        }

        return r;
    }

}
