public class MaximumSumof3NonOverlappingSubarrays_689 {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] W = new int[nums.length - k + 1];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= k) sum -= nums[i - k];
            if (i >= k - 1) W[i - k + 1] = sum;
        }

        int[] left = new int[W.length];
        int best = 0;
        for (int i = 0; i < W.length; i++) {
            if (W[i] > W[best]) best = i;
            left[i] = best;
        }

        int[] right = new int[W.length];
        best = W.length - 1;
        for (int i = W.length - 1; i >= 0; i--) {
            if (W[i] >= W[best]) best = i;
            right[i] = best;
        }

        int[] ans = new int[]{-1, -1, -1};
        for (int j = k; j < W.length - k; j++) {
            int l = left[j - k], r = right[j + k];
            if (ans[0] == -1 || W[l] + W[j] + W[r] >
                    W[ans[0]] + W[ans[1]] + W[ans[2]]) {

                ans[0] = l;
                ans[1] = j;
                ans[2] = r;
            }
        }
        return ans;
    }
}
