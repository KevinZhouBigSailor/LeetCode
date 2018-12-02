import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle_118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> allrows = new ArrayList<List<Integer>>();
        List<Integer> row = new ArrayList<>();
        for (int j = 0; j < numRows; j++) {
            row.add(0, 1);
            for (int i = 1; i < row.size() - 1; i++) {
                row.set(i, row.get(i) + row.get(i + 1));
            }
            allrows.add(new ArrayList<>(row));
        }
        return allrows;
    }
}
