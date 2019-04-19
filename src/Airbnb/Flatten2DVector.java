package Airbnb;

import java.util.Iterator;

public class Flatten2DVector {
    int[][] copy;
    int rowIdx = 0;
    int colIdx = 0;
    int next = 0;

    public void Vector2D(int[][] v) {
        copy = v;
    }

    public int next() {
        if (hasNext()) {
            colIdx++;
        }
        return next;
    }

    public boolean hasNext() {
        while (rowIdx < copy.length) {
            if (copy[rowIdx] == null) {
                rowIdx++;
                continue;
            }

            if (colIdx >= copy[rowIdx].length) {
                rowIdx++;
                colIdx = 0;
                continue;
            }

            next = copy[rowIdx][colIdx];
            return true;
        }
        return false;
    }
}
