package leetCode;

/**
 * Created by zzhou on 12/28/2017.
 */
public class OptimalDivision_553 {
    public String optimalDivision(int[] nums) {
        if (nums.length == 1)
            return Integer.toString(nums[0]);
        if (nums.length == 2)
            return nums[0] + "/" + nums[1];
        StringBuilder res = new StringBuilder(nums[0] + "/(" + nums[1]);
        for (int i = 2; i < nums.length; i++) {
            res.append("/" + nums[i]);
        }
        res.append(")");
        return res.toString();
    }
}
