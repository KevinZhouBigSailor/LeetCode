package leetCode;

/**
 * Created by zzhou on 12/29/2017.
 */
public class HammingDistance_461 {
    public int hammingDistance(int x, int y) {
        int xor = x ^ y, count = 0;
        for (int i = 0; i < 32; i++) count += (xor >> i) & 1;
        return count;
    }
}
