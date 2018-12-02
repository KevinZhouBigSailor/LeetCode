/**
 * Created by zzhou on 2/26/2018.
 */
public class ArrayNesting_565 {
    public int arrayNesting(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == false) {
                int start = nums[i], count = 0;
                do {
                    start = nums[start];
                    count++;
                    visited[start] = true;
                }
                while (start != nums[i]);
                res = Math.max(res, count);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ArrayNesting_565 instance = new ArrayNesting_565();
        int[] nums = {5,4,0,3,1,6,2};
        instance.arrayNesting(nums);
    }
}
