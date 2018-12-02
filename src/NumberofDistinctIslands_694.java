package leetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zzhou on 1/17/2018.
 */
public class NumberofDistinctIslands_694 {
    private static final int[][] directions = new int[][]{{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    int[][] grid;
    boolean[][] seen;
    ArrayList<Integer> shape;

    public void explore(int r, int c, int di) {
        if (0 <= r && r < grid.length && 0 <= c && c < grid[0].length && grid[r][c] == 1 && !seen[r][c]) {
            seen[r][c] = true;
            shape.add(di);
            int i = 0;
            for (int[] dir : directions) {
                explore(r + dir[0], c + dir[1], ++i);
            }
            shape.add(0);
        }
    }

    public int numDistinctIslands(int[][] grid) {
        this.grid = grid;
        seen = new boolean[grid.length][grid[0].length];
        Set shapes = new HashSet<ArrayList<Integer>>();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                shape = new ArrayList<Integer>();
                explore(r, c, 0);
                if (!shape.isEmpty()) shapes.add(shape);
            }
        }
        return shapes.size();
    }

    public static void main(String[] args) {
        NumberofDistinctIslands_694 instance = new NumberofDistinctIslands_694();
        int[][] testGrid = new int[][]{{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        instance.numDistinctIslands(testGrid);
    }
}
