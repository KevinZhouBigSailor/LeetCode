package leetCode;

/**
 * Created by zzhou on 1/2/2018.
 */
public class MonotoneIncreasingDigits_738 {
    public int monotoneIncreasingDigits(int N) {
        char[] s = String.valueOf(N).toCharArray();
        int i = 1;
        while (i < s.length && s[i - 1] <= s[i]) i++;
        while (i > 0 && i < s.length && s[i - 1] > s[i]) s[--i]--;
        for (int j = i + 1; j < s.length; j++) {
            s[j] = '9';
        }
        return Integer.parseInt(String.valueOf(s));
    }
}
