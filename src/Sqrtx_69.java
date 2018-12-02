/**
 * Created by zzhou on 7/28/2017.
 */
public class Sqrtx_69 {
    public int mySqrt(int x) {
        long r = x;
        while (r*r > x)
            r = (r + x/r) / 2;
        return (int) r;
    }
}
