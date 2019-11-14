package Twitter;

import java.util.Arrays;

public class NumberofMusicPlaylists {

    public int numMusicPlaylists(int N, int L, int K) {
        int MOD = 1_000_000_007;

        long[][] dp = new long[L + 1][N + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= L; ++i)
            for (int j = 1; j <= N; ++j) {
                dp[i][j] += dp[i - 1][j - 1] * (N - j + 1);
                dp[i][j] += dp[i - 1][j] * Math.max(j - K, 0);
                dp[i][j] %= MOD;
            }

        return (int) dp[L][N];
    }

    public int numMusicPlaylists2(int N, int L, int K) {
        int MOD = 1_000_000_007;

        // dp[S] at time P = <S, P> as discussed in article
        long[] dp = new long[L - N + 1];
        Arrays.fill(dp, 1);
        for (int p = 2; p <= N - K; ++p)
            for (int i = 1; i <= L - N; ++i) {
                dp[i] += dp[i - 1] * p;
                dp[i] %= MOD;
            }

        // Multiply by N!
        long ans = dp[L - N];
        for (int k = 2; k <= N; ++k)
            ans = ans * k % MOD;
        return (int) ans;
    }
}
