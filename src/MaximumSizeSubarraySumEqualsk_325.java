import java.util.HashMap;

public class MaximumSizeSubarraySumEqualsk_325 {
    public int maxSubArrayLen(int[] nums, int k) {
        int sum = 0, max = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) max = i + 1;
            else if (map.containsKey(sum - k)) max = Math.max(max, i - map.get(sum - k));
            if (!map.containsKey(sum)) map.put(sum, i);
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumSizeSubarraySumEqualsk_325 instance = new MaximumSizeSubarraySumEqualsk_325();
        int[] arr = {-2, -1, 2, 1};
        instance.maxSubArrayLen(arr, 1);
    }
}
