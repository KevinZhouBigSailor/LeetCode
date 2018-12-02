public class PaintFence_276 {
    public int numWays(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;
        int differentColors = k * (k - 1);
        int sameColor = k;
        for (int i = 2; i < n; i++) {
            int temp = differentColors; //Former different colors become same color.
            differentColors = (differentColors + sameColor) * (k - 1);
            sameColor = temp;
        }
        return differentColors + sameColor;
    }
}
