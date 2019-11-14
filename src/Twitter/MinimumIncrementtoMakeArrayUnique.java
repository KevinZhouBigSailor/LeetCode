package Twitter;

import java.util.Arrays;

public class MinimumIncrementtoMakeArrayUnique {

    public int minIncrementForUnique(int[] A) {
        int[] count = new int[100000];
        for (int x : A) count[x]++;

        int ans = 0, taken = 0;

        for (int x = 0; x < 100000; ++x) {
            if (count[x] >= 2) {
                taken += count[x] - 1;
                ans -= x * (count[x] - 1);
            } else if (taken > 0 && count[x] == 0) {
                taken--;
                ans += x;
            }
        }

        return ans;
    }

    public int minIncrementForUnique2(int[] A) {
        Arrays.sort(A);
        int ans = 0, taken = 0;

        for (int i = 1; i < A.length; ++i) {
            if (A[i - 1] == A[i]) {
                taken++;
                ans -= A[i];
            } else {
                int give = Math.min(taken, A[i] - A[i - 1] - 1);
                ans += give * (give + 1) / 2 + give * A[i - 1];
                taken -= give;
            }
        }

        if (A.length > 0)
            ans += taken * (taken + 1) / 2 + taken * A[A.length - 1];

        return ans;
    }

}
