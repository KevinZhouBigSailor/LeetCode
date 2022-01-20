package Google;

import java.util.*;

public class MinimizeMaximumPairSumInArray_1877 {

    /**
     * Intuition
     * Sort A,
     * make the pair with the min + max,
     * and continue do this.
     *
     *
     * Prove
     * assuming amin <= ai <= amax, amin <= aj <= amax,
     * the combination of (amin + amax, ai + aj),
     * is always no worse than
     * the combination of (amin + ai, aj + amax).
     *
     * So we can always pair amin with amax,
     * and we can reach the optimized result.
     */

    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0, n = nums.length;
        for (int i = 0; i < n / 2; ++i)
            res = Math.max(res, nums[i] + nums[n - i - 1]);
        return res;
    }
}
