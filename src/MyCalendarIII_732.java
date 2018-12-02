package leetCode;

import java.util.TreeMap;

/**
 * Created by zzhou on 1/12/2018.
 */
public class MyCalendarIII_732 {
    TreeMap<Integer, Integer> delta;

    public MyCalendarIII_732() {
        delta = new TreeMap();
    }

    public int book(int start, int end) {
        delta.put(start, delta.getOrDefault(start, 0) + 1);
        delta.put(end, delta.getOrDefault(end, 0) - 1);

        int active = 0, ans = 0;
        for (int d : delta.values()) {
            active += d;
            ans = Math.max(ans, active);
        }
        return ans;
    }
}
