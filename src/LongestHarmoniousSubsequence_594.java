import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzhou on 5/26/2017.
 */
public class LongestHarmoniousSubsequence_594 {
    public int findLHS(int[] nums) {
        Map<Long, Integer> map = new HashMap<>();
        for (long num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int result = 0;
        for (long key : map.keySet()) {
            if (map.containsKey(key + 1)) {
                result = Math.max(result, map.get(key + 1) + map.get(key));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LongestHarmoniousSubsequence_594 instance = new LongestHarmoniousSubsequence_594();
        int[] nums = {1,3,2,2,5,2,3,7};
        System.out.println(instance.findLHS(nums));
    }
}
