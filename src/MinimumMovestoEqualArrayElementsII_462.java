import java.util.Arrays;

/**
 * Created by zzhou on 8/29/2017.
 */
public class MinimumMovestoEqualArrayElementsII_462 {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int i =0, j = nums.length - 1;
        int count = 0;
        while (i < j) {
            count += nums[j] - nums[i];
            i++;
            j--;
        }
        return count;
    }
}
