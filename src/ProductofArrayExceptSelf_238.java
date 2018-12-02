package leetCode;

/**
 * Created by zzhou on 1/17/2018.
 */
public class ProductofArrayExceptSelf_238 {
    public int[] productExceptSelf(int[] nums) {
        /*
        Numbers:     2    3    4     5
        Lefts:       1    2  2*3 2*3*4
        Rights:  3*4*5  4*5    5     1
        */
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        ProductofArrayExceptSelf_238 instance = new ProductofArrayExceptSelf_238();
        int[] nums = {1, 2, 3, 4};
        instance.productExceptSelf(nums);
    }
}
