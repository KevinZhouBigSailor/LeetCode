package leetCode;

import java.util.*;

/**
 * Created by zzhou on 1/17/2018.
 */
public class NumberofDistinctIslandsII_711 {
    final int[][] directions = new int[][]{{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    final int[][] trans={{1,1}, {1,-1}, {-1,1}, {-1,-1}};
    int[][] grid;
    boolean[][] seen;
    ArrayList<int[]> cells;

    public void explore(int r, int c, List<int[]> shape) {
        if (0 <= r && r < grid.length && 0 <= c && c < grid[0].length && grid[r][c] == 1 && !seen[r][c]) {
            seen[r][c] = true;
            shape.add(new int[]{r, c});
            for (int[] dir : directions) {
                explore(r + dir[0], c + dir[1], shape);
            }
        }
    }

    private String norm(List<int[]> cells){
        List<String> forms=new ArrayList<>();
        // generate the 8 different transformations
        // (x, y), (x, -y), (-x, y), (-x, -y)
        // (y, x), (-y, x), (y, -x), (-y, -x)
        for (int[] ts:trans){
            List<int[]> list1=new ArrayList<>();
            List<int[]> list2=new ArrayList<>();
            for (int[] cell:cells){
                list1.add(new int[]{cell[0]*ts[0], cell[1]*ts[1]});
                list2.add(new int[]{cell[1]*ts[1], cell[0]*ts[0]});
            }
            forms.add(getKey(list1));
            forms.add(getKey(list2));
        }

        // sort the keys: take the first one as the representative key
        Collections.sort(forms);
        return forms.get(0);
    }

    private String getKey(List<int[]>cells){
        // sort the cells before generate the key
        Collections.sort(cells, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0]!=b[0]){
                    return a[0]-b[0];
                }else{
                    return a[1]-b[1];
                }
            }
        });

        StringBuilder sb=new StringBuilder();
        int x=cells.get(0)[0], y=cells.get(0)[1];
        for (int[] cell:cells)
            sb.append((cell[0]-x)+":"+(cell[1]-y)+":");

        return sb.toString();
    }

    public int numDistinctIslands2(int[][] grid) {
        this.grid = grid;
        seen = new boolean[grid.length][grid[0].length];
        Set shapes = new HashSet<String>();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                cells = new ArrayList<int[]>();
                explore(r, c, cells);
                if (!cells.isEmpty()) shapes.add(norm(cells));
            }
        }
        return shapes.size();
    }

    public static void main(String[] args) {
        NumberofDistinctIslandsII_711 instance = new NumberofDistinctIslandsII_711();
        int[][] testGrid = new int[][]{{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        instance.numDistinctIslands2(testGrid);
    }
}
