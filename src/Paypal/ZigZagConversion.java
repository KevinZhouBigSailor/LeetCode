package Paypal;

import java.util.ArrayList;
import java.util.List;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * <p>
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */

public class ZigZagConversion {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        int step = numRows * 2 - 2, len = s.length();
        StringBuilder sb = new StringBuilder(s.length());
        // first row
        for (int i = 0; i < len; i += step)
            sb.append(s.charAt(i));
        for (int i = 1; i < numRows - 1; i++) {
            for (int j = i; j < len; j += step) {
                sb.append(s.charAt(j));
                if (j + (step - i * 2) < len)
                    sb.append(s.charAt(j + (step - i * 2)));
            }
        }
        // last row
        for (int i = numRows - 1; i < len; i += step)
            sb.append(s.charAt(i));
        return sb.toString();
    }

    public static void main(String[] args) {
        ZigZagConversion s = new ZigZagConversion();
        System.out.println(s.convert("PAYPALISHIRING", 3));
    }

    class Solution {
        public String convert(String s, int numRows) {

            if (numRows == 1) return s;

            List<StringBuilder> rows = new ArrayList<>();
            for (int i = 0; i < Math.min(numRows, s.length()); i++)
                rows.add(new StringBuilder());

            int curRow = 0;
            boolean goingDown = false;

            for (char c : s.toCharArray()) {
                rows.get(curRow).append(c);
                if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
                curRow += goingDown ? 1 : -1;
            }

            StringBuilder ret = new StringBuilder();
            for (StringBuilder row : rows) ret.append(row);
            return ret.toString();
        }
    }
}
