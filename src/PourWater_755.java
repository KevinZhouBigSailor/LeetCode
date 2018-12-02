package leetCode;

/**
 * Created by zzhou on 1/12/2018.
 */
public class PourWater_755 {
    public int[] pourWater(int[] H, int V, int K) {
        int[] directions = {-1, 1};

        while (V-- > 0) droplet:{
            for (int d : directions) {
                int i = K, best = K;
                while (0 <= i + d && i + d < H.length && H[i + d] <= H[i]) {
                    if (H[i + d] < H[i]) best = i + d;
                    i += d;
                }
                if (H[best] < H[K]) {
                    H[best]++;
                    break droplet;
                }
            }
            H[K]++;
        }
        return H;
    }
}
