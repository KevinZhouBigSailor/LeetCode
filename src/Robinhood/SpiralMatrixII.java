package Robinhood;

public class SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        if (n == 0) {
            return matrix;
        }

        int rowBegin = 0, rowEnd = matrix.length - 1, colBegin = 0, colEnd = matrix[0].length - 1;
        int num = 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            //Traverse right
            for (int j = colBegin; j <= colEnd; j++) {
                matrix[rowBegin][j] = num++;
            }
            rowBegin++;

            //Traverse down
            for (int j = rowBegin; j <= rowEnd; j++) {
                matrix[j][colEnd] = num++;
            }
            colEnd--;

            if (rowBegin <= rowEnd) {
                //Traverse left
                for (int j = colEnd; j >= colBegin; j--) {
                    matrix[rowEnd][j] = num++;
                }
            }
            rowEnd--;

            if (colBegin <= colEnd) {
                //Traverse up
                for (int j = rowEnd; j >= rowBegin; j--) {
                    matrix[j][colBegin] = num++;
                }
            }
            colBegin++;
        }

        return matrix;
    }
}
