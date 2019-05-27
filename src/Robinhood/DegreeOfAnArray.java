package Robinhood;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DegreeOfAnArray {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> left = new HashMap<>(),
                right = new HashMap<>(), count = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            left.putIfAbsent(x, i);
            right.put(x, i);
            count.put(x, count.getOrDefault(x, 0) + 1);
        }

        int res = nums.length;
        int degree = Collections.max(count.values());
        for (int x : count.keySet()) {
            if (count.get(x).equals(degree)) {
                res = Math.min(res, right.get(x) - left.get(x) + 1);
            }
        }
        return res;
    }
}
