package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by zzhou on 1/18/2018.
 */
public class TrappingRainWaterII_407 {
    /*int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};*/

    final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    List<int[]>[] graph;
    int start;

    private int[] dijkstra() {
        int[] dist = new int[graph.length];
        boolean[] visited = new boolean[graph.length];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        PriorityQueue<int[]> queue = new PriorityQueue<>((u, v) -> u[1] - v[1]);
        queue.add(new int[]{start, 0});
        dist[start] = 0;
        while (!queue.isEmpty()) {
            int curVertex = queue.peek()[0], curWeight = queue.poll()[1];
            if (visited[curVertex]) continue;

            visited[curVertex] = true;
            for (int[] edge : graph[curVertex]) {
                int vertex = edge[0], weight = edge[1];
                if (Math.max(curWeight, weight) < dist[vertex]) {
                    queue.add(new int[]{vertex, Math.max(curWeight, weight)});
                    dist[vertex] = Math.max(curWeight, weight);
                }
            }
        }
        return dist;
    }

    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) return 0;
        int row = heightMap.length, col = heightMap[0].length;

        start = row * col;
        graph = new List[row * col + 1];
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) graph[start].add(new int[]{i * col + j, 0}); //boundary
                for (int[] dir : directions) {
                    int x = i + dir[0], y = j + dir[1];
                    if (x >= 0 && x < row && y >= 0 && y < col) graph[i * col + j].add(new int[]{x * col + y, heightMap[i][j]});
                }
            }

        int ans = 0;
        int[] dist = dijkstra();
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                int cb = dist[i * col + j];
                if (cb > heightMap[i][j]) ans += cb - heightMap[i][j];
            }

        return ans;
    }
}
