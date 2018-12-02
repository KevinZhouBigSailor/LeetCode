package leetCode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zzhou on 12/26/2017.
 */
public class FindKClosestElements_658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;

        if (x < arr[0]) {
            return Arrays.stream(Arrays.copyOfRange(arr, 0, k)).boxed().collect(Collectors.toList());
        } else if (x >= arr[n - 1]) {
            return Arrays.stream(Arrays.copyOfRange(arr, n - k, n)).boxed().collect(Collectors.toList());
        } else {
            int index = Arrays.binarySearch(arr, x);
            if (index < 0) index = -index - 1;
            int low = Math.max(0, index - k - 1), high = Math.min(index + k - 1, n - 1);
            while (high - low > k - 1) {
                if (low < 0 || (x - arr[low]) <= (arr[high] - x)) high--;
                else if (high > n - 1 || (x - arr[low]) > (arr[high] - x)) low++;
                else return null;
            }
            return Arrays.stream(Arrays.copyOfRange(arr, low, high + 1)).boxed().collect(Collectors.toList());
        }
    }

    public static void main(String[] args) {
        FindKClosestElements_658 instance = new FindKClosestElements_658();
        int[] arr = {0, 0, 1, 2, 3, 3, 4, 7, 7, 8};
        instance.findClosestElements(arr, 3, 5);
    }
}
