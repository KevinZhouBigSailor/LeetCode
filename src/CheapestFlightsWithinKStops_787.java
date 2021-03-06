import java.util.*;

/**
 * Created by zzhou on 3/29/2018.
 */
public class CheapestFlightsWithinKStops_787 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        /*int[][] dist = new int[2][n];
        int INF = Integer.MAX_VALUE / 2;
        Arrays.fill(dist[0], INF);
        Arrays.fill(dist[1], INF);
        dist[src][0] = dist[0][src] = 0;

        for (int i = 0; i <= K; i++) {
            for (int[] flight : flights) {
                dist[i & 1][flight[1]] = Math.min(dist[i & 1][flight[1]], dist[~i & 1][flight[0]] + flight[2]);
            }
        }
        return dist[K & 1][dst] < INF ? dist[K & 1][dst] : -1;*/
        int[][] graph = new int[n][n];
        for (int[] flight : flights)
            graph[flight[0]][flight[1]] = flight[2];

        Map<Integer, Integer> best = new HashMap();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0, src});

        while (!pq.isEmpty()) {
            int[] info = pq.poll();
            int cost = info[0], k = info[1], place = info[2];
            if (k > K + 1 || cost > best.getOrDefault(k * 1000 + place, Integer.MAX_VALUE))
                continue;
            if (place == dst)
                return cost;

            for (int nei = 0; nei < n; ++nei)
                if (graph[place][nei] > 0) {
                    int newcost = cost + graph[place][nei];
                    if (newcost < best.getOrDefault((k + 1) * 1000 + nei, Integer.MAX_VALUE)) {
                        pq.offer(new int[]{newcost, k + 1, nei});
                        best.put((k + 1) * 1000 + nei, newcost);
                    }
                }
        }
        return -1;
    }
}
