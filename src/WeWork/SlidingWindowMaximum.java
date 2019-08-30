package WeWork;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] a, int k) {
        if (a == null || k <= 0) {
            return new int[0];
        }
        int n = a.length;
        int[] r = new int[n - k + 1];
        int ri = 0;
        // store index
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < a.length; i++) {
            // remove numbers out of range k
            while (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }
            // remove smaller numbers in k range as they are useless
            while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
                q.pollLast();
            }
            // q contains index... r contains content
            q.offer(i);
            if (i >= k - 1) {
                r[ri++] = a[q.peek()];
            }
        }
        return r;
    }

    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            if (n * k == 0) return new int[0];

            int[] output = new int[n - k + 1];
            for (int i = 0; i < n - k + 1; i++) {
                int max = Integer.MIN_VALUE;
                for (int j = i; j < i + k; j++)
                    max = Math.max(max, nums[j]);
                output[i] = max;
            }
            return output;
        }

        /**
         * For Example: A = [2,1,3,4,6,3,8,9,10,12,56], w=4
         * <p>
         * partition the array in blocks of size w=4. The last block may have less then w.
         * 2, 1, 3, 4 | 6, 3, 8, 9 | 10, 12, 56|
         * <p>
         * Traverse the list from start to end and calculate max_so_far. Reset max after each block boundary (of w elements).
         * left_max[] = 2, 2, 3, 4 | 6, 6, 8, 9 | 10, 12, 56
         * <p>
         * Similarly calculate max in future by traversing from end to start.
         * right_max[] = 4, 4, 4, 4 | 9, 9, 9, 9 | 56, 56, 56
         * <p>
         * now, sliding max at each position i in current window, sliding-max(i) = max{right_max(i), left_max(i+w-1)}
         * sliding_max = 4, 6, 6, 8, 9, 10, 12, 56
         */
        public int[] slidingWindowMax(final int[] in, final int w) {
            final int[] max_left = new int[in.length];
            final int[] max_right = new int[in.length];

            max_left[0] = in[0];
            max_right[in.length - 1] = in[in.length - 1];

            for (int i = 1; i < in.length; i++) {
                max_left[i] = (i % w == 0) ? in[i] : Math.max(max_left[i - 1], in[i]);

                final int j = in.length - i - 1;
                max_right[j] = (j % w == 0) ? in[j] : Math.max(max_right[j + 1], in[j]);
            }

            final int[] sliding_max = new int[in.length - w + 1];
            for (int i = 0, j = 0; i + w <= in.length; i++) {
                sliding_max[j++] = Math.max(max_right[i], max_left[i + w - 1]);
            }

            return sliding_max;
        }
    }
}
