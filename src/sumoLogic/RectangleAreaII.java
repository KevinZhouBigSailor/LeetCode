package sumoLogic;

import java.util.*;

public class RectangleAreaII {
    /**
     * Intuition
     *
     * Imagine we pass a horizontal line from bottom to top over the shape. We have some active intervals on this horizontal line, which gets updated twice for each rectangle. In total, there are 2 * N2∗N events, and we can update our (up to NN) active horizontal intervals for each update.
     *
     * Algorithm
     *
     * For a rectangle like rec = [1,0,3,1], the first update is to add [1, 3] to the active set at y = 0, and the second update is to remove [1, 3] at y = 1. Note that adding and removing respects multiplicity - if we also added [0, 2] at y = 0, then removing [1, 3] at y = 1 will still leave us with [0, 2] active.
     *
     * This gives us a plan: create these two events for each rectangle, then process all the events in sorted order of y. The issue now is deciding how to process the events add(x1, x2) and remove(x1, x2) such that we are able to query() the total horizontal length of our active intervals.
     *
     * We can use the fact that our remove(...) operation will always be on an interval that was previously added. Let's store all the (x1, x2) intervals in sorted order. Then, we can query() in linear time using a technique similar to a classic LeetCode problem, Merge Intervals.
     */
    class Solution {
        public int rectangleArea(int[][] rectangles) {
            int OPEN = 0, CLOSE = 1;
            int[][] events = new int[rectangles.length * 2][];
            int t = 0;
            for (int[] rec: rectangles) {
                events[t++] = new int[]{rec[1], OPEN, rec[0], rec[2]};
                events[t++] = new int[]{rec[3], CLOSE, rec[0], rec[2]};
            }

            Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

            List<int[]> active = new ArrayList();
            int cur_y = events[0][0];
            long ans = 0;
            for (int[] event: events) {
                int y = event[0], typ = event[1], x1 = event[2], x2 = event[3];

                // Calculate query
                long query = 0;
                int cur = -1;
                for (int[] xs: active) {
                    cur = Math.max(cur, xs[0]);
                    query += Math.max(xs[1] - cur, 0);
                    cur = Math.max(cur, xs[1]);
                }

                ans += query * (y - cur_y);

                if (typ == OPEN) {
                    active.add(new int[]{x1, x2});
                    Collections.sort(active, (a, b) -> Integer.compare(a[0], b[0]));
                } else {
                    for (int i = 0; i < active.size(); ++i)
                        if (active.get(i)[0] == x1 && active.get(i)[1] == x2) {
                            active.remove(i);
                            break;
                        }
                }

                cur_y = y;
            }

            ans %= 1_000_000_007;
            return (int) ans;
        }
    }

    /*public int rectangleArea(int[][] rectangles) {
        int OPEN = 1, CLOSE = -1;
        int[][] events = new int[rectangles.length * 2][];
        Set<Integer> Xvals = new HashSet();
        int t = 0;
        for (int[] rec : rectangles) {
            events[t++] = new int[]{rec[1], OPEN, rec[0], rec[2]};
            events[t++] = new int[]{rec[3], CLOSE, rec[0], rec[2]};
            Xvals.add(rec[0]);
            Xvals.add(rec[2]);
        }

        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        Integer[] X = Xvals.toArray(new Integer[0]);
        Arrays.sort(X);
        Map<Integer, Integer> Xi = new HashMap();
        for (int i = 0; i < X.length; ++i)
            Xi.put(X[i], i);

        Node active = new Node(0, X.length - 1, X);
        long ans = 0;
        long cur_x_sum = 0;
        int cur_y = events[0][0];

        for (int[] event : events) {
            int y = event[0], typ = event[1], x1 = event[2], x2 = event[3];
            ans += cur_x_sum * (y - cur_y);
            cur_x_sum = active.update(Xi.get(x1), Xi.get(x2), typ);
            cur_y = y;

        }

        ans %= 1_000_000_007;
        return (int) ans;
    }*/
}

/*
class Node {
    int start, end;
    Integer[] X;
    Node left, right;
    int count;
    long total;

    public Node(int start, int end, Integer[] X) {
        this.start = start;
        this.end = end;
        this.X = X;
        left = null;
        right = null;
        count = 0;
        total = 0;
    }

    public int getRangeMid() {
        return start + (end - start) / 2;
    }

    public Node getLeft() {
        if (left == null) left = new Node(start, getRangeMid(), X);
        return left;
    }

    public Node getRight() {
        if (right == null) right = new Node(getRangeMid(), end, X);
        return right;
    }

    public long update(int i, int j, int val) {
        if (i >= j) return 0;
        if (start == i && end == j) {
            count += val;
        } else {
            getLeft().update(i, Math.min(getRangeMid(), j), val);
            getRight().update(Math.max(getRangeMid(), i), j, val);
        }

        if (count > 0) total = X[end] - X[start];
        else total = getLeft().total + getRight().total;

        return total;
    }

}
*/
