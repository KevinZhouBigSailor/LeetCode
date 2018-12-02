/**
 * Created by zzhou on 3/6/2018.
 */
public class ReverseWordsinaString_151 {
    public String reverseWords(String s) {
        if (s == null) return null;

        char[] a = s.toCharArray();
        int len = a.length;

        reverse(a, 0, len - 1);
        reverseWords(a, len);

        return cleanSpaces(a, len);
    }

    private void reverseWords(char[] a, int len) {
        int i = 0, j = 0;
        while (i < len) {
            while (i < j || i < len && a[i] == ' ') i++; // skip spaces
            while (j < i || j < len && a[j] != ' ') j++; // skip non spaces
            reverse(a, i, j - 1);
        }
    }

    private void reverse(char[] a, int i, int j) {
        while (i < j) {
            char tmp = a[i];
            a[i++] = a[j];
            a[j--] = tmp;
        }
    }

    private String cleanSpaces(char[] a, int n) {
        int i = 0, j = 0;

        while (j < n) {
            while (j < n && a[j] == ' ') j++;             // skip spaces
            while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
            while (j < n && a[j] == ' ') j++;             // skip spaces
            if (j < n) a[i++] = ' ';                      // keep only one space
        }

        return new String(a).substring(0, i);
    }
}
