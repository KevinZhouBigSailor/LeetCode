package Facebook;

import java.util.*;

/**
 * Created by zzhou on 4/21/2017.
 * Given a non-empty array of integers, return the k most frequent elements.
 * <p>
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopKFrequentElements_347 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();

        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }

    class Solution {
        int[] unique;
        Map<Integer, Integer> count;

        public void swap(int a, int b) {
            int tmp = unique[a];
            unique[a] = unique[b];
            unique[b] = tmp;
        }

        public int partition(int left, int right, int pivot_index) {
            int pivot_frequency = count.get(unique[pivot_index]);
            // 1. move pivot to end
            swap(pivot_index, right);
            int store_index = left;

            // 2. move all less frequent elements to the left
            for (int i = left; i <= right; i++) {
                if (count.get(unique[i]) < pivot_frequency) {
                    swap(store_index, i);
                    store_index++;
                }
            }

            // 3. move pivot to its final place
            swap(store_index, right);

            return store_index;
        }

        public void quickselect(int left, int right, int k_smallest) {
        /*
        Sort a list within left..right till kth less frequent element
        takes its place.
        */

            // base case: the list contains only one element
            if (left == right) return;

            // select a random pivot_index
            Random random_num = new Random();
            int pivot_index = left + random_num.nextInt(right - left);

            // find the pivot position in a sorted list
            pivot_index = partition(left, right, pivot_index);

            // if the pivot is in its final sorted position
            if (k_smallest == pivot_index) {
                return;
            } else if (k_smallest < pivot_index) {
                // go left
                quickselect(left, pivot_index - 1, k_smallest);
            } else {
                // go right
                quickselect(pivot_index + 1, right, k_smallest);
            }
        }

        public int[] topKFrequent(int[] nums, int k) {
            // build hash map : character and how often it appears
            count = new HashMap();
            for (int num : nums) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }

            // array of unique elements
            int n = count.size();
            unique = new int[n];
            int i = 0;
            for (int num : count.keySet()) {
                unique[i] = num;
                i++;
            }

            // kth top frequent element is (n - k)th less frequent.
            // Do a partial sort: from less frequent to the most frequent, till
            // (n - k)th less frequent element takes its place (n - k) in a sorted array.
            // All element on the left are less frequent.
            // All the elements on the right are more frequent.
            quickselect(0, n - 1, n - k);
            // Return top k frequent elements
            return Arrays.copyOfRange(unique, n - k, n);
        }
    }

}
