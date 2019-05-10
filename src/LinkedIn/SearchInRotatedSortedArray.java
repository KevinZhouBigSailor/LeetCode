package LinkedIn;

public class SearchInRotatedSortedArray {
    public int findMinIdx(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else {
                mid = end;
            }
        }
        return start;
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int minIdx = findMinIdx(nums);
        if (target == nums[minIdx]) return target;
        int len = nums.length;
        int start = (target <= nums[len - 1]) ? minIdx : 0;
        int end = (target > nums[len - 1]) ? minIdx : len - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return mid;
            if (target > nums[mid]) start = mid + 1;
            else end = mid - 1;
        }
        return -1;
    }
}
