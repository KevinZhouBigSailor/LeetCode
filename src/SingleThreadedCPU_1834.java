import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/single-threaded-cpu/discuss/1164102/Java:-sort-by-time-and-use-PQ
 */

public class SingleThreadedCPU_1834 {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[] ans = new int[n];
        int[][] extTasks = new int[n][3];
        for (int i = 0; i < n; i++) {
            extTasks[i][0] = i;
            extTasks[i][1] = tasks[i][0];
            extTasks[i][2] = tasks[i][1];
        }
        /**
         * Just the heads up for interview guys. Always try to use compare method instead of subtracting cause it might overflow.
         * Like Integer.compare(a, b) instead of a - b in the Comparator
         */
        Arrays.sort(extTasks, (a, b) -> a[1] - b[1]);
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[2] == b[2] ? a[0] - b[0] : a[2] - b[2]);
        int time = 0;
        int ai = 0;
        int ti = 0;
        while (ai < n) {
            while (ti < n && extTasks[ti][1] <= time) {
                pq.offer(extTasks[ti++]);

            }
            if (pq.isEmpty()) {
                time = extTasks[ti][1];
                continue;
            }
            int[] bestFit = pq.poll();
            ans[ai++] = bestFit[0];
            time += bestFit[2];
        }
        return ans;
    }
}
