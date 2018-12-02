/**
 * Created by zzhou on 12/12/2017.
 */
public class OnesandZeroes_474 {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] memo = new int[strs.length][m + 1][n + 1];
        return calculate(strs, 0, m, n, memo);
    }

    public int calculate(String[] strs, int i, int zeros, int ones, int[][][] memo) {
        if (i == strs.length) return 0;
        if (memo[i][zeros][ones] != 0) return memo[i][zeros][ones];
        int[] count = countzerosones(strs[i]);
        int taken = -1;
        if (zeros - count[0] >= 0 && ones - count[1] >= 0)
            taken = calculate(strs, i + 1, zeros - count[0], ones - count[1], memo) + 1;
        int not_taken = calculate(strs, i + 1, zeros, ones, memo);
        memo[i][zeros][ones] = Math.max(taken, not_taken);
        return memo[i][zeros][ones];
    }

    public int[] countzerosones(String s) {
        int[] res = new int[2];
        for (int i = 0; i < s.length(); i++) {
            res[s.charAt(i) - '0']++;
        }
        return res;
    }
}
