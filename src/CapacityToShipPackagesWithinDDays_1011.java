public class CapacityToShipPackagesWithinDDays_1011 {
    /**
     * Given the number of bags,
     * return the minimum capacity of each bag,
     * so that we can put items one by one into all bags.
     *
     * We binary search the final result.
     * The left bound is max(A),
     * The right bound is sum(A).
     */
    public int shipWithinDays(int[] weights, int D) {
        int left = 0, right = 0;
        for (int w: weights) {
            left = Math.max(left, w);
            right += w;
        }
        while (left < right) {
            int mid = (left + right) / 2, need = 1, cur = 0;
            for (int w: weights) {
                if (cur + w > mid) {
                    need += 1;
                    cur = 0;
                }
                cur += w;
            }
            if (need > D) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}
