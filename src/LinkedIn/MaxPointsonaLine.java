package LinkedIn;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzhou on 3/6/2018.
 */
public class MaxPointsonaLine {
    public int maxPoints(Point[] points) {
        int len = points.length;
        if (len == 0) return 0;
        if (len <= 2) return len;
        int res = 0;
        for (int i = 0; i < len - 1; i++) {
            Map<String, Integer> map = new HashMap<>();
            int lineMax = 0;
            int overlap = 0;
            for (int j = i + 1; j < len; j++) {
                int x = points[i].x - points[j].x;
                int y = points[i].y - points[j].y;
                if (x == 0 && y == 0) {
                    overlap++;
                    continue;
                }
                int gcd = generateGcd(x, y);
                x /= gcd;
                y /= gcd;
                // 用string来存储斜率
                String slope = String.valueOf(x) + String.valueOf(y);
                int count = map.getOrDefault(slope, 0);
                count++;
                map.put(slope, count);
                lineMax = Math.max(lineMax, count);
            }
            res = Math.max(res, lineMax + overlap + 1);
        }
        return res;
    }

    private int generateGcd(int a, int b) {
        if (b == 0) return a;
        else return generateGcd(b, a % b);
    }

    class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }
}
