package Facebook;

import java.util.*;

public class ToeplitzMatrix_766 {
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int r = 0; r < matrix.length; ++r)
            for (int c = 0; c < matrix[0].length; ++c)
                if (r > 0 && c > 0 && matrix[r - 1][c - 1] != matrix[r][c])
                    return false;
        return true;
    }

    // For follow up 1 & 2
    // https://leetcode.com/problems/toeplitz-matrix/discuss/271388/Java-Solution-for-Follow-Up-1-and-2
    /*
    For the follow-up 1, we are only able to load one row at one time, so we have to use a buffer (1D data structure) to store the row info. When next row comes as a streaming flow, we can compare each value (say, matrix[i][j], i>=1, j>=1) to the "upper-left" value in the buffer (buffer[j - 1]); meanwhile, we update the buffer by inserting the 1st element of the current row (matrix[i][0]) to buffer at position 0 (buffer[0]), and removing the last element in the buffer.
     */
    public boolean isToeplitzMatrix2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return true;
        int row = matrix.length;
        int col = matrix[0].length;
        List<Integer> buffer = new LinkedList<>();
        for (int j = 0; j < col; j++) buffer.add(matrix[0][j]);
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (buffer.get(j - 1) != matrix[i][j]) return false;
            }
            buffer.remove(buffer.size() - 1);
            buffer.add(0, matrix[i][0]);
        }
        return true;
    }

    /*
    For the follow-up 2, we can only load a partial row at one time. We could split the whole matrix vertically into several sub-matrices, while each sub-matrix should have one column overlapped. We repeat the same method in follow-up 1 for each sub-matrix.

For example:

[1 2 3 4 5 6 7 8 9 0]              [1 2 3 4]              [4 5 6 7]              [7 8 9 0]
[0 1 2 3 4 5 6 7 8 9]              [0 1 2 3]              [3 4 5 6]              [6 7 8 9]
[1 0 1 2 3 4 5 6 7 8]     -->      [1 0 1 2]       +      [2 3 4 5]       +      [5 6 7 8]
[2 1 0 1 2 3 4 5 6 7]              [2 1 0 1]              [1 2 3 4]              [4 5 6 7]
[3 2 1 0 1 2 3 4 5 6]              [3 2 1 0]              [0 1 2 3]              [3 4 5 6]
     */
}
