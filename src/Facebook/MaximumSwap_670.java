package Facebook;

public class MaximumSwap_670 {
    /**
     https://leetcode.com/problems/maximum-swap/discuss/107068/Java-simple-solution-O(n)-time

     Use buckets to record the last position of digit 0 ~ 9 in this num.
     Loop through the num array from left to right. For each position, we check whether there exists a larger digit in this num (start from 9 to current digit). We also need to make sure the position of this larger digit is behind the current one. If we find it, simply swap these two digits and return the result.
     */
    public int maximumSwap(int num) {
        char[] A = Integer.toString(num).toCharArray();
        int[] last = new int[10];
        for (int i = 0; i < A.length; i++) {
            last[A[i] - '0'] = i;
        }

        for (int i = 0; i < A.length; i++) {
            for (int d = 9; d > A[i] - '0'; d--) {
                if (last[d] > i) {
                    char tmp = A[i];
                    A[i] = A[last[d]];
                    A[last[d]] = tmp;
                    return Integer.valueOf(new String(A));
                }
            }
        }
        return num;
    }
}
