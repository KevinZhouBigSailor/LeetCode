package Facebook;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zzhou on 2/23/2018.
 */
public class ShortestDistancefromAllBuildings_317 {
    int[] dx = new int[]{0, 0, -1, 1};
    int[] dy = new int[]{1, -1, 0, 0};

    public int shortestDistance(int[][] grid) {

        if (grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        int m = grid.length;
        int n = grid[0].length;
        int[][] distance = new int[m][n];  //accumulated distance of each house (1) to each 0
        int[][] reachCount = new int[m][n]; //count reachable house for each 0
        int houseCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    houseCount++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (!bfs(grid, distance, reachCount, houseCount, m, n, i, j)) {
                        return -1;
                    }
                }
            }
        }
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && reachCount[i][j] == houseCount) {
                    minDistance = Math.min(minDistance, distance[i][j]);
                }
            }
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;

    }

    private boolean bfs(int[][] grid, int[][] distance, int[][] reachCount, int houseCount, int m, int n, int sr, int sc) {
        int[][] seen = new int[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sr, sc});
        int level = 0;
        int count = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            level++;
            for (int i = 0; i < size; i++) {//level by level
                int[] curr = q.poll();
                for (int di = 0; di < 4; di++) { //visit all neighbors & accumulate distance
                    int nr = curr[0] + dx[di];
                    int nc = curr[1] + dy[di];
                    if (nr >= 0 && nc >= 0 && nr < m && nc < n && seen[nr][nc] == 0) {
                        if (grid[nr][nc] == 0) {
                            distance[nr][nc] += level;
                            seen[nr][nc] = 1;
                            reachCount[nr][nc]++;
                            q.offer(new int[]{nr, nc});
                        } else if (grid[nr][nc] == 1) {
                            count++;
                            seen[nr][nc] = 1;
                        }
                    }
                }
            }
        }
        return count == houseCount;
    }
}
