package Facebook;

import java.util.*;

public class DiagonalTraverse_498 {
    /**
     * https://leetcode.com/problems/diagonal-traverse/discuss/581868/Easy-Python-NO-DIRECTION-CHECKING
     * For example, in
     * <p>
     * [1,2,3]
     * [4,5,6]
     * [7,8,9]
     * 2, 4 are on the same diagonal, and they share the index sum of 1. (2 is matrix[0][1] and 4 is in matrix[1][0]). 3,5,7 are on the same diagonal, and they share the sum of 2. (3 is matrix[0][2], 5 is matrix[1][1], and 7 is matrix [2][0]).
     * <p>
     * SO, if you can loop through the matrix, store each element by the sum of its indices in a dictionary, you have a collection of all elements on shared diagonals.
     * <p>
     * The last part is easy, build your answer (a list) by elements on diagonals. To capture the 'zig zag' or 'snake' phenomena of this problem, simply reverse ever other diagonal level. So check if the level is divisible by 2.
     */
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new int[0];
        int N = matrix.length;
        int M = matrix[0].length;
        int[] res = new int[N * M];
        Map<Integer, List<Integer>> dict = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int sum = i + j;
                if (!dict.containsKey(sum)) {
                    dict.put(sum, new ArrayList<>());
                }
                dict.get(sum).add(matrix[i][j]);
            }
        }
        int ctr = 0;
        for (Map.Entry<Integer, List<Integer>> entry : dict.entrySet()) {
            List<Integer> temp_list = new ArrayList<>();
            if (entry.getKey() % 2 == 0) {
                temp_list = entry.getValue();
                Collections.reverse(temp_list);
            } else {
                temp_list = entry.getValue();
            }
            for (int i = 0; i < temp_list.size(); i++) {
                res[ctr++] = temp_list.get(i);
            }
        }
        return res;
    }
}
