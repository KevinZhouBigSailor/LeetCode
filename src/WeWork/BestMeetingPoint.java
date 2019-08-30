package WeWork;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BestMeetingPoint {
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = collectRows(grid);
        List<Integer> cols = collectCols(grid);
        int row = rows.get(rows.size() / 2);
        int col = cols.get(cols.size() / 2);
        return minDistance1D(rows, row) + minDistance1D(cols, col);
    }

    private int minDistance1D(List<Integer> points, int origin) {
        int distance = 0;
        for (int point : points) {
            distance += Math.abs(point - origin);
        }
        return distance;
    }

    private List<Integer> collectRows(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    rows.add(row);
                }
            }
        }
        return rows;
    }

    private List<Integer> collectCols(int[][] grid) {
        List<Integer> cols = new ArrayList<>();
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = 0; row < grid.length; row++) {
                if (grid[row][col] == 1) {
                    cols.add(col);
                }
            }
        }
        return cols;
    }

    class solution {
        public int minTotalDistance(int[][] grid) {
            int minDistance = Integer.MAX_VALUE;
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    int distance = search(grid, row, col);
                    minDistance = Math.min(distance, minDistance);
                }
            }
            return minDistance;
        }

        private int search(int[][] grid, int row, int col) {
            Queue<Point> q = new LinkedList<>();
            int m = grid.length;
            int n = grid[0].length;
            boolean[][] visited = new boolean[m][n];
            q.add(new Point(row, col, 0));
            int totalDistance = 0;
            while (!q.isEmpty()) {
                Point point = q.poll();
                int r = point.row;
                int c = point.col;
                int d = point.distance;
                if (r < 0 || c < 0 || r >= m || c >= n || visited[r][c]) {
                    continue;
                }
                if (grid[r][c] == 1) {
                    totalDistance += d;
                }
                visited[r][c] = true;
                q.add(new Point(r + 1, c, d + 1));
                q.add(new Point(r - 1, c, d + 1));
                q.add(new Point(r, c + 1, d + 1));
                q.add(new Point(r, c - 1, d + 1));
            }
            return totalDistance;
        }

        public class Point {
            int row;
            int col;
            int distance;

            public Point(int row, int col, int distance) {
                this.row = row;
                this.col = col;
                this.distance = distance;
            }
        }
    }
}
