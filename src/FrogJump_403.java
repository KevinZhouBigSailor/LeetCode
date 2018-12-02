import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FrogJump_403 {
    public boolean canCross(int[] stones) {
        HashMap<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
        int len = stones.length;
        for (int i = 0; i < len; i++) {
            map.put(stones[i], new HashSet<>());
        }
        map.get(0).add(0);
        for (int i = 0; i < len; i++) {
            for (int k : map.get(stones[i])) {
                for (int step = k - 1; step <= k + 1; step++) {
                    if (step > 0 && map.containsKey(stones[i] + step)) {
                        map.get(stones[i] + step).add(step);
                    }
                }
            }
        }
        return map.get(stones[len - 1]).size() > 0;
    }
}
