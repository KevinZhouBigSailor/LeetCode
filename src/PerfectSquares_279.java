import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by zzhou on 3/19/2018.
 */
public class PerfectSquares_279 {
    public int numSquares(int n) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(0);
        visited.add(0);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            while (size-- > 0) {
                int u = queue.poll();
                for (int i = 1; i * i <= n; i++) {
                    int v = u + i * i;
                    if (v == n) return depth;
                    if (v > n) break;
                    if (!visited.contains(v)) {
                        queue.offer(v);
                        visited.add(v);
                    }
                }
            }
        }
        return depth;
    }
}
