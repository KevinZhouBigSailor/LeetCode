package Facebook.Practice;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class KthLargestElementinanArray_215 {
    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        k = nums.length - k;
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            final int j = partition(nums, low, high);
            if (j < k) {
                low = j + 1;
            } else if (j > k) {
                high = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    private int partition(int[] a, int low, int high) {
        int i = low;
        int j = high + 1;
        while (true) {
            while (i < high && a[++i] < a[low]) ;
            while (j > low && a[low] < a[--j]) ;
            if (i >= j) break;
            swap(a, i, j);
        }
        swap(a, low, j);
        return j;
    }

    private void shuffle(int[] a) {
        for (int i = 1; i < a.length; i++) {
            final int r = ThreadLocalRandom.current().nextInt(i + 1);
            swap(a, i, r);
        }
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    class Solution {
        int[] nums;

        public void swap(int a, int b) {
            int tmp = this.nums[a];
            this.nums[a] = this.nums[b];
            this.nums[b] = tmp;
        }


        public int partition(int left, int right, int pivot_index) {
            int pivot = this.nums[pivot_index];
            // 1. move pivot to end
            swap(pivot_index, right);
            int store_index = left;

            // 2. move all smaller elements to the left
            for (int i = left; i <= right; i++) {
                if (this.nums[i] < pivot) {
                    swap(store_index, i);
                    store_index++;
                }
            }

            // 3. move pivot to its final place
            swap(store_index, right);

            return store_index;
        }

        public int quickselect(int left, int right, int k_smallest) {
            /*
            Returns the k-th smallest element of list within left..right.
            */

            if (left == right) // If the list contains only one element,
                return this.nums[left];  // return that element

            // select a random pivot_index
            Random random_num = new Random();
            int pivot_index = left + random_num.nextInt(right - left);

            pivot_index = partition(left, right, pivot_index);

            // the pivot is on (N - k)th smallest position
            if (k_smallest == pivot_index)
                return this.nums[k_smallest];
            // go left side
            else if (k_smallest < pivot_index)
                return quickselect(left, pivot_index - 1, k_smallest);
            // go right side
            return quickselect(pivot_index + 1, right, k_smallest);
        }

        public int findKthLargest(int[] nums, int k) {
            this.nums = nums;
            int size = nums.length;
            // kth largest is (N - k)th smallest
            return quickselect(0, size - 1, size - k);
        }
    }
}
