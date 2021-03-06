/*
 * 
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */

public class ZigZagConversion6 {
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
		ZigZagConversion6 s = new ZigZagConversion6();
		System.out.println(s.convert("PAYPALISHIRING", 3));
	}
}
