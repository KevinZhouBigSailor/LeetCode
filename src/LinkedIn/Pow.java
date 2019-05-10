package LinkedIn;

public class Pow {
    public double myPow(double x, int n) {
        if (x > Double.MAX_VALUE) return 0;
        if (n == 0) return 1;
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        return (n % 2 == 0) ? myPow(x, n / 2) : x * myPow(x, n / 2);
    }
}
