package Google;

import java.util.*;

public class LongestArithmeticSubsequenceOfGivenDifference_1218 {
    /**
     * At each integer i in the array we can consider it as the end of AP(Arithmetic Progression/Sequence) and
     * check the length of that AP which will be the (length of AP that ends with i-difference) + 1. We can thus use a hashmap to store this while traversing the array.
     */
    public int longestSubsequence2(int[] arr, int difference) {
        HashMap<Integer, Integer> dp = new HashMap<>();
        int longest = 0;
        for (int i = 0; i < arr.length; i++) {
            dp.put(arr[i], dp.getOrDefault(arr[i] - difference, 0) + 1);
            longest = Math.max(longest, dp.get(arr[i]));
        }
        return longest;
    }

    /**
     * hashmap record the target element,and dp records the max sequence.
     * actually, we can merge dp to hashmap.
     * hashmap record the target element and the max sequence that begins at i;
     */

    public int longestSubsequence(int[] arr, int difference) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] dp = new int[arr.length];
        int max = 0;
        Map<Integer, List<Integer>> index = new HashMap<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (index.get(arr[i]) == null) {
                index.put(arr[i], new ArrayList<>());
            }
            List<Integer> list = index.get(arr[i]);
            list.add(i);
        }
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = index.getOrDefault(arr[i] - difference, new ArrayList<>());
            for (int cur : list) {
                if (cur >= 0 && cur < i) {
                    dp[i] = dp[cur] + 1;
                    max = Math.max(max, dp[i]);
                    break;
                }
            }
        }
        return Math.max(max + 1, 1);
    }
}
