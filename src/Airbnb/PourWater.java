package Airbnb;

public class PourWater {
    public int[] pourWater(int[] H, int V, int K) {
        int[] dir = {-1, 1};

        while (V-- > 0) droplet:{
            for (int d : dir) {
                int i = K, best = K;
                while (0 <= i + d && i + d < H.length && H[i + d] <= H[i]) { //Important
                    if (H[i + d] < H[i]) best = i + d; //Important
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

    public static void main(String[] args) {
        PourWater pourWater = new PourWater();
        int[] H = {2, 1, 1, 2, 1, 2, 2};
        int V = 4, K = 3;
        pourWater.pourWater(H, V, K);
    }
}
