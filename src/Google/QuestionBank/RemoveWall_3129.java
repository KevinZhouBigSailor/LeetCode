package Google.QuestionBank;

import java.util.*;

public class RemoveWall_3129 {
    // TODO: Think about bfs

    private final int UNVISITED = 0;
    private final int REACHABLE_FROM_SRC = 1;
    private final int REACHABLE_FROM_DEST = 2;

    private final int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public boolean RunDFS_SRC(int n, int[][] matrix, int[][] mark, int r, int c) {
        if (r < 0 || c < 0) return false;
        if (r >= n || c >= n) return false;

        // no demolition needed
        if (r == n - 1 && c == n - 1) return true;
        // already visited
        if (mark[r][c] == REACHABLE_FROM_SRC) return false;
        // mark as visited from src, even if it is a wall
        mark[r][c] = REACHABLE_FROM_SRC;
        // no need to traverse, if it is a wall
        if (matrix[r][c] == 1) return false;
        for (int[] dir : directions) {
            if (RunDFS_SRC(n, matrix, mark, r + dir[0], c + dir[1])) return true;
        }
        return false;
    }

    public boolean RunDFS_DEST(int n, int[][] matrix, int[][] mark, int r, int c) {
        if (r < 0 || c < 0) return false;
        if (r >= n || c >= n) return false;

        // already visited by the first traverse
        if (mark[r][c] == REACHABLE_FROM_SRC) return true;
        // already visited by the second traverse
        if (mark[r][c] == REACHABLE_FROM_DEST) return false;
        // no need to traverse, if it is a wall
        if (matrix[r][c] == 1) return false;
        // mark as visited from src, even if it is a wall
        mark[r][c] = REACHABLE_FROM_DEST;
        for (int[] dir : directions) {
            if (RunDFS_DEST(n, matrix, mark, r + dir[0], c + dir[1])) return true;
        }
        return false;
    }

    public boolean isReachable(int[][] matrix, int n) {
        int[][] mark = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mark[i][j] = UNVISITED;
            }
        }
        if (RunDFS_SRC(n, matrix, mark, 0, 0)) return true;
        return RunDFS_DEST(n, matrix, mark, n - 1, n - 1);
    }
}
