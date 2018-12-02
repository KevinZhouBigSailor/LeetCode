public class HouseRobberII_213 {
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }

    public int rob(int[] nums, int low, int high) {
        int preMax = 0, currMax = 0;
        for (int i = low; i <= high; i++) {
            int temp = currMax;
            currMax = Math.max(nums[i] + preMax, currMax);
            preMax = temp;
        }
        return currMax;
    }
}
