/**
 * Created by zzhou on 2/9/2018.
 */
public class FindMinimuminRotatedSortedArray_153 {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (mid > 0 && nums[mid] < nums[mid - 1]) return nums[mid];
            if (nums[start] <= nums[mid] && nums[mid] > nums[end]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return nums[start];
    }

    public static void main(String[] args) {
        FindMinimuminRotatedSortedArray_153 instance = new FindMinimuminRotatedSortedArray_153();
        instance.findMin(new int[]{1, 2, 3});
    }
}
