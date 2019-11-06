import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class ReachingPoints_780 {

    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx >= sx && ty >= sy) {
            if (tx == ty) break;
            if (tx > ty) {
                if (ty > sy) tx %= ty;
                else return (tx - sx) % ty == 0;
            } else {
                if (tx > sx) ty %= tx;
                else return (ty - sy) % tx == 0;
            }
        }
        return (tx == sx && ty == sy);
    }

    public boolean reachingPoints2(int sx, int sy, int tx, int ty) {
        while (tx >= sx && ty >= sy) {
            if (sx == tx && sy == ty)
                return true;
            if (tx > ty) tx -= ty;
            else ty -= tx;
        }
        return false;
    }

    class Solution {
        Set<Point> seen;
        int tx, ty;

        public boolean reachingPoints(int sx, int sy, int tx, int ty) {
            seen = new HashSet();
            this.tx = tx;
            this.ty = ty;
            search(new Point(sx, sy));
            return seen.contains(new Point(tx, ty));
        }

        public void search(Point P) {
            if (seen.contains(P)) return;
            if (P.x > tx || P.y > ty) return;
            seen.add(P);
            search(new Point(P.x + P.y, P.y));
            search(new Point(P.x, P.x + P.y));
        }
    }

}
