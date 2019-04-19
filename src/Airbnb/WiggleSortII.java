package Airbnb;

import java.util.Arrays;

public class WiggleSortII {
    public void wiggleSort(int[] nums) {
        int median = findKthLargest(nums, (nums.length + 1) >> 1);
        int n = nums.length;

        int left = 0, i = 0, right = n - 1;

        while (i <= right) {
            if (nums[newIndex(i, n)] > median) {
                swap(nums, newIndex(left++, n), newIndex(i++, n));
            } else if (nums[newIndex(i, n)] < median) {
                swap(nums, newIndex(right--, n), newIndex(i, n));
            } else {
                i++;
            }
        }
    }

    public int findKthLargest(int[] nums, int k) {
        final int N = nums.length;
        Arrays.sort(nums);
        return nums[N - k];
    }

    private int newIndex(int index, int n) {
        return (1 + 2 * index) % (n | 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
         WiggleSortII wiggleSortII = new WiggleSortII();
        int[] nums = {1, 5, 1, 1, 6, 4};
        wiggleSortII.wiggleSort(nums);
    }
}
