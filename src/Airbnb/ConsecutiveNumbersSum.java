package Airbnb;

public class ConsecutiveNumbersSum {
    public int consecutiveNumbersSum(int N) {
        int ans = 0, m = 1;
        int mx = N - m * (m - 1) / 2;
        while (mx >= m) {
            if (mx % m == 0)
                ans++;
            m++;
            mx = N - m * (m - 1) / 2;
        }
        return ans;
    }
   /* public int consecutiveNumbersSum(int N) {
        while ((N & 1) == 0) N >>= 1;
        int ans = 1, d = 3;

        while (d * d <= N) {
            int e = 0;
            while (N % d == 0) {
                N /= d;
                e++;
            }
            ans *= e + 1;
            d += 2;
        }

        if (N > 1) ans <<= 1;
        return ans;
    }*/

    public static void main(String[] args) {
        ConsecutiveNumbersSum consecutiveNumbersSum = new ConsecutiveNumbersSum();
        consecutiveNumbersSum.consecutiveNumbersSum(15);
    }
}
