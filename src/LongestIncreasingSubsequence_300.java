import java.util.Arrays;

/**
 * Created by zzhou on 5/12/2017.
 * Given an unsorted array of integers, find the length of longest increasing subsequence.

 For example,
 Given [10, 9, 2, 5, 3, 7, 101, 18],
 The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

 Your algorithm should run in O(n2) complexity.

 Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LongestIncreasingSubsequence_300 {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;

        for(int x : nums) {
            int i = Arrays.binarySearch(dp, 0, len, x);
            if(i < 0) i = -(i + 1);
            dp[i] = x;
            if(i == len) len++;
        }

        return len;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence_300 instance = new LongestIncreasingSubsequence_300();
        int[] nums = {4, 5, 6, 3};
        System.out.println(instance.lengthOfLIS(nums));
    }
}
