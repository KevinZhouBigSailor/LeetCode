import java.awt.*;
import java.util.*;
import java.util.List;

public class BusRoutes_815 {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) return 0;
        int N = routes.length;

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Arrays.sort(routes[i]);
            graph.add(new ArrayList<>());
        }
        Set<Integer> seens = new HashSet<>();
        Set<Integer> targets = new HashSet<>();
        Queue<Point> queue = new ArrayDeque<>();

        for (int i = 0; i < N; ++i)
            for (int j = i + 1; i < N; ++j)
                if (intersect(routes[i], routes[j])) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }

        for (int i = 0; i < N; i++) {
            if (Arrays.binarySearch(routes[i], S) >= 0) {
                seens.add(i);
                queue.offer(new Point(i, 0));
            }
            if (Arrays.binarySearch(routes[i], T) >= 0)
                targets.add(i);
        }

        while (!queue.isEmpty()) {
            Point info = queue.poll();
            int node = info.x;
            int depth = info.y;
            if (targets.contains(node)) return depth + 1;
            for (Integer nei : graph.get(node)) {
                if (!seens.contains(nei)) {
                    seens.add(nei);
                    queue.offer(new Point(nei, depth + 1));
                }
            }
        }

        return -1;
    }

    public boolean intersect(int[] A, int[] B) {
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            if (A[i] == B[j]) return true;
            if (A[i] < B[j]) i++;
            else j++;
        }
        return false;
    }
}
