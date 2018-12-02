/**
 * Created by zzhou on 5/24/2017.
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

 For example,
 Given nums = [0, 1, 3] return 2.

 Note:
 Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 */
public class MissingNumber_268 {
    public int missingNumber(int[] nums) {
        int len = nums.length;
        int sum = (0 + len) * (len + 1) / 2;
        int sum2 = 0;
        for (int n: nums) {
            sum2 += n;
        }
        return sum - sum2;
    }
}
