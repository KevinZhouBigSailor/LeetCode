package Facebook;

/**
 * Created by zzhou on 7/27/2017.
 */
public class Pow_50 {
    public double myPow(double x, int n) {
        if (x > Double.MAX_VALUE) return 0;
        if (n == 0) return 1;
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }

    public boolean isPowOfTwo(double x) {
        if (x > Double.MAX_VALUE) return false;
        if (x == 1) return true;
        if (x % 2 == 1 || x <= 0)return false;
        return isPowOfTwo(x / 2);
    }

    public static void main(String[] args) {
        Pow_50 instance = new Pow_50();
        System.out.println(instance.isPowOfTwo(10));
    }
}
