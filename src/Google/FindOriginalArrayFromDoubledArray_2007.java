package Google;

import java.util.TreeMap;

/**
 * https://leetcode.com/problems/find-original-array-from-doubled-array/discuss/1470959/JavaC++Python-Match-from-the-Smallest-or-Biggest-100
 */

public class FindOriginalArrayFromDoubledArray_2007 {
    public int[] findOriginalArray(int[] changed) {
        int n = changed.length, i = 0;
        if (n % 2 == 1) return new int[0];
        int[] res = new int[n / 2];
        TreeMap<Integer, Integer> count = new TreeMap<>();
        for (int ele : changed) {
            count.put(ele, count.getOrDefault(ele, 0) + 1);
        }
        for (int x : count.keySet()) {
            if (count.get(x) > count.getOrDefault(x + x, 0)) {
                return new int[0];
            }
            for (int j = 0; j < count.get(x); ++j) {
                res[i++] = x;
                count.put(x + x, count.get(x + x) - 1);
            }
        }
        return res;
    }
}
