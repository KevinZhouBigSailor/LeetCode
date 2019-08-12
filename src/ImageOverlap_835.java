import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImageOverlap_835 {
    public int largestOverlap(int[][] A, int[][] B) {
        int N = A.length;
        List<Point> A2 = new ArrayList();
        List<Point> B2 = new ArrayList();
        for (int i = 0; i < N * N; ++i) {
            if (A[i / N][i % N] == 1) A2.add(new Point(i / N, i % N));
            if (B[i / N][i % N] == 1) B2.add(new Point(i / N, i % N));
        }

        Set<Point> Bset = new HashSet(B2);

        int ans = 0;
        Set<Point> seen = new HashSet();
        for (Point a : A2)
            for (Point b : B2) {
                Point delta = new Point(b.x - a.x, b.y - a.y);
                if (!seen.contains(delta)) {
                    seen.add(delta);
                    int cand = 0;
                    for (Point p : A2)
                        if (Bset.contains(new Point(p.x + delta.x, p.y + delta.y)))
                            cand++;
                    ans = Math.max(ans, cand);
                }
            }

        return ans;
    }
}
